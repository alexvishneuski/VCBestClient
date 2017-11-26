package com.github.alexvishneuski.vkbestclient.presentation.model;

public class MessageViewModel {

    private Long id;
    private String contactUserFullName;
    private String currentUserFullName;
    private MessageDirection messageDirection;
    private String messageSendingDate;
   // private String title;
    private String MessageBody;
    private String contactUserAvatarUrl;
    private String currentUserAvatarUrl;
    private Boolean isMessageRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContactUserFullName() {
        return contactUserFullName;
    }

    public void setContactUserFullName(String contactUserFullName) {
        this.contactUserFullName = contactUserFullName;
    }

    public String getCurrentUserFullName() {
        return currentUserFullName;
    }

    public void setCurrentUserFullName(String currentUserFullName) {
        this.currentUserFullName = currentUserFullName;
    }

    public MessageDirection getMessageDirection() {
        return messageDirection;
    }

    public void setMessageDirection(MessageDirection messageDirection) {
        this.messageDirection = messageDirection;
    }

    public String getMessageSendingDate() {
        return messageSendingDate;
    }

    public void setMessageSendingDate(String messageSendingDate) {
        this.messageSendingDate = messageSendingDate;
    }

    public String getMessageBody() {
        return MessageBody;
    }

    public void setMessageBody(String messageBody) {
        this.MessageBody = messageBody;
    }

    public String getContactUserAvatarUrl() {
        return contactUserAvatarUrl;
    }

    public void setContactUserAvatarUrl(String contactUserAvatarUrl) {
        this.contactUserAvatarUrl = contactUserAvatarUrl;
    }

    public String getCurrentUserAvatarUrl() {
        return currentUserAvatarUrl;
    }

    public void setCurrentUserAvatarUrl(String currentUserAvatarUrl) {
        this.currentUserAvatarUrl = currentUserAvatarUrl;
    }

    public Boolean getRead() {
        return isMessageRead;
    }

    public void setRead(Boolean read) {
        isMessageRead = read;
    }
}
   /*
    https://vk.com/dev/objects/message
   Список объектов › Личное сообщение
        Объект, описывающий личное сообщение, содержит следующие поля:

        id
        integer	идентификатор сообщения (не возвращается для пересланных сообщений).
        user_id
        integer	идентификатор пользователя, в диалоге с которым находится сообщение.
        from_id
        integer	идентификатор автора сообщения.
        положительное число
        date
        integer	дата отправки сообщения в формате Unixtime.
        read_state
        integer, [0,1]	статус сообщения (0 — не прочитано, 1 — прочитано, не возвращается для пересланных сообщений).
        out
        integer, [0,1]	тип сообщения (0 — полученное, 1 — отправленное, не возвращается для пересланных сообщений).
        title
        string	заголовок сообщения или беседы.
        MessageBody
        string	текст сообщения.
        geo
        object	информация о местоположении , содержит поля:
        type (string) — тип места;
        coordinates (string) — координаты места;
        place (object) — описание места (если оно добавлено), объект с полями:
        id (integer) — идентификатор места (если назначено);
        title (string) — название места (если назначено);
        latitude (number) — географическая широта;
        longitude (number) — географическая долгота;
        created (integer) — дата создания (если назначено);
        icon (string) — URL изображения-иконки;
        country (string) — название страны;
        city (string) — название города;
        attachments
        array	медиавложения сообщения (фотографии, ссылки и т.п.). Описание массива attachments находится на отдельной странице.
        fwd_messages
        array	массив пересланных сообщений (если есть). Максимальное количество элементов — 100. Максимальная глубина вложенности для пересланных сообщений — 45, общее максимальное количество в цепочке с учетом вложенности — 500.
        emoji
        integer, [0,1]	содержатся ли в сообщении emoji-смайлы.
        important
        integer, [0,1]	является ли сообщение важным.
        deleted
        integer, [0,1]	удалено ли сообщение.
        random_id
        integer	идентификатор, используемый при отправке сообщения. Возвращается только для исходящих сообщений.

        Дополнительные поля в сообщениях из мультидиалогов

        chat_id
        integer	идентификатор беседы.
        chat_active
        array	идентификаторы авторов последних сообщений беседы (integer).
        push_settings	настройки уведомлений для беседы, если они есть.
        users_count
        integer	количество участников беседы.
        admin_id
        integer	идентификатор создателя беседы.
        action
        string	тип действия (если это служебное сообщение). Возможные значения:
        chat_photo_update — обновлена фотография беседы;
        chat_photo_remove — удалена фотография беседы;
        chat_create — создана беседа;
        chat_title_update — обновлено название беседы;
        chat_invite_user — приглашен пользователь;
        chat_kick_user — исключен пользователь.
        chat_pin_message — закреплено сообщение;
        chat_unpin_message — откреплено сообщение.
        action_mid
        integer	идентификатор пользователя (если > 0) или email (если < 0), которого пригласили или исключили (для служебных сообщений с action = chat_invite_user или chat_kick_user). Идентификатор пользователя, который закрепил/открепил сообщение для action = chat_pin_message или chat_unpin_message.
        action_email
        string	email, который пригласили или исключили (для служебных сообщений с action = chat_invite_user или chat_kick_user и отрицательным action_mid).
        action_text
        string	название беседы (для служебных сообщений с action = chat_create или chat_title_update). Текст закрепленного сообщения для action = chat_pin_message.
        photo_50
        string	URL копии фотографии беседы шириной 50 px.
        photo_100
        string	URL копии фотографии беседы шириной 100 px.
        photo_200
        string	URL копии фотографии беседы шириной 200 px.*/