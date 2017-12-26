package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl;

import android.support.annotation.WorkerThread;
import android.util.Log;

import com.github.alexvishneuski.vkbestclient.repository.networking.http.HttpClient;
import com.github.alexvishneuski.vkbestclient.repository.networking.utils.Parameters;
import com.github.alexvishneuski.vkbestclient.repository.networking.utils.VKApiAuthParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.utils.VKApiGetDialogsParams;
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

        final String methodsTag = "getDialogs()";

        Log.d(TAG, "getDialogs called");

        //================ TESTING
        //building url
        VKApiGetDialogsParams getDialogsParams = VKApiGetDialogsParams.getBuilder().build();
        VKApiUri getDialogsUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodMessagesGetDialogs.METHOD_NAME)
                .setParameters(getDialogsParams)
                .build();
        String getDialogs = parse(getDialogsUri);
        System.out.println("!!!======================" + getDialogs);

        //================

        final String url = String.format(
                RepositoryConstants.VKApiConstants.METHOD_BASE_PATH,
                RepositoryConstants.VKApiConstants.VK_API_SERVICE_URL,
                RepositoryConstants.VKApiConstants.VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS,
                RepositoryConstants.VKApiConstants.VK_API_ACCESS_TOKEN,
                RepositoryConstants.VKApiConstants.VK_API_VERSION);

        @SuppressWarnings("unchecked") final VKApiMessagesGetDialogsResult result =
                (VKApiMessagesGetDialogsResult)
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

    private String parse(VKApiUri pUri) {

        StringBuilder uriBuilder = new StringBuilder();
        boolean isServiceMethod = pUri.getBasePath()
                .equals(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH);
        Parameters params = pUri.getParameters();

        //base url
        uriBuilder
                .append(pUri.getProtocol())
                .append(RepositoryConstants.Sign.COLON_DOUBLE_SLASH)
                .append(pUri.getBasePath());

        //if not authorization -> getting method url
        if (isServiceMethod) {
            uriBuilder
                    .append(RepositoryConstants.Sign.SLASH)
                    .append(pUri.getMethod());
        }

        //start parametrs parsing
        uriBuilder.append(RepositoryConstants.Sign.QUESTION);
        //if authorization params
        if (!isServiceMethod) {
            //parsing authorization params
        } else if (isServiceMethod
                //if message.getDialogs params
                && (params instanceof VKApiGetDialogsParams)) {
            //TODO EXTRACT TO SEPARATE METHOD
            uriBuilder.append(parseParams((VKApiGetDialogsParams) params));
        }
        //if ( (uriBuilder.charAt(uriBuilder.length() - 1)).equals(RepositoryConstants.Sign.QUESTION)){}
        //auth params
        uriBuilder
                .append(RepositoryConstants.CommonUrlParts.ACCESS_TOKEN_KEY)
                .append(RepositoryConstants.Sign.EQUAL)
                .append(RepositoryConstants.VKApiConstants.VK_API_ACCESS_TOKEN);

        uriBuilder
                .append(RepositoryConstants.Sign.AMPERSAND)
                .append(RepositoryConstants.CommonUrlParts.VERSION_KEY)
                .append(RepositoryConstants.Sign.EQUAL)
                .append(RepositoryConstants.CommonUrlParts.VERSION);

        String resultUri = uriBuilder.toString();

        return resultUri;
    }

    private StringBuilder parseParams(VKApiAuthParams pParams) {
        return null;
    }

    private StringBuilder parseParams(VKApiGetDialogsParams pParams) {
        StringBuilder paramsBuilder = new StringBuilder();
        //parsing message.getDialogs params
        if (pParams.getOffset() != null) {
            paramsBuilder
                    .append(RepositoryConstants.VkMethodMessagesGetDialogs.OFFSET_KEY)
                    .append(RepositoryConstants.Sign.EQUAL)
                    .append(pParams.getOffset());
        }
        if (pParams.getCount() != null) {
//
        }
        if (pParams.getStartMessageId() != null) {
//
        }
        if (pParams.getPreviewLength() != null) {
//
        }
        if (pParams.getUnreadFlag() != null) {
//
        }
        if (pParams.getImportantFlag() != null) {
//
        }
        if (pParams.getUnansweredFlag() != null) {
//
        }

        return paramsBuilder;
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
