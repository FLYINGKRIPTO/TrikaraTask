package com.example.trikaratask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   Button usersActivity,login,register;
   EditText email,password;
    private static final String TAG = "MainActivity";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.login);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);
        requestQueue = Volley.newRequestQueue(this);
        password = findViewById(R.id.password);
        usersActivity = findViewById(R.id.usersActivityButton);

        usersActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,UsersActivity.class);
                startActivity(in);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(in);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "https://reqres.in/api/login";
                loginPostRequest(url);
    }
        });
    }

    private void loginPostRequest(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                 if(response.contains("token")){
                     Intent in = new Intent(MainActivity.this,ScreenActivity.class);
                     startActivity(in);
                 }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.toString());
                Toast.makeText(MainActivity.this,"Wrong Email or Password",Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override protected Map<String , String > getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("email",email.getText().toString());
                params.put("password",password.getText().toString());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }



    }

