package com.shankar.firechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.shankar.firechat.holder.ChatHolder;
import com.shankar.firechat.model.ChatClass;

public class ChatsActivity extends AppCompatActivity {

    FirebaseRecyclerAdapter<ChatClass, ChatHolder> chatAdapter;
    RecyclerView chatsRecycler;
    DatabaseReference mChats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        chatsRecycler = findViewById(R.id.chatsRecycler);
        chatsRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mChats = FirebaseDatabase.getInstance().getReference("Chats");
        mChats.keepSynced(true);
        Query chatsQuery = mChats;

        FirebaseRecyclerOptions<ChatClass> chatOption = new FirebaseRecyclerOptions.Builder<ChatClass>()
                .setQuery(chatsQuery,ChatClass.class)
                .build();

        chatAdapter = new FirebaseRecyclerAdapter<ChatClass, ChatHolder>(chatOption) {
            @Override
            protected void onBindViewHolder(@NonNull ChatHolder chatHolder, int i, @NonNull ChatClass chatClass) {


                chatHolder.userName.setText(chatAdapter.getRef(i).getKey());
                chatHolder.chatLayout.setOnClickListener(v -> {
                    startActivity(new Intent(ChatsActivity.this,MainActivity.class));
                });
            }

            @NonNull
            @Override
            public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_chat, parent, false);
                return new ChatHolder(itemView);
            }
        };

        chatsRecycler.setAdapter(chatAdapter);
        chatAdapter.startListening();
    }
}