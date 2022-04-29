package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class registerActivity<mAuth> extends AppCompatActivity implements View.OnClickListener {
    private EditText name_text , address_text ,  email_text , password ;
    private Button   registerbtn;
    private ProgressBar progressbar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();



        registerbtn = (Button) findViewById(R.id.register_btn);
        registerbtn.setOnClickListener((View.OnClickListener) this);

        name_text = (EditText) findViewById(R.id.name_text);
        address_text = (EditText) findViewById(R.id.address_text);
        email_text = (EditText) findViewById(R.id.email_text);
        password = (EditText) findViewById(R.id.password);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
    }
    @Override
    public void onClick(View v){
        if (v.getId() == R.id.register_btn) {
            register();
        }
    }

    private void register() {
        String fullname = name_text.getText().toString().trim();
        String adress = address_text.getText().toString().trim();
        String email = email_text.getText().toString().trim();
        String Password = password.getText().toString().trim();

        if(fullname.isEmpty()){
            name_text.setError("Full Name is required");
            name_text.requestFocus();
            return;
        }
        if(adress.isEmpty()){
            address_text.setError("Address is required");
            address_text.requestFocus();
            return;
        }
        if(email.isEmpty()){
           email_text.setError("Email Address is required");
            email_text.requestFocus();
            return;
        }


        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            email_text.setError("Please provide valide Email ");
            email_text.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(Password.length() < 6){
            password.setError("Min password length should be 6 characters !");
            password.requestFocus();
            return;
        }

        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,Password)
               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           User User = new User(fullname , adress , email);
                           FirebaseDatabase.getInstance().getReference("Users")
                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                   .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){

                                       Toast.makeText(registerActivity.this,"User has been registered successfully",Toast.LENGTH_LONG).show();
                                       Intent intent = new Intent(registerActivity.this, MenuActivity.class);
                                       startActivity(intent);

                                   }else{
                                       Toast.makeText(registerActivity.this,"Failed to register! Try again!",Toast.LENGTH_LONG).show();
                                   }
                                   progressbar.setVisibility(View.GONE);
                               }
                           });
                       }else{
                           Toast.makeText(registerActivity.this,"Failed to register! verify your information!",Toast.LENGTH_LONG).show();
                           progressbar.setVisibility(View.GONE);
                       }
                   }
               });




    }
}