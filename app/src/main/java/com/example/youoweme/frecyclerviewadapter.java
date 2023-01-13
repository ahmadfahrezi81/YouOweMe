package com.example.youoweme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class frecyclerviewadapter extends RecyclerView.Adapter<frecyclerviewadapter.myviewholder> {
    private final frecyclerviewinterface frecyclerviewinterface;

    Context context;
    ArrayList<friendmodel>friendmodels;

    public frecyclerviewadapter(Context context, ArrayList<friendmodel> friendmodels, frecyclerviewinterface frecyclerviewinterface){
        this.context = context;
        this.friendmodels = friendmodels;
        this.frecyclerviewinterface = frecyclerviewinterface;
    }

    @NonNull
    @Override
    public frecyclerviewadapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //this is where you inflate the layout (giving a look to rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new frecyclerviewadapter.myviewholder(view, frecyclerviewinterface);
    }

    @Override
    public void onBindViewHolder(@NonNull frecyclerviewadapter.myviewholder holder, int position) {
        //update values based on the position of the recycler view
        holder.tvname.setText(friendmodels.get(position).getFriendname());
        holder.tvdebtstatus.setText(friendmodels.get(position).getDebtstatus());
        holder.tvdebtamount.setText(friendmodels.get(position).getDebtamount());
        holder.imageView.setImageResource(friendmodels.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        //numbers of item want to be displayed
        return friendmodels.size();
    }

    public static class myviewholder extends RecyclerView.ViewHolder{
        //grabbing the views from recycler_view_row layout file
        //like in the oncreate method

        ImageView imageView;
        TextView tvname, tvdebtstatus, tvdebtamount;

        public myviewholder(@NonNull View itemView, frecyclerviewinterface frecyclerviewinterface) {
            super(itemView);

            imageView = itemView.findViewById(R.id.IVfriends);
            tvname = itemView.findViewById(R.id.TVfriendname);
            tvdebtstatus = itemView.findViewById(R.id.TVdebtstatus);
            tvdebtamount = itemView.findViewById(R.id.TVdebtamount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(frecyclerviewinterface!=null){
                        int pos =getBindingAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            frecyclerviewinterface.onitemclick(pos);
                        }
                    }
                }
            });

        }
    }

}
