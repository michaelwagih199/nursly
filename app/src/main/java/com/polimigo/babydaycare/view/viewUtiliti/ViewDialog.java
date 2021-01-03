package com.polimigo.babydaycare.view.viewUtiliti;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.polimigo.babydaycare.R;
import com.polimigo.babydaycare.view.AboutApplicationScreen;
import com.polimigo.babydaycare.view.LoginScreen;
import com.polimigo.babydaycare.view.OwnerNurslyHome;
import com.polimigo.babydaycare.view.OwnerNurslyRegister;
import com.polimigo.babydaycare.view.SeekerNurslyHome;
import com.polimigo.babydaycare.view.SeekerNurslyRegister;

import maes.tech.intentanim.CustomIntent;

public class ViewDialog {

    public void showDialog(Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.my_dialog);

        TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
        text.setText(msg);

        Button btnOwner = (Button) dialog.findViewById(R.id.btnOwner);
        btnOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, OwnerNurslyRegister.class));
                CustomIntent.customType(activity,"bottom-to-up");
                activity.finish();
                dialog.dismiss();
            }
        });
        Button btnSeeker = (Button) dialog.findViewById(R.id.btnSeeker);
        btnSeeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, SeekerNurslyRegister.class));
                CustomIntent.customType(activity,"bottom-to-up");
                activity.finish();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
