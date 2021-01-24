package com.polimigo.babydaycare.view.nursly;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;
import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.databinding.ActivityNurslyProfileBinding;
import com.polimigo.babydaycare.view.GetLocation;
import com.polimigo.babydaycare.view.events.RegisterEvents;
import com.polimigo.babydaycare.view.login_screen.LoginScreen;
import com.polimigo.babydaycare.view.viewUtiliti.AwsemdialogIm;
import com.polimigo.babydaycare.viewModel.NurslyProfileViewModel;


public class NurslyProfile extends AppCompatActivity implements RegisterEvents {
    View llProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNurslyProfileBinding activityNurslyProfileBinding = DataBindingUtil.setContentView(this,R.layout.activity_nursly_profile);
        NurslyProfileViewModel nurslyProfileViewModel = new NurslyProfileViewModel(this,"Save",this);
        activityNurslyProfileBinding.setViewModel(nurslyProfileViewModel);
        activityNurslyProfileBinding.executePendingBindings();
        llProgressBar = findViewById(R.id.llProgressBar);
        startActivity(new Intent(this, GetLocation.class));
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