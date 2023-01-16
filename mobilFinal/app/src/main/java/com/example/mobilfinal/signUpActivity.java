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


public class signUpActivity extends AppCompatActivity {
    ImageButton done;
    EditText mail_sign,password_sign;
   FirebaseAuth firebaseAuth;
    Context context=signUpActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        done = (ImageButton) findViewById(R.id.btn_signoldu);
        mail_sign = (EditText) findViewById(R.id.et_user_sign);
        password_sign = (EditText) findViewById(R.id.et_password_sign);
        firebaseAuth=FirebaseAuth.getInstance();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doneClick();
            }
        });
    }
        private void doneClick(){
            String userMail_sign=mail_sign.getText().toString();
            String userPassword_sign=password_sign.getText().toString();

            if(userMail_sign.isEmpty()){
                Toast.makeText(this, "mail boş olamaz", Toast.LENGTH_SHORT).show();
            }

            if(userPassword_sign.isEmpty()||userPassword_sign.length()<8){
                Toast.makeText(this, "geçersiz şifre", Toast.LENGTH_SHORT).show();
            }
            firebaseAuth.createUserWithEmailAndPassword(userMail_sign,userPassword_sign).
                    addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(context, "kayıt başarılı", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(context, "işlem başarısız", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    public void btn_signuptologin(View view){
        Intent i=new Intent(signUpActivity.this,logInActivity.class);
        startActivity(i);
    }

}