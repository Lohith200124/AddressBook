package com.example.addressbook.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.addressbook.R;
import com.example.addressbook.db.UserInfo;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    Context context;
    ArrayList<UserInfo> userInfo;
  public  RecyclerViewAdapter(Context context, ArrayList<UserInfo> userInfo){
        this.context = context;
        this.userInfo = userInfo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerviewstructurer,parent,false);
        ViewHolder  viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    //holder.textView.setText(userInfo.get(position).getUserName().getFirstName());
    holder.textView.setText(userInfo.get(position).getUserName().toString());
    }

    @Override
    public int getItemCount() {
        return userInfo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.MainFirstName);
    }
}
}
