package com.github.alexvishneuski.vkbestclient.presentation.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.DialogsHistoryRecyclerAdapter;

//holder for for incoming and read messages
public class DialogsHistoryRecyclerViewHolder extends RecyclerView.ViewHolder {

    private DialogsHistoryRecyclerAdapter mAdapter;

    private TextView mMessageSendingDate;
    private TextView mMessageBody;

    public DialogsHistoryRecyclerViewHolder(final View pView, final DialogsHistoryRecyclerAdapter pAdapter) {
        super(pView);

        mAdapter = pAdapter;

        mMessageBody = pView.findViewById(R.id.message_in_history_text_view);
        mMessageSendingDate = pView.findViewById(R.id.message_in_history_date_text_view);
    }

    public TextView getMessageSendingDate() {

        return mMessageSendingDate;
    }

    public void setMessageSendingDate(TextView pMessageSendingDate) {
        mMessageSendingDate = pMessageSendingDate;
    }

    public TextView getMessageBody() {

        return mMessageBody;
    }

    public void setMessageBody(TextView pMessageBody) {
        mMessageBody = pMessageBody;
    }
}