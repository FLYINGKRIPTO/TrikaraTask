package com.example.trikaratask;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UsersActivity extends AppCompatActivity {

    TextView textView ;
    Button usersListButton;
    TextView total_pages;
    int total_pages_string;
    private static final String TAG = "UsersActivity";
    private RequestQueue mQueue;
    private RequestQueue gQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        usersListButton= findViewById(R.id.parse);
        textView = findViewById(R.id.textView);
        mQueue = Volley.newRequestQueue(this);
        gQueue = Volley.newRequestQueue(this);
        total_pages = findViewById(R.id.total_pages);
        usersListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
    }
    private  void jsonParse(){

        Uri baseUri = Uri.parse("https://reqres.in/api/users?");

        final Uri.Builder uriBuilder  = baseUri.buildUpon();

        //uriBuilder.appendQueryParameter("page",)
        final JsonObjectRequest  request = new JsonObjectRequest(Request.Method.GET, String.valueOf(baseUri), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            total_pages_string = Integer.parseInt(response.getString("total_pages"));
                            Log.d(TAG, "onResponse: "+ response.getString("total_pages"));


                            for(int j=1; j<=total_pages_string;j++){
                                uriBuilder.appendQueryParameter("page", String.valueOf(j));
                                Log.d(TAG, "onResponse: "+uriBuilder.toString());
                                newResponse(uriBuilder.toString());
                                uriBuilder.clearQuery();

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);


    }

    private void newResponse(final String toString) {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, toString, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            Log.d(TAG, "onResponse: response no "+ toString);
                            JSONArray jsonArray =  response.getJSONArray("data");
                            for(int i = 0 ; i<jsonArray.length(); i++){
                                JSONObject data = jsonArray.getJSONObject(i);
                                int id = data.getInt("id");
                                String email = data.getString("email");
                                String firstName = data.getString("first_name");
                                String lastName = data.getString("last_name");
                                String avatar = data.getString("avatar");

                                textView.append("ID :" +id +"\n"+ " Name : " +firstName+" "+lastName+
                                        "\n"+" Email : "+ email + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        gQueue.add(request);
    }


}
