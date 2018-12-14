package com.example.anubhavchoudhary.movietuvi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class setting extends Activity {


    Button changepass;
    Button del;
    Button aboutus;
    Button feedba;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        del = findViewById(R.id.deact);
        aboutus = findViewById(R.id.us);
        changepass = findViewById(R.id.pass);
        feedba = findViewById(R.id.cont);


        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(setting.this, AboutUS.class);
                startActivity(intent3);
            }
        });


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delAccount();
            }
        });

                    changepass.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(setting.this, changepass.class);
                            startActivity(intent);

                        }
                    });


                    feedba.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent2 = new Intent(setting.this, Feedback.class);
                            startActivity(intent2);

                        }
                    });

                      dialog = new ProgressDialog(this);

                }

// this doesnt work for now i dongt know what just happed
    private void delAccount() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            dialog.setMessage("Deactivating  please wait!!");
            dialog.show();
            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "account Deleated", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent d = new Intent(setting.this, login.class);
                        startActivity(d);
                    } else {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "could not Deleat the account", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

}


