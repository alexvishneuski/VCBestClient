package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.datamodel.Dialog;
import com.github.alexvishneuski.vkbestclient.interactor.IDialogInteractor;
import com.github.alexvishneuski.vkbestclient.networking.vkapi.network.IDialogVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.networking.vkapi.network.impl.DialogVKApiNetworkingImpl;

import java.util.List;

public class DialogInteractorImpl implements IDialogInteractor {

    private final String TAG = this.getClass().getSimpleName();

    private IDialogVKApiNetworking mDialogVKApiNetworkingImpl = new DialogVKApiNetworkingImpl();

    @Override
    public List<Dialog> getDialogList() {

        return null;
    }

    @Override
    public List<Dialog> getDialogList(int pCount) {

        return null;
    }

    @Override
    public String getResult() {
        Log.d(TAG, "getResult called");
        String result = mDialogVKApiNetworkingImpl.getDialogList();
        Log.d(TAG, "getResult returned");

        return result;
    }
}
