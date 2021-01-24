package com.polimigo.babydaycare.repositories;

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
import com.polimigo.babydaycare.helpers.SharedPrefrenceHelper;
import com.polimigo.babydaycare.helpers.ToastMessage;
import com.polimigo.babydaycare.model.NurslyModel;
import com.polimigo.babydaycare.model.Users;
import com.polimigo.babydaycare.view.adabters.NurslyRecyclerViewAdapter;
import com.polimigo.babydaycare.view.nursly.OwnerNurslyHome;
import com.polimigo.babydaycare.viewModel.NurslyProfileViewModel;

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

                                        Log.d("testR",document.getData().get("governorate").toString());
                                        Log.d("testR2",document.getId());
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
    public void getData2 (@NotNull final Context mContext, @NotNull final RecyclerView recycleCustomer) {
        contactsCollectionReference
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<NurslyModel> list = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        NurslyModel taskItem = document.toObject(NurslyModel.class);
                        list.add(taskItem);
                        NurslyRecyclerViewAdapter nurslyRecyclerViewAdapter = new NurslyRecyclerViewAdapter((ArrayList<NurslyModel>) list,mContext);
                        recycleCustomer.setAdapter(nurslyRecyclerViewAdapter);
                    }
                    Log.d("Tag", list.toString());
                }
            }
        });

    }

    public void updateContact(Users contact) {
        String documentId = contact.getDocumentId();
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.set(contact);
    }

    public void deleteContact(String documentId) {
        DocumentReference documentReference = contactsCollectionReference.document(documentId);
        documentReference.delete();
    }

}
