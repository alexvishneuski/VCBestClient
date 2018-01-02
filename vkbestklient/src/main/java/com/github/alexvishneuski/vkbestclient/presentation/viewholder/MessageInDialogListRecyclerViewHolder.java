package com.github.alexvishneuski.vkbestclient.presentation.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexvishneuski.vkbestclient.R;
import com.github.alexvishneuski.vkbestclient.presentation.adapters.MessageInDialogListRecyclerAdapter;

//holder for for incoming and read messages
public class MessageInDialogListRecyclerViewHolder extends RecyclerView.ViewHolder {

    private MessageInDialogListRecyclerAdapter mAdapter;

    private TextView mContactUserFullName;
    private ImageView mCurrentUserAvatarPath;
    private ImageView mContactUserAvatarPath;
    private TextView mMessageSendingDate;
    private TextView mMessageBody;

    public MessageInDialogListRecyclerViewHolder(final View pView, MessageInDialogListRecyclerAdapter pAdapter) {
        super(pView);

        mAdapter = pAdapter;

        mContactUserFullName = pView.findViewById(R.id.contact_user_name_text_view);
        mContactUserAvatarPath = pView.findViewById(R.id.contact_user_avatar_image_view);
        mCurrentUserAvatarPath = pView.findViewById(R.id.current_user_avatar_image_view);
        mMessageBody = pView.findViewById(R.id.mesage_body_text_view);
        mMessageSendingDate = pView.findViewById(R.id.message_sending_date_text_view);

        pView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.getListener() != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mAdapter.getListener().onItemClick(itemView, position);
                    }
                }
            }
        });
    }

    public TextView getContactUserFullName() {

        return mContactUserFullName;
    }

    public void setContactUserFullName(TextView pContactUserFullName) {
        mContactUserFullName = pContactUserFullName;
    }

    public ImageView getCurrentUserAvatarPath() {

        return mCurrentUserAvatarPath;
    }

    public void setCurrentUserAvatarPath(ImageView pCurrentUserAvatarPath) {
        mCurrentUserAvatarPath = pCurrentUserAvatarPath;
    }

    public ImageView getContactUserAvatarPath() {

        return mContactUserAvatarPath;
    }

    public void setContactUserAvatarPath(ImageView pContactUserAvatarPath) {
        mContactUserAvatarPath = pContactUserAvatarPath;
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