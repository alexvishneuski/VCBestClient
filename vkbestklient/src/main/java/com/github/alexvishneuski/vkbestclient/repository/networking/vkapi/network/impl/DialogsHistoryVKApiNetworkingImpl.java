package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.modelparsing.GsonParser;
import com.github.alexvishneuski.vkbestclient.repository.networking.utils.VKApiRequestParser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.exception.VKApiException;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basic.VKApiMessage;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.dialogshistory.VKApiMessagesGetHistoryResult;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogsHistoryVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DialogsHistoryVKApiNetworkingImpl implements IDialogsHistoryVKApiNetworking {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public List<VKApiMessage> get(VKApiUri pUri) {

        Log.d(TAG, "get() called with: pUri = [" + pUri + "]");

        final String url = VKApiRequestParser.parse(pUri);

        @SuppressWarnings("unchecked") final VKApiMessagesGetHistoryResult result =
                (VKApiMessagesGetHistoryResult)
                        new HttpClient().requestGet(url, VKApiMessagesGetHistoryResult.class);

        if (result.getError() != null) {
            final String errorMessage = TAG + result.getError();
            //TODO refactor to: throw new VKApiException, change return to VKApiDialog object
            throw new VKApiException(errorMessage);
        }

        List<VKApiMessage> msgs = new ArrayList<>();
        msgs.addAll(result.getResponse().getMessages());

        int dialogCount = result.getResponse().getMessagesCount();
        Log.d(TAG, "getDialogs returned " + dialogCount + " messages");

        return msgs;
    }

    @Override
    public int getTotalCount(VKApiUri pUri) {
        Log.d(TAG, "getTotalCount() called with: pUri = [" + pUri + "]");

        final String url = VKApiRequestParser.parse(pUri);

        @SuppressWarnings("unchecked") final VKApiMessagesGetHistoryResult result =
                (VKApiMessagesGetHistoryResult)
                        new HttpClient().requestGet(url, VKApiMessagesGetHistoryResult.class);

        if (result.getError() != null) {
            final String errorMessage = TAG + result.getError();
            //TODO refactor to: throw new VKApiException, change return to VKApiDialog object
            throw new VKApiException(errorMessage);
        }

        int msgCount = result.getResponse().getMessagesCount();
        Log.d(TAG, "getTotalCount returned " + msgCount + " dialogs");

        return msgCount;
    }


    public int getTotalCountParse(VKApiUri pUri) {
        Log.d(TAG, "getTotalCount() called with: pUri = [" + pUri + "]");

        final String url = VKApiRequestParser.parse(pUri);

        final InputStream iStream =
                new HttpClient().requestGet(url);

        @SuppressWarnings("unchecked") GsonParser<VKApiMessagesGetHistoryResult> parser =
                new GsonParser(iStream);

        VKApiMessagesGetHistoryResult result =
                parser.parse(VKApiMessagesGetHistoryResult.class);

        if (result.getError() != null) {
            final String errorMessage = TAG + result.getError();
            //TODO refactor to: throw new VKApiException, change return to VKApiDialog object
            throw new VKApiException(errorMessage);
        }

        int msgCount = result.getResponse().getMessagesCount();
        Log.d(TAG, "getDialogsTotalCount returned " + msgCount + " dialogs");

        return msgCount;
    }
}
