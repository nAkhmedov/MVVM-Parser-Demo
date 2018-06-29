package uz.nakhmedov.data.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.UUID;

import uz.nakhmedov.BR;
import uz.nakhmedov.data.model.enums.TypeEnum;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/26/18
 * Time: 7:07 PM
 * To change this template use File | Settings | File Templates
 */
public class Item extends BaseObservable implements Serializable {

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("imageUrlString")
    private String imageUrlString;

    @SerializedName("type")
    private TypeEnum type;

    public Item() {
        this.uuid = UUID.randomUUID().toString();
        this.imageUrlString = "";
        this.type = TypeEnum.ACTION_DEFAULT;
    }

    public Item(String uuid, String imageUrlString, TypeEnum type) {
        this.uuid = uuid;
        this.imageUrlString = imageUrlString;
        this.type = type;
    }

    @Bindable
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
        notifyPropertyChanged(BR.uuid);
    }

    @Bindable
    public String getImageUrlString() {
        return imageUrlString;
    }

    public void setImageUrlString(String imageUrlString) {
        this.imageUrlString = imageUrlString;
        notifyPropertyChanged(BR.imageUrlString);
    }

    @Bindable
    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }

    @Override
    public String toString() {
        return "Item{" +
                "uuid='" + uuid + '\'' +
                ", imageUrlString='" + imageUrlString + '\'' +
                ", type=" + type +
                '}';
    }
}
