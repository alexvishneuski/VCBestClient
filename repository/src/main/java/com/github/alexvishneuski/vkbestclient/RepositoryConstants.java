package com.github.alexvishneuski.vkbestclient;

public class RepositoryConstants {



    public interface RepositoryTest {
        String TEST_MESSAGE = "I'm repository's test";
        int SDK_VERSION = 21;
    }

    public interface VkApiRequest {
        String REQUEST_URL_BASE_PATH = "https://api.vk.com/method/";
        String REQUEST_URL_METHOD_NAME = "%";
        String REQUEST_URL_PARAMETERS = "?%&access_token=%&v=%";
        //String REQUEST_URL = "https://api.vk.com/method/%?%&access_token=ACCESS_TOKEN&v=%";
        String API_VERSION = "5.69";
        String ACCESS_TOKEN = "";
    }
}
