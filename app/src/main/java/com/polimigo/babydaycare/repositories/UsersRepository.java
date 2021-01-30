package com.polimigo.babydaycare.repositories;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.polimigo.babydaycare.helpers.SharedPrefrenceHelper;
import com.polimigo.babydaycare.helpers.ToastMessage;
import com.polimigo.babydaycare.model.Users;
import com.polimigo.babydaycare.view.login_screen.LoginScreen;
import com.polimigo.babydaycare.view.nursly.OwnerNurslyHome;
import com.polimigo.babydaycare.view.seeker.SeekerNurslyHome;

public class UsersRepository {

    /* ContactsFirestoreManager object **/
    private static UsersRepository usersRepository;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();


    public static UsersRepository newInstance() {
        if (usersRepository == null) {
            usersRepository = new UsersRepository();
        }
        return usersRepository;
    }


    private UsersRepository() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("users");
    }

    public boolean createDocument(Users contact) {
        final boolean[] result = {true};
        contactsCollectionReference.add(contact).addOnCompleteListener(command -> {
            Log.d("dddd",""+command.isSuccessful());
            if (command.isSuccessful())
                result[0] = true;
            if (command.isCanceled())
                result[0] = false;
        });
        Log.d("resss",""+result[0]);
        return result[0];
    }

    public void getAllContacts(OnCompleteListener<QuerySnapshot> onCompleteListener) {
        contactsCollectionReference.get().addOnCompleteListener(onCompleteListener);
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

    public void isUser(final String userName, String password, final String userType, final Context context) {
        Log.e("tt",""+userName+""+password+""+userType);
        contactsCollectionReference
                .whereEqualTo("userType", userType)
                .whereEqualTo("userName", userName)
                .whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(task -> {
//                    Log.i("error",""+task.getResult().getDocuments().isEmpty());
                    if (task.getResult().getDocuments().isEmpty()){
                        ToastMessage.addMessage("check user name or password", context);
                        Intent i = new Intent(context.getApplicationContext(), LoginScreen.class);
                        context.startActivity(i);
                        ((Activity)context).finish();
                    }
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            if (document.getData().isEmpty()) {

                                ToastMessage.addMessage("check user name or password", context);

                            } else {
                                if (userType.equals("owner")) {
                                    Intent i = new Intent(context.getApplicationContext(), OwnerNurslyHome.class);
                                    //i.putExtra("editFlag", Constant.updateFlag);4
                                    sharedPrefrenceHelper.setUsername(context, userName);
                                    context.startActivity(i);
                                    ((Activity)context).finish();
                                }
                                if (userType.equals("seeker")) {
                                    Intent i = new Intent(context.getApplicationContext(), SeekerNurslyHome.class);
                                    sharedPrefrenceHelper.setUsername(context, userName);
                                    context.startActivity(i);
                                    ((Activity)context).finish();
                                }
                            }
                        }
                    } else {
                        ToastMessage.addMessage("false", context);
                        Log.i("error","false");
                    }
                });

    }


}
