package com.polimigo.babydaycare.view.seeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;

import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivityBookingOrdersBinding;
import com.polimigo.babydaycare.databinding.ActivityVacationsBinding;
import com.polimigo.babydaycare.databinding.ActivityVaccinationAddBinding;
import com.polimigo.babydaycare.repositories.SeekerBookingRepository;
import com.polimigo.babydaycare.repositories.VaccinationRepository;

public class Vacations extends AppCompatActivity {

    private ActivityVacationsBinding binding;
    private VaccinationRepository vaccinationRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vaccinationRepository = VaccinationRepository.newInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vacations);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.VaccctionData.setLayoutManager(linearLayoutManager);
        populateData();
    }

    public void populateData() {
        vaccinationRepository.getVacations(this, binding.VaccctionData);
    }


}