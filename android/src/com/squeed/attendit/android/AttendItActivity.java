package com.squeed.attendit.android;

import java.util.ArrayList;

import com.squeed.attendit.api.PersonDTO;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

public class AttendItActivity extends ListActivity {
	private ArrayList<PersonDTO> persons;
	private PersonAdapter listAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_list);

		persons = new ArrayList<PersonDTO>();
		
		PersonDTO person = new PersonDTO();
		person.setUser("erik.ramfelt");
		person.setName("Erik Ramfelt");
		persons.add(person);
		
		
		
		listAdapter = new PersonAdapter(this, R.layout.person_list_item, persons);
		setListAdapter(listAdapter);
/*
		viewOrders = new Runnable() {
			@Override
			public void run() {
				getOrders();
			}
		};
		Thread thread = new Thread(null, viewOrders, "MagentoBackground");
		thread.start();
		m_ProgressDialog = ProgressDialog.show(SoftwarePassionView.this,
				"Please wait...", "Retrieving data ...", true);*/
		
	}
}