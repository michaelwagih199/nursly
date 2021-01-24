package com.polimigo.babydaycare.view.nursly;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.polimigo.babydaycare.R;

public class OwnerNurslyHome extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_nursly_home);
    }

    public void onProfileClick(View view) {
        startActivity(new Intent(this,NurslyProfile.class));
    }
}