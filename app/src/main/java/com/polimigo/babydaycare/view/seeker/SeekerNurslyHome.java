package com.polimigo.babydaycare.view.seeker;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivityNurslyProfileBinding;
import com.polimigo.babydaycare.databinding.ActivitySeekerNurslyHomeBindingImpl;
import com.polimigo.babydaycare.model.NurslyModel;
import com.polimigo.babydaycare.repositories.NurslyRepository;
import com.polimigo.babydaycare.view.adabters.NurslyRecyclerViewAdapter;
import com.polimigo.babydaycare.viewModel.NurslyProfileViewModel;
import com.polimigo.babydaycare.viewModel.SeekerHomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class SeekerNurslyHome extends AppCompatActivity {

    private ActivitySeekerNurslyHomeBindingImpl binding;
    private NurslyRepository nurslyRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nurslyRepository = NurslyRepository.newInstance();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_seeker_nursly_home);
        SeekerHomeViewModel seekerHomeViewModel = new SeekerHomeViewModel(this);
        binding.setViewModel(seekerHomeViewModel);
        binding.executePendingBindings();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recyclerView3.setLayoutManager(linearLayoutManager);
        populateData();
    }

    private void populateData() {
        nurslyRepository.getData2(this,binding.recyclerView3);
    }

}