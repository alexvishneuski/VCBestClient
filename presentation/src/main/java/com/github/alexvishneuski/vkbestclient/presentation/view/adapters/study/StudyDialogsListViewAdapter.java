package com.github.alexvishneuski.vkbestclient.presentation.view.adapters.study;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.alexvishneuski.vkbestclient.presentation.model.study.StudyMessageViewModelStub;
import com.github.alexvishneuski.vklayouts.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudyDialogsListViewAdapter extends BaseAdapter {

    public final String TAG = this.getClass().getSimpleName();

    private List<StudyMessageViewModelStub> lastMessages;
    private Context context;
    private LayoutInflater inflater;
    private Set<View> viewSet;


    public StudyDialogsListViewAdapter(Context context, List<StudyMessageViewModelStub> messages) {
        this.lastMessages = messages;
        this.context = context;
        viewSet = new HashSet<View>();
        LastMessageViewHolder viewHolder = new LastMessageViewHolder();
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount");
        return lastMessages.size();
    }

    @Override
    public Object getItem(int position) {
        Log.d(TAG, "getItem");
        return lastMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId");
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Log.d(TAG, "getView");

        LastMessageViewHolder viewHolder;

        if (view == null) {
            inflater = LayoutInflater.from(this.context);
            view = inflater.inflate(R.layout.view_list_dialogs_item, null);

            viewHolder = new LastMessageViewHolder();
            viewHolder.textViewContactUserFullName = ((TextView) view.findViewById(R.id.contact_user_name_text_view));
            viewHolder.textViewMessageSendingDate = ((TextView) view.findViewById(R.id.message_date_text_view));
            viewHolder.textViewMessageBody = ((TextView) view.findViewById(R.id.mesage_body_text_view));
            viewHolder.imageViewConactAvatar = ((ImageView) view.findViewById(R.id.contact_user_avatar_image_view));

            view.setTag(viewHolder);
        } else {
            viewHolder = (LastMessageViewHolder) view.getTag();
        }

        final StudyMessageViewModelStub message = lastMessages.get(position);

        viewHolder.textViewContactUserFullName.setText(message.getContactUserFullName());
        viewHolder.textViewMessageSendingDate.setText(message.getMessageSendingDate());
        viewHolder.textViewMessageBody.setText(message.getMessageBody());
        viewHolder.imageViewConactAvatar.setImageResource(message.getContactUserAvatarId());
        viewSet.add(view);

        Log.d(TAG, "getView: Index: " + position + " : " + view + " Size " + viewSet.size());

        return view;
    }

    private static class LastMessageViewHolder {

        private TextView textViewContactUserFullName;
        private TextView textViewMessageSendingDate;
        private TextView textViewMessageBody;
        private ImageView imageViewConactAvatar;
    }
}
