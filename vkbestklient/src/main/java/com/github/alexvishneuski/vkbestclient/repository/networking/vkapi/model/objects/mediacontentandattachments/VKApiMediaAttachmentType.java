package com.github.alexvishneuski.vkbestclient.repository.networking.vkapi.model.objects.mediacontentandattachments;

/**
* model for VK API
* object Type of Attachment
* https://vk.com/dev/objects/attachments_m
*/
enum VKApiMediaAttachmentType {

    // Фотография
    PHOTO,
    // Видеозапись
    VIDEO,
    //Аудиозапись
    AUDIO,
    //Документ
    DOC,
    //Ссылка
    LINK,
    //Товар
    MARKET,
    //Подборка товаров
    MARKET_ALBUM,
    //Запись на стене
    WALL,
    //Комментарий на стене
    WALL_REPLY,
    //Стикер
    STICKER,
    // Подарок
    GIFT
}


