package com.android.ariessa.eventlagi.Forms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.android.ariessa.eventlagi.Model.Event;
import com.android.ariessa.eventlagi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kofigyan.stateprogressbar.StateProgressBar;

//
public class FocusGroupCategories extends AppCompatActivity {

    String[] descriptionData = {"Details", "Categories", "VIP", "Fees", "Caterer"};
    private Button mButton;
    private RadioGroup mRadioGroup;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    public String eventId;
    private static final String TAG = FocusGroupCategories.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_group_categories);

        StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(descriptionData);

        mButton = findViewById(R.id.focus_group_categories_btn);

        // get reference to 'events' node
        mFirebaseDatabase = mFirebaseInstance.getReference("events");

        // Save / update the name, date, time, and location of event
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //name = inputName.getText().toString();

                // Check for already existed userId
                if (TextUtils.isEmpty(eventId)) {
                    //createEvent(name, location, from_date, from_time, to_date, to_time);
                    Intent intent = new Intent(FocusGroupCategories.this, VIPPersonInCharge.class);
                    startActivity(intent);
                } else {
                    //updateEvent(name, location, from_date, from_time, to_date, to_time);
                    Intent intent = new Intent(FocusGroupCategories.this, VIPPersonInCharge.class);
                    startActivity(intent);
                }
            }
        });
    }


    /**
     * Creating new event node under 'event'
     */
    private void createEvent(String name, String location, String from_date, String from_time, String to_date, String to_time) {
        // In real apps this eventId should be fetched
        // by implementing firebase auth
        if (TextUtils.isEmpty(eventId)) {
            eventId = mFirebaseDatabase.push().getKey();
        }

        Event event = new Event(name, location, from_date, from_time, to_date, to_time);

        mFirebaseDatabase.child(eventId).setValue(event);

        addEventChangeListener();
    }

    /**
     * Event data change listener
     */
    private void addEventChangeListener() {
        // Event data change listener
        mFirebaseDatabase.child(eventId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Event event = dataSnapshot.getValue(Event.class);

                // Check for null
                if (event == null) {
                    Log.e(TAG, "Event data is null!");
                    return;
                }

                Log.e(TAG, "Event data is changed!" + event.name + ", " + event.location);

                // clear edit text
//                inputLocation.setText("");
//                inputName.setText("");
//                inputFromDate.setText("");
//                inputFromTime.setText("");
//                inputToDate.setText("");
//                inputToTime.setText("");

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read event", error.toException());
            }
        });
    }


    private void updateEvent(String name, String location, String from_date, String from_time, String to_date, String to_time) {
        // updating the event via child nodes
//        if (!TextUtils.isEmpty(name))
//            mFirebaseDatabase.child(eventId).child("name").setValue(name);


    }

}
