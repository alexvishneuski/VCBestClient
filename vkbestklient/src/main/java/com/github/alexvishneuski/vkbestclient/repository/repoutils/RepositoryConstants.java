package com.github.alexvishneuski.vkbestclient.repository.repoutils;

public class RepositoryConstants {

    public interface VKApiConstants {

        /*common method data*/
        String METHOD_BASE_PATH = "%s/%s?access_token=%s&v=%s";

        String VK_API_SERVICE_URL = "https://api.vk.com/method";
        String VK_API_ACCESS_TOKEN = "";
        String VK_API_VERSION = "5.69";

        /*method names*/
        String VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS = "messages.getDialogs";
        String VK_API_METHOD_NAME_USERS_GET = "users.get";
    }

    public interface RepositoryTest {
        String TEST_MESSAGE = "I'm com.github.alexvishneuski.vkbestclient.repository's test";
    }

    //common signs
    public interface Sign {

        String EQUAL = "=";
        String COLON = ":";
        String SLASH = "/";
        String DOUBLE_SLASH = "//";
        String COLON_DOUBLE_SLASH = "://";
        String AMPERSAND = "&";
        String QUESTION = "?";
        String COMMA = ",";
    }


    public interface CommonUrlParts{
        //common url parts
        String PROTOCOL = "https";

        String VK_AUTH_BASE_PATH = "oauth.vk.com/authorize";
        String VK_METHOD_BASE_PATH = "api.vk.com/method";

        String ACCESS_TOKEN_KEY = "access_token";

        String VERSION_KEY = "v";
        String VERSION = "5.69";
    }




    //authorization params keys
    public static final String VK_CLIENT_ID_KEY = "client_id";
    public static final String VK_REDIRECT_URI_KEY = "redirect_uri";
    public static final String VK_DISPLAY_KEY = "display";
    public static final String VK_SCOPE_KEY = "scope";
    public static final String VK_RESPONSE_TYPE_KEY = "response_type";

    public static final String VK_STATE_KEY = "state";
    public static final String VK_REVOKE_KEY = "revoke";

    //authorization params values
    public static final String VK_CLIENT_ID = "6261957";
    public static final String VK_REDIRECT_URI = "https://oauth.vk.com/blank.html";
    public static final String VK_DISPLAY = "mobile";
    public static final String VK_RESPONSE_TYPE = "oauth.vk.com/authorize";

    public static final String VK_STATE = "123456";
    public static final String VK_REVOKE_1 = "1";

    public static final String VK_SCOPE_NOTIFY = "notify";
    public static final String VK_SCOPE_FRIENDS = "friends";
    public static final String VK_SCOPE_PHOTOS = "photos";
    public static final String VK_SCOPE_AUDIO = "audio";
    public static final String VK_SCOPE_VIDEO = "video";
    public static final String VK_SCOPE_PAGES = "pages";
    public static final String VK_SCOPE_STATUS = "status";
    public static final String VK_SCOPE_NOTES = "notes";
    public static final String VK_SCOPE_MESSAGES = "messages";
    public static final String VK_SCOPE_WALL = "wall";
    public static final String VK_SCOPE_ADS = "ads";
    public static final String VK_SCOPE_OFFLINE = "offline";
    public static final String VK_SCOPE_DOCS = "docs";
    public static final String VK_SCOPE_GROUPS = "groups";
    public static final String VK_SCOPE_NOTIFICATIONS = "notifications";
    public static final String VK_SCOPE_STATS = "stats";
    public static final String VK_SCOPE_EMAIL = "email";
    public static final String VK_SCOPE_MARKET = "market";


    public interface VkMethodMessagesGetDialogs {
        //methods
        String METHOD_NAME = "messages.getDialogs";

        //parameters keys for "messages.getDialogs"
        String OFFSET_KEY = "offset";
        String COUNT_KEY = "count";
        String START_MESSAGE_ID_KEY = "start_message_id";
        String PREVIEW_LENGTH_KEY = "preview_length";
        String UNREAD_KEY = "unread";
        String IMPORTANT_KEY = "important";
        String UNANSWERED_KEY = "unanswered";
    }


}
