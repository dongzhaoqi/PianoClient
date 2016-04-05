package com.piano.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.pianostudy.R;
import com.pianostudy.ui.PitchPortraitButtonActivity;

public class VolumeFragment extends Fragment implements OnClickListener{
	
	private Button volume_1,volume_2,volume_3,volume_4,volume_5,volume_6,volume_7,volume_8;
	private static final String TAG = "tag";
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.frame_volume, container, false);
		
		volume_1 = (Button) v.findViewById(R.id.volume_1);
		volume_2 = (Button) v.findViewById(R.id.volume_2);
		volume_3 = (Button) v.findViewById(R.id.volume_3);
		volume_4 = (Button) v.findViewById(R.id.volume_4);
		volume_5 = (Button) v.findViewById(R.id.volume_5);
		volume_6 = (Button) v.findViewById(R.id.volume_6);
		volume_7 = (Button) v.findViewById(R.id.volume_7);
		volume_8 = (Button) v.findViewById(R.id.volume_8);
		
		volume_1.setOnClickListener(this);
		volume_2.setOnClickListener(this);
		volume_3.setOnClickListener(this);
		volume_4.setOnClickListener(this);
		volume_5.setOnClickListener(this);
		volume_6.setOnClickListener(this);
		volume_7.setOnClickListener(this);
		volume_8.setOnClickListener(this);
		
		return v;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(), PitchPortraitButtonActivity.class);
		switch (v.getId()) {
		case R.id.volume_1:
			intent.putExtra("itemType", 0);
			startActivity(intent);
			break;
		case R.id.volume_2:
			intent.putExtra("itemType", 1);
			startActivity(intent);
			break;
		case R.id.volume_3:
			intent.putExtra("itemType", 2);
			startActivity(intent);
			break;
		case R.id.volume_4:
			intent.putExtra("itemType", 3);
			startActivity(intent);
			break;
		case R.id.volume_5:
			intent.putExtra("itemType", 4);
			startActivity(intent);
			break;
		case R.id.volume_6:
			intent.putExtra("itemType", 5);
			startActivity(intent);
			break;
		case R.id.volume_7:
			intent.putExtra("itemType", 6);
			startActivity(intent);
			break;
		case R.id.volume_8:
			intent.putExtra("itemType", 7);
			startActivity(intent);
			break;
		
		default:
			break;
		}

	}

}
