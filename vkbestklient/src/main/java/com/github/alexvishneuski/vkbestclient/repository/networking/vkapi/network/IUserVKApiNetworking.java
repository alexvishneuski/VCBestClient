package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network;

import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;

import java.util.List;

public interface IUserVKApiNetworking {

    List<VKApiUser> getUsers();

}
