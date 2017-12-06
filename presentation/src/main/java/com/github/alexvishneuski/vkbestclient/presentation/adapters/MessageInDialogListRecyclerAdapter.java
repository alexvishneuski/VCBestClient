package com.github.alexvishneuski.vkbestclient.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.alexvishneuski.vkbestclient.imageloader.NOrda;
import com.github.alexvishneuski.vkbestclient.presentation.model.MessageInDialogListViewModel;
import com.github.alexvishneuski.vkbestclient.presentation.viewholder.MessageInDialogListRecyclerViewHolder;
import com.github.alexvishneuski.vklayouts.R;

import java.util.List;

/**
 * Created by alex_vishneuski on 29.11.2017.
 */

public class MessageInDialogListRecyclerAdapter extends RecyclerView.Adapter<MessageInDialogListRecyclerViewHolder> {

    //TODO Handle view types (interface...)

    private List<MessageInDialogListViewModel> mMessageList;

    public MessageInDialogListRecyclerAdapter(final List<MessageInDialogListViewModel> pMessageList) {
        mMessageList = pMessageList;
    }

    @Override
    public MessageInDialogListRecyclerViewHolder onCreateViewHolder(ViewGroup pParent, int pViewType) {

        final View view = LayoutInflater.from(pParent.getContext()).inflate(R.layout.view_message_in_dialog_list_item_outgoing_read, pParent, false);

        return new MessageInDialogListRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessageInDialogListRecyclerViewHolder pHolder, int pPosition) {

        final MessageInDialogListViewModel messageModel = mMessageList.get(pPosition);

        pHolder.getContactUserFullName().setText(messageModel.getContactUser().getUserFullName());
        //FiXME add imageloader

        pHolder.getMessageBody().setText(messageModel.getMessageBody());
        pHolder.getMessageSendingDate().setText(messageModel.getMessageSendingDate());
        NOrda.INSTANCE.load(messageModel.getContactUser().getUserAvatarPath()).into(pHolder.getContactUserAvatarPath());
    }

    @Override
    public int getItemCount() {

        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }


}
