
package com.polimigo.babydaycare.view.nursly;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivityBookingOrdersBinding;
import com.polimigo.babydaycare.repositories.SeekerBookingRepository;

public class BookingOrders extends AppCompatActivity {

    private ActivityBookingOrdersBinding binding;
    private SeekerBookingRepository seekerBookingRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        seekerBookingRepository = SeekerBookingRepository.newInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_booking_orders);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.bookingRecycle.setLayoutManager(linearLayoutManager);
        populateData();

    }

    public void populateData() {
        seekerBookingRepository.getDataByUser(this, binding.bookingRecycle);
    }

}