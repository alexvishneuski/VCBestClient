package com.github.alexvishneuski.vkbestclient.presentation.adapters;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageDirectionViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.uimodel.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.viewholder.MessageInDialogListRecyclerViewHolder;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class DialogsHistoryRecyclerAdapter extends RecyclerView.Adapter<MessageInDialogListRecyclerViewHolder> {

    @IntDef({
            MessageType.OUTGOING_READ_NOATTACHMENT,
            MessageType.OUTGOING_UNREAD_NOATTACHMENT,
            MessageType.INCOMING_NOATTACHMENT
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessageType {
        //TODO Add ddate type
        int OUTGOING_READ_NOATTACHMENT = 0;
        int OUTGOING_UNREAD_NOATTACHMENT = 1;
        int INCOMING_NOATTACHMENT = 2;

        //TODO Add new types: with attachments...

    }

    public final String TAG = this.getClass().getSimpleName();

    private List<MessageInDialogListViewModel> mMessages;

    public DialogsHistoryRecyclerAdapter(List<MessageInDialogListViewModel> pMessages) {
        mMessages = pMessages;
    }

    @Override
    public MessageInDialogListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MessageInDialogListRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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

        if (pMessage.getMessageDirection() == (MessageDirectionViewModel.OUTGOING) && pMessage.getMessageRead()) {

            return MessageType.OUTGOING_READ_NOATTACHMENT;

        } else if (pMessage.getMessageDirection() == MessageDirectionViewModel.OUTGOING && !pMessage.getMessageRead()) {

            return MessageType.OUTGOING_UNREAD_NOATTACHMENT;
        } else if (pMessage.getMessageDirection() == MessageDirectionViewModel.INCOMING) {

            return MessageType.INCOMING_NOATTACHMENT;
        }

        return MessageType.INCOMING_NOATTACHMENT;
    }
}
