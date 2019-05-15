package com.example.trikaratask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CreateActivity extends AppCompatActivity {

    EditText name,job;
    Button create,update,delete;
    TextView responseText;
    RequestQueue requestQueue,requestQueue2,requestQueue3;
    private static final String TAG = "CreateActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        name = findViewById(R.id.name);
        job = findViewById(R.id.job);
        create = findViewById(R.id.create);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        responseText = findViewById(R.id.response);
       requestQueue = Volley.newRequestQueue(this);
       requestQueue2 = Volley.newRequestQueue(this);
       requestQueue3 = Volley.newRequestQueue(this);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://reqres.in/api/users";
                createPostRequest(url);

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://reqres.in/api/users/2";
                updatePutRequest(url);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://reqres.in/api/users/2";
                deleteRequest(url);
            }
        });

    }

    private void deleteRequest(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                responseText.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue3.add(stringRequest);
    }


    private void updatePutRequest(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                responseText.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override protected Map<String , String > getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("name",name.getText().toString());
                params.put("job",job.getText().toString());

                return params;
            }
        };
        requestQueue2.add(stringRequest);

    }

    private void createPostRequest(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: " + response);
                responseText.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override protected Map<String , String > getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("name",name.getText().toString());
                params.put("job",job.getText().toString());

                return params;
            }
        };
        requestQueue.add(stringRequest);

}
}
