package com.example.anubhavchoudhary.movietuvi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//just to check



public class editinfo extends AppCompatActivity {


    private static final String TAG = editinfo.class.getSimpleName();

    private EditText ProfileName;

    private EditText ProfileCountry;

    private EditText ProfilePhoneNumber;

    private EditText ProfileHobby;

    private EditText ProfileBirthday;

    private EditText Profilegender;

    FirebaseUser user;

    String uid;


//    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editinfo);


        findAllViews();
        getDatabase();

        Button saveEditButton = (Button)findViewById(R.id.save_edit_button);
        saveEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeData(ProfileName.getText(), ProfileCountry.getText(), ProfilePhoneNumber.getText(), ProfileHobby.getText(),ProfileBirthday.getText(),Profilegender.getText());
                Intent intent6 = new Intent(editinfo.this, Account.class);
                startActivity(intent6);
            }
        });

        Button back =(Button)findViewById(R.id.Return);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(editinfo.this, Account.class);
                startActivity(intent5);
            }
        });


    }

    private void getDatabase(){
        // TODO: Find the refernce form the database.
        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("data").child(uid);

    }

    private DataStructure createData(Editable ProfileName, Editable ProfileCountry, Editable ProfilePhoneNumber, Editable ProfileHobby,  Editable ProfileBirthday, Editable Profilegender){

       // Long time = System.currentTimeMillis()/1000;
      //   String timestamp = time.toString();
       return new DataStructure(String.valueOf(ProfileName),
                String.valueOf(ProfileCountry),
                String.valueOf(ProfilePhoneNumber),
                String.valueOf(ProfileHobby),
                String.valueOf(ProfileBirthday),
                String.valueOf(Profilegender)
                );
    }


    private void writeData(Editable ProfileName, Editable ProfileCountry, Editable ProfilePhoneNumber, Editable ProfileHobby,Editable ProfileBirthday,Editable Profilegender) {

        DataStructure mData = createData(ProfileName, ProfileCountry, ProfilePhoneNumber, ProfileHobby, ProfileBirthday, Profilegender);
        // Select one of the following methods to update the data.
        // 1. To set the value of data
        // myRef.setValue(mData);
        // 2. To create a new node on database.
        //  myRef.push().setValue(mData);
        // TODO: Write the data to the database.
        // 3. To create a new node on database and detect if the writing is successful.
        myRef.push().setValue(mData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Value was set. ", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Writing failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void findAllViews() {
        ProfileName = (EditText) findViewById(R.id.profile_name);
        ProfileCountry = (EditText) findViewById(R.id.profile_country);
        ProfilePhoneNumber = (EditText) findViewById(R.id.profile_phone);
        ProfileHobby = (EditText) findViewById(R.id.profile_hobby);
        ProfileBirthday = (EditText) findViewById(R.id.profile_birth);
        Profilegender=(EditText)findViewById(R.id.profile_gender);
    }


}

