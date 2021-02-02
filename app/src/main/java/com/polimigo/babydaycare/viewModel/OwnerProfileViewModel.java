package com.polimigo.babydaycare.viewModel;

import android.content.Context;

import com.polimigo.babydaycare.model.NurslyModel;

public class OwnerProfileViewModel {

    private NurslyModel nurslyModel;
    private Context context;

    public OwnerProfileViewModel(NurslyModel nurslyModel,Context context) {
        this.nurslyModel = nurslyModel;
        this.context = context;
    }

    public NurslyModel getNurslyModel() {
        return nurslyModel;
    }

    public void setNurslyModel(NurslyModel nurslyModel) {
        this.nurslyModel = nurslyModel;
    }

}
