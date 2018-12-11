package com.example.anubhavchoudhary.movietuvi;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class forgot extends Activity {
    Toolbar toolbar;
    ProgressBar progressBar;
    EditText userEmail;
    Button userpass;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        userEmail=findViewById(R.id.emailinputver);
        userpass=findViewById(R.id.sendem);
        firebaseAuth=FirebaseAuth.getInstance();

        userpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(forgot.this, "Password send to the email", Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(forgot.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }


                            }
                        });
            }
        });

    }

}
