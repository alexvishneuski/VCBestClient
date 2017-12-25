package com.github.alexvishneuski.vkbestclient.repository.repoutils;

public class RepositoryConstants {

    public interface VKApiConstants {

        /*common method data*/
        String METHOD_BASE_PATH = "%s/%s?access_token=%s&v=%s";

        String VK_API_SERVICE_URL = "https://api.vk.com/method";
        String VK_API_ACCESS_TOKEN = "bd02994c4534c152b19cf9e3ef82867543346185a82e4610f55a930368edddad9cc037d249a4364df9d84";
        String VK_API_VERSION = "5.69";

        /*method names*/
        String VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS = "messages.getDialogs";
        String VK_API_METHOD_NAME_USERS_GET = "users.get";
    }

    public interface RepositoryTest {
        String TEST_MESSAGE = "I'm com.github.alexvishneuski.vkbestclient.repository's test";
    }


    //common signs
    public static final String SIGN_EQUAL = "=";
    public static final String SIGN_COLON = ":";
    public static final String SIGN_SLASH = "/";
    public static final String SIGN_DOUBLE_SLASH = "//";
    public static final String SIGN_COLON_DOUBLE_SLASH = "://";
    public static final String SIGN_AMPERSAND = "&";
    public static final String SIGN_QUESTION = "?";
    public static final String SIGN_COMMA = ",";

    //common url parts
    public static final String PROTOCOL = "https";

    public static final String VK_AUTH_BASE_PATH = "oauth.vk.com/authorize";
    public static final String VK_METHOD_BASE_PATH = "api.vk.com/method";

    //authorization params keys
    public static final String VK_CLIENT_ID_KEY = "client_id";
    public static final String VK_REDIRECT_URI_KEY = "redirect_uri";
    public static final String VK_DISPLAY_KEY = "display";
    public static final String VK_SCOPE_KEY = "scope";
    public static final String VK_RESPONSE_TYPE_KEY = "response_type";
    public static final String VK_VERSION_KEY = "v";
    public static final String VK_STATE_KEY = "state";
    public static final String VK_REVOKE_KEY = "revoke";

    //authorization params values
    public static final String VK_CLIENT_ID = "6261957";
    public static final String VK_REDIRECT_URI = "https://oauth.vk.com/blank.html";
    public static final String VK_DISPLAY = "mobile";
    public static final String VK_RESPONSE_TYPE = "oauth.vk.com/authorize";
    public static final String VK_VERSION = "5.69";
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

    //methods
    public static final String VK_METHOD_MESSAGES_GET_DIALOGS = "messages.getDialogs";

    //parameters for "messages.getDialogs"
    public static final String VK_OFFSET_KEY = "offset";
    public static final String VK_COUNT_KEY = "count";
    public static final String VK_START_MESSAGE_ID_KEY = "start_message_id";
    public static final String VK_PREVIEW_LENGTH_KEY = "preview_length";
    public static final String VK_UNREAD_KEY = "unread";
    public static final String VK_IMPORTANT_KEY = "important";
    public static final String VK_UNANSWERED_KEY = "unanswered";

}
