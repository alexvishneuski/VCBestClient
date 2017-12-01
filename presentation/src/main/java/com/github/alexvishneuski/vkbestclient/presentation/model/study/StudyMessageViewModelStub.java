package com.github.alexvishneuski.vkbestclient.presentation.model.study;

import com.github.alexvishneuski.vkbestclient.presentation.model.MessageDirectionViewModel;

public class StudyMessageViewModelStub {

    private Long id;
    private String contactUserFullName;
    private String currentUserFullName;
    private MessageDirectionViewModel messageDirection;
    private String messageSendingDate;
    private String messageBody;
    private int contactUserAvatarId;
    private int currentUserAvatarId;
    private Boolean isMessageRead;

    public StudyMessageViewModelStub(String contactUserFullName, String currentUserFullName, MessageDirectionViewModel messageDirection, String messageSendingDate, String messageBody, Boolean isMessageRead) {
        this.contactUserFullName = contactUserFullName;
        this.currentUserFullName = currentUserFullName;
        this.messageDirection = messageDirection;
        this.messageSendingDate = messageSendingDate;
        this.messageBody = messageBody;
        this.isMessageRead = isMessageRead;
    }

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

    public MessageDirectionViewModel getMessageDirection() {
        return messageDirection;
    }

    public void setMessageDirection(MessageDirectionViewModel messageDirection) {
        this.messageDirection = messageDirection;
    }

    public String getMessageSendingDate() {
        return messageSendingDate;
    }

    public void setMessageSendingDate(String messageSendingDate) {
        this.messageSendingDate = messageSendingDate;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public int getContactUserAvatarId() {
        return contactUserAvatarId;
    }

    public void setContactUserAvatarId(int contactUserAvatarId) {
        this.contactUserAvatarId = contactUserAvatarId;
    }

    public int getCurrentUserAvatarId() {
        return currentUserAvatarId;
    }

    public void setCurrentUserAvatarId(int currentUserAvatarId) {
        this.currentUserAvatarId = currentUserAvatarId;
    }

    public Boolean getRead() {
        return isMessageRead;
    }

    public void setRead(Boolean read) {
        isMessageRead = read;
    }
}
