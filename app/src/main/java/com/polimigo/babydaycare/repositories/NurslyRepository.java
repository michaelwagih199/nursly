package com.polimigo.babydaycare.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.polimigo.babydaycare.databinding.ActivityNurslyProfileBindingImpl;
import com.polimigo.babydaycare.databinding.ActivityOwnerNurslyHomeBindingImpl;
import com.polimigo.babydaycare.helpers.SharedPrefrenceHelper;
import com.polimigo.babydaycare.helpers.ToastMessage;
import com.polimigo.babydaycare.model.NurslyModel;
import com.polimigo.babydaycare.model.Users;
import com.polimigo.babydaycare.view.adabters.NurslyRecyclerViewAdapter;
import com.polimigo.babydaycare.view.events.RegisterEvents;
import com.polimigo.babydaycare.view.login_screen.LoginScreen;
import com.polimigo.babydaycare.view.nursly.OwnerNurslyHome;
import com.polimigo.babydaycare.viewModel.NurslyProfileViewModel;
import com.polimigo.babydaycare.viewModel.OwnerProfileViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NurslyRepository {

    /* ContactsFirestoreManager object **/
    private static NurslyRepository nurslyRepository;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();

    public static NurslyRepository newInstance() {
        if (nurslyRepository == null) {
            nurslyRepository = new NurslyRepository();
        }
        return nurslyRepository;
    }

    private NurslyRepository() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("nursly");
    }


    public void createDocument(NurslyModel contact, final Context context) {
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

    public void getAllContacts(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        contactsCollectionReference.get().addOnCompleteListener(onCompleteListener);
    }

    public NurslyModel popUi(Context context) {
        String userName = sharedPrefrenceHelper.getUsername(context);
        NurslyModel nurslyModel = new NurslyModel();
        contactsCollectionReference
                .whereEqualTo("userName", userName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if (document.getData().isEmpty()) {

                                } else {

                                    try {
//                                        name.setText(document.getData().get("name").toString());
//                                        age.setText(document.getData().get("age").toString());
//                                        experienceYear.setText(document.getData().get("experienceYears").toString());
//                                        phone.setText(document.getData().get("phone").toString());
//                                        degree.setText(document.getData().get("degee").toString());
//                                        collage.setText(document.getData().get("collage").toString());
//                                        grade.setText(document.getData().get("grade").toString());
//                                        id.setText(document.getId());

                                        Log.d("testR", document.getData().get("governorate").toString());
                                        Log.d("testR2", document.getId());
                                        nurslyModel.setGovernorate(document.getData().get("governorate").toString());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }

                            }

                        }
                    }
                });
        return nurslyModel;
    }


    //get user of company
    public void findByGovernorate(@NotNull final Context mContext, @NotNull final RecyclerView recycleCustomer, @NotNull String governorate) {
        ArrayList<NurslyModel> list = new ArrayList<>();
        contactsCollectionReference
                .whereEqualTo("governorate", governorate)
                .get().addOnCompleteListener(task -> {
            if (task.getResult().getDocuments().isEmpty()) {
                NurslyRecyclerViewAdapter nurslyRecyclerViewAdapter = new NurslyRecyclerViewAdapter(list, mContext);
                recycleCustomer.setAdapter(nurslyRecyclerViewAdapter);
            }
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    NurslyModel taskItem = document.toObject(NurslyModel.class);
                    list.add(taskItem);
                    NurslyRecyclerViewAdapter nurslyRecyclerViewAdapter = new NurslyRecyclerViewAdapter(list, mContext);
                    recycleCustomer.setAdapter(nurslyRecyclerViewAdapter);
                }
            }
        });
    }

    //get user of company
    public void getData2(@NotNull final Context mContext, @NotNull final RecyclerView recycleCustomer) {
        contactsCollectionReference
                .orderBy("todayPrice")
                .get().addOnCompleteListener((OnCompleteListener<QuerySnapshot>) task -> {
            if (task.isSuccessful()) {
                ArrayList<NurslyModel> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    NurslyModel taskItem = document.toObject(NurslyModel.class);
                    list.add(taskItem);
                    NurslyRecyclerViewAdapter nurslyRecyclerViewAdapter = new NurslyRecyclerViewAdapter(list, mContext);
                    recycleCustomer.setAdapter(nurslyRecyclerViewAdapter);
                }
                Log.d("Tag", list.toString());
            }
        });
    }

    public void getProfile(@NotNull String userName, ActivityOwnerNurslyHomeBindingImpl binding, Context context) {
        contactsCollectionReference
                .whereEqualTo("userName", userName)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.getResult().getDocuments().isEmpty()) {
                    NurslyModel nurslyModel = new NurslyModel("", "", "", "", "", "", 0.0, "", "", "");
                    OwnerProfileViewModel ownerProfileViewModel = new OwnerProfileViewModel(nurslyModel, context);
                    binding.setOwnerViewModel(ownerProfileViewModel);
                    binding.executePendingBindings();
                }
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        NurslyModel taskItem = document.toObject(NurslyModel.class);
                        OwnerProfileViewModel ownerProfileViewModel = new OwnerProfileViewModel(taskItem, context);
                        binding.setOwnerViewModel(ownerProfileViewModel);
                        binding.executePendingBindings();
                    }
                }
            }
        });
    }

    public void editeProfile(@NotNull String userName, ActivityNurslyProfileBindingImpl binding, Context context, RegisterEvents registerEvents) {
        contactsCollectionReference
                .whereEqualTo("userName", userName)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.getResult().getDocuments().isEmpty()) {
                    NurslyModel nurslyModel = new NurslyModel("", "", "", "", "", "", 0.0, "", "", "");
                    NurslyProfileViewModel nurslyProfileViewModel = new NurslyProfileViewModel(registerEvents, "Save", context, nurslyModel);
                    binding.setViewModel(nurslyProfileViewModel);
                    binding.executePendingBindings();
                }

                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        NurslyModel taskItem = document.toObject(NurslyModel.class);
                        NurslyProfileViewModel nurslyProfileViewModel = new NurslyProfileViewModel(registerEvents, "Edit", context, taskItem);
                        binding.setViewModel(nurslyProfileViewModel);
                        binding.executePendingBindings();
                    }
                }
            }
        });
    }


    public void updateContact(NurslyModel contact, Context context) {
        String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
        Intent i = new Intent(context.getApplicationContext(), OwnerNurslyHome.class);
        context.startActivity(i);
        ((Activity) context).finish();
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }

}
