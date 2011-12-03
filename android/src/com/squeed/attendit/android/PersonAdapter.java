package com.squeed.attendit.android;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squeed.attendit.api.AttendantDTO;
import com.squeed.attendit.api.PersonDTO;

public class PersonAdapter extends ArrayAdapter<AttendantDTO> implements Filterable, OnCheckedChangeListener {
	
	private boolean showAll;
	
	private ArrayList<AttendantDTO> attendants;
	private HashMap<String, Bitmap> gravatars = new HashMap<String, Bitmap>();
	
	public PersonAdapter(Context context, int textViewResourceId,
			ArrayList<AttendantDTO> items) {
		super(context, textViewResourceId, new ArrayList<AttendantDTO>());
		this.attendants = items;
		setShowAll(false);
	}
	
	private List<AttendantDTO> getFilteredResults(CharSequence constraint) {
		List<AttendantDTO> list = new ArrayList<AttendantDTO>();
		for (AttendantDTO attendant : attendants) {
			PersonDTO person = attendant.getPerson();
			if (person.getName().toLowerCase().contains(constraint.toString().toLowerCase()) && 
					(attendant.getStatus() == 0)) {
				list.add(attendant);
			}
		}
		return list;
	}

	@Override
	public Filter getFilter() {
		return new Filter() {
			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				PersonAdapter.this.clear();
				for (AttendantDTO attendant : (ArrayList<AttendantDTO>) results.values) {
					PersonAdapter.this.add(attendant);	
				}				
			    PersonAdapter.this.notifyDataSetChanged();
			}
		
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				List<AttendantDTO> filteredResults = getFilteredResults(constraint);
	            FilterResults results = new FilterResults();
	            results.values = filteredResults;
	            return results;
			}
	    };
	}


	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.person_list_item, null);
        }
        AttendantDTO attendant = getItem(position);
        if (attendant != null) {
            PersonDTO person = attendant.getPerson();
            TextView tt = (TextView) v.findViewById(R.id.toptext);
            TextView bt = (TextView) v.findViewById(R.id.bottomtext);
            if (tt != null) {
				tt.setText(person.getName());                            }
            if(bt != null){
            	bt.setText(person.getEmailAddress());
            }
            CheckBox cb = (CheckBox) v.findViewById(R.id.checkBox1);
            cb.setTag(attendant);
            cb.setChecked(attendant.getStatus()== 1);
            cb.setOnCheckedChangeListener(this);
            new DownloadImageTask((ImageView) v.findViewById(R.id.icon)).execute("http://www.gravatar.com/avatar/" + MD5Util.md5Hex(person.getEmailAddress()));
        }
        return v;
    }
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		private final ImageView view;
		public DownloadImageTask(ImageView view) {
			this.view = view;
		}
	     protected Bitmap doInBackground(String... urls) {
	    	try {
	    		if (gravatars.containsKey(urls[0])) {
	    			return gravatars.get(urls[0]);
	    		}
	    		Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(urls[0]).getContent());
	    		gravatars.put(urls[0], bitmap);
	            return bitmap;
	 		} catch (Exception e) {
	 			return null;
	 		}
	     }

	     protected void onPostExecute(Bitmap result) {
	    	 if (result != null) {
	    		 view.setImageBitmap(result);
	    	 }
	     }
	 }

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		AttendantDTO attendantDto = (AttendantDTO) buttonView.getTag();
		
		RestClient restClient = null;
		if(isChecked) {
			restClient = new RestClient(AttendItActivity.URL + "/attendit/rest/registration/register/attendant/" + attendantDto.getId());
			if (!showAll) {
				remove(attendantDto);
			}
		} else {
			restClient = new RestClient(AttendItActivity.URL + "/attendit/rest/registration/register/attendant/" + attendantDto.getId());
		}
		
		try {
			restClient.Execute(RestClient.RequestMethod.POST);
			if (isChecked) {
				Toast.makeText(this.getContext(), attendantDto.getPerson().getName() + " was registered!", Toast.LENGTH_SHORT);
			} else {
				Toast.makeText(this.getContext(), attendantDto.getPerson().getName() + " is no longer registered!", Toast.LENGTH_SHORT);
			}
		} catch (Exception e) {
			Toast.makeText(this.getContext(), "An error occured registering attendant: " + e.getMessage(), Toast.LENGTH_LONG);
		}
	}

	public boolean isShowAll() {
		return showAll;
	}

	public void setShowAll(boolean showAll) {
		clear();
		
		for (AttendantDTO attendant : attendants) {
			if (showAll || attendant.getStatus() == 0) {
				add(attendant);
			}
		}
		notifyDataSetChanged();
		this.showAll = showAll;
	}
}