package gasyidea.org.androidserver.activities;

import android.app.Activity;
import android.os.Bundle;

import static gasyidea.org.androidserver.utils.MyDataHelper.getAllData;
import static gasyidea.org.androidserver.utils.MyDataHelper.sendAllSms;

public class NextActivity extends Activity {

    private static final String RETRO = "retro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        call(extras.getString(RETRO));
    }

    private void call(final String retro) {
        String[] results = getAllData(retro);
        sendAllSms(getApplicationContext(), results);
        finish();
    }
}
