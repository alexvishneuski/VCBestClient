package repository.networking.vkapi.model.objects.mediacontentandattachments;

import com.google.gson.annotations.SerializedName;

/**
 * model for VK API
 * object Document
 * https://vk.com/dev/objects/doc
 */
public class VKApiMediaAttachmentTypeDocument {

    // идентификатор документа
    @SerializedName("id")
    private Integer mId;

    // идентификатор пользователя, загрузившего документ
    @SerializedName("owner_id")
    private Integer mDocUploadUserId;

    public Integer getId() {

        return mId;
    }

    public void setId(Integer pId) {
        mId = pId;
    }

    public Integer getDocUploadUserId() {

        return mDocUploadUserId;
    }

    public void setDocUploadUserId(Integer pDocUploadUserId) {
        mDocUploadUserId = pDocUploadUserId;
    }

    @Override
    public String toString() {
        return "VKApiMediaAttachmentTypeDocument{" +
                "mId=" + mId +
                ", mDocUploadUserId=" + mDocUploadUserId +
                '}';
    }
}

        /*id
        integer	идентификатор документа.

        owner_id
        integer	идентификатор пользователя, загрузившего документ.

        title
        string	название документа.

        size
        integer	размер документа в байтах.

        ext
        string	расширение документа.

        url
        string	адрес документа, по которому его можно загрузить.

        date
        integer	дата добавления в формате Unixtime.

        type
        integer	тип документа. Возможные значения:
        1 — текстовые документы;
        2 — архивы;
        3 — gif;
        4 — изображения;
        5 — аудио;
        6 — видео;
        7 — электронные книги;
        8 — неизвестно.

        preview
        object	информация для предварительного просмотра документа. Может содержать следующие поля:

        photo (object) — изображения для предпросмотра. Содержит единственное поле:

        sizes (array) — массив копий изображения в разных размерах. Подробное описание структуры Вы найдете на этой странице.

        graffiti (object) — данные о граффити. Содержит следующие поля:

        src (string) — URL документа с граффити;

        width (integer) — ширина изображения в px;

        height (integer) — высота изображения в px.

        audio_msg — данные об аудиосообщении. Объект, который содержит следующие поля:

        duration (integer) — длительность аудиосообщения в секундах;

        waveform (array) — массив значений (integer) для визуального отображения звука;

        link_ogg (string) — URL .ogg-файла;

        link_mp3 (string) — URL .mp3-файла.*/