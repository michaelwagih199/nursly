package com.polimigo.babydaycare.view.nursly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivityBookingBinding;
import com.polimigo.babydaycare.databinding.ActivityVaccinationAddBinding;
import com.polimigo.babydaycare.model.SeekerBookingModel;
import com.polimigo.babydaycare.model.VaccinationModel;
import com.polimigo.babydaycare.view.events.RegisterEvents;
import com.polimigo.babydaycare.viewModel.BookingViewModel;
import com.polimigo.babydaycare.viewModel.VacctionsViewModel;

public class VaccinationAdd extends AppCompatActivity implements RegisterEvents {

    private ActivityVaccinationAddBinding binding;
    String nurslyName;
    View llProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vaccination_add);
        VaccinationModel vaccinationModel = new VaccinationModel();
        binding.setViewModel(new VacctionsViewModel(vaccinationModel,this,this));
        binding.executePendingBindings();
        llProgressBar = findViewById(R.id.llProgressBar);
    }


    @Override
    public void onStartedL() {
        llProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessL() {
        llProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailerL() {

    }



}