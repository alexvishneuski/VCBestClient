package com.github.alexvishneuski.vkbestclient.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.github.alexvishneuski.vkbestclient.datamodel.Domain;
import com.github.alexvishneuski.vkbestclient.interactor.Test;
import com.github.alexvishneuski.vkbestclient.view.utils.BitmapUtils;
import com.github.alexvishneuski.vklayouts.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LoadImagesTask imagesTask = null;
    private ImageView imageOwner = null;
    private ImageView image1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        imageOwner = (ImageView) findViewById(R.id.owner_photo);
        image1 = (ImageView) findViewById(R.id.sender1_avatar_image_view);

        testTiers();
    }

    @Override
    protected void onStart() {
        super.onStart();
        imagesTask = new LoadImagesTask();
        imagesTask.execute();

    }

    @Override
    protected void onStop() {
        super.onStop();
        imagesTask.cancel(true);
    }

    private void testTiers() {
        Test test = new Test();
        System.out.println(test.getS());

        Domain domain = new Domain();
        domain.testPrint();
    }

    private class LoadImagesTask extends AsyncTask<Void, Void, List<Bitmap>> {

        @Override
        protected List<Bitmap> doInBackground(Void... params) {
            List<Bitmap> bitmaps = new ArrayList<>();
            bitmaps.add(BitmapUtils.getCircleMaskedBitmapUsingShader(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.mipmap.ic_launcher_round), 25));
            bitmaps.add(BitmapUtils.getCircleMaskedBitmapUsingShader(BitmapFactory.decodeResource(MainActivity.this.getResources(), R.drawable._di_caprio), 25));

            return bitmaps;
        }

        @Override
        protected void onPostExecute(List<Bitmap> result) {
            if (isCancelled() || result == null) {
                return;
            }
            imageOwner.setImageBitmap(result.get(0));
            image1.setImageBitmap(result.get(1));

        }

    }
}
