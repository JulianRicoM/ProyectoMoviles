package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Profile extends Fragment {

    private FirebaseUser user;
    private DatabaseReference reference;
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
        reference = FirebaseDatabase.getInstance().getReference("users");

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullName = userProfile.name;
                    String email = userProfile.email;

                    fullNameTextView.setText(fullName);
                    emailTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Toast.makeText(AccountFragment.this, "Something wrong happened!", Toast.LENGTH_LONG).show();

            }
        });

        return root;


    }
}