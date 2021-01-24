package com.polimigo.babydaycare.view.adabters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.polimigo.babydaycare.BR;
import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.NurslyItemRowBinding;
import com.polimigo.babydaycare.model.NurslyModel;
import com.polimigo.babydaycare.view.events.CustomClickListener;

import java.util.List;


public class NurslyRecyclerViewAdapter extends RecyclerView.Adapter<NurslyRecyclerViewAdapter.ViewHolder> implements CustomClickListener {

    private List<NurslyModel> dataModelList;
    private Context context;

    public NurslyRecyclerViewAdapter(List<NurslyModel> dataModelList, Context ctx) {
        this.dataModelList = dataModelList;
        context = ctx;
    }

    @Override
    public NurslyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        NurslyItemRowBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.nursly_item_row, parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NurslyModel dataModel = dataModelList.get(position);
        holder.itemRowBinding.setModel(dataModel);
        holder.bind(dataModel);
        holder.itemRowBinding.setItemClickListener(this);
    }


    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public NurslyItemRowBinding itemRowBinding;

        public ViewHolder(NurslyItemRowBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.model, obj);
            itemRowBinding.executePendingBindings();
        }

    }

    public void cardClicked(NurslyModel f) {
        Uri gmmIntentUri = Uri.parse("geo:".concat(f.getLatitude().concat(",").concat(f.getLongitude())));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);
    }

}


