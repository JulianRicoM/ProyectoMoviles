package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class Profile extends Fragment {

    private FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String userID;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_profille, container, false);

        final TextView fullNameTextView = (TextView) root.findViewById(R.id.fullName);
        final TextView emailTextView = (TextView) root.findViewById(R.id.emailAddress);

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        DocumentReference docRef = db.collection("users").document(userID);
        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            User user = documentSnapshot.toObject(User.class);
                            String fullName = user.name;
                            String email = user.email;
                            fullNameTextView.setText(fullName);
                            emailTextView.setText(email);
                    }
                });
        return root;

    }
}