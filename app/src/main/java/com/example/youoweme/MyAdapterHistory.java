package com.example.youoweme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youoweme.object_model.History;

import java.util.ArrayList;

public class MyAdapterHistory extends RecyclerView.Adapter<MyAdapterHistory.MyViewHolder> {
    Context context;
    ArrayList<History> historyArrayList;

    public MyAdapterHistory(Context context, ArrayList<History> historyArrayList) {
        this.context = context;
        this.historyArrayList = historyArrayList;
    }


    @NonNull
    @Override
    public MyAdapterHistory.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.history_item_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterHistory.MyViewHolder holder, int position) {
        History history = historyArrayList.get(position);
        holder.description.setText(history.getDescription());
        holder.date.setText(history.getDate());
        holder.time.setText(history.getTime());
    }

    @Override
    public int getItemCount() {
        return historyArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView description, date, time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.TVHistoryItemRowDescription);
            date = itemView.findViewById(R.id.TVHistoryItemRowDate);
            time = itemView.findViewById(R.id.TVHistoryItemRowTime);
        }
    }
}
