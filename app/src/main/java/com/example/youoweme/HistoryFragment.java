package com.example.youoweme;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.youoweme.object_model.History;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<History> list;
    MyAdapterHistory myAdapterHistory;
    FirebaseFirestore db;
    ProgressDialog progressDialog;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        progressDialog = new ProgressDialog(getActivity());

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.recyclerviewHistory);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        db = FirebaseFirestore.getInstance();
        list = new ArrayList<History>();
        myAdapterHistory = new MyAdapterHistory(getActivity(), list);

        recyclerView.setAdapter(myAdapterHistory);

        EventChangeListener();

        return view;
    }

    private void EventChangeListener() {
        progressDialog.setMessage("Fetching data...");
//        progressDialog.setTitle("Login");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

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