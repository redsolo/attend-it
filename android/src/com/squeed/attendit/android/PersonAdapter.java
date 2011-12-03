package com.squeed.attendit.android;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.squeed.attendit.api.AttendantDTO;
import com.squeed.attendit.api.PersonDTO;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonAdapter extends ArrayAdapter<PersonDTO> implements Filterable, OnClickListener {

	private ArrayList<PersonDTO> persons;
	private HashMap<String, Bitmap> gravatars;
	
	public PersonAdapter(Context context, int textViewResourceId,
			ArrayList<PersonDTO> items) {
		super(context, textViewResourceId, new ArrayList<PersonDTO>(items));
		this.persons = items;
	}
	
	private List<PersonDTO> getFilteredResults(CharSequence constraint) {
		List<PersonDTO> list = new ArrayList<PersonDTO>();
		for (PersonDTO person : persons) {
			if (person.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
				list.add(person);
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
				for (PersonDTO person : (ArrayList<PersonDTO>) results.values) {
					PersonAdapter.this.add(person);	
				}				
			    PersonAdapter.this.notifyDataSetChanged();
			}
		
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				List<PersonDTO> filteredResults = getFilteredResults(constraint);
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
        PersonDTO person = getItem(position);
        if (person != null) {
            TextView tt = (TextView) v.findViewById(R.id.toptext);
            TextView bt = (TextView) v.findViewById(R.id.bottomtext);
            if (tt != null) {
                  tt.setText(person.getName());                            }
            if(bt != null){
                  bt.setText(person.getEmailAddress());
            }
            CheckBox cb = (CheckBox) v.findViewById(R.id.checkBox1);
            cb.setOnClickListener(this);
            ImageView iv = (ImageView) v.findViewById(R.id.icon);
            new DownloadImageTask(iv).execute("http://www.gravatar.com/avatar/" + MD5Util.md5Hex(person.getEmailAddress()));
            v.setTag(person);
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
	public void onClick(View view) {
		// tell server to update
	}
}