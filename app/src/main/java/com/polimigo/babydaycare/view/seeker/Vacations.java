package com.polimigo.babydaycare.view.seeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivityBookingOrdersBinding;
import com.polimigo.babydaycare.databinding.ActivityVacationsBinding;
import com.polimigo.babydaycare.databinding.ActivityVaccinationAddBinding;
import com.polimigo.babydaycare.helpers.StaticData;
import com.polimigo.babydaycare.repositories.SeekerBookingRepository;
import com.polimigo.babydaycare.repositories.VaccinationRepository;

public class Vacations extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

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

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerVacctionsData);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        String[] foo_array = this.getResources().getStringArray(R.array.vacctionList);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, foo_array);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        populateData();
    }

    public void populateData() {
        vaccinationRepository.getVacations(this, binding.VaccctionData);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if (item.equals("All Vacctions"))
            populateData();
        else
            vaccinationRepository.getDataByAge(this, binding.VaccctionData, item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}