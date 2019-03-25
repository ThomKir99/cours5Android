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

public class SingUpActivity extends AppCompatActivity {
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        auth = FirebaseAuth.getInstance();
        setListener();
    }

    private void setListener() {
        findViewById(R.id.btn_singUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singUpUser();
            }
        });
    }

    private void singUpUser() {
        EditText userEmail = findViewById(R.id.editText_email);
        EditText password = findViewById(R.id.editText_password);
        EditText confirmPassword = findViewById(R.id.editText_passwordConfirm);

       /* if(password.getText().toString() != confirmPassword.getText().toString()){
            Toast.makeText(this,"Password dont match",Toast.LENGTH_SHORT).show();
            return;
        }*/
        auth.createUserWithEmailAndPassword(userEmail.getText().toString(),password.getText().toString())
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
