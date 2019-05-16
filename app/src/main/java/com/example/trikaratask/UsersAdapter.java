package com.example.trikaratask;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.android.volley.VolleyLog.TAG;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {
    private Context mContext;
    private List<UserDetails>  userDetailsList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pageNumber, content;
        public ImageView avatar;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pageNumber = itemView.findViewById(R.id.pageNumber);
            content = itemView.findViewById(R.id.textContent);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }

    public UsersAdapter(List<UserDetails> usersList,Context mContext){
        this.userDetailsList = usersList;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listrow,viewGroup,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        UserDetails userDetails = userDetailsList.get(i);
        myViewHolder.pageNumber.setText(userDetails.getPageNumber());
        myViewHolder.content.setText(userDetails.getId()+ "\n" +userDetails.getName() +"\n"+
                userDetails.getEmail());

        if(userDetails.getAvatar()!= null){
            Log.d( TAG,"onBindViewHolder: avatar link  "+userDetails.getAvatar());
            Uri uri = Uri.parse(userDetails.getAvatar());
            Log.d(TAG, "onBindViewHolder: uri "+ uri);
            Glide.with(mContext).load(uri).into(myViewHolder.avatar);
            Picasso.get().load(uri).into(myViewHolder.avatar);


        }
        else
        {

            myViewHolder.avatar.setImageDrawable(null);
        }

    }

    @Override
    public int getItemCount() {
        return userDetailsList.size();
    }


}
