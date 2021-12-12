package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;


public class Profile extends Fragment {

    private FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String userID;
    private Button btnLogout, btnSave, btnSearchImg, btnCamera;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 1888;
    ImageView mImageView;
    private Uri mImageUri;
    FirebaseStorage mStorage = FirebaseStorage.getInstance("gs://proyecto-app-moviles-907c3.appspot.com");


    private static final int RESULT_OK = -1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.activity_profille, container, false);
        btnSave = root.findViewById(R.id.btnAddImagen);
        btnSave.setVisibility(View.INVISIBLE);
        btnLogout = root.findViewById(R.id.BtnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent newIntent = new Intent(getActivity(), MainOptions.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(newIntent);
            }
        });

        btnCamera = root.findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });


        btnSearchImg = root.findViewById(R.id.btnSearchImg);
        btnSearchImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
                btnSave.setVisibility(View.VISIBLE);
            }
        });

        mImageView = root.findViewById(R.id.home_images);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
                btnSave.setVisibility(View.VISIBLE);
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("users").document(userID).update(
                  "photo", userID
                );
                StorageReference fileReference = mStorage.getReference().child("pictures/"+userID+".jpeg");
                fileReference.putFile(mImageUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getActivity(), "Photo upload successfully", Toast.LENGTH_LONG).show();
                                btnSave.setVisibility(View.INVISIBLE);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getActivity(), "Photo upload failed", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });


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
                            String photo = user.photo;

                            fullNameTextView.setText(fullName);
                            emailTextView.setText(email);

                            StorageReference mStorageReference = FirebaseStorage.getInstance().getReference().child("pictures/" +photo+".jpeg");

                            try {
                                final File localFile = File.createTempFile(photo,"jpeg");
                                mStorageReference.getFile(localFile)
                                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());

                                                mImageView.setImageBitmap(bitmap); }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            } catch (IOException e){
                                e.printStackTrace();
                            }

                            //String IMAGE_URL = "gs://proyecto-app-moviles-907c3.appspot.com/a7iaicycPnY6RVF0wLxo7QinYXu2";
                            //String IMAGE_URL = "https://firebasestorage.googleapis.com/v0/b/proyecto-app-moviles-907c3.appspot.com/o/a7iaicycPnY6RVF0wLxo7QinYXu2?alt=media&token=adafb2df-6c5b-4eb3-9ea1-3029be4cfde8";
                            //StorageReference gsReference = mStorage.getReferenceFromUrl("gs://proyecto-app-moviles-907c3.appspot.com/");
                            //Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/proyecto-app-moviles-907c3.appspot.com/o/a7iaicycPnY6RVF0wLxo7QinYXu2.jpg?alt=media&token=19233614-fb69-447b-9426-bc46124907d2").into(mImageView);
                            //Glide.with(mImageView.getContext()).load(IMAGE_URL).into(mImageView);

                    }
                });
        return root;

    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Profile.RESULT_OK
                && data != null && data.getData() != null){
            mImageUri = data.getData();
            mImageView.setImageURI(mImageUri);


        }
    }

}