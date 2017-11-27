package com.github.alexvishneuski.vkbestclient.presentation.view.activities;


import android.content.Context;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.alexvishneuski.vkbestclient.presentation.model.MessageViewModelStub;
import com.github.alexvishneuski.vklayouts.R;

import java.util.List;
import java.util.Set;

public class LastMessageListAdapter extends BaseAdapter {

    public final String TAG = this.getClass().getSimpleName();

    private List<MessageViewModelStub> lastMessages;
    private Context context;
    private LayoutInflater inflater;

    private Set<View> viewSet;


    public LastMessageListAdapter(Context context, List<MessageViewModelStub> messages) {
        this.lastMessages = messages;
        this.context = context;
        viewSet = new ArraySet<View>();
        viewHolder = new LastMessageViewHolder();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView");
        LastMessageViewHolder viewHolder;
        if (convertView == null) {
            inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.view_list_last_messages_item, null);
            viewHolder = new LastMessageViewHolder();
            viewHolder.textViewContactUserFullName = ((TextView) convertView.findViewById(R.id.contact_name_text_view));
            viewHolder.textViewCurrentUserFullName = ((TextView) convertView.findViewById(R.id.current_user_avatar_image_view));
            viewHolder.textViewMessageSendingDate = ((TextView) convertView.findViewById(R.id.message_date_text_view));
            viewHolder.textViewMessageBody = ((TextView) convertView.findViewById(R.id.mesage_body_text_view));

        }

        final MessageViewModelStub message = lastMessages.get(position);

        ((TextView) convertView.findViewById(R.id.contact_name_text_view)).setText(message.getContactUserFullName());
        ((TextView) convertView.findViewById(R.id.current_user_avatar_image_view)).setText(message.getCurrentUserFullName());
        ((TextView) convertView.findViewById(R.id.message_date_text_view)).setText(message.getMessageSendingDate());
        ((TextView) convertView.findViewById(R.id.mesage_body_text_view)).setText(message.getMessageBody());

        viewSet.add(convertView);

        Log.d(TAG, "getView: Index: " + position + " : " + convertView + " Size " + viewSet.size());
        return convertView;
    }

    private static class LastMessageViewHolder {

        private TextView textViewContactUserFullName;
        private TextView textViewCurrentUserFullName;
        private TextView textViewMessageSendingDate;
        private TextView textViewMessageBody;

    }

}
