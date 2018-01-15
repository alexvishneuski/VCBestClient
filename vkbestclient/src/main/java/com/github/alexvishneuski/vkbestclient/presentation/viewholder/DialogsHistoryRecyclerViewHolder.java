package com.github.alexvishneuski.vkbestclient.presentation.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.alexvishneuski.vkbestclient.R;

//holder for for incoming and read messages
public class DialogsHistoryRecyclerViewHolder extends RecyclerView.ViewHolder {

    private TextView mMessageBody;

    public DialogsHistoryRecyclerViewHolder(final View pView) {
        super(pView);

        mMessageBody = pView.findViewById(R.id.message_in_history_text_view);
        pView.findViewById(R.id.message_in_history_date_text_view);
    }

    public TextView getMessageBody() {

        return mMessageBody;
    }

}