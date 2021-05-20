package com.polimigo.babydaycare.viewModel;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.polimigo.babydaycare.BR;
import com.polimigo.babydaycare.model.SeekerBookingModel;
import com.polimigo.babydaycare.model.VaccinationModel;
import com.polimigo.babydaycare.repositories.SeekerBookingRepository;
import com.polimigo.babydaycare.repositories.VaccinationRepository;
import com.polimigo.babydaycare.view.events.RegisterEvents;

public class VacctionsViewModel extends BaseObservable {

    private VaccinationModel vaccinationModel;
    private Context context;
    RegisterEvents registerEvents;
    private String successMessage = "Successful";
    private String errorMessage = "Please fill All Data";
    private VaccinationRepository vaccinationRepository;

    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }


    public VacctionsViewModel(VaccinationModel vaccinationModel, Context context, RegisterEvents registerEvents) {
        this.vaccinationModel = vaccinationModel;
        this.context = context;
        this.registerEvents = registerEvents;
        vaccinationRepository = VaccinationRepository.newInstance();
    }

    public VaccinationModel getVaccinationModel() {
        return vaccinationModel;
    }

    public void setVaccinationModel(VaccinationModel vaccinationModel) {
        this.vaccinationModel = vaccinationModel;
    }

    public void onSelectItem(AdapterView<?> parent, View view, int pos, long id) {
        vaccinationModel.setAge(parent.getSelectedItem().toString());
    }

    public void saveData() {
        if (isInputDataValid()) {
            VaccinationModel model = new VaccinationModel();
            model.setAge(vaccinationModel.getAge());
            model.setVacationDate(vaccinationModel.getVacationDate());
            model.setVacationName(vaccinationModel.getVacationName());
            registerEvents.onStartedL();
            vaccinationRepository.createDocument(model, context);
        } else
            setToastMessage(errorMessage);
    }

    public boolean isInputDataValid() {
        boolean result = false;
        if (
                !TextUtils.isEmpty(vaccinationModel.getAge())
                        && !TextUtils.isEmpty(vaccinationModel.getVacationDate())
                        && !TextUtils.isEmpty(vaccinationModel.getVacationName())
        )
            result = true;
        return result;
    }


}
