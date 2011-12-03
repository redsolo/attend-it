package com.squeed.attendit.android;

import java.util.ArrayList;

import com.squeed.attendit.api.PersonDTO;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;

public class AttendItActivity extends ListActivity {
	private ArrayList<PersonDTO> persons;
	private PersonAdapter listAdapter;
	private EditText filterText;

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
		
		filterText = (EditText) findViewById(R.building_list.search_box);
		filterText.addTextChangedListener(filterTextWatcher);
		
		listAdapter = new PersonAdapter(this, R.layout.person_list_item, persons);
		setListAdapter(listAdapter);
/*
		viewOrders = new Runnable() {
			@Override
			public void run() {
				// Get stuff here
			}
		};
		Thread thread = new Thread(null, viewOrders, "MagentoBackground");
		thread.start();
		m_ProgressDialog = ProgressDialog.show(SoftwarePassionView.this,
				"Please wait...", "Retrieving data ...", true);*/
		
		
		
	}

	private TextWatcher filterTextWatcher = new TextWatcher() {
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			listAdapter.getFilter().filter(s);
		}
		@Override
		public void afterTextChanged(Editable arg0) {
		}
		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {	
		}
	};
}