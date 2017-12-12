package repository.networking.vkapi.network;

import repository.networking.vkapi.model.VKApiMessagesGetDialogsResponse;

public interface IDialogVKApiNetworking {


    String getDialogListAsString();


    VKApiMessagesGetDialogsResponse getDialogList();

}
