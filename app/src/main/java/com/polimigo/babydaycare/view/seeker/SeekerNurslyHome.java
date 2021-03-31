package com.polimigo.babydaycare.view.seeker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivitySeekerNurslyHomeBindingImpl;
import com.polimigo.babydaycare.helpers.StaticData;
import com.polimigo.babydaycare.repositories.NurslyRepository;

import java.util.ArrayList;
import java.util.List;


public class SeekerNurslyHome extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivitySeekerNurslyHomeBindingImpl binding;
    private NurslyRepository nurslyRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nurslyRepository = NurslyRepository.newInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_seeker_nursly_home);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.seekerData.setLayoutManager(linearLayoutManager);

        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerFilterData);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, StaticData.getEgyptGovernorate());
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        populateData();

    }

    private void populateData() {
        nurslyRepository.getData2(this, binding.seekerData);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if (item.equals("All governorates"))
            nurslyRepository.getData2(this, binding.seekerData);
        else
            nurslyRepository.findByGovernorate(this, binding.seekerData, item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}