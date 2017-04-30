package denis.easyweather.app.model;

public class ItemModel {
    public String date;
    public String condition;
    public String dayTemp;
    public String nightTemp;

    public ItemModel(String date, String condition, String dayTemp, String nightTemp) {
        this.date = date;
        this.condition = condition;
        this.dayTemp = dayTemp;
        this.nightTemp = nightTemp;
    }
}
