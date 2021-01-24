package com.polimigo.babydaycare.viewModel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.polimigo.babydaycare.BR;
import com.polimigo.babydaycare.helpers.SharedPrefrenceHelper;
import com.polimigo.babydaycare.helpers.StaticData;
import com.polimigo.babydaycare.model.NurslyModel;
import com.polimigo.babydaycare.repositories.NurslyRepository;
import com.polimigo.babydaycare.view.events.RegisterEvents;
import java.util.List;

public class NurslyProfileViewModel extends BaseObservable {

    private NurslyModel nurslyModel;
    private String btnTextCheck;
    private String successMessage = "Successful";
    private String errorMessage = "Failed";
    private String governorates = "";
    RegisterEvents registerEvents;
    private NurslyRepository nurslyRepository;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();
    Context context;

    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public NurslyProfileViewModel(RegisterEvents registerViewModel, String btnTextCheck, Context context) {
        nurslyModel = new NurslyModel("", "", "", "", "", "", "", "", "","");
        this.btnTextCheck = btnTextCheck;
        this.registerEvents = registerViewModel;
        this.context = context;
        nurslyRepository = NurslyRepository.newInstance();
    }

    public NurslyModel getNurslyModel() {
        return nurslyModel;
    }

    public void setNurslyModel(NurslyModel nurslyModel) {
        this.nurslyModel = nurslyModel;
    }

    public void saveData() {
            Log.e("kk", sharedPrefrenceHelper.getLatitude(context));
            Log.e("kk", sharedPrefrenceHelper.getLongitude(context));
        if (isInputDataValid()){
            NurslyModel model = new NurslyModel();
            model.setGovernorate(nurslyModel.getGovernorate());
            model.setAddress(nurslyModel.getAddress());
            model.setNurslyPhone(nurslyModel.getNurslyPhone());
            model.setNurslyBedsNumber(nurslyModel.getNurslyBedsNumber());
            model.setNurslyName(nurslyModel.getNurslyName());
            model.setRegion(nurslyModel.getRegion());
            model.setLatitude(sharedPrefrenceHelper.getLatitude(context));
            model.setLongitude(sharedPrefrenceHelper.getLongitude(context));
            model.setUserName(sharedPrefrenceHelper.getUsername(context));
            registerEvents.onStartedL();
            nurslyRepository.createDocument(model,context);
        }else
            setToastMessage(errorMessage);
//        Log.e("kk", nurslyModel.toString());
    }

    public List<String> getData() {
        return StaticData.getEgyptGovernorate();
    }

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        nurslyModel.setGovernorate(parent.getSelectedItem().toString());
        //Log.d("mm",parent.getSelectedItem().toString());
        //pos                                 get selected item position
        //view.getText()                      get lable of selected item
        //parent.getAdapter().getItem(pos)    get item by pos
        //parent.getAdapter().getCount()      get item count
        //parent.getCount()                   get item count
        //parent.getSelectedItem()            get selected item
        //and other...
    }


    public boolean isInputDataValid() {
        boolean result = false;
        if (!TextUtils.isEmpty(nurslyModel.getAddress())
                && !TextUtils.isEmpty(nurslyModel.getGovernorate())
                && !TextUtils.isEmpty(nurslyModel.getNurslyBedsNumber())
                && !TextUtils.isEmpty(nurslyModel.getNurslyPhone())
                && !TextUtils.isEmpty(nurslyModel.getNurslyName())
                && !TextUtils.isEmpty(nurslyModel.getRegion()))
            result = true;
        return result;
    }

}
