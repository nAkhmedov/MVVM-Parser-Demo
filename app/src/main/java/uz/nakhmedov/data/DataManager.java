package uz.nakhmedov.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import uz.nakhmedov.data.model.Data;
import uz.nakhmedov.data.model.ImageData;
import uz.nakhmedov.data.model.Item;
import uz.nakhmedov.data.model.enums.TypeEnum;
import uz.nakhmedov.ui.utils.ContextConstants;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:21 PM
 * To change this template use File | Settings | File Templates
 */
@Singleton
public class DataManager implements DataSource {

    @Inject
    Gson mGson;

    private final Context mContext;
    private ImageData cachedData;

    @Inject
    public DataManager(Context context) {
        mContext = context;
        cachedData = new ImageData();
    }

    @Override
    public Flowable<ImageData> loadData() {
        Data jsonData = loadJSONData();
        if (jsonData == null)
            return Flowable.empty();

        Type type = new TypeToken<ImageData>(){}.getType();
        ImageData image = mGson.fromJson(jsonData.getJson(), type);
        if (jsonData.isFirstTime()) {
            Item addItem = new Item(null, null, TypeEnum.ACTION_ADD);
            image.getItems().add(addItem);
        }
        cachedData = image;
        return Flowable.just(image);
    }

    @Override
    public boolean updateFile() {
        try {
            write2InternalFile();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void write2InternalFile() throws Exception {
        String json = mGson.toJson(cachedData);
        FileOutputStream outputStream = mContext.openFileOutput(ContextConstants.JSON_FILE,
                Context.MODE_PRIVATE);
        outputStream.write(json.getBytes());
        outputStream.close();
    }

    private Data loadJSONData() {

        File file = new File(mContext.getFilesDir(), ContextConstants.JSON_FILE);
        try {
            if (file.exists()) {
                FileInputStream fis = mContext.openFileInput(ContextConstants.JSON_FILE);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                return new Data(sb.toString(), false);
            } else {
                InputStream is = mContext.getAssets().open(ContextConstants.JSON_FILE);
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                String json = new String(buffer, Charset.forName("UTF-8"));
                return new Data(json, true);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
