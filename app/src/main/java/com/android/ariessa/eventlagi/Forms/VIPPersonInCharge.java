package com.android.ariessa.eventlagi.Forms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.ariessa.eventlagi.R;
import com.kofigyan.stateprogressbar.StateProgressBar;

public class VIPPersonInCharge extends AppCompatActivity {

    String[] descriptionData = {"Details", "Categories", "VIP", "Fees", "Caterer"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vipperson_in_charge);

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

    }
}
