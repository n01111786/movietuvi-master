package com.example.anubhavchoudhary.movietuvi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class AboutUS extends AppCompatActivity {
    private TextView first,second, third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        first = findViewById(R.id.linkedin1);
        second = findViewById(R.id.pin1);
        third = findViewById(R.id.linkedin2);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url1 = "https://www.linkedin.com/in/anubhav-choudhary-a30512146";
                Intent ihelp = new Intent(Intent.ACTION_VIEW);
                ihelp.setData(Uri.parse(url1));
                startActivity(ihelp);

            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.pinterest.ca/malinlomax/";
                Intent ihelp = new Intent(Intent.ACTION_VIEW);
                ihelp.setData(Uri.parse(url));
                startActivity(ihelp);

            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://www.pinterest.ca/malinlomax/";
                Intent ihelp = new Intent(Intent.ACTION_VIEW);
                ihelp.setData(Uri.parse(url));
                startActivity(ihelp);

            }
        });
    }





}
