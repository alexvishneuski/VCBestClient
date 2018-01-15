package com.github.alexvishneuski.vkbestclient.presentation.asynctasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class PseudoDownloadAT extends AsyncTask<Pair<Context, String>, Integer, Void> {

    @SuppressLint("StaticFieldLeak")
    private Context mContext;
    private String mSource;

    @SafeVarargs
    @Override
    protected final Void doInBackground(Pair<Context, String>... pParams) {
        mContext = pParams[0].first;
        mSource = pParams[0].second;
        try {

            for (int i = 0; i <= 100; i += 20) {

                downloadFile();

                publishProgress(i);
            }

            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onProgressUpdate(Integer... vValues) {
        super.onProgressUpdate(vValues);
        Toast.makeText(mContext, String.format("Downloaded %d percent from %s", vValues[0], mSource), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        Toast.makeText(mContext, "App is updated", Toast.LENGTH_SHORT).show();
    }


    private void downloadFile() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
    }
}
