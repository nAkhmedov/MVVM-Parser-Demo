package uz.nakhmedov.data.model;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/29/18
 * Time: 10:36 AM
 * To change this template use File | Settings | File Templates
 */
public class Data {

    private String json;
    private boolean isFirstTime;

    public Data(String json, boolean isFirstTime) {
        this.json = json;
        this.isFirstTime = isFirstTime;
    }

    public String getJson() {
        return json;
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }
}
