package com.example.thomaskirouac.cours5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

    private void updateUi(FirebaseUser currentUser){
    if(currentUser==null){
        sendUserToSingUpOrLogin();
    }
    }

    private void sendUserToSingUpOrLogin(){
        Intent sendToSingUpOrLogin = new Intent(this, SingUpOrLoginActivity.class)  ;
        startActivity(sendToSingUpOrLogin);
    }
}
