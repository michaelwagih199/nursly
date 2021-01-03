package com.polimigo.babydaycare.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefrenceHelper {

    private String PREF_NAME = "prefs";
    private SharedPreferences getPrefs(Context context)  {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

   public  void setUsername(Context context , String input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putString("userName", input);
        editor.commit();
    }

    public String getUsername(Context context) {
        return getPrefs(context).getString("userName", "admin");
    }

}


