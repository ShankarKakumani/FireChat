package com.shankar.firechat.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.shankar.firechat.R;

public class ChatHolder extends RecyclerView.ViewHolder {

    public TextView userName;
    public LinearLayout chatLayout;
    public CircularImageView profilePic;

    public ChatHolder(View itemView)
    {

        super(itemView);
        userName = itemView.findViewById(R.id.userName);
        chatLayout = itemView.findViewById(R.id.chatLayout);
        profilePic = itemView.findViewById(R.id.userPic);
    }
}
