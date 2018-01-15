package com.github.alexvishneuski.vkbestclient.repository.networking.http;

import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.errors.VKApiError;
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
    public T requestGet(String pUrl, Class<T> pClazz) {
        Log.d(TAG, "requestGet() called with: pUrl = [" + pUrl + "], pClazz = [" + pClazz + "]");

        final InputStreamReader inputStreamReader;
        Object response = null;
        final InputStream inputStream;

        try {
            inputStream = openStream(pUrl);
            inputStreamReader = new InputStreamReader(inputStream);

            response = new GsonBuilder()
                    .setLenient()
                    .create().fromJson(inputStreamReader, pClazz);

            if (response.getClass().getDeclaredField(VKApiError.class.getSimpleName()) != null) {
                //TODO get error info
            }

            con.disconnect();
        } catch (IOException pE) {
            pE.printStackTrace();
            //TODO add sort of RepoVKApiHttpException()
        } catch (NoSuchFieldException pE) {
            pE.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }

        //noinspection unchecked
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

    public void getCurrentAppVersion(String pUrl, ResponseListener pListener) {
        try {
            InputStream is;
            con = (HttpURLConnection) (new URL(pUrl)).openConnection();
            is = con.getInputStream();
            pListener.onResponse(is);
            con.disconnect();
        } catch (final Throwable t) {
            //       pListener.onError(t);
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
