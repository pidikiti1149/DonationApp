package com.example.donatedemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<DonationClass> mdonationlist;
    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_layout,parent,false);
        ExampleViewHolder evh =new ExampleViewHolder(v);
        return evh;
    }

    public ExampleAdapter(ArrayList<DonationClass> donationlist){
        mdonationlist=donationlist;

    }


    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        DonationClass item = mdonationlist.get(i);
        exampleViewHolder.textview1.setText("Donation "+ item.donation_name);
        exampleViewHolder.textview2.setText("$"+Integer.toString(item.donation_amount));

    }

    @Override
    public int getItemCount() {
        return mdonationlist.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{

        public TextView textview1;
        public TextView textview2;
        public ExampleViewHolder(@NonNull View itemView) {


            super(itemView);
            textview1 = itemView.findViewById(R.id.textView);
            textview2 = itemView.findViewById(R.id.textView3);
        }
    }


}
