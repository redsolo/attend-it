package com.squeed.attendit.android;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import com.squeed.attendit.api.PersonDTO;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonAdapter extends ArrayAdapter<PersonDTO> {

	private ArrayList<PersonDTO> items;

	public PersonAdapter(Context context, int textViewResourceId,
			ArrayList<PersonDTO> items) {
		super(context, textViewResourceId, items);
		this.items = items;
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.person_list_item, null);
        }
        PersonDTO o = items.get(position);
        if (o != null) {
            TextView tt = (TextView) v.findViewById(R.id.toptext);
            TextView bt = (TextView) v.findViewById(R.id.bottomtext);
            if (tt != null) {
                  tt.setText("Name: "+o.getName());                            }
            if(bt != null){
                  bt.setText("Email: "+ o.getEmailAddress());
            }
            ImageView iv = (ImageView) v.findViewById(R.id.icon);
            loadImageFromWebOperations(iv, "http://www.gravatar.com/avatar/" + MD5Util.md5Hex(o.getEmailAddress()));
        }
        return v;
    }

	private void loadImageFromWebOperations(ImageView imageView, String url) {
		try {
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			imageView.setImageDrawable(d);
		} catch (Exception e) {
			System.out.println("Exc=" + e);
			imageView.setImageResource(R.drawable.ic_launcher);
		}
	}
}