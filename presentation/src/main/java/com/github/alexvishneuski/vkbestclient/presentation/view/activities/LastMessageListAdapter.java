package com.github.alexvishneuski.vkbestclient.presentation.view.activities;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.alexvishneuski.vkbestclient.presentation.model.MessageViewModelStub;
import com.github.alexvishneuski.vklayouts.R;

import java.util.List;

public class LastMessageListAdapter extends BaseAdapter {

    public final String TAG = this.getClass().getSimpleName();

    private List<MessageViewModelStub> lastMessages;
    private Context context;
    private LayoutInflater inflater;

    public LastMessageListAdapter(Context context, List<MessageViewModelStub> messages) {
        this.lastMessages = messages;
        this.context = context;
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

        inflater = LayoutInflater.from(this.context);

        final MessageViewModelStub message = lastMessages.get(position);

        convertView = inflater.inflate(R.layout.view_list_last_messages_item, null);
        ((TextView) convertView.findViewById(R.id.contact_name_text_view)).setText(message.getCurrentUserFullName());
        ((TextView) convertView.findViewById(R.id.message_date_text_view)).setText(message.getMessageSendingDate());
        ((TextView) convertView.findViewById(R.id.mesage_body_text_view)).setText(message.getMessageBody());

        Log.d(TAG, "getView: Index: " + position + " : " + convertView);
        return convertView;
    }

}
