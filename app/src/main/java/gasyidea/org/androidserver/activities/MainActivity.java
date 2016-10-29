package gasyidea.org.androidserver.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import gasyidea.org.androidserver.R;
import gasyidea.org.androidserver.utils.MyApplication;
import gasyidea.org.androidserver.utils.MyDataHelper;

public class MainActivity extends AppCompatActivity {
    private static final Integer MY_PORT = 11111;
    private static final String RETRO = "retro";
    protected PrintWriter printWriter;
    protected Socket client;

    MyApplication myApplication;

    class ReceivingSmsAsync extends AsyncTask<Socket, Void, String> {
        @Override
        protected String doInBackground(Socket... params) {
            return getData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            startAnotherActivity(result);
        }

        public String getData(Socket socket) {
            String result = "";
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                result = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        public void startAnotherActivity(String result) {
            Intent intent = new Intent(MainActivity.this, NextActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(RETRO, result);
            getApplicationContext().startActivity(intent);
        }
    }

    private void runServer() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    ServerSocket server = new ServerSocket(MY_PORT);
                    server.setReuseAddress(true);
                    executeTaskFor(server);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void executeTaskFor(ServerSocket socket) throws IOException {
        client = socket.accept();
        printWriter = new PrintWriter(client.getOutputStream(), true);
        initMyApplication();
        while (true) {
            ReceivingSmsAsync asyncTask = new ReceivingSmsAsync();
            asyncTask.execute(new Socket[]{client});
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        initList();
        runServer();
        setVisible(false);
    }

    private void initList() {
        MyDataHelper.initList(getApplicationContext());
    }

    private void initMyApplication() {
        myApplication = (MyApplication) getApplicationContext();
        myApplication.setWriter(printWriter);
    }
}
