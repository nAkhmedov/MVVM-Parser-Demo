package uz.nakhmedov.data;

import io.reactivex.Flowable;
import uz.nakhmedov.data.model.ImageData;
import uz.nakhmedov.data.model.Item;

/**
 * Created with Android Studio
 * User: nakhmedov
 * Date: 6/25/18
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates
 */
public interface DataSource {

    Flowable<ImageData> loadData();

    boolean updateFile();
}
