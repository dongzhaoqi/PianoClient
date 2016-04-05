package com.piano.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pianostudy.R;

public class MusicFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.frame_music, container, false);
		
		return v;
	}

}
