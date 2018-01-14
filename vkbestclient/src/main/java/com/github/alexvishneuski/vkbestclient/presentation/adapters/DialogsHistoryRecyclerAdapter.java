package com.github.alexvishneuski.vkbestclient.presentation.adapters;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageDirectionViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.viewholder.DialogsHistoryRecyclerViewHolder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class DialogsHistoryRecyclerAdapter extends RecyclerView.Adapter<DialogsHistoryRecyclerViewHolder> {

    @IntDef({
            MessageType.OUTGOING_NOATTACHMENT_FIRST_READ,
            MessageType.OUTGOING_NOATTACHMENT_FIRST_UNREAD,
            MessageType.OUTGOING_NOATTACHMENT_READ,
            MessageType.OUTGOING_NOATTACHMENT_UNREAD,
            MessageType.INCOMING_NOATTACHMENT_FIRST,
            MessageType.INCOMING_NOATTACHMENT,
            MessageType.DATE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessageType {

        int OUTGOING_NOATTACHMENT_FIRST_READ = 0;
        int OUTGOING_NOATTACHMENT_FIRST_UNREAD = 2;
        int OUTGOING_NOATTACHMENT_READ = 3;
        int OUTGOING_NOATTACHMENT_UNREAD = 4;
        int INCOMING_NOATTACHMENT_FIRST = 5;
        int INCOMING_NOATTACHMENT = 6;
        int DATE = 7;

        //TODO Add new types: with attachments...

    }

    public final String TAG = this.getClass().getSimpleName();

    private List<MessageInDialogListViewModel> mMessages;

    public DialogsHistoryRecyclerAdapter(List<MessageInDialogListViewModel> pMessages) {
        mMessages = pMessages;
    }

    @Override
    public DialogsHistoryRecyclerViewHolder onCreateViewHolder(ViewGroup pParent, int pViewType) {

        final int viewId;

        switch (pViewType) {
            case MessageType.DATE:
                viewId = R.layout.view_message_in_history_date;
                break;
            case MessageType.INCOMING_NOATTACHMENT:
                viewId = R.layout.view_message_in_history_incoming_noattachment_item;
                break;
            case MessageType.INCOMING_NOATTACHMENT_FIRST:
                viewId = R.layout.view_message_in_history_incoming_noattachment_first_item;
                break;
            case MessageType.OUTGOING_NOATTACHMENT_FIRST_READ:
                viewId = R.layout.view_message_in_history_outgoing_noattachment_first_item;
                break;
            case MessageType.OUTGOING_NOATTACHMENT_FIRST_UNREAD:
                viewId = R.layout.view_message_in_history_outgoing_noattachment_first_unread_item;
                break;
            case MessageType.OUTGOING_NOATTACHMENT_READ:
                viewId = R.layout.view_message_in_history_outgoing_noattachment_item;
                break;
            case MessageType.OUTGOING_NOATTACHMENT_UNREAD:
                viewId = R.layout.view_message_in_history_outgoing_noattachment_unread_item;
                break;
            default:
                viewId = R.layout.view_message_in_history_date;
                break;
        }

        final View view = LayoutInflater.from(pParent.getContext()).inflate(viewId, pParent, false);

        return new DialogsHistoryRecyclerViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(DialogsHistoryRecyclerViewHolder pHolder, int pPosition) {
        final MessageInDialogListViewModel messageModel = mMessages.get(pPosition);

        pHolder.getMessageBody().setText(messageModel.getMessageBody());
        //TODO make date convertion corresponding date format in fragment
      //  pHolder.getMessageSendingDate().setText(messageModel.getMessageSendingDate());

    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int pPosition) {

        if (mMessages != null) {
            MessageInDialogListViewModel message = mMessages.get(pPosition);

            return getMessageType(message);
        }
        //returned type OUTGOING_READ_NOATTACHMENT
        return 0;
    }

    /**
     * @param pMessage
     * @return MessageType depends direction, read status, attachments etc.
     */
    private int getMessageType(@NonNull MessageInDialogListViewModel pMessage) {
        //TODO add another types(date, first) with correcsp logic
        if (pMessage.getMessageDirection() == (MessageDirectionViewModel.OUTGOING) && pMessage.getMessageRead()) {

            return MessageType.OUTGOING_NOATTACHMENT_READ;

        } else if (pMessage.getMessageDirection() == MessageDirectionViewModel.OUTGOING && !pMessage.getMessageRead()) {

            return MessageType.OUTGOING_NOATTACHMENT_UNREAD;
        } else if (pMessage.getMessageDirection() == MessageDirectionViewModel.INCOMING) {

            return MessageType.INCOMING_NOATTACHMENT;
        }

        return MessageType.INCOMING_NOATTACHMENT;
    }
}
