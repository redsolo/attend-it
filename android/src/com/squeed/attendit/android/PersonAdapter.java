package com.squeed.attendit.android;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squeed.attendit.api.RegistrationDTO;
import com.squeed.attendit.api.PersonDTO;

public class PersonAdapter extends ArrayAdapter<RegistrationDTO> implements Filterable, OnCheckedChangeListener {
	
	private boolean showAll;
	
	private ArrayList<RegistrationDTO> attendants;
	private HashMap<RegistrationDTO, Bitmap> gravatars = new HashMap<RegistrationDTO, Bitmap>();
	
	public PersonAdapter(Context context, int textViewResourceId,
			ArrayList<RegistrationDTO> items) {
		super(context, textViewResourceId, new ArrayList<RegistrationDTO>());
		this.attendants = items;
		setShowAll(false);
	}
	
	private List<RegistrationDTO> getFilteredResults(CharSequence constraint) {
		List<RegistrationDTO> list = new ArrayList<RegistrationDTO>();
		for (RegistrationDTO attendant : attendants) {
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
				for (RegistrationDTO attendant : (ArrayList<RegistrationDTO>) results.values) {
					PersonAdapter.this.add(attendant);	
				}				
			    PersonAdapter.this.notifyDataSetChanged();
			}
		
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				List<RegistrationDTO> filteredResults = getFilteredResults(constraint);
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
        RegistrationDTO attendant = getItem(position);
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
            cb.setOnCheckedChangeListener(null);
            cb.setChecked(attendant.getStatus()== 1);
            cb.setOnCheckedChangeListener(this);

            ImageView imageView = (ImageView) v.findViewById(R.id.icon);
            if (gravatars.containsKey(attendant)) {
            	imageView.setImageBitmap(gravatars.get(attendant));
            } else {
            	imageView.setImageResource(R.drawable.icon);
            	new DownloadImageTask(imageView).execute(attendant);
            }
        }
        v.setTag(attendant.getPerson());
        v.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				final PersonDTO person = (PersonDTO) v.getTag();

				AlertDialog.Builder builder;

				Context mContext = getContext();
				LayoutInflater inflater = (LayoutInflater) mContext
						.getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
				View layout = inflater.inflate(R.layout.custom_dialog,
						(ViewGroup) v.findViewById(R.id.custom_dialog));

				final EditText editText = (EditText) layout
						.findViewById(R.id.nameFld);
				editText.setText(person.getName());

				builder = new AlertDialog.Builder(mContext);
				builder.setView(layout);
				final AlertDialog alertDialog = builder.create();

				final Button saveBtn = (Button) layout
						.findViewById(R.id.savePoiNameBtn);
				final Button cancelBtn = (Button) layout
						.findViewById(R.id.cancelPoiNameBtn);

				saveBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						person.setName(editText.getText().toString());
						
//						RestClient rc = new RestClient(AttendItActivity.URL
//								+ "/attendit/rest/registration/person/"
//								+ person.getId());
//						GsonBuilder builder = new GsonBuilder();				
//						Gson gson = builder.create();
//						
//						String json = gson.toJson(person, PersonDTO.class);
//						Log.i("PersonAdapter", "Person JSON: " + json);
//						rc.AddParam("person", json);
//						try {
//							rc.Execute(RestClient.RequestMethod.POST);
//							Toast.makeText(getContext(), "Successfully updated person", Toast.LENGTH_SHORT).show();
//							alertDialog.dismiss();
//						} catch (Exception e) {
//							Toast.makeText(getContext(), "An error occured saving person: " + e.getMessage(), Toast.LENGTH_LONG).show();
//						}
						Toast.makeText(getContext(), "Successfully updated person", Toast.LENGTH_SHORT).show();
						alertDialog.dismiss();
					}
				});

				cancelBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						alertDialog.dismiss();
					}
				});

				alertDialog.show();

				return false;
			}
		});
		return v;
    }
	
	private class DownloadImageTask extends AsyncTask<RegistrationDTO, Void, Bitmap> {
		private final ImageView view;
		public DownloadImageTask(ImageView view) {
			this.view = view;
		}
	     protected Bitmap doInBackground(RegistrationDTO... attendants) {
	    	try {
	    		String url = "http://www.gravatar.com/avatar/" + MD5Util.md5Hex(attendants[0].getPerson().getEmailAddress());
	    		Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
	    		if (bitmap != null) {
	    			gravatars.put(attendants[0], bitmap);
	    		}
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
		RegistrationDTO attendantDto = (RegistrationDTO) buttonView.getTag();
		if (attendantDto == null) {
			return ;
		}
		attendantDto.setStatus((isChecked ? 1 : 0));
		
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
				Toast.makeText(this.getContext(), attendantDto.getPerson().getName() + " was registered!", Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this.getContext(), attendantDto.getPerson().getName() + " is no longer registered!", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			Toast.makeText(this.getContext(), "An error occured registering attendant: " + e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}	

	public boolean isShowAll() {
		return showAll;
	}
	public void setShowAll(boolean showAll) {
		clear();
		if(attendants != null) {
			for (RegistrationDTO attendant : attendants) {
				if (showAll || attendant.getStatus() == 0) {
					add(attendant);
				}
			}
		}
		notifyDataSetChanged();
		this.showAll = showAll;
	}
}