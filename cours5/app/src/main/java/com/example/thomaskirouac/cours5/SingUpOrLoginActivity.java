package com.example.thomaskirouac.cours5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SingUpOrLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_up_or_login);
        setListener();
    }

    private void setListener(){
    findViewById(R.id.btn_goToLogin).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendToLogin();
        }
    });
    findViewById(R.id.btn_goToSingUp).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sendToSingUp();
        }
    });
    }
    private void sendToSingUp(){
        Intent sendToSingUpOrLogin = new Intent(this, SingUpActivity.class)  ;
        startActivity(sendToSingUpOrLogin);
    }

    private void sendToLogin(){
        Intent sendToSingUpOrLogin = new Intent(this, LoginActivity.class)  ;
        startActivity(sendToSingUpOrLogin);
    }
}
