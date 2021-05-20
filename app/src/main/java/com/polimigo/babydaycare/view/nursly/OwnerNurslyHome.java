package com.polimigo.babydaycare.view.nursly;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivityOwnerNurslyHomeBindingImpl;
import com.polimigo.babydaycare.helpers.SharedPrefrenceHelper;
import com.polimigo.babydaycare.model.NurslyModel;
import com.polimigo.babydaycare.repositories.NurslyRepository;
import com.polimigo.babydaycare.viewModel.OwnerProfileViewModel;

public class OwnerNurslyHome extends AppCompatActivity {

    private ActivityOwnerNurslyHomeBindingImpl binding;
    private NurslyRepository nurslyRepository;
    private NurslyModel nurslyModel;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    View llProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nurslyRepository = NurslyRepository.newInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_owner_nursly_home);
        populateData();
        llProgressBar = findViewById(R.id.llProgressBar);
    }

    public void onProfileClick(View view) {
        startActivity(new Intent(this, NurslyProfile.class));
    }

    public void onOrdersClick(View view) {
        startActivity(new Intent(this, BookingOrders.class));
    }

    public void onVacationsClick(View v){
        startActivity(new Intent(this, VaccinationAdd.class));
    }

    private void populateData() {
        nurslyRepository.getProfile(sharedPrefrenceHelper.getUsername(this), binding, this);
    }
}