package com.example.android.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Farmer> f;
    public Context mContext;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(View view,int position);

        void onItemLongClick(View view,int position);
    }


    public RecyclerViewAdapter(Context context, ArrayList<Farmer> farmer,OnItemClickListener listener){
        f = farmer;
        mContext = context;
        mListener = listener;
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,final int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item,viewGroup,false);
        final MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //noinspection deprecation
                mListener.onItemClick(v,holder.getPosition());
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder,int i) {
        Log.d(TAG,"onBindViewHolder:called.");

        viewHolder.farmerLoc.setText(f.get(i).getLocation());
        viewHolder.description.setText(f.get(i).getDescription());
        viewHolder.detail.setText(f.get(i).getDetails());
        viewHolder.farmerName.setText(f.get(i).getName());
        Picasso.get().load(f.get(i).getProfilePic()).into(viewHolder.imageView);
        viewHolder.Stock.setText(f.get(i).getStock());

    }

    @Override
    public int getItemCount() {
        return f.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView farmerName,farmerLoc,bar,description,Stock,detail;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (CircleImageView) itemView.findViewById(R.id.image_no_1);

            Stock = (TextView)itemView.findViewById(R.id.Gooods_stock);
            description =(TextView) itemView.findViewById(R.id.Farmer_desc);
            farmerName = (TextView)itemView.findViewById(R.id.Farmer_name);
            detail = (TextView)itemView.findViewById(R.id.Details_no_1);
            farmerLoc = (TextView) itemView.findViewById(R.id.Farmer_Loc);

            bar = (TextView)itemView.findViewById(R.id.barrier);

        }

    }

}
