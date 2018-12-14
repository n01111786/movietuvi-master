package com.example.anubhavchoudhary.movietuvi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.text.TextUtils;


//import com.firebaseloginapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.support.annotation.NonNull;

import static com.facebook.internal.CallbackManagerImpl.RequestCodeOffset.Login;


public class login extends AppCompatActivity {

    private Button login1;
    private Button register;
    private Button forgot1;
    private EditText edtEmail,edtPassword;
    private FirebaseAuth auth;
    EditText user,passward;

    SharedPreferences Sharedfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SetOrientation();
    //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(login.this, Account.class));
            finish();
        }

        setContentView(R.layout.activity_login);
        login1 = findViewById(R.id.btSignIn);
        edtEmail = findViewById(R.id.emailinput);
        edtPassword = findViewById(R.id.passwordinput);
        register=findViewById(R.id.btSignUp);
        forgot1=findViewById(R.id.forgotbtn);

        //get firebase auth instance
        auth =FirebaseAuth.getInstance();
        FillFields();

        register = (Button) findViewById(R.id.btSignUp);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);

            }
        });


        forgot1 = (Button) findViewById(R.id.forgotbtn);
        forgot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, forgot.class);
                startActivity(intent);

            }
        });

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplyChanges();

                String email = edtEmail.getText().toString();
                final String password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

          //      progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                        //        progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        edtPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(login.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(login.this, Account.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });



    }

    public void SetOrientation(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean orientation = preferences.getBoolean("portrait", true);
        if(orientation)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void FillFields(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean rememberme = preferences.getBoolean("rememberme", false);
        String user_fill = preferences.getString("username", "");
        String pass_fill = preferences.getString("password", "");
        user = (EditText) findViewById(R.id.emailinput);
        passward = (EditText) findViewById(R.id.passwordinput);
        if(rememberme) {
            user.setText(user_fill);
            passward.setText(pass_fill);
        }

    }

    public void ApplyChanges(){
        CheckBox rem_me;
        Boolean rem;
        rem_me = (CheckBox) findViewById(R.id.rememberme_checkbox);
        if(rem_me.isChecked())
            rem=true;
        else
            rem=false;
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(login.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String username_fill,pass_fill;
        username_fill = user.getText().toString();
        pass_fill = passward.getText().toString();
        editor.putBoolean("rememberme", rem);
        editor.putString("username",username_fill);
        editor.putString("password",pass_fill);
        editor.commit();
    }




}








