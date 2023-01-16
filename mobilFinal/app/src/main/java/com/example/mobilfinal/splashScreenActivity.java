package com.example.mobilfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class splashScreenActivity extends AppCompatActivity {
       FirebaseAuth firebaseAuth;
        Context context=splashScreenActivity.this;
        Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
       firebaseAuth=FirebaseAuth.getInstance();

        splashThread();
        if(firebaseAuth.getCurrentUser()!=null){
            Toast.makeText(context,"oturum daha önce açılmış, ana sayfaya yönlendiriliyorsunuz",Toast.LENGTH_SHORT).show();
            thread.start();
        }

    }

    public void btn_signClick(View view){
        Intent i=new Intent(splashScreenActivity.this,signUpActivity.class);
        startActivity(i);

    }
    public void btn_loginClick(View view){
        Intent i=new Intent(splashScreenActivity.this,logInActivity.class);
        startActivity(i);

    }
    public void splashThread(){
         thread=new Thread(){
            @Override
            public void run(){
                try{
                    sleep(2000);
                    startActivity(new Intent(splashScreenActivity.this,MainActivity.class));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
    }
}