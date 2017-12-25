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


    //variables
    //common signs
    public static final String SIGN_EQUAL = "=";
    public static final String SIGN_COLON = ":";
    public static final String SIGN_SLASH = "/";
    public static final String SIGN_DOUBLE_SLASH = "//";
    public static final String SIGN_COLON_DOUBLE_SLASH = "://";
    public static final String SIGN_AMPERSAND = "&";
    public static final String SIGN_QUESTION = "?";

    

}
