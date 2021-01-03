package com.polimigo.babydaycare.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivityLoginScreenBinding;
import com.polimigo.babydaycare.view.viewUtiliti.ViewDialog;
import com.polimigo.babydaycare.viewModel.LoginViewModel;

import maes.tech.intentanim.CustomIntent;

public class LoginScreen extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginScreenBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen);
        activityMainBinding.setViewModel(new LoginViewModel());
        activityMainBinding.executePendingBindings();
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
        startActivity(new Intent(this,OwnerNurslyHome.class));
        CustomIntent.customType(LoginScreen.this,"bottom-to-up");
//        finish();
    }

}