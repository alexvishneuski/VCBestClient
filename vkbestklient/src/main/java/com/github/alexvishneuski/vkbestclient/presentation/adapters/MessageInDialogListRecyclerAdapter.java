package com.github.alexvishneuski.vkbestclient.presentation.adapters;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.imageloader.NOrda;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageDirectionViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.viewholder.MessageInDialogListRecyclerViewHolder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/**
 * Created by alex_vishneuski on 29.11.2017.
 */

public class MessageInDialogListRecyclerAdapter extends RecyclerView.Adapter<MessageInDialogListRecyclerViewHolder> {

    public final String TAG = this.getClass().getSimpleName();

    //TODO Handle view types (interface...)

    @IntDef({
            MessageType.OUTGOING_READ_NOATTACHMENT,
            MessageType.INCOMING_READ_NOATTACHMENT,
            MessageType.OUTGOING_UNREAD_NOATTACHMENT,
            MessageType.INCOMING_UNREAD_NOATTACHMENT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessageType {

        int OUTGOING_READ_NOATTACHMENT = 0;
        int INCOMING_READ_NOATTACHMENT = 1;
        int OUTGOING_UNREAD_NOATTACHMENT = 2;
        int INCOMING_UNREAD_NOATTACHMENT = 3;
        //TODO Add new types: with attachments...
    }

    //TO Handle click area on recyclerview item
    @IntDef({
            TouchArea.MESSAGE_AREA,
            TouchArea.USER_AREA})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TouchArea {

        int MESSAGE_AREA = 0;
        int USER_AREA = 1;
    }

    private List<MessageInDialogListViewModel> mMessageList;

    public MessageInDialogListRecyclerAdapter(final List<MessageInDialogListViewModel> pMessageList) {
        mMessageList = pMessageList;
    }


    /***** Creating OnItemClickListener *****/
    // Define listener member variable
    private OnItemClickListener listener;

    public OnItemClickListener getListener() {
        return listener;
    }

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, int pViewArea);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public MessageInDialogListRecyclerViewHolder onCreateViewHolder(ViewGroup pParent, int pViewType) {
        final int viewId;

        switch (pViewType) {
            case MessageType.INCOMING_READ_NOATTACHMENT:
                viewId = R.layout.view_message_in_dialogs_incoming_read_noattachment_item;
                break;
            case MessageType.OUTGOING_READ_NOATTACHMENT:
                viewId = R.layout.view_message_in_dialogs_outgoing_read_noattachment_item;
                break;
            case MessageType.INCOMING_UNREAD_NOATTACHMENT:
                viewId = R.layout.view_message_in_dialogs_incoming_unread_noattachment_item;
                break;
            case MessageType.OUTGOING_UNREAD_NOATTACHMENT:
                viewId = R.layout.view_message_in_dialogs_outgoing_unread_noattachment_item;
                break;
            default:
                viewId = R.layout.view_message_in_dialogs_incoming_read_noattachment_item;
                break;
        }

        final View view = LayoutInflater.from(pParent.getContext()).inflate(viewId, pParent, false);

        return new MessageInDialogListRecyclerViewHolder(view, this);
    }

    //TODO to resolve create a few separate viewHolder for every view?
    @Override
    public void onBindViewHolder(MessageInDialogListRecyclerViewHolder pHolder, int pPosition) {

        final MessageInDialogListViewModel messageModel = mMessageList.get(pPosition);

        pHolder.getContactUserFullName().setText(messageModel.getContactUser().getUserFullName());

        pHolder.getMessageBody().setText(messageModel.getMessageBody());
        pHolder.getMessageSendingDate().setText(messageModel.getMessageSendingDate());

        //TODO add imageloader
        NOrda.INSTANCE.load(messageModel.getContactUser().getUserAvatarPath()).into(pHolder.getContactUserAvatarPath());
        NOrda.INSTANCE.load(messageModel.getCurrentUser().getUserAvatarPath()).into(pHolder.getCurrentUserAvatarPath());
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
        //returned type OUTGOING_READ_NOATTACHMENT
        return 0;
    }


    //TODO add unread & attachment types according logic

    /**
     * @param pMessage
     * @return MessageType depends direction, read status, attachments etc.
     */
    private int getMessageType(@NonNull MessageInDialogListViewModel pMessage) {

        if (pMessage.getMessageDirection() == (MessageDirectionViewModel.OUTGOING) && pMessage.getMessageRead()) {

            return MessageType.OUTGOING_READ_NOATTACHMENT;

        } else if (pMessage.getMessageDirection() == MessageDirectionViewModel.OUTGOING && !pMessage.getMessageRead()) {

            return MessageType.OUTGOING_UNREAD_NOATTACHMENT;

        } else if (pMessage.getMessageDirection() == MessageDirectionViewModel.INCOMING && pMessage.getMessageRead()) {

            return MessageType.INCOMING_READ_NOATTACHMENT;

        } else if (pMessage.getMessageDirection() == MessageDirectionViewModel.INCOMING && !pMessage.getMessageRead()) {

            return MessageType.INCOMING_UNREAD_NOATTACHMENT;
        }

        return MessageType.INCOMING_READ_NOATTACHMENT;
    }
}
