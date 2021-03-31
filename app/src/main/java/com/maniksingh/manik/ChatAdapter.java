package com.maniksingh.manik;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.maniksingh.manik.MainActivity.receiver_code;
import static com.maniksingh.manik.MainActivity.sender_code;

public class ChatAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<TextPOJO> mMessageList;

    public ChatAdapter(Context context, List<TextPOJO> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        TextPOJO message = (TextPOJO) mMessageList.get(position);

        if (message.getType() == sender_code) {
            // If the current user is the sender of the message
            return sender_code;
        } else {
            // If some other user sent the message
            return receiver_code;
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == sender_code) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chat_sender, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == receiver_code) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_chat_receiver, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextPOJO message = (TextPOJO) mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case sender_code:
                ((SentMessageHolder) holder).bind(message);
                break;
            case receiver_code:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.tv_text_sender);
        }

        void bind(TextPOJO message) {
            messageText.setText(message.getMessage());

            // Format the stored timestamp into a readable String using method.
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText;

        ReceivedMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.tv_text_receiver);
        }

        void bind(TextPOJO message) {
            messageText.setText(message.getMessage());

        }
    }


}
