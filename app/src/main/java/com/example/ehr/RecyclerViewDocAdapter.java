package com.example.ehr;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;
public class RecyclerViewDocAdapter extends RecyclerView.Adapter<RecyclerViewDocAdapter.ViewHolder> {

    Context context;
    List<DocumentUploadInfo> MainImageUploadInfoList;
    DatabaseReference databaseReference;
  public static String contact;
    public RecyclerViewDocAdapter(Context context, List<DocumentUploadInfo> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.docrecycler, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final DocumentUploadInfo UploadInfo = MainImageUploadInfoList.get(position);

        holder.imageNameTextView.setText(UploadInfo.getImageName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(UploadInfo.getImageURL()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        //Loading image from Glide library.
        Glide.with(context).load(UploadInfo.getImageURL()).into(holder.imageView);


        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the clicked item label
                DocumentUploadInfo itemLabel = MainImageUploadInfoList.get(position);

                // Remove the item on remove/button click
                MainImageUploadInfoList. remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,MainImageUploadInfoList.size());

                // Show the removed item label
                String del=UploadInfo.getImageURL();
                databaseReference = FirebaseDatabase.getInstance().getReference(Document.Database_Path);
                Query queryRef = databaseReference.child(contact).orderByChild("imageURL").equalTo(del);

                queryRef.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                        snapshot.getRef().setValue(null);
                        Toast toast = Toast.makeText(context,"Document Removed", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



                Query queryRef1 = databaseReference.child("menu1").orderByChild("menuCat").equalTo(del);

                queryRef1.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                        snapshot.getRef().setValue(null);

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




            }
        });
    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView imageNameTextView;
        public ImageButton mRemoveButton;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    String item=imageNameTextView.getText().toString();
                    // item clicked
                    //   Intent intent = new Intent(context, DisplayMenu.class);
                    //   intent.putExtra("message", item);
                    //   context.startActivity(intent);

                    //   Toast toast = Toast.makeText(context,item, Toast.LENGTH_SHORT);
                    // toast.show();
                }
            });
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mRemoveButton = (ImageButton) itemView.findViewById(R.id.ib_remove);
            imageNameTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView);
        }
    }
}