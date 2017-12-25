package com.github.alexvishneuski.vkbestclient.repository.repoutils;

public class RepositoryConstants {

    public interface VKApiConstants {

        /*common method data*/
        String METHOD_BASE_PATH = "%s/%s?access_token=%s&v=%s";

        String VK_API_SERVICE_URL = "https://api.vk.com/method";
        String VK_API_ACCESS_TOKEN = "50f6c8e1165705e23b1fa07b9af92da78644a7e29e0ef1bf61847e6ee11244a4fe2a4c074dd0f41def518";
        String VK_API_VERSION = "5.69";

        /*method names*/
        String VK_API_METHOD_NAME_MESSAGES_GET_DIALOGS = "messages.getDialogs";
        String VK_API_METHOD_NAME_USERS_GET = "users.get";
    }

    public interface RepositoryTest {
        String TEST_MESSAGE = "I'm com.github.alexvishneuski.vkbestclient.repository's test";
    }
}
