package presentation.model;

/**
 * Created by alex_vishneuski on 29.11.2017.
 */

public class UserInDialogListViewModel {

    private Long mUserId;
    private String mUserFullName;
    private String mUserAvatarPath;

    public UserInDialogListViewModel() {
    }

    public UserInDialogListViewModel(String pUserFullName, String pUserAvatarPath) {
        mUserFullName = pUserFullName;
        mUserAvatarPath = pUserAvatarPath;
    }

    public Long getUserId() {

        return mUserId;
    }

    public void setUserId(Long pUserId) {
        mUserId = pUserId;
    }

    public String getUserFullName() {

        return mUserFullName;
    }

    public void setUserFullName(String pUserFullName) {
        mUserFullName = pUserFullName;
    }

    public String getUserAvatarPath() {

        return mUserAvatarPath;
    }

    public void setUserAvatarPath(String pUserAvatarPath) {
        mUserAvatarPath = pUserAvatarPath;
    }
}
