package denis.easyweather.app.model;

/**
 * Created by denis on 12/23/15.
 */
public class ItemModel {
    private String date;
    private String condition;
    private String dayTemp;
    private String nightTemp;

    public ItemModel(String date, String condition, String dayTemp, String nightTemp) {
        this.date = date;
        this.condition = condition;
        this.dayTemp = dayTemp;
        this.nightTemp = nightTemp;
    }

    public String getDate() {
        return date;
    }

    public String getCondition() {
        return condition;
    }

    public String getDayTemp() {
        return dayTemp;
    }

    public String getNightTemp() {
        return nightTemp;
    }
}
