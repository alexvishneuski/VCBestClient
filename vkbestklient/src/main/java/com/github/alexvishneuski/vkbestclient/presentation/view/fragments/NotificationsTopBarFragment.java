package com.github.alexvishneuski.vkbestclient.presentation.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexvishneuski.vkbestclient.R;

public class NotificationsTopBarFragment extends Fragment {

    public final String TAG = this.getClass().getSimpleName();

    /*id of container in activity*/
    private int mTopBarLayoutId;

    /*view of this fragment*/
    private View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView called ");

        /*find id*/
        mTopBarLayoutId = R.layout.fragment_top_bar_notifications;

        /*create view*/
        mView = inflater.inflate(mTopBarLayoutId, null);

        return mView;
    }
}
