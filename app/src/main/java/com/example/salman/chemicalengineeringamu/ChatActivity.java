package com.example.salman.chemicalengineeringamu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//import com.firebase.ui.common.ChangeEventType;
//import com.firebase.ui.database.FirebaseListAdapter;
//import com.firebase.ui.database.FirebaseListOptions;

public class ChatActivity extends AppCompatActivity {

    private static final String TAG = "ChatActivity";
    private static final String REQUIRED = "Required";

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder> adapter;
    private DatabaseReference mMessageReference;
    private RecyclerView rcvListMessage;
    private ArrayList<ChatMessage> messageList;
    private ChildEventListener mMessageListener;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ChatActivity.this,
                                    "You have been signed out.",
                                    Toast.LENGTH_LONG)
                                    .show();

                            // Close activity
                            finish();
                        }
                    });
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,
                        "Successfully signed in. Welcome!",
                        Toast.LENGTH_LONG)
                        .show();
                displayChatMessages();
            } else {
                Toast.makeText(this,
                        "We couldn't sign you in. Please try again later.",
                        Toast.LENGTH_LONG)
                        .show();

                // Close the app
                finish();
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat );

        FloatingActionButton fab =
                (FloatingActionButton) findViewById(R.id.fab);

        mMessageReference = FirebaseDatabase.getInstance().getReference();
        rcvListMessage = (RecyclerView) findViewById(R.id.rcv_list_message);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(false);
        rcvListMessage.setHasFixedSize(true);
        rcvListMessage.setLayoutManager(layoutManager);


        messageList = new ArrayList<>();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText) findViewById(R.id.input);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance()
                        .getReference()
                        .push()
                        .setValue(new ChatMessage(input.getText().toString(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName())
                        );
//                FirebaseDatabase.getInstance()
//                        .getReference().child("chats").setValue(new ChatMessage(input.getText().toString(),
//                        FirebaseAuth.getInstance()
//                                .getCurrentUser()
//                                .getDisplayName())
//                );


                // Clear the input
                input.setText("");
            }
        });


        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .build(),
                    SIGN_IN_REQUEST_CODE
            );
        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this, "Welcome " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), Toast.LENGTH_LONG).show();

            // Load chat room contents
            displayChatMessages();
        }


    }


    private void displayChatMessages() {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setReverseLayout(false);
//        rcvListMessage.setHasFixedSize(true);
//        rcvListMessage.setLayoutManager(layoutManager);


        Query query = mMessageReference.limitToLast(100);

        adapter = new FirebaseRecyclerAdapter<ChatMessage, MessageViewHolder>(
                ChatMessage.class, R.layout.item_message, MessageViewHolder.class, query) {
            @Override
            protected void populateViewHolder(MessageViewHolder viewHolder, ChatMessage model, int position) {
                viewHolder.authorView.setText(model.getMessageUser());
                String pattern = "dd MMM yyyy HH:mm:ss";
                viewHolder.timeView.setText(Epoch2DateString(""+model.getMessageTime(),pattern));
                viewHolder.bodyView.setText(""+model.getMessageText());
                System.out.println(" position " +position + "   " + model);
            }

            @Override
            public void onChildChanged(EventType type, DataSnapshot snapshot, int index, int oldIndex) {
                super.onChildChanged(type, snapshot, index, oldIndex);
                rcvListMessage.scrollToPosition(index);
            }
        };

        rcvListMessage.setAdapter(adapter);



    }
    public static String Epoch2DateString(String epochSeconds, String formatString) {
        Date updatedate = new Date(Long.parseLong(epochSeconds));
        SimpleDateFormat format = new SimpleDateFormat(formatString);
        return format.format(updatedate);
    }

    @Override
    protected void onStart() {
        super.onStart();

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                // A new message has been added
                // onChildAdded() will be called for each node at the first time
                ChatMessage message = dataSnapshot.getValue(ChatMessage.class);
                messageList.add(message);

                Log.e(TAG, "onChildAdded:" + message.getMessageUser());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.e(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A message has changed
                ChatMessage message = dataSnapshot.getValue(ChatMessage.class);
                Toast.makeText(ChatActivity.this, "onChildChanged: " + message.getMessageUser(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.e(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A message has been removed
                ChatMessage message = dataSnapshot.getValue(ChatMessage.class);
                Toast.makeText(ChatActivity.this, "onChildRemoved: " + message.getMessageUser(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.e(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A message has changed position
                ChatMessage message = dataSnapshot.getValue(ChatMessage.class);
                Toast.makeText(ChatActivity.this, "onChildMoved: " + message.getMessageUser(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "postMessages:onCancelled", databaseError.toException());
                Toast.makeText(ChatActivity.this, "Failed to load Message.", Toast.LENGTH_SHORT).show();
            }
        };

        mMessageReference.addChildEventListener(childEventListener);

        // copy for removing at onStop()
        mMessageListener = childEventListener;

    }
    @Override
    protected void onStop() {
        super.onStop();

        if (mMessageListener != null) {
            mMessageReference.removeEventListener(mMessageListener);
        }

        for (ChatMessage message: messageList) {
            Log.e(TAG, "listItem: " + message.getMessageUser());
        }
    }
}
