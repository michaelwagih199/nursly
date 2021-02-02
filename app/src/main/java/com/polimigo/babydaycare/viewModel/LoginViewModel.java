package com.polimigo.babydaycare.viewModel;


import android.content.Context;
import android.text.TextUtils;

import android.widget.RadioGroup;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.polimigo.babydaycare.BR;
import com.polimigo.babydaycare.model.Users;
import com.polimigo.babydaycare.repositories.UsersRepository;
import com.polimigo.babydaycare.view.events.RegisterEvents;

public class LoginViewModel extends BaseObservable {
    private Users users;
    private String successMessage = "Login was successful";
    private String errorMessage = "Login Failed";
    private UsersRepository usersRepository;
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
        usersRepository = UsersRepository.newInstance();
    }

    @Bindable
    public Users getUsers() {
        return users;
    }


    @Bindable
    public void setUsers(Users users) {
        this.users = users;
    }

    public void setSeekerUserType(){
        users.setUserType("seeker");
    }

    public void setOwnerUserType(){
        users.setUserType("owner");
    }



    public void onSplitTypeChanged(RadioGroup radioGroup, int id) {
//        users.setUserType("");
////        Log.d("userType",""+id+""+radioGroup.getCheckedRadioButtonId());
//
//       if (id==1)
//           users.setUserType("owner");
//       if (id == 2)
//           users.setUserType("seeker");
    }

    public void onLoginClicked() {
        registerEvents.onStartedL();
        usersRepository.isUser(
                users.getUserName(),
                users.getPassword(),
                users.getUserType(),
                context);
//        if (isInputDataValid()) {
//            registerEvents.onStartedL();
//            usersRepository.isUser(
//                    users.getUserName(),
//                    users.getPassword(),
//                    users.getUserType(),
//                    context);
//        }
//        else
//            setToastMessage(errorMessage);

    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(users.getUserName()) &&
                !TextUtils.isEmpty(users.getPassword()) &&
                !users.getUserType().isEmpty();
    }

}