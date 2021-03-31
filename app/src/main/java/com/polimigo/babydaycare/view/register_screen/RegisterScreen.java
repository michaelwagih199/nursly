package com.polimigo.babydaycare.view.register_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivityOwnerNurslyBinding;
import com.polimigo.babydaycare.view.events.RegisterEvents;
import com.polimigo.babydaycare.view.login_screen.LoginScreen;
import com.polimigo.babydaycare.view.viewUtiliti.AwsemdialogIm;
import com.polimigo.babydaycare.viewModel.RegisterViewModel;

public class RegisterScreen extends AppCompatActivity implements RegisterEvents {
    private View llProgressBar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityOwnerNurslyBinding activityOwnerNurslyBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_owner_nursly);
        String userType = getIntent().getExtras().getString("userType");
        context = this;
        RegisterViewModel registerViewModel = new RegisterViewModel(this, userType, context);
        activityOwnerNurslyBinding.setRegisterModel(registerViewModel);
        activityOwnerNurslyBinding.executePendingBindings();
        llProgressBar = findViewById(R.id.llProgressBar);
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStartedL() {
        llProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessL() {
        llProgressBar.setVisibility(View.GONE);
        AwsemdialogIm awsemdialogIm = new AwsemdialogIm();
        awsemdialogIm.sucssDialog(this,
                new Intent(this, LoginScreen.class),
                "Sucess", "body");
    }

    @Override
    public void onFailerL() {

    }


}