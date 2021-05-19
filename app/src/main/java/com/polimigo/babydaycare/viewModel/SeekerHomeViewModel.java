package com.polimigo.babydaycare.viewModel;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;

import com.polimigo.babydaycare.helpers.StaticData;

import java.util.List;

public class SeekerHomeViewModel {

    Context context;

    public List<String> getData() {
        return StaticData.getEgyptGovernorate();
    }

    public SeekerHomeViewModel(Context context) {
        this.context = context;
    }

}
