package com.example.donatedemo;

import java.io.Serializable;

public class DonationClass implements Serializable {
    String donation_name;
    int donation_amount;

    public DonationClass(){
        donation_name="";
        donation_amount=0;
    }
}
