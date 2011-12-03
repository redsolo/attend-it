package com.squeed.attendit.android;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squeed.attendit.api.AttendantDTO;

public class AttendItActivity extends ListActivity {
	
	public static final String URL = "http://10.48.227.236:8080";
	
	private ArrayList<AttendantDTO> attendants = new ArrayList<AttendantDTO>();
	private PersonAdapter listAdapter;
	private EditText filterText;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_list);

		
		RestClient client = new RestClient(URL + "/attendit/rest/registration/event/0/attendants");
		try {
			client.Execute(RestClient.RequestMethod.GET);
			Type jsonType = new TypeToken<List<AttendantDTO>>(){}.getType();
			attendants = new Gson().fromJson(client.getResponse(), jsonType);	
		} catch (Exception e) {			
			Toast.makeText(this.getBaseContext(), "An error occured during retrieval: " + e.getMessage(), Toast.LENGTH_SHORT).show();

		}
		
		
		filterText = (EditText) findViewById(R.building_list.search_box);
		filterText.addTextChangedListener(filterTextWatcher);
		
		listAdapter = new PersonAdapter(this, R.layout.person_list_item, attendants);
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.list_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.list_show_all:
	        listAdapter.setShowAll(true);
	        return true;
	    case R.id.list_show_registered:
	    	listAdapter.setShowAll(false);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
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