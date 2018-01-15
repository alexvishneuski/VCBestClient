package com.github.alexvishneuski.vkbestclient.presentation.asynctasks;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.github.alexvishneuski.vkbestclient.BuildConfig;
import com.github.alexvishneuski.vkbestclient.domain.AppVersion;
import com.github.alexvishneuski.vkbestclient.domain.DomainApi;
import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

//todo to difine to corresponding package, add usual HTTP
public class AppVersionChecker {


    public final String VERSION_URL = BuildConfig.APP_VERSION_URL;

    private Context mContext;

    public AppVersionChecker(Context pContext) {
        mContext = pContext;
    }

    public Boolean checkAppVersion() {

        Integer onDeviceVersion = getAppVersionOnDevice();

        Integer fromServerVersion = getAppVersionFromServer();
        //Integer fromServerVersion = 1;

        return (fromServerVersion.equals(onDeviceVersion));
    }

    private Integer getAppVersionFromServer() {

        //getting url
        final String url = new DomainApi(VERSION_URL).getLastAppVersionPath();
        MyResponseListener listener = new MyResponseListener();

         new HttpClient().getCurrentAppVersion(url, listener);

        if (listener.getThrowable() != null) {
            //TODO implement error handling on UI
            throw new UnsupportedOperationException(listener.getThrowable());
        }
        return listener.getVersion().getId();
    }


    private Integer getAppVersionOnDevice() {
        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo pi = pm.getPackageInfo(mContext.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException ex) {
            return 0;
        }
    }

    private static class MyResponseListener implements HttpClient.ResponseListener {

        private AppVersion version;
        private Throwable mThrowable;

        @Override
        public void onResponse(final InputStream pInputStream) throws Exception {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(pInputStream);
                version = new GsonBuilder()
                        .setLenient()
                        .create().fromJson(inputStreamReader, AppVersion.class);
            } finally {
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (final Exception ignored) {
                    }
                }
            }
        }

        public Throwable getThrowable() {
            return mThrowable;
        }

        @Override
        public void onError(final Throwable pThrowable) {
            mThrowable = pThrowable;
        }

        public AppVersion getVersion() {
            return version;
        }
    }
}
