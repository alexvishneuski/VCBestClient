package com.github.alexvishneuski.vkbestclient.repository.repoutils;

public class RepositoryConstants {

    public interface VKApiConstants {

        /*common method data*/
        String METHOD_BASE_PATH = "%s/%s?access_token=%s&v=%s";

        String VK_API_SERVICE_URL = "https://api.vk.com/method";
        String VK_API_ACCESS_TOKEN = "1d15678a0b585260957e567c4d170d2cf5d8b8ca51e0d58db5ac799d4f2fadb9fbd217e705203e37529bd";
        String VK_API_VERSION = "5.69";

        /*method names*/
        String VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS = "messages.getDialogs";
        String VK_API_METHOD_NAME_USERS_GET = "users.get";
    }

    public interface RepositoryTest {
        String TEST_MESSAGE = "I'm com.github.alexvishneuski.vkbestclient.repository's test";
    }
}
