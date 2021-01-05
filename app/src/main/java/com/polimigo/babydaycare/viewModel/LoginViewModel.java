package com.polimigo.babydaycare.viewModel;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.RadioGroup;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.polimigo.babydaycare.BR;
import com.polimigo.babydaycare.model.Users;
import com.polimigo.babydaycare.repositories.UsersFirestoreManager;
import com.polimigo.babydaycare.view.register_screen.RegisterEvents;


public class LoginViewModel extends BaseObservable {
    private Users users;
    private String successMessage = "Login was successful";
    private String errorMessage = "Email or Password not valid ";
    private UsersFirestoreManager usersFirestoreManager;
    RegisterEvents registerEvents;
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

    public LoginViewModel(RegisterEvents registerViewModel, Context context) {
        users = new Users("", "", "", "", "");
        this.registerEvents = registerViewModel;
        this.context = context;
        usersFirestoreManager = UsersFirestoreManager.newInstance();
    }

    @Bindable
    public Users getUsers() {
        return users;
    }


    @Bindable
    public void setUsers(Users users) {
        this.users = users;
    }


    public void onSplitTypeChanged(RadioGroup radioGroup, int id) {
       if (id==1)
           users.setUserType("owner");
       if (id == 2)
           users.setUserType("seeker");
    }

    public void onLoginClicked() {
        if (isInputDataValid()) {
            registerEvents.onStartedL();
            Log.e("getUserName",users.getUserName());
            Log.e("getPassword",users.getPassword());
            Log.e("getUserType",users.getUserType());

            usersFirestoreManager.isUser(users.getUserName(),
                    users.getPassword(),
                    users.getUserType(),
                    context);
        }
        else
            setToastMessage(errorMessage);
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(users.getUserName()) && !TextUtils.isEmpty(users.getPassword()) && !TextUtils.isEmpty(users.getUserType());
    }

}