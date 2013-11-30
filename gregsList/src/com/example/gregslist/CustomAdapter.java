package com.example.gregslist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final ArrayList<String> titles;
	private final ArrayList<String> categories;
	
	public CustomAdapter(Context context, ArrayList<String> titles, ArrayList<String> categories) {
		super(context, R.layout.row_layout, titles);
		this.context = context;
		this.titles = titles;
		this.categories = categories;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
	    LayoutInflater inflater = (LayoutInflater) context
	        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = convertView;
	    if (rowView == null) {
	    rowView = inflater.inflate(R.layout.row_layout, parent, false);
	    }
	    Typeface typeFace = Typeface.createFromAsset(context.getAssets(),"fonts/CLARENDO.TTF");
	    TextView textView = (TextView) rowView.findViewById(R.id.title);
	    textView.setTypeface(typeFace);
	    TextView textView2= (TextView) rowView.findViewById(R.id.category);
	    textView2.setTypeface(typeFace);
	    textView.setText(titles.get(position));
	    textView2.setText(categories.get(position));
	    return rowView;
	  }

}
