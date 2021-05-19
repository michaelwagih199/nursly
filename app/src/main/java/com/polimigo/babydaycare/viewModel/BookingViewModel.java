package com.polimigo.babydaycare.viewModel;

import android.content.Context;
import android.text.TextUtils;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.polimigo.babydaycare.BR;
import com.polimigo.babydaycare.model.SeekerBookingModel;
import com.polimigo.babydaycare.repositories.SeekerBookingRepository;
import com.polimigo.babydaycare.view.events.RegisterEvents;

public class BookingViewModel extends BaseObservable {

    private SeekerBookingModel seekerBookingModel;
    private Context context;
    RegisterEvents registerEvents;
    private String successMessage = "Successful";
    private String errorMessage = "Please fill All Data";
    private SeekerBookingRepository seekerBookingRepository;

    @Bindable
    private String toastMessage = null;

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }


    public BookingViewModel(SeekerBookingModel seekerBookingModel, Context context, RegisterEvents registerEvents) {
        this.seekerBookingModel = seekerBookingModel;
        this.context = context;
        this.registerEvents = registerEvents;
        seekerBookingRepository = SeekerBookingRepository.newInstance();
    }

    public SeekerBookingModel getSeekerBookingModel() {
        return seekerBookingModel;
    }

    public void setSeekerBookingModel(SeekerBookingModel seekerBookingModel) {
        this.seekerBookingModel = seekerBookingModel;
    }

    public void saveData() {
        if (isInputDataValid()) {
            SeekerBookingModel model = new SeekerBookingModel();
            model.setName(seekerBookingModel.getName());
            model.setNotes(seekerBookingModel.getNotes());
            model.setNumberBab(seekerBookingModel.getNumberBab());
            model.setPhone(seekerBookingModel.getPhone());
            model.setNurslyName(seekerBookingModel.getNurslyName());
            registerEvents.onStartedL();
            seekerBookingRepository.createDocument(model, context);
        } else
            setToastMessage(errorMessage);
    }

    public boolean isInputDataValid() {
        boolean result = false;
        if (!TextUtils.isEmpty(seekerBookingModel.getName())
                && !TextUtils.isEmpty(seekerBookingModel.getNotes())
                && !TextUtils.isEmpty(seekerBookingModel.getNumberBab())
                && !TextUtils.isEmpty(seekerBookingModel.getPhone()))
            result = true;
        return result;
    }


}
