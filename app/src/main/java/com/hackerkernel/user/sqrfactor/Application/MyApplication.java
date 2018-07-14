package com.hackerkernel.user.sqrfactor.Application;

import android.app.Application;
import android.content.Context;

import com.hackerkernel.user.sqrfactor.Storage.MySharedPreferences;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static MyApplication mInstance;
    private static MySharedPreferences mSp;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        mSp = MySharedPreferences.getInstance(this);
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

}
