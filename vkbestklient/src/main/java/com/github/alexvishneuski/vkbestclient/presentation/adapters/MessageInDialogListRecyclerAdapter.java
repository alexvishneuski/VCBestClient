package com.github.alexvishneuski.vkbestclient.presentation.adapters;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexvishneuski.vkbestclient.imageloader.NOrda;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageDirectionViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.viewholder.MessageInDialogListRecyclerViewHolder;
import com.github.alexvishneuski.vkbestclient.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by alex_vishneuski on 29.11.2017.
 */

public class MessageInDialogListRecyclerAdapter extends RecyclerView.Adapter<MessageInDialogListRecyclerViewHolder> {

    //TODO Handle view types (interface...)

    @IntDef({MessageType.OUTGOING_READ, MessageType.INCOMING_READ})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessageType {

        int OUTGOING_READ = 0;
        int INCOMING_READ = 1;
        //TODO Add new types: with attachments, unread...
    }

    private List<MessageInDialogListViewModel> mMessageList;

    public MessageInDialogListRecyclerAdapter(final List<MessageInDialogListViewModel> pMessageList) {
        mMessageList = pMessageList;
    }

    @Override
    public MessageInDialogListRecyclerViewHolder onCreateViewHolder(ViewGroup pParent, int pViewType) {
        final int viewId;

        switch (pViewType) {
            case MessageType.INCOMING_READ:
                viewId = R.layout.view_message_in_dialog_list_item_incoming_read;
                break;
            case MessageType.OUTGOING_READ:
                viewId = R.layout.view_message_in_dialog_list_item_outgoing_read;
                break;
            default:
                viewId = R.layout.view_message_in_dialog_list_item_incoming_read;
                break;
        }

        final View view = LayoutInflater.from(pParent.getContext()).inflate(viewId, pParent, false);

        return new MessageInDialogListRecyclerViewHolder(view);
    }

    //TODO to resolve create a few separate viewHolder for every view?
    @Override
    public void onBindViewHolder(MessageInDialogListRecyclerViewHolder pHolder, int pPosition) {

        final MessageInDialogListViewModel messageModel = mMessageList.get(pPosition);

        pHolder.getContactUserFullName().setText(messageModel.getContactUser().getUserFullName());

        pHolder.getMessageBody().setText(messageModel.getMessageBody());
        pHolder.getMessageSendingDate().setText(messageModel.getMessageSendingDate());
        //FiXME add imageloader
        NOrda.INSTANCE.load(messageModel.getContactUser().getUserAvatarPath()).into(pHolder.getContactUserAvatarPath());
    }

    @Override
    public int getItemCount() {

        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int pPosition) {

        if (mMessageList != null) {
            MessageInDialogListViewModel message = mMessageList.get(pPosition);

            return getMessageType(message);
        }
        //returned type OUTGOING_READ
        return 0;
    }


    //TODO add unread & attachment types detection logic

    /**
     * @param pMessage
     * @return MessageType depends direction, read status, attachments etc.
     */
    private int getMessageType(@NonNull MessageInDialogListViewModel pMessage) {

        if (pMessage.getMessageDirection()==(MessageDirectionViewModel.OUTGOING) && pMessage.getMessageRead()) {
            return MessageType.OUTGOING_READ;
        } else
            //if (pMessage.getMessageDirection() == MessageDirectionViewModel.INCOMING & pMessage.getMessageRead() == Boolean.TRUE) {

            return MessageType.INCOMING_READ;
    }
}
