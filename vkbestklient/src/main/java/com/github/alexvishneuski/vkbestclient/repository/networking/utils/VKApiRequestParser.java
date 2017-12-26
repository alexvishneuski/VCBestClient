package com.github.alexvishneuski.vkbestclient.repository.networking.utils;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.Parameters;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiAuthParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiGetDialogsParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

/**
 * used to build uri for any request related to VK API (through extracting request's params)
 */
public class VKApiRequestParser {

    public VKApiRequestParser() {
    }

    public static String parse(VKApiUri pUri) {

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
            //parsing message.getDialogs params
            uriBuilder.append(parseParams((VKApiGetDialogsParams) params));
        }
        //if ( (uriBuilder.charAt(uriBuilder.length() - 1)).equals(RepositoryConstants.Sign.QUESTION)){}
        //auth params
        uriBuilder
                .append(RepositoryConstants.CommonUrlParts.ACCESS_TOKEN_KEY)
                .append(RepositoryConstants.Sign.EQUAL)
                .append(RepositoryConstants.VKApiConstants.VK_API_ACCESS_TOKEN)
                .append(RepositoryConstants.Sign.AMPERSAND);

        uriBuilder

                .append(RepositoryConstants.CommonUrlParts.VERSION_KEY)
                .append(RepositoryConstants.Sign.EQUAL)
                .append(RepositoryConstants.CommonUrlParts.VERSION);

        String resultUri = uriBuilder.toString();

        return resultUri;
    }

    private static StringBuilder parseParams(VKApiAuthParams pParams) {

        return null;
    }

    private static StringBuilder parseParams(VKApiGetDialogsParams pParams) {
        StringBuilder paramsBuilder = new StringBuilder();
        //parsing message.getDialogs params
        if (pParams.getOffset() != null) {
            paramsBuilder
                    .append(RepositoryConstants.VkMethodMessagesGetDialogs.OFFSET_KEY)
                    .append(RepositoryConstants.Sign.EQUAL)
                    .append(pParams.getOffset())
                    .append(RepositoryConstants.Sign.AMPERSAND);
        }
        if (pParams.getCount() != null) {
            paramsBuilder
                    .append(RepositoryConstants.VkMethodMessagesGetDialogs.COUNT_KEY)
                    .append(RepositoryConstants.Sign.EQUAL)
                    .append(pParams.getCount())
                    .append(RepositoryConstants.Sign.AMPERSAND);
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
}