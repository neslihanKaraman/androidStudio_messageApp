package com.example.mobilfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class logInActivity extends AppCompatActivity {
    ImageButton arrow_btn;
    EditText mail_login,password_login;
    FirebaseAuth firebaseAuth;
    Context context=logInActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        arrow_btn = (ImageButton) findViewById(R.id.btn_loginoldu);
        mail_login = (EditText) findViewById(R.id.et_user_login);
        password_login = (EditText) findViewById(R.id.et_password_login);
       firebaseAuth= FirebaseAuth.getInstance();


        arrow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrowClick();
            }
        });
    }
    private void arrowClick() {
        String userMail_login = mail_login.getText().toString();
        String userPassword_login = password_login.getText().toString();

        if (userMail_login.isEmpty()) {
            Toast.makeText(this, "mail boş olamaz", Toast.LENGTH_SHORT).show();
        }

        if (userPassword_login.isEmpty() || userPassword_login.length() < 8) {
            Toast.makeText(this, "geçersiz şifre", Toast.LENGTH_SHORT).show();
        }
        firebaseAuth.signInWithEmailAndPassword(userMail_login,userPassword_login).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(context,"hoşgeldiniz",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(logInActivity.this,MainActivity.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(context, "oturum açma başarısız", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void btn_logintosignup(View view){
        Intent i=new Intent(logInActivity.this,signUpActivity.class);
        startActivity(i);
    }
}