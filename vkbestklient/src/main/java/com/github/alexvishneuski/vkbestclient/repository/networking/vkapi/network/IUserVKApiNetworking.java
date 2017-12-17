package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.responses.users.VKApiUsersGetResponse;

public interface IUserVKApiNetworking {

    VKApiUsersGetResponse getUsers();

}
