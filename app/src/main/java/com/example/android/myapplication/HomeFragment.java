package com.example.android.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment{

    private static final String TAG = "HomeFragment";
    public ArrayList<Farmer> farmer;
    RecyclerViewAdapter.OnItemClickListener listener;
    View mView;
    RecyclerView recyclerView;
    DatabaseReference reference;
    RecyclerViewAdapter recyclerViewAdapter,getRecyclerViewAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        this.mView = view;

        farmer = new ArrayList<Farmer>();
        recyclerView = mView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reference = FirebaseDatabase.getInstance().getReference("Farmer");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                farmer.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Farmer f = dataSnapshot1.getValue(Farmer.class);
                    farmer.add(f);
                }
                recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), farmer, new RecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        NewFragment fragment = new NewFragment();
                        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();

                        fragmentManager.beginTransaction()
                                .replace(R.id.Fragment_container,fragment)
                                .addToBackStack(null)
                                .commit();
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

