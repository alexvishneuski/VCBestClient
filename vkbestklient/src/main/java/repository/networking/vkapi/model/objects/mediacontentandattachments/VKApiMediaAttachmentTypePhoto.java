package repository.networking.vkapi.model.objects.mediacontentandattachments;

import repository.networking.vkapi.model.objects.supportingobjectsandvaluessets.VKApiPhotoSizeDescriptionFormat;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * model for VK API
 * object Photo
 * https://vk.com/dev/objects/photo
 */
public class VKApiMediaAttachmentTypePhoto {

    // идентификатор фотографии
    @SerializedName("id")
    private Integer mId;

    // идентификатор альбома, в котором находится фотография
    @SerializedName("album_id")
    private Integer mAlbumId;

    // идентификатор владельца фотографии
    @SerializedName("owner_id")
    private Integer mPhotosOwnerId;

    // идентификатор пользователя, загрузившего фото (если фотография размещена в сообществе). Для фотографий, размещенных от имени сообщества, user_id = 100
    @SerializedName("user_id")
    private Integer mUploadUserId;

    // текст описания фотографии
    @SerializedName("text")
    private String mDescriptionText;

    // дата добавления в формате Unixtime
    @SerializedName("date")
    private Integer mUploadDate;

    // массив с копиями изображения в разных размерах. Описание объекта находится на отдельной странице. Поле возвращается только при передаче параметра photo_sizes = 1 в запросе. Если параметр не передан, вместо поля sizes возвращаются поля, описанные ниже
    @SerializedName("sizes")
    private ArrayList<VKApiPhotoSizeDescriptionFormat> mPhotoCopiesArray;

    // URL копии фотографии с максимальным размером 75x75px
    @SerializedName("photo_75")
    private String mMaxSize75x75pxUrl;

    // URL копии фотографии с максимальным размером 130x130px
    @SerializedName("photo_130")
    private String mMaxSize130x130pxUrl;

    // URL копии фотографии с максимальным размером 604x604px
    @SerializedName("photo_604")
    private String mMaxSize604x604pxUrl;

    // URL копии фотографии с максимальным размером 807x807px
    @SerializedName("photo_807")
    private String mMaxSize807x807pxUrl;

    // URL копии фотографии с максимальным размером 1280x1024px
    @SerializedName("photo_1280")
    private String mMaxSize1280x1024pxUrl;

    // URL копии фотографии с максимальным размером 2560x2048px.
    @SerializedName("photo_2560")
    private String mMaxSize2560x2048pxUrl;

    // ширина оригинала фотографии в пикселах. значения могут быть недоступны для фотографий, загруженных на сайт до 2012 года
    @SerializedName("width")
    private Integer mOriginalWidthInPx;

    // высота оригинала фотографии в пикселах. значения могут быть недоступны для фотографий, загруженных на сайт до 2012 года
    @SerializedName("height")
    private Integer mOriginalHeightInPx;

    public Integer getId() {

        return mId;
    }

    public void setId(Integer pId) {
        mId = pId;
    }

    public Integer getAlbumId() {

        return mAlbumId;
    }

    public void setAlbumId(Integer pAlbumId) {
        mAlbumId = pAlbumId;
    }

    public Integer getPhotosOwnerId() {

        return mPhotosOwnerId;
    }

    public void setPhotosOwnerId(Integer pPhotosOwnerId) {
        mPhotosOwnerId = pPhotosOwnerId;
    }

    public Integer getUploadUserId() {

        return mUploadUserId;
    }

    public void setUploadUserId(Integer pUploadUserId) {
        mUploadUserId = pUploadUserId;
    }

    public String getDescriptionText() {

        return mDescriptionText;
    }

    public void setDescriptionText(String pDescriptionText) {
        mDescriptionText = pDescriptionText;
    }

    public Integer getUploadDate() {

        return mUploadDate;
    }

    public void setUploadDate(Integer pUploadDate) {
        mUploadDate = pUploadDate;
    }

