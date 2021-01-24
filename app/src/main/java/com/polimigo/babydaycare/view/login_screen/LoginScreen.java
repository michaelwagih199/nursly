package com.polimigo.babydaycare.view.login_screen;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivityLoginScreenBinding;
import com.polimigo.babydaycare.view.AboutApplicationScreen;
import com.polimigo.babydaycare.view.events.RegisterEvents;
import com.polimigo.babydaycare.view.viewUtiliti.ViewDialog;
import com.polimigo.babydaycare.viewModel.LoginViewModel;


public class LoginScreen extends AppCompatActivity implements RegisterEvents {
    View llProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginScreenBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen);
        activityMainBinding.setViewModel(new LoginViewModel(this,this));
        activityMainBinding.executePendingBindings();
        llProgressBar = findViewById(R.id.llProgressBar);
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void onSignUp(View view) {
        ViewDialog alert = new ViewDialog();
        alert.showDialog(LoginScreen.this, "Chose Profile Type");
    }

    public void onAboutApplication(View view) {
        startActivity(new Intent(this, AboutApplicationScreen.class));
//        finish();
    }


    @Override
    public void onStartedL() {
        llProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccessL() {
        llProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailerL() {

    }

}