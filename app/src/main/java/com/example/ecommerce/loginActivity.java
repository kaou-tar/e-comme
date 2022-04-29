package com.example.ecommerce;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {
    private EditText emaillog , passwordlog ;
    private boolean passwordvisblty;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();


        emaillog = findViewById(R.id.emaillog);
        passwordlog = findViewById(R.id.passwordlog);

        progressbar = findViewById(R.id.progressbar);

        passwordlog.setOnTouchListener(new View.OnTouchListener() {
           @SuppressLint("ClickableViewAccessibility")
           @Override
               public boolean onTouch(View v, MotionEvent event) {
               final  int right=2;
               if(event.getAction()==MotionEvent.ACTION_UP) {
                   if (event.getRawX() >= passwordlog.getRight() - passwordlog.getCompoundDrawables()[right].getBounds().width()) {
                       int selection = passwordlog.getSelectionEnd();
                       if (passwordvisblty) {
                           passwordlog.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0);
                           passwordlog.setTransformationMethod(PasswordTransformationMethod.getInstance());
                           passwordvisblty = false;
                       } else {
                           passwordlog.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0);
                           passwordlog.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                           passwordvisblty = true;
                       }
                       passwordlog.setSelection(selection);
                       return true;
                   }
               }
               return false;
           }
        }

        );

        Button loginbtn =findViewById(R.id.loginbtn);
       loginbtn.setOnClickListener(view -> {
           userlogin();
        });


        TextView register =findViewById(R.id.register);
        register.setOnClickListener(view -> {
            Intent intent = new Intent(loginActivity.this, registerActivity.class);
            startActivity(intent);

        });


        TextView forgetpassword =findViewById(R.id.forgetpasswod);
       forgetpassword.setOnClickListener(view -> {
           startActivity(new Intent(this, forgetpasswordActivity.class));

        });



    }






    private void userlogin() {
        String Email = emaillog.getText().toString().trim();
        String Password = passwordlog.getText().toString().trim();


        if (Email.isEmpty()) {
            emaillog.setError("Email Address is required");
            emaillog.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            emaillog.setError("Please provide valide Email ");
            emaillog.requestFocus();
            return;
        }
        if (Password.isEmpty()) {
            passwordlog.setError("Password is required");
            passwordlog.requestFocus();
            return;
        }
        if (Password.length() < 6) {
            passwordlog.setError("Min password length should be 6 characters !");
            passwordlog.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                  FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                assert user != null;
                if(user.isEmailVerified()){
                      progressbar.setVisibility(View.GONE);
                      Toast.makeText(loginActivity.this,"User has been registered successfully",Toast.LENGTH_LONG).show();
                      startActivity(new Intent(loginActivity.this, MenuActivity.class));

                  }else{
                      user.sendEmailVerification();
                      Toast.makeText(loginActivity.this, "Failed to Login! Check your Email s", Toast.LENGTH_LONG).show();
                  }

            } else {
                Toast.makeText(loginActivity.this, "Failed to Login! try again !", Toast.LENGTH_LONG).show();
                progressbar.setVisibility(View.GONE);
            }
        });

    }

}
