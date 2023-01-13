package com.example.youoweme;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.youoweme.Friend_module.FriendFragment;
import com.example.youoweme.databinding.ActivityHomePageBinding;
import com.example.youoweme.object_model.History;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<History> list;
    MyAdapterHistory myAdapterHistory;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //loading pop up
        progressDialog = new ProgressDialog(this);//loading stuff with progressDialog

        //recyclerView
        recyclerView = findViewById(R.id.recyclerviewHistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        db = FirebaseFirestore.getInstance();
        list = new ArrayList<History>();
        myAdapterHistory = new MyAdapterHistory(HistoryActivity.this, list);

        recyclerView.setAdapter(myAdapterHistory);

        EventChangeListener();
    }

    private void EventChangeListener() {
        db.collection("history").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                for(DocumentChange documentChange: queryDocumentSnapshots.getDocumentChanges()){

                    if(documentChange.getType() == DocumentChange.Type.ADDED){
//                        Log.d(TAG, String.valueOf(documentChange.getDocument()));
//                        Log.d(TAG, String.valueOf(History.class));

                        list.add(documentChange.getDocument().toObject(History.class));
//                        list.add(documentChange.getDocument().toObject(User.class));
                    }
                    progressDialog.dismiss();
                    myAdapterHistory.notifyDataSetChanged();
                }
            }
        });
    }
}