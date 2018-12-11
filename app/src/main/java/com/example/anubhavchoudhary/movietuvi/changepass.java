package com.example.anubhavchoudhary.movietuvi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepass extends AppCompatActivity {
    EditText e2;
    Button BTT;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        dialog=new ProgressDialog(this);
        e2=(EditText)findViewById(R.id.e1);
        BTT=findViewById(R.id.passchange);
        auth=FirebaseAuth.getInstance();

        BTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatepass(e2);

            }
        });


    }


    private void updatepass(View v){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            dialog.setMessage("changing password please wait!!");
            dialog.show();
            user.updatePassword(e2.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "passWord has been changed", Toast.LENGTH_SHORT).show();
                                auth.signOut();
                                finish();
                                Intent b =new Intent(changepass.this,login.class);
                                startActivity(b);
                            }
                            else
                                {
                                    dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "passWord could not changed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

}
