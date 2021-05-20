package com.polimigo.babydaycare.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.polimigo.babydaycare.helpers.SharedPrefrenceHelper;
import com.polimigo.babydaycare.helpers.ToastMessage;
import com.polimigo.babydaycare.model.NurslyModel;
import com.polimigo.babydaycare.model.SeekerBookingModel;
import com.polimigo.babydaycare.model.VaccinationModel;
import com.polimigo.babydaycare.view.adabters.BookingRecyclerViewAdapter;
import com.polimigo.babydaycare.view.adabters.NurslyRecyclerViewAdapter;
import com.polimigo.babydaycare.view.adabters.VacctionsRecyclerViewAdapter;
import com.polimigo.babydaycare.view.nursly.OwnerNurslyHome;
import com.polimigo.babydaycare.view.seeker.NurslyHome;
import com.polimigo.babydaycare.view.seeker.Vacations;

import java.util.ArrayList;

public class VaccinationRepository {

    private static VaccinationRepository vaccinationRepository;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;

    public static VaccinationRepository newInstance() {
        if (vaccinationRepository == null) {
            vaccinationRepository = new VaccinationRepository();
        }
        return vaccinationRepository;
    }

    private VaccinationRepository() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("Vaccinations");
    }

    public void createDocument(VaccinationModel contact, final Context context) {
        contactsCollectionReference.add(contact).addOnCompleteListener(command -> {
            if (command.isSuccessful()) {
                ToastMessage.addMessage("Data Saved", context);
                Intent i = new Intent(context.getApplicationContext(), OwnerNurslyHome.class);
                context.startActivity(i);
                ((Activity) context).finish();
            } else
                ToastMessage.addMessage("Saved Faild", context);
        });

    }


    public void getDataByAge(Context mContext, RecyclerView bookingRecycle, String age) {
        Log.e("ff", age);
        ArrayList<VaccinationModel> list = new ArrayList<>();
        contactsCollectionReference
                .whereEqualTo("age", age)
                .get().addOnCompleteListener((OnCompleteListener<QuerySnapshot>) task -> {
            if (task.getResult().getDocuments().isEmpty()) {
                VacctionsRecyclerViewAdapter nurslyRecyclerViewAdapter = new VacctionsRecyclerViewAdapter(list, mContext);
                bookingRecycle.setAdapter(nurslyRecyclerViewAdapter);
            }
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    VaccinationModel taskItem = document.toObject(VaccinationModel.class);
                    list.add(taskItem);
                    VacctionsRecyclerViewAdapter nurslyRecyclerViewAdapter = new VacctionsRecyclerViewAdapter(list, mContext);
                    bookingRecycle.setAdapter(nurslyRecyclerViewAdapter);
                }
                Log.d("Tag", list.toString());
            }
        });

    }

    public void getVacations(Context mContext, RecyclerView vaccctionData) {
        contactsCollectionReference
                .get().addOnCompleteListener((OnCompleteListener<QuerySnapshot>) task -> {
            if (task.isSuccessful()) {
                ArrayList<VaccinationModel> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    VaccinationModel taskItem = document.toObject(VaccinationModel.class);
                    list.add(taskItem);
                    VacctionsRecyclerViewAdapter nurslyRecyclerViewAdapter = new VacctionsRecyclerViewAdapter(list, mContext);
                    vaccctionData.setAdapter(nurslyRecyclerViewAdapter);
                }
                Log.d("Tag", list.toString());
            }
        });
    }
}
