package com.github.alexvishneuski.vkbestclient.repository.repoutils;

public class RepositoryConstants {

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


    public interface CommonUrlParts {
        //common url parts
        String PROTOCOL = "https";

        String VK_AUTH_BASE_PATH = "oauth.vk.com/authorize";
        String VK_METHOD_BASE_PATH = "api.vk.com/method";

        String ACCESS_TOKEN_KEY = "access_token";
        String ACCESS_TOKEN = "";

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
        String FIELD_PHOTO_ID = "photo_id";
        String FIELD_VERIFIED = "verified";
        String FIELD_SEX = "sex";
        String FIELD_BDATE = "bdate";
        String FIELD_CITY = "city";
        String FIELD_COUNTRY = "country";
        String FIELD_HOME_TOWN = "home_town";
        String FIELD_HAS_PHOTO = "has_photo";
        String FIELD_PHOTO_50 = "photo_50";
        String FIELD_PHOTO_100 = "photo_100";
        String FIELD_PHOTO_200_ORIG = "photo_200_orig";
        String FIELD_PHOTO_200 = "photo_200";
        String FIELD_PHOTO_400_ORIG = "photo_400_orig";
        String FIELD_PHOTO_MAX = "photo_max";
        String FIELD_PHOTO_MAX_ORIG = "photo_max_orig";
        String FIELD_ONLINE = "online";
        String FIELD_DOMAIN = "domain";
        String FIELD_HAS_MOBILE = "has_mobile";
        String FIELD_CONTACTS = "contacts";
        String FIELD_SITE = "site";
        String FIELD_EDUCATION = "education";
        String FIELD_UNIVERSITIES = "universities";
        String FIELD_SCHOOLS = "schools";
        String FIELD_STATUS = "status";
        String FIELD_LAST_SEEN = "last_seen";
        String FIELD_FOLLOWERS_COUNT = "followers_count";
        String FIELD_COMMON_COUNT = "common_count";
        String FIELD_OCCUPATION = "occupation";
        String FIELD_NICKNAME = "nickname";
        String FIELD_RELATIVES = "relatives";
        String FIELD_RELATION = "relation";
        String FIELD_PERSONAL = "personal";
        String FIELD_CONNECTIONS = "connections";
        String FIELD_EXPORTS = "exports";
        String FIELD_WALL_COMMENTS = "wall_comments";
        String FIELD_ACTIVITIES = "activities";
        String FIELD_INTERESTS = "interests";
        String FIELD_MUSIC = "music";
        String FIELD_MOVIES = "movies";
        String FIELD_TV = "tv";
        String FIELD_BOOKS = "books";
        String FIELD_GAMES = "games";
        String FIELD_ABOUT = "about";
        String FIELD_QUOTES = "quotes";
        String FIELD_CAN_POST = "can_post";
        String FIELD_CAN_SEE_ALL_POSTS = "can_see_all_posts";
        String FIELD_CAN_SEE_AUDIO = "can_see_audio";
        String FIELD_CAN_WRITE_PRIVATE_MESSAGE = "can_write_private_message";
        String FIELD_CAN_SEND_FRIEND_REQUEST = "can_send_friend_request";
        String FIELD_IS_FAVORITE = "is_favorite";
        String FIELD_IS_HIDDEN_FROM_FEED = "is_hidden_from_feed";
        String FIELD_TIMEZONE = "timezone";
        String FIELD_SCREEN_NAME = "screen_name";
        String FIELD_MAIDEN_NAME = "maiden_name";
        String FIELD_CROP_PHOTO = "crop_photo";
        String FIELD_IS_FRIEND = "is_friend";
        String FIELD_FRIEND_STATUS = "friend_status";
        String FIELD_CAREER = "career";
        String FIELD_MILITARY = "military";
        String FIELD_BLACKLISTED = "blacklisted";
        String FIELD_BLACKLISTED_BY_ME = "blacklisted_by_me";

        //parameter's values for "users.getDialogs name_case_key"
        String NAME_CASE_NOM = "nom";
        String NAME_CASE_GEN = "gen";
        String NAME_CASE_DAT = "dat";
        String NAME_CASE_ACC = "acc";
        String NAME_CASE_INS = "ins";
        String NAME_CASE_ABL = "abl";
    }
}
