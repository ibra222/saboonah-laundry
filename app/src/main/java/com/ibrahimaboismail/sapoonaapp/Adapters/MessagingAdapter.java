package com.ibrahimaboismail.sapoonaapp.Adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibrahimaboismail.sapoonaapp.Models.Message.Message;
import com.ibrahimaboismail.sapoonaapp.Models.Message.MessageType;
import com.ibrahimaboismail.sapoonaapp.R;
import com.ibrahimaboismail.sapoonaapp.Utils.Session;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by d_200 on 2/13/2018.
 */

public class MessagingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messages;
    private Context context;

    public MessagingAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * check the type of view and return holder
         */
        if (viewType == MessageType.SENT_TEXT) {
            return new SentTextHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sent_message_text, parent, false));
        } else if (viewType == MessageType.SENT_IMAGE) {
            return new SentImageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sent_message_img, parent, false));
        } else if (viewType == MessageType.RECEIVED_TEXT) {
            return new ReceivedTextHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_received_message_text, parent, false));
        } else if (viewType == MessageType.RECEIVED_IMAGE) {
            return new ReceivedImageHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_received_message_img, parent, false));
        }

        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder mHolder, int position) {
        int type = getItemViewType(position);
        Message message = messages.get(position);
        /**
         * check message type and init holder to user it and set data in the right place for every view
         */
        if (type == MessageType.SENT_TEXT) {
            SentTextHolder holder = (SentTextHolder) mHolder;
            holder.tvTime.setText(message.getCreatedAt());
            holder.tvMessageContent.setText(message.getContent());

        } else if (type == MessageType.SENT_IMAGE) {
            SentImageHolder holder = (SentImageHolder) mHolder;
            holder.tvTime.setText(message.getCreatedAt());
            Picasso.with(context).load("http://api.palcode.co/images/adv/"+ message.getContent()).into(holder.imgMsg);


        } else if (type == MessageType.RECEIVED_TEXT) {
            ReceivedTextHolder holder = (ReceivedTextHolder) mHolder;
            holder.tvTime.setText(message.getCreatedAt());
            holder.tvUsername.setText(message.getUserName());
            holder.tvMessageContent.setText(message.getContent());


        } else if (type == MessageType.RECEIVED_IMAGE) {
            ReceivedImageHolder holder = (ReceivedImageHolder) mHolder;
            holder.tvTime.setText(message.getCreatedAt());
            holder.tvUsername.setText(message.getUserName());
            Picasso.with(context).load("http://api.palcode.co/images/adv/"+ message.getContent()).into(holder.imgMsg);

        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    @Override
    public int getItemViewType(int position) {

        int user_id = Session.getInstance().getUser().getId();
        Message message = messages.get(position);

        if (user_id == message.getUserId()){

            if (message.getType().equals(1)){
                return MessageType.SENT_TEXT;
            }else if (message.getType().equals(2)){
                return MessageType.SENT_IMAGE;
            }
        }else {
            if (message.getType().equals(1)){
                return MessageType.RECEIVED_TEXT;
            }else if (message.getType().equals(2)){
                return MessageType.RECEIVED_IMAGE;
            }
        }

        return super.getItemViewType(position);
    }

    // sent message holders

    class SendMessageHoldr extends RecyclerView.ViewHolder{
        TextView tvTime;
        public SendMessageHoldr(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

    // sent message with type text
    class SentTextHolder extends SendMessageHoldr {

        TextView tvMessageContent;

        public SentTextHolder(View itemView) {
            super(itemView);

            tvMessageContent = itemView.findViewById(R.id.tv_message_content);

        }
    }


    // sent message with type image
    class SentImageHolder extends SendMessageHoldr {
        ImageView imgMsg;

        public SentImageHolder(View itemView) {
            super(itemView);
            imgMsg = itemView.findViewById(R.id.img_msg);
        }
    }


    // received message holders
    class ReceivedMessageHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;
        TextView tvTime;

        public ReceivedMessageHolder(View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvTime = itemView.findViewById(R.id.tv_time);

        }
    }

    // received message with type text
    class ReceivedTextHolder extends ReceivedMessageHolder {

        TextView tvMessageContent;

        public ReceivedTextHolder(View itemView) {
            super(itemView);

            tvMessageContent = itemView.findViewById(R.id.tv_message_content);

        }
    }


    // received message with type image

    class ReceivedImageHolder extends ReceivedMessageHolder {
        ImageView imgMsg;

        public ReceivedImageHolder(View itemView) {
            super(itemView);
            imgMsg = itemView.findViewById(R.id.img_msg);
        }
    }

}
