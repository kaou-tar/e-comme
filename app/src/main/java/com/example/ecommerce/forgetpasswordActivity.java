package com.example.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText addresEmail;
    private ProgressBar progressbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);

        mAuth = FirebaseAuth.getInstance();


         addresEmail =  findViewById(R.id.addresEmail);

        progressbar = findViewById(R.id.progressbar);

        Button resetpassword = findViewById(R.id.resetpassword);
        resetpassword.setOnClickListener(this::onClick);

    }
    private void onClick(View view) {
        restePassowrd();
    }

    private void restePassowrd() {
        String email = addresEmail.getText().toString().trim();
        if (email.isEmpty()) {
            addresEmail.setError("Email Address is required");
            addresEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            addresEmail.setError("Please provide valide Email ");
            addresEmail.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);
        Task<Void> voidTask = mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(forgetpasswordActivity.this, "Check your Email to rest your Password !", Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(forgetpasswordActivity.this, "Try again !", Toast.LENGTH_LONG).show();


                }

            }
        });
    }


}