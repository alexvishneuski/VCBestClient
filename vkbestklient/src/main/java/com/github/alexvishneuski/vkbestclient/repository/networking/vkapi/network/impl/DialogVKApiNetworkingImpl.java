package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.utils.VKApiRequestParser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.exception.VKApiException;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.messages.VKApiMessagesGetDialogsResult;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DialogVKApiNetworkingImpl implements IDialogVKApiNetworking {

    private final String TAG = this.getClass().getSimpleName();

    @WorkerThread
    @Override
    public List<VKApiDialog> getDialogs(VKApiUri pUri) {

        Log.d(TAG, "getDialogs called");

        final String url = VKApiRequestParser.parse(pUri);

        @SuppressWarnings("unchecked") final VKApiMessagesGetDialogsResult result =
                (VKApiMessagesGetDialogsResult)
                        new HttpClient().request(url, VKApiMessagesGetDialogsResult.class);

        if (result.getError() != null) {
            final String errorMessage = TAG + result.getError();
            //TODO refactor to: throw new VKApiException, change return to VKApiDialog object
            throw new VKApiException(errorMessage);
        }

        List<VKApiDialog> dialogs = new ArrayList<>();
        dialogs.addAll(result.getResponse().getDialogs());

        int dialogCount = result.getResponse().getDialogCount();
        Log.d(TAG, "getDialogs returned " + dialogCount + " dialogs");

        return dialogs;
    }

    @WorkerThread
    @Override
    public int getDialogsTotalCount(VKApiUri pUri) {

        Log.d(TAG, "getDialogsTotalCount() called with: pUri = [" + pUri + "]");

        final String url = VKApiRequestParser.parse(pUri);

        @SuppressWarnings("unchecked") final VKApiMessagesGetDialogsResult result =
                (VKApiMessagesGetDialogsResult)
                        new HttpClient().request(url, VKApiMessagesGetDialogsResult.class);

        if (result.getError() != null) {
            final String errorMessage = TAG + result.getError();
            //TODO refactor to: throw new VKApiException, change return to VKApiDialog object
            throw new VKApiException(errorMessage);
        }

        int dialogCount = result.getResponse().getDialogCount();
        Log.d(TAG, "getDialogsTotalCount returned " + dialogCount + " dialogs");

        return dialogCount;
    }
}
