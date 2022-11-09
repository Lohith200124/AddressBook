package com.example.addressbook.Adapter;

import static androidx.core.content.ContextCompat.checkSelfPermission;
import static androidx.core.content.ContextCompat.getDrawable;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.addressbook.Activity.HomePageActivity;
import com.example.addressbook.Activity.UpdateAddressBookActivity;
import com.example.addressbook.Entity.Image;
import com.example.addressbook.Fragments.HomeFragment;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.example.addressbook.db.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * recyclerview adapter
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    Context context;
   public List<UserInfo> userInfo = new ArrayList<UserInfo>() ;
   private List<UserInfo> searchUserinfo = new ArrayList<>();
    DataBaseHelper db = DataBaseHelper.getDb(context);
    public static final int PERMISSION_REQUEST_CODE =120;
  public  RecyclerViewAdapter(Context context, List<UserInfo> userInfo){
        this.context = context;
        this.userInfo = userInfo;
        searchUserinfo = new ArrayList<>(userInfo);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerviewstructurer,parent,false);
        ViewHolder  viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    /**
     * intent passing and binding of recyclerView
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    //holder.textView.setText(userInfo.get(position).getUserName().getFirstName());
    holder.textView.setText(userInfo.get(position).getUserName().getFirstName());
    holder.LastName.setText(userInfo.get(position).getUserName().getLastName());
    //holder.Email.setText(userInfo.get(position).getEmailList().get(position).getEmail());
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(context, UpdateAddressBookActivity.class);
            //Bundle bundle = new Bundle();
           // bundle.putSerializable("UserName",  userInfo.get(holder.getPosition()).getUserName());
          // bundle.putSerializable("ListOfAddress",(Serializable)userInfo.get(position).getList());
           /* bundle.putSerializable("ListOfEmails",(Serializable)userInfo.get(position).getEmailList());
            bundle.putSerializable("ListOfPhoneNo",(Serializable) userInfo.get(position).getPhoneList());*/
            //intent.putExtra("BUNDLE",bundle);
            intent.putExtra("UserName",  userInfo.get(position).getUserName());
            intent.putExtra("ListOfAddress",(Serializable) userInfo.get(position).getList());
            intent.putExtra("ListOfEmails",(Serializable) userInfo.get(position).getEmailList());
            intent.putExtra("ListOfPhoneNo",(Serializable) userInfo.get(position).getPhoneList());
            intent.putExtra("position",position);
            intent.putExtra("Images", (Serializable) userInfo.get(position).getImage());
            /*intent.putExtra("ListOfEmails",(Serializable)userInfo.get(position).getEmailList());
            intent.putExtra("ListOfPhoneNo",(Serializable) userInfo.get(position).getPhoneList());*/
           // intent.putExtras(bundle);
            context.startActivity(intent);
        }
    });
    holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            AlertDialog.Builder alertDailog = new AlertDialog.Builder(context);
            alertDailog.setTitle("Delete");
            alertDailog.setMessage("do you really want to delete");
            alertDailog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    db.dao().delUserName1ById(userInfo.get(position-1).getUserName().getId());
                    try {
                        db.dao().delImageById((int) userInfo.get(position).getImage().getId());
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    for(int j=0;j<=userInfo.get(position).getEmailList().size()-1;j++) {
                        db.dao().delEmailbyId(userInfo.get(position).getEmailList().get(j).getEmailId());
                    }
                    for(int j=0;j<=userInfo.get(position).getPhoneList().size()-1;j++) {
                        db.dao().delPhoneById(userInfo.get(position).getPhoneList().get(j).getPhoneNoId());
                    }
                    for(int j=0;j<=userInfo.get(position).getList().size()-1;j++) {
                        db.dao().delAddressById(userInfo.get(position).getList().get(j).getAddress_id());
                    }
                    userInfo.remove(position);
                    notifyItemRemoved(position);
                }
            });
            alertDailog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            alertDailog.show();

            return false;
        }
    });
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int result = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);
                if (result==PackageManager.PERMISSION_GRANTED) {
                    try {
                        holder.imageView.setImageURI(Uri.parse(userInfo.get(position).getImage().getUri()));
                    }
                    catch(NullPointerException nullPointerException){
                        nullPointerException.printStackTrace();
                    }
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                }
            }
            else { //permission is automatically granted on sdk<23 upon installation

                    holder.imageView.setImageURI(Uri.parse(userInfo.get(position).getImage().getUri()));

            }



    }

    @Override
    public int getItemCount() {
        return userInfo.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private  Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<UserInfo> userInfoList = new ArrayList<>();
            if(charSequence == null||charSequence == ""){
                userInfoList.addAll(searchUserinfo);
            }
            else{
                String pattrenOfString = charSequence.toString().toLowerCase().trim();
                for (UserInfo user:userInfoList)
                {
                 if((user.getUserName().getFirstName()).contains(pattrenOfString)){
                     userInfoList.add(user);
                 }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = userInfoList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            searchUserinfo.clear();
            searchUserinfo.addAll((List<UserInfo>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView,LastName,Email;
        LinearLayout linearLayout;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.linearLayout = itemView.findViewById(R.id.linearLayout1);
            this.textView = itemView.findViewById(R.id.MainFirstName);
            this.LastName = itemView.findViewById(R.id.MainLastName);
            this.Email = itemView.findViewById(R.id.MainEmailName);
            this.imageView = itemView.findViewById(R.id.imageOfPerson);
    }
}

}
