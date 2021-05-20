package com.polimigo.babydaycare.view.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.polimigo.babydaycare.BR;
import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.BookingItemRowBinding;
import com.polimigo.babydaycare.databinding.VacctionsItemRowBinding;
import com.polimigo.babydaycare.model.SeekerBookingModel;
import com.polimigo.babydaycare.model.VaccinationModel;

import java.util.List;


public class VacctionsRecyclerViewAdapter extends RecyclerView.Adapter<VacctionsRecyclerViewAdapter.ViewHolder> {

    private List<VaccinationModel> dataModelList;
    private Context context;

    public VacctionsRecyclerViewAdapter(List<VaccinationModel> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    @Override
    public VacctionsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VacctionsItemRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.vacctions_item_row, parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VaccinationModel dataModel = dataModelList.get(position);
        holder.itemRowBinding.setModel(dataModel);
        holder.bind(dataModel);
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public VacctionsItemRowBinding itemRowBinding;

        public ViewHolder(VacctionsItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.model, obj);
            itemRowBinding.executePendingBindings();
        }

    }

}


