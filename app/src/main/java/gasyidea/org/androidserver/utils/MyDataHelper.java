package gasyidea.org.androidserver.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import gasyidea.org.androidserver.R;
import gasyidea.org.androidserver.models.MyList;
import gasyidea.org.androidserver.models.MyModel;

public class MyDataHelper {

    public static final String START = "%";
    public static final String END = "@";
    public static final String COMMA = ",";

    public static String encodeData(String data) {
        return START + data + END;
    }

    public static String[] getAllData(String retro) {
        return retro.split(END);
    }

    public static List readNumbers(Context context, int id) {
        InputStream inputStream = context.getResources().openRawResource(id);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder builder = new StringBuilder();
        List<String> results = new ArrayList<>();
        try {
            while ((line = reader.readLine()) != null) {
                results.add(line.trim());
                builder.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return results;
    }

    public static MyModel createModel(String str) {
        return new MyModel(str);
    }

    public static void initList(Context context) {
        String[] numbers = getNumbersFromFile(context, R.raw.responsables);
        MyList.getList().clear();
        for (int i = 1; i < numbers.length - 1; i++) {
            MyList.add(MyDataHelper.createModel(numbers[i]));
        }
    }

    public static void sendAllSms(Context context, String[] results) {
        SmsManager smsManager = SmsManager.getDefault();
        String[] numbers = getNumbersFromFile(context, R.raw.responsables);
        for (String data : results) {
            for (int i = 1; i < numbers.length - 1; i++) {
                try {
                    smsManager.sendTextMessage(numbers[i], null, encodeData(data), null, null);
                    Toast.makeText(context, "Message Sent",
                            Toast.LENGTH_LONG).show();
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String[] getNumbersFromFile(Context context, int id) {
        return String.valueOf(MyDataHelper.readNumbers(context, id)).split(COMMA);
    }
}


