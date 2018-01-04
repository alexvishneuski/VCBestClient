package com.github.alexvishneuski.vkbestclient;

import android.app.Application;
import android.widget.Toast;

import com.github.alexvishneuski.vkbestclient.util.ContextHolder;


public class VKApp extends Application {

    private IDbOperations mDbOperations;

    @Override
    public void onCreate() {
        super.onCreate();
        ContextHolder.setContext(this);
        Toast.makeText(this, "App is created", Toast.LENGTH_SHORT).show();

        //INSTANCE.setConfig(new INSTANCE.Config(getCacheDir()));
        mDbOperations = IDbOperations.Imp.newInstance();
    }

    @Override
    public Object getSystemService(String name) {
        if (name.equals(IDbOperations.Imp.SYSTEM_SERVICE_NAME)) {

            return mDbOperations;
        }
        return super.getSystemService(name);
    }
}
