package com.piano.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.piano.bean.VerifiedUser;
import com.pianostudy.R;

public class VerifiedAdapter extends BaseAdapter {

	private Context mContext;
	// 填充数据的list
	//public ArrayList<HashMap<String, Object>> list;
	private ArrayList<VerifiedUser>list = new ArrayList<VerifiedUser>();
	// 用来导入布局
	private LayoutInflater inflater = null;

	// 用来控制CheckBox的选中状况  
    private static HashMap<Integer, Boolean> isSelected;  
	
	public static HashMap<Integer, Boolean> getIsSelected() {
		return isSelected;
	}

	public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
		VerifiedAdapter.isSelected = isSelected;
	}

	// 构造器
	public VerifiedAdapter(Context context,ArrayList<VerifiedUser> list) {
		this.mContext = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
		
		isSelected = new HashMap<Integer, Boolean>();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			
			holder = new ViewHolder();
			// 导入布局并赋值给convertview
			convertView = inflater.inflate(R.layout.verified_list_item, null);
			holder.tv = (TextView) convertView.findViewById(R.id.userName);
			holder.cb = (CheckBox) convertView.findViewById(R.id.verified_checkbox);
			
			holder.cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					int getPosition = (int) buttonView.getTag();
					//Toast.makeText(mContext, getPosition+":"+isChecked, 3).show();
					list.get(getPosition).setSelected(buttonView.isChecked());
				}
			});

			convertView.setTag(holder);
			convertView.setTag(R.id.userName, holder.tv);
			convertView.setTag(R.id.verified_checkbox,holder.cb);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.cb.setTag(position);
		holder.tv.setText(list.get(position).getUserName());
		holder.cb.setChecked(list.get(position).isSelected());
		
		return convertView;
	}
}

class ViewHolder {
	TextView tv;
	CheckBox cb;
}