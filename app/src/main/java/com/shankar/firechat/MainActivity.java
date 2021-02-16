package com.shankar.firechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.shankar.firechat.holder.MessageHolder;
import com.shankar.firechat.holder.MessageHolder;
import com.shankar.firechat.model.MessageModel;
import com.shankar.firechat.model.MessageModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;

    EditText text_content;
    DatabaseReference mMessages;
    RecyclerView messagesRecyclerView;
    FirebaseRecyclerAdapter<MessageModel, MessageHolder> messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initComponents();
    }

    private void initComponents() {
        text_content = findViewById(R.id.text_content);
        messagesRecyclerView = findViewById(R.id.messagesRecyclerView);
        mMessages = FirebaseDatabase.getInstance().getReference("Chats").child("Shankar").child("messages");
        messagesRecyclerView();
        findViewById(R.id.btn_send).setOnClickListener(v ->
        {
            String message = text_content.getText().toString();
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat time = new SimpleDateFormat("hh:mm aa");
            String timeformat = time.format(calendar.getTime());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
            String strDate = sdf.format(calendar.getTime());


            if(!message.isEmpty())
            {
                String id= UUID.randomUUID().toString();

                MessageModel messageModel = new MessageModel(message,timeformat,strDate,true);

                mMessages.child(id).setValue(messageModel);
                text_content.setText("");

            }
            else
            {
                Toast.makeText(this, "Message is Empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void messagesRecyclerView() {

        messagesRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        mMessages = FirebaseDatabase.getInstance().getReference("Chats").child("Shankar").child("messages");

        mMessages.keepSynced(true);
        Query messagesQuery = mMessages.orderByChild("strDate");

        FirebaseRecyclerOptions<MessageModel> chatOption = new FirebaseRecyclerOptions.Builder<MessageModel>()
                .setQuery(messagesQuery,MessageModel.class)
                .build();

        messageAdapter = new FirebaseRecyclerAdapter<MessageModel, MessageHolder>(chatOption) {
            @Override
            protected void onBindViewHolder(@NonNull MessageHolder messageHolder, int i, @NonNull MessageModel model) {


                messageHolder.text_content.setText(model.getMessage());
                messageHolder.text_time.setText(model.getTime());


//                if(i == messageAdapter.getItemCount()-1)
//                {
//                    messageHolder.lyt_read.setVisibility(View.VISIBLE);
//                }
//                if(i != messageAdapter.getItemCount()-1)
//                {
//                    messageHolder.lyt_read.setVisibility(View.GONE);
//                }


            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();

                messagesRecyclerView.scrollToPosition(messageAdapter.getItemCount()-1);

                Log.i("TAG78","onDataChanged "+messageAdapter.getItemCount());
            }

            @Override
            public int getItemCount() {

                return super.getItemCount();
            }

            @NonNull
            @Override
            public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_chat_facebook_me, parent, false);
                return new MessageHolder(itemView);
            }
        };

        messagesRecyclerView.setAdapter(messageAdapter);
        messageAdapter.startListening();

    }

    public void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
//        Tools.setSystemBarColorInt(this, Color.parseColor("#006ACF"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat_facebook, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}