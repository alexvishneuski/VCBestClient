package com.github.alexvishneuski.vkbestclient.repository.repoutils;

public class RepositoryConstants {

    //common signs
    public interface Sign {
        String EQUAL = "=";
        String SLASH = "/";
        String COLON_DOUBLE_SLASH = "://";
        String AMPERSAND = "&";
        String QUESTION = "?";
        String COMMA = ",";
    }

    public interface CommonUrlParts {
        //common url parts
        String PROTOCOL = "https";
        String VK_METHOD_BASE_PATH = "api.vk.com/method";
        String ACCESS_TOKEN_KEY = "access_token";
        String ACCESS_TOKEN = "";
        String VERSION_KEY = "v";
        String VERSION = "5.69";
    }

    public interface VkMethodMessagesCommon {
        String OFFSET_KEY = "offset";
        String COUNT_KEY = "count";
        String START_MESSAGE_ID_KEY = "start_message_id";
    }

    public interface VkMethodMessagesGetHistory {

        String METHOD_NAME = "messages.getHistory";

        //parameters keys for "messages.getHistory"
        String USER_ID_KEY = "user_id";
        String DESTINATION_ID_KEY = "peer_id";
        String IN_CHRONOLOGICAL_ORDER_KEY = "rev";
        String IN_CHRONOLOGICAL_ORDER_VALUE_REVERSE = "0";
    }

    public interface VkMethodMessagesGetDialogs {

        String METHOD_NAME = "messages.getDialogs";

        //parameters keys for "messages.getDialogs"
        String PREVIEW_LENGTH_KEY = "preview_length";
        String UNREAD_KEY = "unread";
        String IMPORTANT_KEY = "important";
        String UNANSWERED_KEY = "unanswered";
    }

    public interface VkMethodUsersGet {

        String METHOD_NAME = "users.get";

        //parameter's keys for "users.getDialogs"
        String USER_IDS_KEY = "user_ids";
        String FIELDS_KEY = "fields";
        String NAME_CASE_KEY = "name_case";

        //parameter's values for "users.getDialogs fields_key"
        String FIELD_BDATE = "bdate";
        String FIELD_PHOTO_50 = "photo_50";
        String FIELD_ACTIVITIES = "activities";
        String FIELD_BOOKS = "books";
        String FIELD_ABOUT = "about";
        String FIELD_CAN_POST = "can_post";
        String FIELD_BLACKLISTED = "blacklisted";
        String FIELD_BLACKLISTED_BY_ME = "blacklisted_by_me";
    }
}
