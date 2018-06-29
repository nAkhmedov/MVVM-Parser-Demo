package uz.nakhmedov.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/28/18
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates
 */
public class ImageData implements Serializable {

    @SerializedName("items")
    private List<Item> mItems;

    public List<Item> getItems() {
        return mItems;
    }
}
