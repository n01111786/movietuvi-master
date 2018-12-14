package com.example.anubhavchoudhary.movietuvi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
//import com.firebaseloginapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.example.anubhavchoudhary.movietuvi.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;






// just to check




public class Account extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//navigation bar
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    DataStructure mData;
    private FirebaseAuth auth;
    ImageView imageViewob;

    private String currentuserid;

    FirebaseStorage storage;
    StorageReference storageReference;

    private TextView ProfileName;
    private TextView ProfileCountry;
    private TextView ProfilePhoneNumber;
    private TextView ProfileHobby;
    private TextView ProfileBirthday;
    private TextView Profilegender;
    private TextView profileemail;
    FirebaseUser user;

    String uid;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        //SetOrientation();
        //firebase auth
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        profileemail=findViewById(R.id.readprofile_email);
       // FirebaseUser user = auth.getCurrentUser();
        user = auth.getCurrentUser();
        profileemail.setText(user.getEmail());



        FirebaseStorage storage = FirebaseStorage.getInstance();

        imageViewob=(ImageView) findViewById(R.id.img);





        getDatabase();
        findAllViews();
        reterieveData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent12 =new Intent(Account.this, sensor.class);
                startActivity(intent12);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




    }





    private void findAllViews() {
        ProfileName = findViewById(R.id.readprofile_name);
        ProfileCountry = findViewById(R.id.readprofile_country);
        ProfileBirthday = findViewById(R.id.readprofile_birth);
        ProfilePhoneNumber = findViewById(R.id.readprofile_phone);
        ProfileHobby = findViewById(R.id.readprofile_hobby);
        Profilegender = findViewById(R.id.readprofile_gender);



    }



    private void getDatabase() {
        // TODO: Find the refernce form the database.
        user=FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("data").child(uid);
    }


    private void reterieveData() {
        // TODO: Get the data on a single node.



        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructure ds = dataSnapshot.getValue(DataStructure.class);
                ProfileName.setText("Name: " + ds.getprofilename());
                ProfileCountry.setText("Country: " + ds.getProfileCountry());
                ProfileBirthday.setText("Birthday: " + ds.getProfileBirthday());
                ProfilePhoneNumber.setText("cellphone: " + ds.getProfilePhoneNumber());
                ProfileHobby.setText("Hobby:" + ds.getProfileHobby());
                Profilegender.setText("Gender:" + ds.getProfilegender());



                // Convert from timestamp to Date and time
                //      timestamp.setText(convertTimestamp(ds.getTimestamp()));
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataStructure ds = dataSnapshot.getValue(DataStructure.class);
                ProfileName.setText("Name: " + ds.getprofilename());
                ProfileCountry.setText("Country: " + ds.getProfileCountry());
                ProfileBirthday.setText("Birthday: " + ds.getProfileBirthday());
                ProfilePhoneNumber.setText("cellphone: " + ds.getProfilePhoneNumber());
                ProfileHobby.setText("Hobby:" + ds.getProfileHobby());
                Profilegender.setText("Gender:" + ds.getProfilegender());


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        // TODO: Get the whole data array on a reference.
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataStructure> arraylist= new ArrayList<DataStructure>();

                // TODO: Now data is reteieved, needs to process data.
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {

                    // iterate all the items in the dataSnapshot
                    for (DataSnapshot a : dataSnapshot.getChildren()) {
                        DataStructure dataStructure = new DataStructure();
                        dataStructure.setprofilename(a.getValue(DataStructure.class).getprofilename());
                        dataStructure.setProfileCountry(a.getValue(DataStructure.class).getProfileCountry());
                        dataStructure.setProfilePhoneNumber(a.getValue(DataStructure.class).getProfilePhoneNumber());
                        dataStructure.setProfileBirthday(a.getValue(DataStructure.class).getProfileBirthday());
                        dataStructure.setProfileHobby(a.getValue(DataStructure.class).getProfileHobby());
                        dataStructure.setProfilegender(a.getValue(DataStructure.class).getProfilegender());


                        arraylist.add(dataStructure);  // now all the data is in arraylist.
                       // Log.d("MapleLeaf", "dataStructure " + dataStructure.getTimestamp());
                    }
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting data failed, log a message
                Log.d("MapleLeaf", "Data Loading Cancelled/Failed.", databaseError.toException());
            }
        });
    }





    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.account, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_quit) {
            finishAndRemoveTask();
        }

        if (id == R.id.action_signout) {
            signOut();

           Intent intent4 =new Intent(Account.this, login.class);
          startActivity(intent4);
        }

        if (id == R.id.action_update) {

            Intent intent5 =new Intent(Account.this, editinfo.class);
            startActivity(intent5);
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override


    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;
        if (id == R.id.nav_camera) {

            intent = new Intent(getApplicationContext(), camera.class);
        } else if (id == R.id.nav_gallery) {
            intent = new Intent(getApplicationContext(), speech.class);
        }  else if (id == R.id.nav_manage) {
            intent = new Intent(getApplicationContext(), setting.class);

        } else if (id == R.id.nav_share) {

            intent = new Intent(getApplicationContext(), object.class);

        } else if (id == R.id.nav_3D) {
            intent = new Intent(getApplicationContext(), Cube3d.class);

        }

         else if (id == R.id.nav_send) {
            intent = new Intent(getApplicationContext(), chat.class);

        }
        else if (id == R.id.nav_weather) {
            intent = new Intent(getApplicationContext(), weather.class);

        }

        if (intent != null)
            startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }








    //sign out method
    public void signOut() {
        auth.signOut();
    }

    public void SetOrientation(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean orientation = preferences.getBoolean("portrait", true);
        if(orientation)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

}
