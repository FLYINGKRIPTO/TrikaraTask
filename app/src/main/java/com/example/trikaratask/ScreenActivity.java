package com.example.trikaratask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScreenActivity extends AppCompatActivity {

    Button usersList,create,update,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        usersList = findViewById(R.id.usersList);
        create = findViewById(R.id.create);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        usersList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ScreenActivity.this,UsersActivity.class);
                startActivity(in);
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ScreenActivity.this,CreateActivity.class);
                startActivity(in);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ScreenActivity.this,CreateActivity.class);
                startActivity(in);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ScreenActivity.this,CreateActivity.class);
                startActivity(in);
            }
        });
    }
}
