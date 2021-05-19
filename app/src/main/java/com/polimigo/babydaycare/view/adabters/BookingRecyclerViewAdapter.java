package com.polimigo.babydaycare.view.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.polimigo.babydaycare.BR;
import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.BookingItemRowBinding;
import com.polimigo.babydaycare.databinding.BookingItemRowBindingImpl;
import com.polimigo.babydaycare.model.SeekerBookingModel;


import java.util.List;


public class BookingRecyclerViewAdapter extends RecyclerView.Adapter<BookingRecyclerViewAdapter.ViewHolder> {

    private List<SeekerBookingModel> dataModelList;
    private Context context;

    public BookingRecyclerViewAdapter(List<SeekerBookingModel> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    @Override
    public BookingRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BookingItemRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.booking_item_row, parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SeekerBookingModel dataModel = dataModelList.get(position);
        holder.itemRowBinding.setModel(dataModel);
        holder.bind(dataModel);
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public BookingItemRowBinding itemRowBinding;

        public ViewHolder(BookingItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.model, obj);
            itemRowBinding.executePendingBindings();
        }

    }

}


