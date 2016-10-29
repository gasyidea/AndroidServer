package gasyidea.org.androidserver.utils;


import android.os.AsyncTask;
import android.telephony.SmsManager;

public class SmsHelper extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... args) {
        sendSms(args[0], args[1]);
        return null;
    }

    private void sendSms(String result, String tel) {
        SmsManager smsManager = SmsManager.getDefault();
        MyDataHelper.sendSms(smsManager,  tel, result);
    }
}
