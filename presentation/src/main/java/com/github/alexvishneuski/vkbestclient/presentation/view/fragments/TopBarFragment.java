package com.github.alexvishneuski.vkbestclient.presentation.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexvishneuski.vklayouts.R;


/**
 * Created by Asus on 30.10.2017.
 */

public class TopBarFragment extends Fragment {

    /*id of container in activity*/
    private int mTopBarFrameContainer;

    /*view of this fragment*/
    View mView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        /*find id*/
        mTopBarFrameContainer = R.layout.fragment_top_bar_messages;

        /*create view*/
        mView = inflater.inflate(mTopBarFrameContainer, null);

        return mView;
    }
}
