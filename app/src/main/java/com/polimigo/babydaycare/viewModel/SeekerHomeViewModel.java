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

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
//       nurslyModel.setGovernorate(parent.getSelectedItem().toString());
        //Log.d("mm",parent.getSelectedItem().toString());
        //pos                                 get selected item position
        //view.getText()                      get lable of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item
        //and other...
    }

}
