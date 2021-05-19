package com.polimigo.babydaycare.view.seeker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.polimigo.babydaycare.R

class NurslyHome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nursly_home)
    }

    fun onNurslyData(view: View) {
        startActivity(Intent(this, NurslyData::class.java))
    }

    fun onVacationsData(view: View) {
        startActivity(Intent(this, Vacations::class.java))
    }
}