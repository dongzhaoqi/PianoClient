package com.pianostudy.ui.listener;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.pianostudy.ui.util.MidiCreateUtil;
import com.pianostudy.ui.util.MidiPlayer;

/**
 * 竖琴的按钮监听器
 * @Description TODO
 * @author lizhao
 * @date 2015-11-8 上午12:28:25
 */
public class ProtraitButtonOnTouchListener implements OnCheckedChangeListener{
	ArrayList<CheckBox> cbList;
	Context context;
	int  i;
	public ProtraitButtonOnTouchListener(Context context,int i,ArrayList<CheckBox> cbList){
		this.context = context;
		this.i = i;
		this.cbList =cbList;
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		System.out.println("哈哈，我被点击了");
		if(isChecked){
			cbList.add((CheckBox) buttonView);
			Log.d("ProtraitButtonOnTouchListener", "我被加入到数组里面了");
		}else{
			cbList.remove((CheckBox) buttonView);
			Log.d("ProtraitButtonOnTouchListener", "我被移除数组了");
		}
		try {
			MidiCreateUtil.write(context, "key", i);//写入文件
			Thread.sleep(200);//睡100ms，避免连续点击导致破音
			MidiPlayer.play(context, "key");//播放
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
