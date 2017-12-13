package com.github.alexvishneuski.vkbestclient.repository.networking.http;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient implements IHttpClient {

    private final String TAG = this.getClass().getSimpleName();

    private HttpURLConnection con;

    @Override
    public void request(final String url, final ResponseListener listener) {
//        Log.d(TAG, "request");

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
