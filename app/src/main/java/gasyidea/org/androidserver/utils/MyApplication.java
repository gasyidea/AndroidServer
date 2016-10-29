package gasyidea.org.androidserver.utils;

import android.app.Application;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MyApplication extends Application {

    private PrintWriter writer;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
}
