/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package denis.easyweather.app.data

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.test.AndroidTestCase

import java.util.HashSet

import denis.easyweather.app.database.WeatherContract
import denis.easyweather.app.database.WeatherDbHelper

class TestDb : AndroidTestCase() {

    // Since we want each test to start with a clean slate
    internal fun deleteTheDatabase() {
        mContext.deleteDatabase(WeatherDbHelper.DB_NAME)
    }

    /*
        This function gets called before each test is executed to delete the database.  This makes
        sure that we always have a clean test.
     */
    public override fun setUp() {
        deleteTheDatabase()
    }

    /*
        Students: Uncomment this test once you've written the code to create the Location
        table.  Note that you will have to have chosen the same column names that I did in
        my solution for this test to compile, so if you haven't yet done that, this is
        a good time to change your column names to match mine.

        Note that this only tests that the Location table has the correct columns, since we
        give you the code for the weather table.  This test does not look at the
     */
    @Throws(Throwable::class)
    fun testCreateDb() {
        // build a HashSet of all of the table names we wish to look for
        // Note that there will be another table in the DB that stores the
        // Android metadata (db version information)
        val tableNameHashSet = HashSet<String>()
        tableNameHashSet.add(WeatherContract.LocationEntry.TABLE_NAME)
        tableNameHashSet.add(WeatherContract.WeatherEntry.TABLE_NAME)

        mContext.deleteDatabase(WeatherDbHelper.DB_NAME)
        val db = WeatherDbHelper(
                this.mContext).writableDatabase
        Assert.assertEquals(true, db.isOpen)

        // have we created the tables we want?
        var c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", // Columns to filter by row groups
                null)

        Assert.assertTrue("Error: This means that the database has not been created correctly",
                c.moveToFirst())

        // verify that the tables have been created
        do {
            tableNameHashSet.remove(c.getString(0))
        } while (c.moveToNext())

        // if this fails, it means that your database doesn't contain both the location entry
        // and weather entry tables
        Assert.assertTrue("Error: Your database was created without both the location entry and weather entry tables",
                tableNameHashSet.isEmpty())

        // now, do our tables contain the correct columns?
        c = db.rawQuery("PRAGMA table_info(" + WeatherContract.LocationEntry.TABLE_NAME + ")", null)

        Assert.assertTrue("Error: This means that we were unable to query the database for table information.",
                c.moveToFirst())

        // Build a HashSet of all of the column names we want to look for
        val locationColumnHashSet = HashSet<String>()
        locationColumnHashSet.add(WeatherContract.LocationEntry._ID)
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_CITY_NAME)
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_COORD_LAT)
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_COORD_LONG)
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING)

        val columnNameIndex = c.getColumnIndex("name")
        do {
            val columnName = c.getString(columnNameIndex)
            locationColumnHashSet.remove(columnName)
        } while (c.moveToNext())

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        Assert.assertTrue("Error: The database doesn't contain all of the required location entry columns",
                locationColumnHashSet.isEmpty())
        db.close()
    }

    /*
        Students:  Here is where you will build code to test that we can insert and query the
        location database.  We've done a lot of work for you.  You'll want to look in TestUtilities
        where you can uncomment out the "createNorthPoleLocationValues" function.  You can
        also make use of the ValidateCurrentRecord function from within TestUtilities.
    */
    fun testLocationTable() {
        insertLocation()
    }

    /*
        Students:  Here is where you will build code to test that we can insert and query the
        database.  We've done a lot of work for you.  You'll want to look in TestUtilities
        where you can use the "createWeatherValues" function.  You can
        also make use of the validateCurrentRecord function from within TestUtilities.
     */
    fun testWeatherTable() {
        // First insert the location, and then use the locationRowId to insert
        // the weather. Make sure to cover as many failure cases as you can.
        val locationRowId = insertLocation()

        // Instead of rewriting all of the code we've already written in testLocationTable
        // we can move this code to insertLocation and then call insertLocation from both
        // tests. Why move it? We need the code to return the ID of the inserted location
        // and our testLocationTable can only return void because it's a test.

        // First step: Get reference to writable database
        val dbHelper = WeatherDbHelper(mContext)
        val db = dbHelper.writableDatabase

        // Create ContentValues of what you want to insert
        // (you can use the createWeatherValues TestUtilities function if you wish)
        val testValues = TestUtilities.createWeatherValues(locationRowId)

        // Insert ContentValues into database and get a row ID back
        val weatherRowId = db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, testValues)

        // Verify we've got the row back
        Assert.assertTrue(weatherRowId != -1)

        // Query the database and receive a Cursor back
        val cursor = db.query(WeatherContract.WeatherEntry.TABLE_NAME, null, null, null, null, null, null)
        // Move the cursor to a valid database row
        Assert.assertTrue("Error. No records returned from location query", cursor.moveToFirst())
        // Validate data in resulting Cursor with the original ContentValues
        // (you can use the validateCurrentRecord function in TestUtilities to validate the
        // query if you like)
        TestUtilities.validateCurrentRecord("Error. Weather query validation failed.", cursor, testValues)

        // Finally, close the cursor and database
        cursor.close()
        db.close()
    }


    /*
        Students: This is a helper method for the testWeatherTable quiz. You can move your
        code from testLocationTable to here so that you can call this code from both
        testWeatherTable and testLocationTable.
     */
    fun insertLocation(): Long {
        // First step: Get reference to writable database
        val dbHelper = WeatherDbHelper(mContext)
        val db = dbHelper.writableDatabase

        // Create ContentValues of what you want to insert
        // (you can use the createNorthPoleLocationValues if you wish)
        val testValues = TestUtilities.createNorthPoleLocationValues()

        // Insert ContentValues into database and get a row ID back
        val locationRowId: Long
        locationRowId = db.insert(WeatherContract.LocationEntry.TABLE_NAME, null, testValues)

        // Verify we've got the row back
        Assert.assertTrue(locationRowId != -1)

        // Query the database and receive a Cursor back
        val cursor = db.query(WeatherContract.LocationEntry.TABLE_NAME, null, null, null, null, null, null)// Table to query
        // All columns
        // Columns for the "WHERE" clause
        // Values for the "WHERE" clause
        // Columns to group by
        // Sort order

        // Move the cursor to a valid database row
        Assert.assertTrue("Error. No records returned from location query", cursor.moveToFirst())

        // Validate data in resulting Cursor with the original ContentValues
        // (you can use the validateCurrentRecord function in TestUtilities to validate the
        // query if you like)
        TestUtilities.validateCurrentRecord("Error. Location query validation failed", cursor, testValues)

        // Move the cursor to demonstrate that there is only one record in the database
        Assert.assertFalse("Error. More than one record returned from location query", cursor.moveToNext())

        // Finally, close the cursor and database
        cursor.close()
        db.close()

        return locationRowId
    }

    companion object {

        val LOG_TAG = TestDb::class.java.simpleName
    }
}
