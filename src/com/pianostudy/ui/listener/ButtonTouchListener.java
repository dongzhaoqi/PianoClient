package com.pianostudy.ui.listener;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.pianostudy.ui.ButtonActivity;

/**
 * 按键的点击事件接口
 */
public class ButtonTouchListener implements OnTouchListener {
	private ButtonActivity ba;
	private int i;
	private String TAG = "ButtonTouchListener";

	public ButtonTouchListener(ButtonActivity ba, int i) {
		this.ba = ba;
		this.i = i;
	}

	public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
		if (paramMotionEvent.getAction() == 0) {// 按下
			Log.d(TAG, i + "");
			ba.soundPool.play(ba.soundMap.get(i), 1.0F, 1.0F, 1, 0, 1.0F);
		}
		return false;
	}
}
