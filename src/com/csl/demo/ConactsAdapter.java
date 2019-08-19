package com.csl.demo;

import java.util.List;

import com.csl.demo.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ConactsAdapter extends ArrayAdapter<Conacts> {
	private int rId;
	
	public ConactsAdapter(Context context, int resource,List<Conacts> objects) {
		super(context, resource, objects);
		rId = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v;
		Conacts c = getItem(position);
		if(convertView == null){
			v = LayoutInflater.from(getContext()).inflate(rId, null);
		}else{
			v = convertView;
		}
		TextView tv = (TextView) v.findViewById(R.id.contacts);
		tv.setText(c.getName() + "\n" + c.getPhone());
		
		return v;
	}

}
