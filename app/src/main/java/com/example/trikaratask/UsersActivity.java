package com.example.trikaratask;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

public class UsersActivity extends AppCompatActivity {

    TextView textView ;
    Button usersListButton;
    TextView total_pages;
    int total_pages_string,page_number;
    private static final String TAG = "UsersActivity";
    private RequestQueue mQueue;
    private RequestQueue gQueue;
    private List<UserDetails> userDetailsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
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

        recyclerView = findViewById(R.id.recycler_items);
        usersAdapter = new UsersAdapter(userDetailsList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(usersAdapter);



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
                              //  UserDetails userDetails = new UserDetails(String.valueOf(j));
                              //  userDetailsList.add(userDetails);
                               // usersAdapter.notifyDataSetChanged();
                                uriBuilder.appendQueryParameter("page", String.valueOf(j));
                                newResponse(uriBuilder.toString());
                             //   Log.d(TAG, "json Response Method onResponse: "+uriBuilder.toString());
                                uriBuilder.clearQuery();
                            //    mQueue.stop();
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
        Log.d(TAG, "newResponse: Method called ");
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, toString, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try{
                            page_number = Integer.parseInt(response.getString("page"));
                            UserDetails userDetails = new UserDetails("Page Number : "+String.valueOf(page_number));
                            userDetailsList.add(userDetails);
                            usersAdapter.notifyDataSetChanged();
                            Log.d(TAG, "newResponse Method onResponse: response no "+ toString);
                            JSONArray jsonArray =  response.getJSONArray("data");
                            for(int i = 0 ; i<jsonArray.length(); i++){
                                JSONObject data = jsonArray.getJSONObject(i);
                                int id = data.getInt("id");
                                String email = data.getString("email");
                                String firstName = data.getString("first_name");
                                String lastName = data.getString("last_name");
                                String avatar = data.getString("avatar");
                                Log.d(TAG, "onResponse: Email :"+email + " Name: "+ firstName+" "
                                +lastName+ " "+avatar);
                            //  textView.append("ID :" +id +"\n"+ " Name : " +firstName+" "+lastName+
                            //            "\n"+" Email : "+ email + "\n\n");
                                UserDetails userDetails1 = new UserDetails(id,firstName+" "+lastName,email,avatar);
                                userDetailsList.add(userDetails1);
                                usersAdapter.notifyDataSetChanged();


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
