package com.polimigo.babydaycare.repositories;

import android.content.Context;
import android.content.Intent;

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
import com.polimigo.babydaycare.view.OwnerNurslyHome;
import com.polimigo.babydaycare.view.SeekerNurslyHome;

public class UsersFirestoreManager {

    /* ContactsFirestoreManager object **/
    private static UsersFirestoreManager usersFirestoreManager;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference contactsCollectionReference;
    SharedPrefrenceHelper sharedPrefrenceHelper = new SharedPrefrenceHelper();


    public static UsersFirestoreManager newInstance() {
        if (usersFirestoreManager == null) {
            usersFirestoreManager = new UsersFirestoreManager();
        }
        return usersFirestoreManager;
    }


    private UsersFirestoreManager() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        contactsCollectionReference = firebaseFirestore.collection("users");
    }

    public void createDocument(Users contact) {
        contactsCollectionReference.add(contact);
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

    public void sendContactsBulk(String firstNameString, String lastNameString, String userName, String password, String userType) {
        createDocument(new Users(firstNameString,lastNameString,userName , password, userType));
    }

    public void isUser(final String userName, String password, final String userType, final Context context) {
        boolean isUser = false;
        contactsCollectionReference
                .whereEqualTo("userType", userType)
                .whereEqualTo("userName", userName)
                .whereEqualTo("password", password)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
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
                                    }
                                    if (userType.equals("seeker")) {
                                        Intent i = new Intent(context.getApplicationContext(), SeekerNurslyHome.class);
                                        sharedPrefrenceHelper.setUsername(context, userName);
                                        context.startActivity(i);

                                    }

                                }
                                //  Log.d("tag", document.getId() + " => " + document.getData());
                            }
                        } else {
                            ToastMessage.addMessage("false", context);

                            // Log.d("tag", "Error getting documents: ", task.getException());
                        }
                    }
                });

    }


}
