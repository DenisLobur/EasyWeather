package denis.easyweather.app.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by denis on 1/16/16.
 */
public class JSONParser {
    private String jsonString;
    private final String LIST_PARAM = "list";
    private final String TEMP_PARAM = "temp";
    private final String MAX_PARAM = "max";
    private final String MIN_PARAM = "min";
    private final String DATE_PARAM = "dt";
    private final String WEATHER_PARAM = "weather";
    private final String DESCRIPTION_PARAM = "description";

    public JSONParser(String JSONString) {
        jsonString = JSONString;
    }

    public String[] parseJsonForDay(int numberOfDays) throws JSONException {
        JSONObject obj = new JSONObject(jsonString);
        JSONArray arr = obj.getJSONArray(LIST_PARAM);

        String[] resultString = new String[numberOfDays];
        for (int i = 0; i < arr.length(); i++) {
            resultString[i] = getDateForDay(i) + "//" + getMaxTempForDay(i) + "//" + getMinTempForDay(i) + "//"+ getDescription(i);
        }

        return resultString;
    }

    private String getMaxTempForDay(int dayIndex) throws JSONException {
        return getSpecificDay(dayIndex).getJSONObject(TEMP_PARAM).getString(MAX_PARAM);
    }

    private String getMinTempForDay(int dayIndex) throws JSONException {
        return getSpecificDay(dayIndex).getJSONObject(TEMP_PARAM).getString(MIN_PARAM);
    }

    private String getDateForDay(int dayIndex) throws JSONException {
        long dateRaw = getSpecificDay(dayIndex).getLong(DATE_PARAM);
        Date dateReady = new Date(dateRaw * 1000);
        SimpleDateFormat format = new SimpleDateFormat("E, MMM, d");

        return format.format(dateReady);
    }

    private JSONObject getSpecificDay(int dayIndex) throws JSONException {
        JSONObject weekObj = new JSONObject(jsonString);
        JSONArray listArr = weekObj.getJSONArray(LIST_PARAM);
        JSONObject dayObj = listArr.getJSONObject(dayIndex);

        return dayObj;
    }

    private String getDescription(int dayIndex) throws JSONException {
        return getSpecificDay(dayIndex).getJSONArray(WEATHER_PARAM).getJSONObject(0).getString(DESCRIPTION_PARAM);
    }

}
