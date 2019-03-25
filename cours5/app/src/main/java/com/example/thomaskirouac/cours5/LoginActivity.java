package com.example.thomaskirouac.cours5;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        auth = FirebaseAuth.getInstance();
        setListener();
    }

    private void setListener() {
        findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        EditText userEmail = findViewById(R.id.editText_loginUsername);
        EditText password = findViewById(R.id.editText_loginrPassword);



        auth.signInWithEmailAndPassword(userEmail.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            sendUserToMainActivity();
                        }else{
                            Toast.makeText(getApplicationContext(),"Failed to sing up",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void sendUserToMainActivity() {
        Intent sendToSingUpOrLogin = new Intent(this, MainActivity.class)  ;
        startActivity(sendToSingUpOrLogin);
    }
    }

