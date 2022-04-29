package com.example.ecommerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.Account);
       login.setOnClickListener(view -> {
           Intent intent = new Intent(MainActivity.this,loginActivity.class);
        startActivity(intent);

      });
       Button join =findViewById(R.id.main_join_now_btn);
       join.setOnClickListener(view -> {
            Intent intnt = new Intent(MainActivity.this,MenuActivity.class);
            startActivity(intnt);

        });

    }


}