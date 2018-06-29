package uz.nakhmedov.data.model.enums;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/28/18
 * Time: 7:35 PM
 * To change this template use File | Settings | File Templates
 */
public enum TypeEnum {

    ACTION_DEFAULT("action_default"),
    ACTION_ADD("action_add");

    public String code;

    TypeEnum(String code) {
        this.code = code;
    }

    public static TypeEnum getByCode(String type) {
        for (TypeEnum item : values()) {
            if (item.code.equals(type)) {
                return item;
            }
        }
        return ACTION_DEFAULT;
    }

}
