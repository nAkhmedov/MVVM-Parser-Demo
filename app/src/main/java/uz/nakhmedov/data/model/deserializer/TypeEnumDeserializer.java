package uz.nakhmedov.data.model.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import uz.nakhmedov.data.model.enums.TypeEnum;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/28/18
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates
 */
public class TypeEnumDeserializer implements JsonDeserializer<TypeEnum>, JsonSerializer<TypeEnum> {

    @Override
    public TypeEnum deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String type = json.getAsString();
        return TypeEnum.getByCode(type);
    }

    @Override
    public JsonElement serialize(TypeEnum src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.code);
    }
}
