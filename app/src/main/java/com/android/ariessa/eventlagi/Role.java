package com.android.ariessa.eventlagi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.ariessa.eventlagi.Forms.NameDateTimeLocation;
import com.android.ariessa.eventlagi.User.MainActivity;

public class Role extends AppCompatActivity {

    private Button mOrganizerBtn;
    private Button mUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);

        mOrganizerBtn = findViewById(R.id.role_organizer_btn);
        mUserBtn = findViewById(R.id.role_user_btn);

        mOrganizerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Role.this, NameDateTimeLocation.class);
                startActivity(intent);
            }
        });

        mUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Role.this, MainActivity.class);
                startActivity(intent);
            }
        });


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
