package com.polimigo.babydaycare.viewModel;
import android.text.TextUtils;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.polimigo.babydaycare.BR;
import com.polimigo.babydaycare.model.Users;
import com.polimigo.babydaycare.repositories.UsersFirestoreManager;
import com.polimigo.babydaycare.view.register_screen.RegisterEvents;

public class RegisterViewModel extends BaseObservable {

    private Users users;
    @Bindable
    private String toastMessage = null;
    RegisterEvents registerEvents;
    private String successMessage = "Login was successful";
    private String errorMessage = "Please fill All data";
    private UsersFirestoreManager usersFirestoreManager;

    public RegisterViewModel(RegisterEvents registerViewModel,String type) {
        users = new Users("", "", "", "",type);
        this.registerEvents = registerViewModel;
        usersFirestoreManager = UsersFirestoreManager.newInstance();
    }

    public String getToastMessage() {
        return toastMessage;
    }


    private void setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    @Bindable
    public Users getUsers() {
        return users;
    }


    @Bindable
    public void setUsers(Users users) {
        this.users = users;
    }

    public void onRegisterClicked() {
        if (isInputDataValid()) {
            registerEvents.onStartedL();
            Users contact = new Users();
            contact.setFirstNameString(users.getFirstNameString());
            contact.setLastNameString(users.getLastNameString());
            contact.setUserName(users.getUserName());
            contact.setPassword(users.getPassword());
            contact.setUserType(users.getUserType());
            if (usersFirestoreManager.createDocument(contact))
                registerEvents.onSuccessL();
            else
                registerEvents.onFailerL();
        } else {
            setToastMessage(errorMessage);
        }
    }

    public boolean isInputDataValid() {
        boolean result = false;
        if (!TextUtils.isEmpty(users.getFirstNameString())
                && !TextUtils.isEmpty(users.getLastNameString())
                && !TextUtils.isEmpty(users.getPassword())
                && !TextUtils.isEmpty(users.getPassword()))
            result = true;
        return result;
    }

}

