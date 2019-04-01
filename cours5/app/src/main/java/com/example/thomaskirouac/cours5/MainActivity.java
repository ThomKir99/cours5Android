package com.example.thomaskirouac.cours5;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thomaskirouac.cours5.notification.NotificationService;
import com.example.thomaskirouac.cours5.notification.model.ImportantMessageModel;
import com.example.thomaskirouac.cours5.notification.model.MessageModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser currentUser;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        database = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        setListener();
    }

    private void setListener() {
        findViewById(R.id.button_singOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singOutUser();
            }
        });
        findViewById(R.id.button_sendMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             sendMessage();
            }
        });
        findViewById(R.id.button_importantMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendImportantMessage();
            }
        });
    }

    private void sendImportantMessage() {
        EditText editTextMessage = findViewById(R.id.editText_message);
        ImportantMessageModel messageModel = new ImportantMessageModel(editTextMessage.getText().toString(),auth.getCurrentUser().getEmail());
        database.collection("NotificationImportant").add(messageModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getApplicationContext(), "Important Message sent" , Toast.LENGTH_LONG).show();
            }
        });
    }

    private void sendMessage() {
        EditText editTextMessage = findViewById(R.id.editText_message);
        MessageModel messageModel = new MessageModel(editTextMessage.getText().toString(),auth.getCurrentUser().getEmail());
        database.collection("Notification").add(messageModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getApplicationContext(), "Message sent" , Toast.LENGTH_LONG).show();
            }
        });
    }

    private void singOutUser() {
        auth.signOut();
        sendUserToSingUpOrLogin();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUi(currentUser);
    }

    private void startService(){
        Intent serviceIntent = new Intent(this, NotificationService.class);
        ContextCompat.startForegroundService(this,serviceIntent);
    }

    private void updateUi(FirebaseUser currentUser){
    if(currentUser==null){
        sendUserToSingUpOrLogin();
    }else {
        startService();
    }
    }

    private void sendUserToSingUpOrLogin(){
        Intent sendToSingUpOrLogin = new Intent(this, SingUpOrLoginActivity.class)  ;
        startActivity(sendToSingUpOrLogin);
    }
}
