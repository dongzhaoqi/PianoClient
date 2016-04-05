package com.pianostudy.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.pianostudy.ui.listener.ButtonTouchListener;
import com.pianostudy.ui.util.ButtonClickUtil;

import android.app.Activity;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

/**
 * �������еļ���
 */
public class ButtonActivity extends Activity implements View.OnClickListener {
	LinearLayout mLinearLayout;
	public HashMap<Integer, Integer> soundMap;
	public SoundPool soundPool;
	List<Button> buttonList;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
	//
//	private static final String TAG = "MainActivity";
//	private Button backButton;
//	private Button black1;
//	private Button black10;
//	private Button black2;
//	private Button black3;
//	private Button black4;
//	private Button black5;
//	private Button black6;
//	private Button black7;
//	private Button black8;
//	private Button black9;
//	// private Button logout;
//
//	private Button white1;
//	private Button white10;
//	private Button white11;
//	private Button white12;
//	private Button white13;
//	private Button white14;
//	private Button white15;
//	private Button white16;
//	private Button white2;
//	private Button white3;
//	private Button white4;
//	private Button white5;
//	private Button white6;
//	private Button white7;
//	private Button white8;
//	private Button white9;
//
//	LinearLayout mLinearLayout;
//	public HashMap<Integer, Integer> soundMap;
//	public SoundPool soundPool;
//	List<Button> buttonList;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		// requestWindowFeature();// ���ñ���
//
//		setContentView(R.layout.key_button);
//		// getWindow().setFeatureInt(7, 2130903047);
//		// getWindowManager().getDefaultDisplay().getWidth();
//		// getWindowManager().getDefaultDisplay().getHeight();
//		initView();
//		String str = getIntent().getStringExtra("tag");
//		System.out.println("tag:" + str);
//		initSound();
//		initEvent();
//	}
//
//	
//	
//	
//	/**
//	 * ���õ���¼�
//	 */
//	private void initEvent() {
//		for(int i = 0; i < buttonList.size(); i++){
//			buttonList.get(i).setOnClickListener(this);
//			Log.d(TAG, i+"");
//		}
//		white1.setOnTouchListener(new ButtonTouchListener(this, 1));
//		for (int i = 2; i < buttonList.size(); i++) {
//			buttonList.get(i).setOnTouchListener(
//					new ButtonTouchListener(this, i-1));
//		}
//	}
//
//	/**
//	 * �����������Ӧ����������
//	 */
//	private void initSound() {
//		this.soundMap = new HashMap();
//		// SoundPool ���� �ʺ϶̴��ҶԷ�Ӧ�ٶȱȽϸߵ��������Ϸ��Ч�򰴼���ȣ�
//		this.soundPool = new SoundPool(25, 3, 5);// ͬʱ���ŵ�����������������ͣ�������ת������
//		// soundPool.load(this, 2130968588, 1);//�����ģ���Դ�ļ�λ�ã�1
//		this.soundMap.put(Integer.valueOf(1),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white1, 1)));
//		this.soundMap.put(Integer.valueOf(2),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white2, 1)));
//		this.soundMap.put(Integer.valueOf(3),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white3, 1)));
//		this.soundMap.put(Integer.valueOf(4),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white4, 1)));
//		this.soundMap.put(Integer.valueOf(5),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white5, 1)));
//		this.soundMap.put(Integer.valueOf(6),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white6, 1)));
//		this.soundMap.put(Integer.valueOf(7),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white7, 1)));
//		this.soundMap.put(Integer.valueOf(8),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white7, 1)));
//		this.soundMap.put(Integer.valueOf(14),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white21, 1)));
//		this.soundMap.put(Integer.valueOf(15),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white22, 1)));
//		this.soundMap.put(Integer.valueOf(16),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white23, 1)));
//		this.soundMap.put(Integer.valueOf(17),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white24, 1)));
//		this.soundMap.put(Integer.valueOf(18),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white25, 1)));
//		this.soundMap.put(Integer.valueOf(19),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white26, 1)));
//		this.soundMap.put(Integer.valueOf(20),
//				Integer.valueOf(this.soundPool.load(this, R.raw.white27, 1)));
//		this.soundMap.put(Integer.valueOf(9),
//				Integer.valueOf(this.soundPool.load(this, R.raw.black1, 1)));
//		this.soundMap.put(Integer.valueOf(10),
//				Integer.valueOf(this.soundPool.load(this, R.raw.black2, 1)));
//		this.soundMap.put(Integer.valueOf(11),
//				Integer.valueOf(this.soundPool.load(this, R.raw.black3, 1)));
//		this.soundMap.put(Integer.valueOf(12),
//				Integer.valueOf(this.soundPool.load(this, R.raw.black4, 1)));
//		this.soundMap.put(Integer.valueOf(13),
//				Integer.valueOf(this.soundPool.load(this, R.raw.black5, 1)));
//		this.soundMap.put(Integer.valueOf(21),
//				Integer.valueOf(this.soundPool.load(this, R.raw.black21, 1)));
//		this.soundMap.put(Integer.valueOf(22),
//				Integer.valueOf(this.soundPool.load(this, R.raw.black22, 1)));
//		this.soundMap.put(Integer.valueOf(23),
//				Integer.valueOf(this.soundPool.load(this, R.raw.black23, 1)));
//		this.soundMap.put(Integer.valueOf(24),
//				Integer.valueOf(this.soundPool.load(this, R.raw.black24, 1)));
//		this.soundMap.put(Integer.valueOf(25),
//				Integer.valueOf(this.soundPool.load(this, R.raw.black25, 1)));
//	}
//
//	/**
//	 * ��ʼ�����а���
//	 */
//	private void initView() {
//		// this.logout = ((Button)findViewById(2131361843));
//		// this.backButton = ((Button)findViewById(2131361842));
//		// this.backButton.setOnClickListener(this);
//		buttonList = new ArrayList<Button>();
//		this.white1 = ((Button) findViewById(R.id.white1));
//		this.white2 = ((Button) findViewById(R.id.white2));
//		this.white3 = ((Button) findViewById(R.id.white3));
//		this.white4 = ((Button) findViewById(R.id.white4));
//		this.white5 = ((Button) findViewById(R.id.white5));
//		this.white6 = ((Button) findViewById(R.id.white6));
//		this.white7 = ((Button) findViewById(R.id.white7));
//		this.white8 = ((Button) findViewById(R.id.white8));
//		this.white9 = ((Button) findViewById(R.id.white9));
//		this.white10 = ((Button) findViewById(R.id.white10));
//		this.white11 = ((Button) findViewById(R.id.white11));
//		this.white12 = ((Button) findViewById(R.id.white12));
//		this.white13 = ((Button) findViewById(R.id.white13));
//		this.white14 = ((Button) findViewById(R.id.white14));
//		this.white15 = ((Button) findViewById(R.id.white15));
//		this.white16 = ((Button) findViewById(R.id.white16));
//		this.black1 = ((Button) findViewById(R.id.black1));
//		this.black2 = ((Button) findViewById(R.id.black2));
//		this.black3 = ((Button) findViewById(R.id.black3));
//		this.black4 = ((Button) findViewById(R.id.black4));
//		this.black5 = ((Button) findViewById(R.id.black5));
//		this.black6 = ((Button) findViewById(R.id.black6));
//		this.black7 = ((Button) findViewById(R.id.black7));
//		this.black8 = ((Button) findViewById(R.id.black8));
//		this.black9 = ((Button) findViewById(R.id.black9));
//		this.black10 = ((Button) findViewById(R.id.black10));
//		buttonList.add(white1);
//		buttonList.add(white16);
//		buttonList.add(white2);
//		buttonList.add(white3);
//		buttonList.add(white4);
//		buttonList.add(white5);
//		buttonList.add(white6);
//		buttonList.add(white7);
//		buttonList.add(white8);
//		
//		buttonList.add(black1);
//		buttonList.add(black2);
//		buttonList.add(black3);
//		buttonList.add(black4);
//		buttonList.add(black5);
//		
//		buttonList.add(white9);
//		buttonList.add(white10);
//		buttonList.add(white11);
//		buttonList.add(white12);
//		buttonList.add(white13);
//		buttonList.add(white14);
//		buttonList.add(white15);
//		buttonList.add(white16);
//	
//		buttonList.add(black6);
//		buttonList.add(black7);
//		buttonList.add(black8);
//		buttonList.add(black9);
//		buttonList.add(black10);
//	}
//
//	// public void onClick(View paramView) {
//	// switch (paramView.getId()) {
//	// default:
//	// case 2131361842:
//	// }
//	// while (true){
//	// return;
//	// finish();
//	// }
//	// }
//
//	// class ButtonTouchListener implements View.OnTouchListener {
//	//
//	// private ButtonActivity ba;
//	// private int i;
//	// public ButtonTouchListener(ButtonActivity ButtonActivity,int i ){
//	// this.ba = ba;
//	// this.i = i;
//	// }
//	//
//	// public boolean onTouch(View paramView, MotionEvent paramMotionEvent) {
//	// if (paramMotionEvent.getAction() == 0)// ����
//	// ba.soundPool.play(soundMap.get(1), 1.0F, 1.0F, 1, 0, 1.0F);
//	// return false;
//	// }
//	// }
//
//	@Override
//	public void onClick(View v) {
//		if (ButtonClickUtil.isFastDoubleClick()) {
//			return;
//		}
//	}
//}
//
//// public boolean onMenuItemClick(MenuItem paramMenuItem)
//// {
//// switch (paramMenuItem.getItemId())
//// {
//// default:
//// case 2131361852:
//// }
//// for (int i = 0; ; i = 1)
//// {
//// return i;
//// User localUser = new User();
//// localUser.setUserName("0");
//// ((CustomApplcation)getApplication()).setUser(localUser);
//// SharedPreferencesUtil.writeString(SharedPreferencesUtil.getSharedPreference(getApplicationContext(),
//// "login"), "userName", localUser.getUserName());
//// startActivity(new Intent(this, LoginActivity.class));
//// finish();
//// }
//// }
////
//// public void showPopup(View paramView)
//// {
//// PopupMenu localPopupMenu = new PopupMenu(this, paramView);
//// localPopupMenu.getMenuInflater().inflate(2131296256,
//// localPopupMenu.getMenu());
//// localPopupMenu.show();
//// localPopupMenu.setOnMenuItemClickListener(this);
//// }
//// }
