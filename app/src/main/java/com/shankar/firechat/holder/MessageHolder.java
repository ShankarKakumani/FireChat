package com.shankar.firechat.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.shankar.firechat.R;

public class MessageHolder extends RecyclerView.ViewHolder {

    public TextView text_content;
    public TextView text_time;
    public RelativeLayout lyt_read;
    public MessageHolder(View itemView)
    {

        super(itemView);
        text_content = itemView.findViewById(R.id.text_content);
        text_time = itemView.findViewById(R.id.text_time);
        lyt_read = itemView.findViewById(R.id.lyt_read);
    }


}
