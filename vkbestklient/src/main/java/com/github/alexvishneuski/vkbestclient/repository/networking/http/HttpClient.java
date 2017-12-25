package com.github.alexvishneuski.vkbestclient.repository.networking.http;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient<T> implements IHttpClient<T> {

    private final String TAG = this.getClass().getSimpleName();

    private HttpURLConnection con;

    @Override
    public T request(String pUrl, Class<T> pClazz) {
        Log.d(TAG, "request() called with: pUrl = [" + pUrl + "], pClazz = [" + pClazz + "]");

        InputStreamReader inputStreamReader;
        Object response = null;
        final InputStream inputStream;

        try {
            inputStream = openStream(pUrl);
            inputStreamReader = new InputStreamReader(inputStream);
            response = new GsonBuilder()
                    .setLenient()
                    .create().fromJson(inputStreamReader, pClazz);
            con.disconnect();
        } catch (IOException pE) {
            pE.printStackTrace();
            //TODO add sort of RepoVKApiHttpException()
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        //noinspection unchecked
        return (T) response;
    }

    @Override
    public void request(final String url, final ResponseListener listener) {
        Log.d(TAG, "request() called with: url = [" + url + "], listener = [" + listener + "]");

        try {
            final InputStream is = openStream(url);
            listener.onResponse(is);
            con.disconnect();
        } catch (final Throwable t) {
            listener.onError(t);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    @VisibleForTesting
    private InputStream openStream(final String url) throws IOException {
        Log.d(TAG, "openStream");

        con = (HttpURLConnection) (new URL(url)).openConnection();
        return con.getInputStream();
    }

    public interface ResponseListener {

        void onResponse(InputStream pInputStream) throws Exception;

        void onError(Throwable pThrowable);
    }

}
