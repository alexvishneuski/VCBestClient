package com.github.alexvishneuski.vkbestclient.repository.networking.http;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient<T> implements IHttpClient<T> {

    private final String TAG = this.getClass().getSimpleName();

    private HttpURLConnection con;

    @Override
    public T requestGet(String pUrl, Class<T> pClazz) {
        Log.d(TAG, "requestGet() called with: pUrl = [" + pUrl + "], pClazz = [" + pClazz + "]");

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
    public T requestPost(String pUrl, Class<T> pClazz, String pBody) {

        final OutputStream outputStream;
        OutputStreamWriter writer;

        final InputStream inputStream;
        InputStreamReader reader;
        Object response = null;

        try {
            HttpURLConnection con = (HttpURLConnection) (new URL(pUrl)).openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");

            outputStream = con.getOutputStream();
            writer = new OutputStreamWriter(outputStream);
            writer.write(pBody);
            writer.flush();
            writer.close();

            inputStream = con.getInputStream();
            reader = new InputStreamReader(inputStream);
            response = new GsonBuilder()
                    .setLenient()
                    .create().fromJson(reader, pClazz);

            con.disconnect();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        return (T) response;
    }

    @Override
    public InputStream requestGet(String pUrl) {
        InputStream inputStream = null;

        try {
            inputStream = openStream(pUrl);
            con.disconnect();
        } catch (IOException pE) {
            pE.printStackTrace();
            //TODO add sort of HTTP Exception()
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return inputStream;
    }

    @Override
    public void requestGet(final String url, final ResponseListener listener) {
        Log.d(TAG, "requestGet() called with: url = [" + url + "], listener = [" + listener + "]");

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
