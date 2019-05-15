package com.example.trikaratask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText email,password;
    Button register;
    TextView responseText;
    RequestQueue requestQueue;
    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        requestQueue = Volley.newRequestQueue(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        responseText = findViewById(R.id.responseText);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://reqres.in/api/register";
                registerPostRequest(url);
            }
        });
    }

    private void registerPostRequest(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                responseText.setText(response);
                if(response.contains("token")&&response.contains("id")){
                    Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();

                    Intent in = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(in);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error.toString());
                Toast.makeText(RegisterActivity.this,"Wrong Email or Password",Toast.LENGTH_SHORT).show();

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
