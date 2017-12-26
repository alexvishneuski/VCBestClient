package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.utils.VKApiGetDialogsParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.utils.VKApiRequestParser;
import com.github.alexvishneuski.vkbestclient.repository.networking.utils.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.exception.VKApiException;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiDialog;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.messages.VKApiMessagesGetDialogsResponse;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.messages.VKApiMessagesGetDialogsResult;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IDialogVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DialogVKApiNetworkingImpl implements IDialogVKApiNetworking {

    private final String TAG = this.getClass().getSimpleName();

    @WorkerThread
    public List<VKApiDialog> getDialogs() {

        String DIALOG_COUNT = "20";

        final String methodsTag = "getDialogs()";

        Log.d(TAG, "getDialogs called");
//TODO Extract the params ssetting for this method into Interactor
        VKApiGetDialogsParams getDialogsParams = VKApiGetDialogsParams.getBuilder().setCount(DIALOG_COUNT).build();
        VKApiUri getDialogsUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodMessagesGetDialogs.METHOD_NAME)
                .setParameters(getDialogsParams)
                .build();
        String url = VKApiRequestParser.parse(getDialogsUri);

        @SuppressWarnings("unchecked") final VKApiMessagesGetDialogsResult result =
                (VKApiMessagesGetDialogsResult)
                        //instead String url
                        new HttpClient().request(url, VKApiMessagesGetDialogsResult.class);

        if (result.getError() != null) {
            final String errorMessage = TAG + " in " + methodsTag + ": " + result.getError();
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
    public VKApiMessagesGetDialogsResponse getDialogList() {

        final String methodsTag = "getDialogs()";

        Log.d(TAG, "getDialogs called");

        final String url = String.format(
                RepositoryConstants.VKApiConstants.METHOD_BASE_PATH,
                RepositoryConstants.VKApiConstants.VK_API_SERVICE_URL,
                RepositoryConstants.VKApiConstants.VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS,
                RepositoryConstants.VKApiConstants.VK_API_ACCESS_TOKEN,
                RepositoryConstants.VKApiConstants.VK_API_VERSION);

        @SuppressWarnings("unchecked") final VKApiMessagesGetDialogsResponse response =
                (VKApiMessagesGetDialogsResponse)
                        new HttpClient().request(url, VKApiMessagesGetDialogsResponse.class);

        return response;
    }
}
