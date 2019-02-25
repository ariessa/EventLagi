package com.android.ariessa.eventlagi.Forms;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.android.ariessa.eventlagi.Model.Event;
import com.android.ariessa.eventlagi.Model.User;
import com.android.ariessa.eventlagi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// Done but haven't tested yet
public class NameDateTimeLocation extends AppCompatActivity {

    String[] descriptionData = {"Details", "Categories", "VIP", "Fees", "Caterer"};

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private EditText inputName;
    private EditText inputLocation;
    private EditText inputFromDate;
    private EditText inputFromTime;
    private EditText inputToDate;
    private EditText inputToTime;
    private Button mButton;
    private Calendar calendar;
    private StateProgressBar stateProgressBar;
    private String eventId;
    private String name;
    private String location;
    private String from_date;
    private String from_time;
    private String to_date;
    private String to_time;
    private static final String TAG = NameDateTimeLocation.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_date_time_location);

        stateProgressBar = findViewById(R.id.first_progress_bar);
        stateProgressBar.setStateDescriptionData(descriptionData);

        inputName = findViewById(R.id.name_edittext);
        inputLocation = findViewById(R.id.location_edittext);
        mButton = findViewById(R.id.name_next_btn);
        calendar = Calendar.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'events' node
        mFirebaseDatabase = mFirebaseInstance.getReference("events");

        inputFromDate = findViewById(R.id.from_date_edittext);
        inputFromTime = findViewById(R.id.from_time_edittext);
        inputToDate = findViewById(R.id.to_date_edittext);
        inputToTime = findViewById(R.id.to_time_edittext);

        findViewById(R.id.from_date_edittext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFromDatePickerDialog();
            }
        });

        findViewById(R.id.from_time_edittext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFromTimePickerDialog();
            }
        });

        findViewById(R.id.to_date_edittext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToDatePickerDialog();
            }
        });

        findViewById(R.id.to_time_edittext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToTimePickerDialog();
            }
        });


        // Save / update the name, date, time, and location of event
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = inputName.getText().toString();
                location = inputLocation.getText().toString();
                from_date = inputFromDate.getText().toString();
                from_time = inputFromTime.getText().toString();
                to_date = inputToDate.getText().toString();
                to_time = inputToTime.getText().toString();

                // Check for already existed userId
                if (TextUtils.isEmpty(eventId)) {
                    createEvent(name, location, from_date, from_time, to_date, to_time);
                    Intent intent = new Intent(NameDateTimeLocation.this, FocusGroupCategories.class);
                    startActivity(intent);
                } else {
                    updateEvent(name, location, from_date, from_time, to_date, to_time);
                    Intent intent = new Intent(NameDateTimeLocation.this, FocusGroupCategories.class);
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
                inputLocation.setText("");
                inputName.setText("");
                inputFromDate.setText("");
                inputFromTime.setText("");
                inputToDate.setText("");
                inputToTime.setText("");

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
        if (!TextUtils.isEmpty(name))
            mFirebaseDatabase.child(eventId).child("name").setValue(name);

        if (!TextUtils.isEmpty(location))
            mFirebaseDatabase.child(eventId).child("location").setValue(location);

        if (!TextUtils.isEmpty(from_date))
            mFirebaseDatabase.child(eventId).child("from_date").setValue(from_date);

        if (!TextUtils.isEmpty(from_time))
            mFirebaseDatabase.child(eventId).child("from_time").setValue(from_time);

        if (!TextUtils.isEmpty(to_date))
            mFirebaseDatabase.child(eventId).child("to_date").setValue(to_date);

        if (!TextUtils.isEmpty(to_time))
            mFirebaseDatabase.child(eventId).child("to_time").setValue(to_time);
    }


    private void showFromDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                Log.d("NameDateTimeLocation", "Selected date is " + date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showToDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calendar.getTime());
                Log.d("NameDateTimeLocation", "Selected date is " + date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    private void showFromTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                Log.d("NameDateTimeLocation", "Selected time is " + time);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();

    }

    private void showToTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                String time = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
                Log.d("NameDateTimeLocation", "Selected time is " + time);
            }
        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();

    }
}