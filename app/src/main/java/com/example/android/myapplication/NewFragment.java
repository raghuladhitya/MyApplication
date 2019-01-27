package com.example.android.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewFragment extends Fragment {

    String FarmerName;
    View m1View;
    DatabaseReference reference1;
    //private ArrayList<Values> value;
    int pos;
    public NewFragment() {

    }


    TextView Money, itemName,produced, stock;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        m1View = inflater.inflate(R.layout.card_view_activity, container, false);
        return m1View;
    }
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        this.m1View = view;

        Money = (TextView) m1View.findViewById(R.id.Money_value);
        itemName = (TextView) m1View.findViewById(R.id.ItemName);
        produced = (TextView) m1View.findViewById(R.id.Produced);
        stock = (TextView) m1View.findViewById(R.id.Stock);

        Bundle bundle = getArguments();
       // value = new ArrayList<>();
        pos = bundle.getInt("position");
        FarmerName = bundle.getString("name");
        reference1 = FirebaseDatabase.getInstance().getReference("Farmer").child(FarmerName).child("values");

        reference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Values v = dataSnapshot.getValue(Values.class);
                Log.v("gautam", v +" ");
                Money.setText(v.getMoney());
                itemName.setText(v.getItemName());
                produced.setText(v.getProduced());
                stock.setText(v.getStock());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        /*reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 value.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Values v = dataSnapshot1.getValue(Values.class);
                    value.add(v);
                }
                Money.setText(v.getMoney());
                itemName.setText(v.getItemName());
                produced.setText(v.getProduced());
                stock.setText(v.getStock());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}

