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
import com.polimigo.babydaycare.view.adabters.BookingRecyclerViewAdapter;
import com.polimigo.babydaycare.view.adabters.NurslyRecyclerViewAdapter;
import com.polimigo.babydaycare.view.nursly.BookingOrders;
import com.polimigo.babydaycare.view.seeker.NurslyHome;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SeekerBookingRepository {


    /* ContactsFirestoreManager object **/
    private static SeekerBookingRepository seekerBookingRepository;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;

    public static SeekerBookingRepository newInstance() {
        if (seekerBookingRepository == null) {
            seekerBookingRepository = new SeekerBookingRepository();
        }
        return seekerBookingRepository;
    }

    private SeekerBookingRepository() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("SeekerBooking");
    }

    public void createDocument(SeekerBookingModel contact, final Context context) {
        contactsCollectionReference.add(contact).addOnCompleteListener(command -> {
            if (command.isSuccessful()) {
                ToastMessage.addMessage("Data Saved", context);
                Intent i = new Intent(context.getApplicationContext(), NurslyHome.class);
                context.startActivity(i);
                ((Activity) context).finish();
            } else
                ToastMessage.addMessage("Saved Faild", context);
        });

    }


    public void getDataByUser(Context mContext, RecyclerView bookingRecycle) {
        String userName = sharedPrefrenceHelper.getUsername(mContext);
        Log.e("ff",userName);
        contactsCollectionReference
                .whereEqualTo("nurslyName", userName)
                .get().addOnCompleteListener((OnCompleteListener<QuerySnapshot>) task -> {
            if (task.isSuccessful()) {
                ArrayList<SeekerBookingModel> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    SeekerBookingModel taskItem = document.toObject(SeekerBookingModel.class);
                    list.add(taskItem);
                    BookingRecyclerViewAdapter nurslyRecyclerViewAdapter = new BookingRecyclerViewAdapter(list, mContext);
                    bookingRecycle.setAdapter(nurslyRecyclerViewAdapter);
                }
                Log.d("Tag", list.toString());
            }
        });

    }



}
