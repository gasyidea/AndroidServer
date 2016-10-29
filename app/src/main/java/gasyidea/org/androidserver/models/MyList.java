package gasyidea.org.androidserver.models;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyList {
    private static HashMap<String, String> list = new HashMap<>();

    public static HashMap<String, String> getList() {
        return list;
    }

    public static void add(MyModel model) {
        addDataToMap(model);
    }

    private static void addDataToMap(MyModel model) {
        list.put(model.getTel(), "");
    }


}
