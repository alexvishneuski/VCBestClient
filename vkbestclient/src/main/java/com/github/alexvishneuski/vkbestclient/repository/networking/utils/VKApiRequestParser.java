package com.github.alexvishneuski.vkbestclient.repository.networking.utils;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.Parameters;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiAuthParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiGetDialogsParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiGetUsersParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiMessagesGetHistoryParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * used to build uri for any request related to VK API (through extracting request's params)
 */
public class VKApiRequestParser {

    public VKApiRequestParser() {
    }

    //the highest level method
    public static String parse(VKApiUri pUri) {

        StringBuilder uriBuilder = new StringBuilder();

        boolean isServiceMethod = pUri.getBasePath()
                .equals(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH);

        Parameters params = pUri.getParameters();

        //base url common required
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
            //TODO parsing authorization params
        } else if (isServiceMethod
                //if message.getDialogs params
                && (params instanceof VKApiGetDialogsParams)) {
            //parsing message.getDialogs params
            uriBuilder.append(parseParams((VKApiGetDialogsParams) params));
        } else if (isServiceMethod
                //if users.get params
                && (params instanceof VKApiGetUsersParams)) {
            //parsing message.getDialogs params
            uriBuilder.append(parseParams((VKApiGetUsersParams) params));
        } else if (isServiceMethod
                //if users.get params
                && (params instanceof VKApiMessagesGetHistoryParams)) {
            //parsing message.getDialogs params
            uriBuilder.append(parseParams((VKApiMessagesGetHistoryParams) params));
        }
        //continue another common required auth params
        uriBuilder
                .append(RepositoryConstants.CommonUrlParts.ACCESS_TOKEN_KEY)
                .append(RepositoryConstants.Sign.EQUAL)
                .append(RepositoryConstants.CommonUrlParts.ACCESS_TOKEN)
                .append(RepositoryConstants.Sign.AMPERSAND);

        uriBuilder

                .append(RepositoryConstants.CommonUrlParts.VERSION_KEY)
                .append(RepositoryConstants.Sign.EQUAL)
                .append(RepositoryConstants.CommonUrlParts.VERSION);

        String resultUri = uriBuilder.toString();

        return resultUri;
    }

    //first-level-nested method
    private static StringBuilder parseParams(VKApiAuthParams pParams) {

        throw new UnsupportedOperationException("VKApiRequestParser parseParams(VKApiAuthParams pParams) is not supported yet");
    }

    //first-level-nested method for parsing parameters related to VK API method message.getDialogs
    private static StringBuilder parseParams(VKApiGetDialogsParams pParams) {

        StringBuilder paramsBuilder = new StringBuilder();

        //HashMap is not used because the order is impportant
        List<String> paramsKeys = new ArrayList<>();
        List<String> paramsValues = new ArrayList<>();

        //parsing message.getDialogs params
        if (pParams.getOffset() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesCommon.OFFSET_KEY);
            paramsValues.add(pParams.getOffset());
        }
        if (pParams.getCount() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesCommon.COUNT_KEY);
            paramsValues.add(pParams.getCount());
        }
        if (pParams.getStartMessageId() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesCommon.START_MESSAGE_ID_KEY);
            paramsValues.add(pParams.getStartMessageId());
        }
        if (pParams.getPreviewLength() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesGetDialogs.PREVIEW_LENGTH_KEY);
            paramsValues.add(pParams.getPreviewLength());
        }
        if (pParams.getUnreadFlag() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesGetDialogs.UNREAD_KEY);
            paramsValues.add(pParams.getUnreadFlag());
        }
        if (pParams.getImportantFlag() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesGetDialogs.IMPORTANT_KEY);
            paramsValues.add(pParams.getImportantFlag());
        }
        if (pParams.getUnansweredFlag() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesGetDialogs.UNANSWERED_KEY);
            paramsValues.add(pParams.getUnansweredFlag());
        }

        paramsBuilder.append(concatWithEqualAndAmpersand(paramsKeys, paramsValues));

        return paramsBuilder;
    }

    //first-level-nested method
    private static StringBuilder parseParams(VKApiMessagesGetHistoryParams pParams) {
        StringBuilder paramsBuilder = new StringBuilder();

        //HashMap is not used because the order is impportant
        List<String> paramsKeys = new ArrayList<>();
        List<String> paramsValues = new ArrayList<>();

        //parsing message.getDialogs params
        if (pParams.getOffset() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesCommon.OFFSET_KEY);
            paramsValues.add(pParams.getOffset());
        }
        if (pParams.getCount() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesCommon.COUNT_KEY);
            paramsValues.add(pParams.getCount());
        }
        if (pParams.getStartMessageId() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesCommon.START_MESSAGE_ID_KEY);
            paramsValues.add(pParams.getStartMessageId());
        }
        if (pParams.getDestinationId() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesGetHistory.DESTINATION_ID_KEY);
            paramsValues.add(pParams.getDestinationId());
        }
        if (pParams.getUserId() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesGetHistory.USER_ID_KEY);
            paramsValues.add(pParams.getUserId());
        }
        if (pParams.getInChronologicalOrder() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodMessagesGetHistory.IN_CHRONOLOGICAL_ORDER_KEY);
            paramsValues.add(pParams.getInChronologicalOrder());
        }

        paramsBuilder.append(concatWithEqualAndAmpersand(paramsKeys, paramsValues));

        return paramsBuilder;
    }

    //first-level-nested method for parsing parameters related to VK API method users.get
    private static StringBuilder parseParams(VKApiGetUsersParams pParams) {

        StringBuilder paramsBuilder = new StringBuilder();

        //HashMap is not used because the order is impportant
        List<String> paramsKeys = new ArrayList<>();
        List<String> paramsValues = new ArrayList<>();

        //parsing users.get params
        if (pParams.getUserIds() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodUsersGet.USER_IDS_KEY);
            paramsValues.add(convertToString(pParams.getUserIds()));
        }
        if (pParams.getFields() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodUsersGet.FIELDS_KEY);
            paramsValues.add(convertToString(pParams.getFields()));
        }
        if (pParams.getNameCase() != null) {
            paramsKeys.add(RepositoryConstants.VkMethodUsersGet.NAME_CASE_KEY);
            paramsValues.add(pParams.getNameCase());
        }

        paramsBuilder.append(concatWithEqualAndAmpersand(paramsKeys, paramsValues));

        return paramsBuilder;
    }

    //second-level-nested method converts stringArray to string sequence separated coma
    private static String convertToString(String[] pUserIds) {
        StringBuilder stringSequence = new StringBuilder();

        for (int i = 0; i < pUserIds.length - 1; i++) {

            stringSequence
                    .append(pUserIds[i])
                    .append(RepositoryConstants.Sign.COMMA);
        }

        stringSequence.append(pUserIds[pUserIds.length - 1]);

        return stringSequence.toString();
    }

    //second-level-nested method
    private static StringBuilder concatWithEqualAndAmpersand(List<String> pParamsKeys, List<String> pParamsValues) {
        StringBuilder concatBuilder = new StringBuilder();

        for (int i = 0; i < pParamsKeys.size(); i++) {

            concatBuilder
                    .append(pParamsKeys.get(i))
                    .append(RepositoryConstants.Sign.EQUAL)
                    .append(pParamsValues.get(i))
                    .append(RepositoryConstants.Sign.AMPERSAND);
        }

        return concatBuilder;
    }
}