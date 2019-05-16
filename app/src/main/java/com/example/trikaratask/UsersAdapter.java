package com.example.trikaratask;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private List<UserDetails>  userDetailsList;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pageNumber, content;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pageNumber = itemView.findViewById(R.id.pageNumber);
            content = itemView.findViewById(R.id.textContent);
        }
    }

    public UsersAdapter(List<UserDetails> usersList){
        this.userDetailsList = usersList;
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
                userDetails.getEmail()+"");
    }

    @Override
    public int getItemCount() {
        return userDetailsList.size();
    }


}
