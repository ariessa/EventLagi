package com.android.ariessa.eventlagi.Forms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.ariessa.eventlagi.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class Caterer extends AppCompatActivity {

    String[] descriptionData = {"Details", "Categories", "VIP", "Fees", "Caterer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer);

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

    }
}
