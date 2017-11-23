package denis.easyweather.app.data

import android.test.AndroidTestCase

class TestPractice : AndroidTestCase() {
    /*
        This gets run before every test.
     */
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
    }

    @Throws(Throwable::class)
    fun testThatDemonstratesAssertions() {
        val a = 5
        val b = 3
        val c = 5
        val d = 10

        Assert.assertEquals("X should be equal", a, c)
        Assert.assertTrue("Y should be true", d > a)
        Assert.assertFalse("Z should be false", a == b)

        if (b > d) {
            Assert.fail("XX should never happen")
        }
    }

    @Throws(Exception::class)
    override fun tearDown() {
        super.tearDown()
    }
}
