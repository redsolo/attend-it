package com.squeed.attendit;

import android.app.Activity;
import android.os.Bundle;

public class AttendItActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_list);
    }
}