package com.piano.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pianostudy.R;

public class LeftMenuAdapter extends ArrayAdapter<LeftMenuItem> {

	public LeftMenuAdapter(Context context, int textViewResourceId,
			List<LeftMenuItem> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	private int resourceId;


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		LeftMenuItem leftMenu = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(resourceId,null);
			holder = new ViewHolder();
			//holder.leftIcon = (ImageView) convertView.findViewById(R.id.left_icon);
			holder.leftText = (TextView) convertView.findViewById(R.id.left_text);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		//holder.leftIcon.setImageResource(leftMenu.getLeftIcon());
		holder.leftText.setText(leftMenu.getLeftText());
		return convertView;
	}
	
	class ViewHolder{
		//ImageView leftIcon;
		TextView leftText;
	}
	
	
}