    public ArrayList<VKApiPhotoSizeDescriptionFormat> getPhotoCopiesArray() {

        return mPhotoCopiesArray;
    }

    public void setPhotoCopiesArray(ArrayList<VKApiPhotoSizeDescriptionFormat> pPhotoCopiesArray) {
        mPhotoCopiesArray = pPhotoCopiesArray;
    }

    public String getMaxSize75x75pxUrl() {

        return mMaxSize75x75pxUrl;
    }

    public void setMaxSize75x75pxUrl(String pMaxSize75x75pxUrl) {
        mMaxSize75x75pxUrl = pMaxSize75x75pxUrl;
    }

    public String getMaxSize130x130pxUrl() {

        return mMaxSize130x130pxUrl;
    }

    public void setMaxSize130x130pxUrl(String pMaxSize130x130pxUrl) {
        mMaxSize130x130pxUrl = pMaxSize130x130pxUrl;
    }

    public String getMaxSize604x604pxUrl() {

        return mMaxSize604x604pxUrl;
    }

    public void setMaxSize604x604pxUrl(String pMaxSize604x604pxUrl) {
        mMaxSize604x604pxUrl = pMaxSize604x604pxUrl;
    }

    public String getMaxSize807x807pxUrl() {

        return mMaxSize807x807pxUrl;
    }

    public void setMaxSize807x807pxUrl(String pMaxSize807x807pxUrl) {
        mMaxSize807x807pxUrl = pMaxSize807x807pxUrl;
    }

    public String getMaxSize1280x1024pxUrl() {

        return mMaxSize1280x1024pxUrl;
    }

    public void setMaxSize1280x1024pxUrl(String pMaxSize1280x1024pxUrl) {
        mMaxSize1280x1024pxUrl = pMaxSize1280x1024pxUrl;
    }

    public String getMaxSize2560x2048pxUrl() {

        return mMaxSize2560x2048pxUrl;
    }

    public void setMaxSize2560x2048pxUrl(String pMaxSize2560x2048pxUrl) {
        mMaxSize2560x2048pxUrl = pMaxSize2560x2048pxUrl;
    }

    public Integer getOriginalWidthInPx() {

        return mOriginalWidthInPx;
    }

    public void setOriginalWidthInPx(Integer pOriginalWidthInPx) {
        mOriginalWidthInPx = pOriginalWidthInPx;
    }

    public Integer getOriginalHeightInPx() {

        return mOriginalHeightInPx;
    }

    public void setOriginalHeightInPx(Integer pOriginalHeightInPx) {
        mOriginalHeightInPx = pOriginalHeightInPx;
    }

    @Override
    public String toString() {
        return "VKApiMediaAttachmentTypePhoto{" +
                "mId=" + mId +
                ", mAlbumId=" + mAlbumId +
                ", mPhotosOwnerId=" + mPhotosOwnerId +
                ", mUploadUserId=" + mUploadUserId +
                ", mDescriptionText='" + mDescriptionText + '\'' +
                ", mUploadDate=" + mUploadDate +
                ", mPhotoCopiesArray=" + mPhotoCopiesArray +
                ", mMaxSize75x75pxUrl='" + mMaxSize75x75pxUrl + '\'' +
                ", mMaxSize130x130pxUrl='" + mMaxSize130x130pxUrl + '\'' +
                ", mMaxSize604x604pxUrl='" + mMaxSize604x604pxUrl + '\'' +
                ", mMaxSize807x807pxUrl='" + mMaxSize807x807pxUrl + '\'' +
                ", mMaxSize1280x1024pxUrl='" + mMaxSize1280x1024pxUrl + '\'' +
                ", mMaxSize2560x2048pxUrl='" + mMaxSize2560x2048pxUrl + '\'' +
                ", mOriginalWidthInPx=" + mOriginalWidthInPx +
                ", mOriginalHeightInPx=" + mOriginalHeightInPx +
                '}';
    }
}

