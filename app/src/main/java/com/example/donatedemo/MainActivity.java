package com.example.donatedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NumberPicker numberPicker = null;
    TextView DonationAmount = null;
    Button donate = null;
    Button ViewDonations=null;
    TextView customamount1 = null;
    ProgressBar pbar= null;
    RadioButton direct = null;
    RadioButton paypal = null;
    int donationTotal = 0;
    TextView textViewAmount;

            ArrayList<DonationClass> donationlist = new ArrayList<>();
    int total =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        donate = findViewById(R.id.donate);
        ViewDonations = (Button)findViewById(R.id.button2);
        numberPicker = (NumberPicker)findViewById(R.id.numberPicker1);
        numberPicker.setMaxValue(1000);
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(false);
        DonationAmount=(TextView)findViewById(R.id.donationamount);
        DonationAmount.setText("$0");
        pbar= findViewById(R.id.progress);
        pbar.setProgress(0);
        customamount1 = findViewById(R.id.customamount);
        customamount1.setText("0");
        paypal = findViewById(R.id.paypal);
        direct = findViewById(R.id.direct);
        textViewAmount = findViewById(R.id.donationamount);

        /*paypal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                direct.setChecked(false);

            }
        });

        direct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                paypal.setChecked(false);

            }
        });
*/
        customamount1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.toString().isEmpty()){
                    numberPicker.setValue(0);
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().isEmpty()){
                    numberPicker.setValue(0);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    int temp1 = Integer.parseInt(s.toString());
                    numberPicker.setValue(temp1);
                    DonationAmount.setText("$"+Integer.toString(temp1));

                }
                else{
                    numberPicker.setValue(0);
                }

            }
        });



        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                int temp = 0;
                temp = picker.getValue();

                DonationAmount.setText("$"+Integer.toString(temp));
            }
        });


        ViewDonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passlist = new Intent(MainActivity.this , LstVew.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Donations",donationlist);
                passlist.putExtras(bundle);
                startActivity(passlist);

            }
        });
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             DonationClass tempdonate = new DonationClass();
             tempdonate.donation_amount = numberPicker.getValue();
             tempdonate.donation_name= Integer.toString(donationlist.size() + 1);
                if(paypal.isChecked()){
                    tempdonate.donation_name = tempdonate.donation_name + " (Paypal)";

                }
                if(direct.isChecked()){
                    tempdonate.donation_name = tempdonate.donation_name + " (Direct)";

                }
             donationlist.add(tempdonate);
             Log.d("Mytag",Integer.toString(donationlist.size()));
             total=total+tempdonate.donation_amount;


             pbar.setProgress(total);


            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when Floating Action Button is clicked, call the method below
                addDonation();
            }
        });
    }

    //this method will add up the number picker selections and set the text of the textview to the
    //total all the donations
    private void addDonation(){
        // code for adding a donation
        int donation = numberPicker.getValue();

        if(donation != 0){
            //if donation is not 0$ run this code
            donationTotal += donation;


            String donationTotalString = "$" + donationTotal;
            textViewAmount.setText(donationTotalString);

        }else{
            //if the donation is $0 run this code
            //add a toast message to the user asking for a donation of greater then 0
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Report) {
            Intent passlist = new Intent(MainActivity.this , LstVew.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Donations",donationlist);
            passlist.putExtras(bundle);
            startActivity(passlist);
        }

        return super.onOptionsItemSelected(item);
    }
}
