package com.example.addressbook.Adapter;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.addressbook.Activity.ViewAddressBook;
import com.example.addressbook.Entity.Address;
import com.example.addressbook.Entity.Email;
import com.example.addressbook.Entity.PhoneNumber;
import com.example.addressbook.R;
import com.example.addressbook.db.DataBaseHelper;
import com.example.addressbook.db.UserInfo;

import java.io.Serializable;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

/**
 * recyclerview adapter
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    Context context;
   public List<UserInfo> userInfo = new ArrayList<UserInfo>() ;
   private List<UserInfo> searchUserinfo = new ArrayList<>();
    DataBaseHelper db = DataBaseHelper.getDb(context);
    public static final int PERMISSION_REQUEST_CODE =120;
    public ActivityResultLauncher<String> activityResultLauncher;
  public  RecyclerViewAdapter(Context context, List<UserInfo> userInfo){
        this.context = context;
        this.userInfo = userInfo;
        searchUserinfo = new ArrayList<>(userInfo);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter_structurer,parent,false);
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
         List<PhoneNumber> phoneNumberList = db.dao().getPhoneList(userInfo.get(position).getUserName().getId());
   List<Address> addressList = db.dao().getAddress(userInfo.get(position).getUserName().getId());
   List<Email> emailList = db.dao().getEmail(userInfo.get(position).getUserName().getId());
    List<UserInfo> userInfoList = db.dao().getUserInfo();
    holder.textView.setText((userInfo.get(position).getUserName().getFirstName()).toUpperCase());
    holder.PhoneNumber.setText(phoneNumberList.get(0).getPhonNo());
    holder.Email.setText(emailList.get(0).getEmail());
    holder.Address.setText(addressList.get(0).getLine1()+
            addressList.get(0).getCity()+addressList.get(0).getCountry());
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent =new Intent(context, ViewAddressBook.class);
            //Bundle bundle = new Bundle();
           // bundle.putSerializable("UserName",  userInfo.get(holder.getPosition()).getUserName());
          // bundle.putSerializable("ListOfAddress",(Serializable)userInfo.get(position).getList());
           /* bundle.putSerializable("ListOfEmails",(Serializable)userInfo.get(position).getEmailList());
            bundle.putSerializable("ListOfPhoneNo",(Serializable) userInfo.get(position).getPhoneList());*/
            //intent.putExtra("BUNDLE",bundle);
            intent.putExtra("UserInfoList",(Serializable)userInfoList);
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
                    db.dao().delUserName1ById(userInfo.get(position).getUserName().getId());
                    try {
                        db.dao().delImageById((int) userInfo.get(position).getImage().getId());
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                    for(int j=0;j<=userInfo.get(position).getEmailList().size()-1;j++) {
                        db.dao().delEmailbyId(userInfo.get(position).getUserName().getId());
                    }
                    for(int j=0;j<=userInfo.get(position).getPhoneList().size()-1;j++) {
                        db.dao().delPhoneById(userInfo.get(position).getUserName().getId());
                    }
                    for(int j=0;j<=userInfo.get(position).getList().size()-1;j++) {
                        db.dao().delAddressById(userInfo.get(position).getUserName().getId());
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
    /*Uri uri = Uri.parse(userInfo.get(position).getImage().getUri());*/
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
              /* activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),
                       *//*new ActivityResultCallback<>(){
                           @Override
                           public void onActivityResult(Object result) {
                               if(result.equals(true)){
                                   holder.imageView.setImageURI(uri);
                               }else{

                               }
                           }*//*
                   isGranted ->{
                   if(isGranted){
                       holder.imageView.setImageURI(uri);
                   }else{

                   }
               });*/
              /* (new ActivityResultContracts.RequestPermission(),isGranted -> {
                   if( String.valueOf(isGranted)){
                       holder.imageView.setImageURI(uri);
                   }else{
                       holder.imageView.setImageURI(uri);
                   }
               });*/
                int result = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);

                if (result==PackageManager.PERMISSION_GRANTED) {
                    try {

                      // holder.imageView.setImageURI(uri);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                } else {
                            //activityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
                   // ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},  1);
                }
            }
            else { //permission is automatically granted on sdk<23 upon installation

                 // holder.imageView.setImageURI(uri);

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
        TextView textView,PhoneNumber,Email,Address;
        LinearLayout linearLayout;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.linearLayout = itemView.findViewById(R.id.linearLayout1);
            this.textView = itemView.findViewById(R.id.MainFirstName);
            this.PhoneNumber = itemView.findViewById(R.id.PhoneNumber);
            this.Address = itemView.findViewById(R.id.Address);
            this.Email = itemView.findViewById(R.id.MainEmailName);
            this.imageView = itemView.findViewById(R.id.imageOfPerson);
    }
}

}
