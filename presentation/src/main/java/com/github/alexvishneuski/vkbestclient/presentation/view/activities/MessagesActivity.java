package com.github.alexvishneuski.vkbestclient.presentation.view.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.github.alexvishneuski.vkbestclient.datamodel.DomainTest;
import com.github.alexvishneuski.vkbestclient.interactor.InteractorTest;
import com.github.alexvishneuski.vkbestclient.presentation.utils.BitmapUtils;
import com.github.alexvishneuski.vkbestclient.presentation.view.fragments.TopBarFragment;
import com.github.alexvishneuski.vklayouts.R;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private int mTopBarFrameContainer;

    private LoadImagesTask imagesTask = null;
    private ImageView imageOwner = null;
    private ImageView image1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        /*creating view*/
        setContentView(R.layout.activity_messages);

        /*find top bar container end show there top bar fragment*/
        findTopBarContainer();
        showTopBarFragment();


        imageOwner = (ImageView) findViewById(R.id.owner_photo);
        image1 = (ImageView) findViewById(R.id.sender1_avatar_image_view);

        invokeOutsideTiers();
    }


    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
        imagesTask = new LoadImagesTask();
        imagesTask.execute();

    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
        imagesTask.cancel(true);
    }

    private void invokeOutsideTiers() {
        Log.d(TAG, "invokeOutsideTiers");
        InteractorTest interactorTest = new InteractorTest();
        System.out.println(interactorTest.getS());

        DomainTest domainTest = new DomainTest();
        domainTest.testPrint();
    }

    private class LoadImagesTask extends AsyncTask<Void, Void, List<Bitmap>> {


        @Override
        protected List<Bitmap> doInBackground(Void... params) {
            List<Bitmap> bitmaps = new ArrayList<>();
            bitmaps.add(BitmapUtils.getCircleMaskedBitmapUsingShader(BitmapFactory.decodeResource(MessagesActivity.this.getResources(), R.mipmap.ic_launcher_round), 25));
            bitmaps.add(BitmapUtils.getCircleMaskedBitmapUsingShader(BitmapFactory.decodeResource(MessagesActivity.this.getResources(), R.drawable._di_caprio), 25));

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

    /*find top bar container for top bar fragment*/
    private void findTopBarContainer() {
        Log.d(TAG, "findTopBarContainer");
        mTopBarFrameContainer = R.id.top_bar_frame_container;

    }

    /*show any fragment on this activity*/
    public void showFragment(int frameContainer, Fragment fragment) {
        Log.d(TAG, "showFragment");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /*show top bar fragment in top bar container*/
    private void showTopBarFragment() {
        Log.d(TAG, "showTopBarFragment");
        showFragment(mTopBarFrameContainer, new TopBarFragment());
    }
}
