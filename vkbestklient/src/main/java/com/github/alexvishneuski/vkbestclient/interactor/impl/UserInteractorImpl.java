package com.github.alexvishneuski.vkbestclient.interactor.impl;

import android.util.Log;

import com.github.alexvishneuski.vkbestclient.interactor.IUserInteractor;
import com.github.alexvishneuski.vkbestclient.interactor.model.UserInDialogs;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.basicobjects.VKApiUser;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.IUserVKApiNetworking;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.network.impl.UserVKApiNetworkingImpl;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiGetUsersParams;
import com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.requestparams.VKApiUri;
import com.github.alexvishneuski.vkbestclient.repository.repoutils.RepositoryConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserInteractorImpl implements IUserInteractor {

    private final String TAG = this.getClass().getSimpleName();

    private IUserVKApiNetworking mUserVKApiNet = new UserVKApiNetworkingImpl();


    //Interactor area
    /*======================================================================================*/
    @Override
    public List<UserInDialogs> getDomainUsersBasicInfo(List<Integer> pInt) {

        List<String> fields = new ArrayList<>();
        fields.add(RepositoryConstants.VkMethodUsersGet
                .FIELD_PHOTO_50);

        List<VKApiUser> users = this.getUsers(pInt, fields);

        return convertFromRepoToInteractor(users);
    }


    @Override
    public List<UserInDialogs> getDomainUsersFullInfo(List<Integer> pInt) {

        List<String> fields = new ArrayList<>();
        prepareFieldsForRequest(fields);

        List<VKApiUser> users = this.getUsers(pInt, fields);

        return convertFromRepoToInteractor(users);
    }

    //TODO add all needed for UserInfoActoivity fields
    private void prepareFieldsForRequest(List<String> pFields) {
        pFields.addAll(Arrays.asList(
                RepositoryConstants.VkMethodUsersGet.FIELD_PHOTO_50,
                RepositoryConstants.VkMethodUsersGet.FIELD_ABOUT,
                RepositoryConstants.VkMethodUsersGet.FIELD_ACTIVITIES,
                RepositoryConstants.VkMethodUsersGet.FIELD_BDATE,
                RepositoryConstants.VkMethodUsersGet.FIELD_BLACKLISTED,
                RepositoryConstants.VkMethodUsersGet.FIELD_BLACKLISTED_BY_ME,
                RepositoryConstants.VkMethodUsersGet.FIELD_BOOKS,
                RepositoryConstants.VkMethodUsersGet.FIELD_CAN_POST)
        );
    }


    @Override
    public UserInDialogs getCurrentUserDomain() {

        return convertFromRepoToInteractor(this.getCurrentUser());
    }


    //Convertor from VK to Interactor
    /*======================================================================================*/

    private UserInDialogs convertFromRepoToInteractor(VKApiUser pUser) {

        UserInDialogs domainUser = new UserInDialogs();

        domainUser.setUserId(pUser.getId());
        domainUser.setUserAvatarPath(pUser.getPhoto50());
        domainUser.setUserFullName(pUser.getFirstName() + " " + pUser.getLastName());

        return domainUser;
    }

    private List<UserInDialogs> convertFromRepoToInteractor(List<VKApiUser> pUsers) {

        List<UserInDialogs> domainUsers = new ArrayList<>();

        UserInDialogs domainUser;
        for (VKApiUser user : pUsers
                ) {
            domainUser = this.convertFromRepoToInteractor(user);
            domainUsers.add(domainUser);
        }

        return domainUsers;
    }


    //VKApi Models Area
    /*======================================================================================*/

    @Override
    public List<VKApiUser> getUsers() {
        Log.d(TAG, "getUsers: called");

        List<VKApiUser> users = new ArrayList<>();

        VKApiGetUsersParams usersParams = VKApiGetUsersParams.getBuilder().build();
        VKApiUri usersUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodUsersGet.METHOD_NAME)
                .setParameters(usersParams)
                .build();

        users.addAll(mUserVKApiNet.getUsers(usersUri));

        Log.d(TAG, "getUsers: returned users");

        return users;
    }


    private List<VKApiUser> getUsers(List<Integer> pUserIds, List<String> pRequestFields) {
        Log.d(TAG, "getUsers() called with: pUserIds = [" + pUserIds + "], pRequestFields = [" + pRequestFields + "]");

        String[] idArray = new String[pUserIds.size()];

        for (int i = 0; i < pUserIds.size(); i++) {
            idArray[i] = String.valueOf(pUserIds.get(i));
        }

        String[] fieldsArray;

        if (pRequestFields != null) {
            fieldsArray = new String[pRequestFields.size()];
            for (int i = 0; i < pRequestFields.size(); i++) {
                fieldsArray[i] = pRequestFields.get(i);
            }
        } else {
            fieldsArray = new String[]{""};
        }

        List<VKApiUser> users = new ArrayList<>();

        VKApiGetUsersParams usersParams = VKApiGetUsersParams.getBuilder().setUserIds(idArray).setFields(fieldsArray).build();

        VKApiUri usersUri = VKApiUri.getBuilder()
                .setProtocol(RepositoryConstants.CommonUrlParts.PROTOCOL)
                .setBasePath(RepositoryConstants.CommonUrlParts.VK_METHOD_BASE_PATH)
                .setMethod(RepositoryConstants.VkMethodUsersGet.METHOD_NAME)
                .setParameters(usersParams)
                .build();

        users.addAll(mUserVKApiNet.getUsers(usersUri));

        Log.d(TAG, "getUsers: returned users");

        return users;
    }

    private VKApiUser getCurrentUser() {
        Log.d(TAG, "getCurrentUser: called");

        VKApiUser currentUser = this.getUsers().get(0);
        Log.d(TAG, "getCurrentUser: returned current user with " +
                "id: " + currentUser.getId()
                + ", FirstName:  " + currentUser.getFirstName()
                + ", LastName:  " + currentUser.getLastName()
                + ", AvatarPath:  " + currentUser.getPhoto50());

        return currentUser;
    }
}
