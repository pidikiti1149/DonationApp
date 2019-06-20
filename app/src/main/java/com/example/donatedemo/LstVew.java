package com.example.donatedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class LstVew extends AppCompatActivity {

    private RecyclerView.LayoutManager mlayoutManager;
    private RecyclerView.Adapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lst_vew);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rview1);
        Bundle passlist = getIntent().getExtras();
        ArrayList<DonationClass> dons = (ArrayList<DonationClass>) passlist.getSerializable("Donations");
        mlayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter=new ExampleAdapter(dons);
        recyclerView.setAdapter(mAdapter);


    }
}
