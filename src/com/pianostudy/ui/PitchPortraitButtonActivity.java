package com.pianostudy.ui;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.piano.view.CustomApplcation;
import com.pianostudy.R;
import com.pianostudy.ui.info.TestType;
import com.pianostudy.ui.listener.ProtraitButtonOnTouchListener;
import com.pianostudy.ui.manager.MidiBaseManager;
import com.pianostudy.ui.thread.PlayerThread;
import com.pianostudy.ui.util.GeiliRestClient;
import com.pianostudy.ui.util.MidiCreateUtil;
import com.pianostudy.ui.util.SITI_LogDebug;

/**
 * 音高钢琴键竖排的界面
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-11-7 下午10:22:03
 */
public class PitchPortraitButtonActivity extends Activity implements
		OnClickListener {

	/**
	 * 十六个白键
	 */
	private CheckBox  w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, w13,
			w14, w15, w16;
	private Button w1;
	/**
	 * 十个黑键
	 */
	private CheckBox b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
	/**
	 * 所有的钢琴键
	 */
	public ArrayList<CheckBox> allCheckBoxList;
	/**
	 * 上一等级
	 */
	private Button lastLevelButton;
	/**
	 * 上一关卡
	 */
	private Button lastItemButton;
	/**
	 * 下一等级
	 */
	private Button nextLevelButton;
	/**
	 * 下一关卡
	 */
	private Button nextItemButton;
	/**
	 * 确认按钮
	 */
	private Button okButton;
	/**
	 * play按钮
	 */
	private Button playButton;
	/**
	 * 提示信息，TextView
	 */
	private TextView tv_reminder;
	/**
	 * 当前等级关卡信息，TextView
	 */
	private TextView tv_itemInfo;

	/**
	 * 用于打印log
	 */
	private String tag = "PortraitButtonActivity";

	/**
	 * 是否是play模式
	 */
	private boolean isPlay;
	/**
	 * 玩的模式
	 */
	private int itemMode;
	private String strItemMode;
	/**
	 * 玩的类型
	 */
	private int itemType;
	private String strItemType;
	/**
	 * 题目管理类
	 */
	public MidiBaseManager midiBaseManager;

	/**
	 * 新开线程用于播放
	 */
	private PlayerThread playerThread;
	private Button button_note_name;
	private ArrayList<CheckBox> cbList;
	
	private String str_itemInfo,userName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_portraitbutton);
		cbList = new ArrayList<CheckBox>();
		initData();
		initView();
		resetKey();
		initEvent();
		initButtonEvent();
		initText();
		playerThread = new PlayerThread(this, itemMode, itemType);

	}

	/**
	 * 设置当前的题目数据
	 */
	private void initData() {
		itemType = getIntent().getIntExtra("itemType", 0);
		itemMode = TestType.ITEM_MODE_PITCH;
		
		System.out.println("itemType:"+itemType+"itemMode:"+itemMode);
		
		switch (itemType) {
		case 0:
			strItemType = "单音";
			break;
		case 1:
			strItemType = "旋律音程";
			break;
		case 2:
			strItemType = "和声音程";
			break;
		case 3:
			strItemType = "分解三和弦";
			break;
		case 4:
			strItemType = "三和弦";
			break;
		case 5:
			strItemType = "分解七和弦";
			break;
		case 6:
			strItemType = "七和弦";
			break;
		case 7:
			strItemType = "音阶";
			break;
		default:
			break;
		}
		
		strItemMode = "音高";
		
		// midiBaseManager = new MidiBaseManager();
		// midiBaseManager.init(itemMode, itemType, 0, 0);
		// Log.d(tag, "itemMode"+itemMode + "itemType:"+itemType);

	}

	/**
	 * 初始化界面 实例化数组和checkBox
	 */
	public void initView() {
		button_note_name = (Button) findViewById(R.id.button_note_name);
		allCheckBoxList = new ArrayList<CheckBox>();
		w1 = (Button) findViewById(R.id.w1);
		w2 = (CheckBox) findViewById(R.id.w2);
		w3 = (CheckBox) findViewById(R.id.w3);
		w4 = (CheckBox) findViewById(R.id.w4);
		w5 = (CheckBox) findViewById(R.id.w5);
		w6 = (CheckBox) findViewById(R.id.w6);
		w7 = (CheckBox) findViewById(R.id.w7);
		w8 = (CheckBox) findViewById(R.id.w8);
		w9 = (CheckBox) findViewById(R.id.w9);
		w10 = (CheckBox) findViewById(R.id.w10);
		w11 = (CheckBox) findViewById(R.id.w11);
		w12 = (CheckBox) findViewById(R.id.w12);
		w13 = (CheckBox) findViewById(R.id.w13);
		w14 = (CheckBox) findViewById(R.id.w14);
		w15 = (CheckBox) findViewById(R.id.w15);
		w16 = (CheckBox) findViewById(R.id.w16);
		b1 = (CheckBox) findViewById(R.id.b1);
		b2 = (CheckBox) findViewById(R.id.b2);
		b3 = (CheckBox) findViewById(R.id.b3);
		b4 = (CheckBox) findViewById(R.id.b4);
		b5 = (CheckBox) findViewById(R.id.b5);
		b6 = (CheckBox) findViewById(R.id.b6);
		b7 = (CheckBox) findViewById(R.id.b7);
		b8 = (CheckBox) findViewById(R.id.b8);
		b9 = (CheckBox) findViewById(R.id.b9);
		b10 = (CheckBox) findViewById(R.id.b10);
		// 按音符值大小将钢琴键添加至allCheckBoxList数组之中
		allCheckBoxList.add(w9);
		allCheckBoxList.add(b6);
		allCheckBoxList.add(w10);
		allCheckBoxList.add(b7);
		allCheckBoxList.add(w11);
		allCheckBoxList.add(b8);
		allCheckBoxList.add(w12);

		allCheckBoxList.add(w13);
		allCheckBoxList.add(b9);
		allCheckBoxList.add(w14);
		allCheckBoxList.add(b10);
		allCheckBoxList.add(w15);
		allCheckBoxList.add(w16);

		// allCheckBoxList.add(w1);音高一样，就不进行设置了
		allCheckBoxList.add(b1);
		allCheckBoxList.add(w2);
		allCheckBoxList.add(b2);
		allCheckBoxList.add(w3);
		allCheckBoxList.add(b3);
		allCheckBoxList.add(w4);

		allCheckBoxList.add(w5);
		allCheckBoxList.add(b4);
		allCheckBoxList.add(w6);
		allCheckBoxList.add(b5);
		allCheckBoxList.add(w7);
		allCheckBoxList.add(w8);

		tv_itemInfo = (TextView) findViewById(R.id.tv_item_info);
		tv_reminder = (TextView) findViewById(R.id.tv_reminder);
		playButton = (Button) findViewById(R.id.play);
		okButton = (Button) findViewById(R.id.ok);
		nextItemButton = (Button) findViewById(R.id.next_item);
		nextLevelButton = (Button) findViewById(R.id.next_level);
		lastItemButton = (Button) findViewById(R.id.last_item);
		lastLevelButton = (Button) findViewById(R.id.last_level);
	

	}
	/**
	 * 初始化钢琴键的选择事件 给他们添加选择变化事件
	 */
	public void initEvent() {
		
		for (int i = 0; i < allCheckBoxList.size(); i++) {
			allCheckBoxList.get(i).setOnCheckedChangeListener(
					new ProtraitButtonOnTouchListener(this, i,cbList));
		}

	}

	/**
	 * 设置下面的菜单项按钮的点击事件
	 */
	public void initButtonEvent() {
		playButton.setOnClickListener(this);
		okButton.setOnClickListener(this);
		nextItemButton.setOnClickListener(this);
		nextLevelButton.setOnClickListener(this);
		lastItemButton.setOnClickListener(this);
		lastLevelButton.setOnClickListener(this);
		button_note_name.setOnClickListener(this);
		w1.setOnClickListener(this);
	}

	

	/**
	 * 设置钢琴键的名字
	 */
	private void initText() {
		
		w1.setText("F4");
		w1.setTextSize(10);
		for (int i = 0; i < allCheckBoxList.size(); i++) {
			allCheckBoxList.get(i).setText(MidiCreateUtil.notename[i]);
			allCheckBoxList.get(i).setTextSize(10);
		}
		w1.setTextColor(Color.TRANSPARENT);
		for (int i = 0; i < allCheckBoxList.size(); i++) {
			allCheckBoxList.get(i).setTextColor(Color.TRANSPARENT);
		}
	}
	private boolean isShowName = false;
	/**
	 * 改变文本
	 */
	private void changeText(){
		if (isShowName) {
	
			isShowName = false;
			w1.setTextColor(Color.TRANSPARENT);
			for (int i = 0; i < allCheckBoxList.size(); i++) {
				allCheckBoxList.get(i).setTextColor(Color.TRANSPARENT);
			}
		} else {
			
			isShowName = true;
			w1.setTextColor(Color.BLACK);
			for (int i = 0; i < allCheckBoxList.size(); i++) {
				allCheckBoxList.get(i).setTextColor(Color.BLACK);
			}
		}
	}
	@Override
	/**
	 * 实现OnClickListener中的onClick()方法
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			Log.d(tag, "play");
			try {
				playEvent();
			} catch (Exception e) {
				System.out.println("error:"+e.getMessage());
				e.printStackTrace();
			}
			break;
		case R.id.ok:
			okevent();
			Log.d(tag, "ok");
			break;
		case R.id.next_item:
			moveevent(1);
			Log.d(tag, "next_item");
			break;
		case R.id.next_level:
			moveevent(2);
			Log.d(tag, "next_level");
			break;
		case R.id.last_item:
			moveevent(-1);
			Log.d(tag, "last_item");
			break;
		case R.id.last_level:
			moveevent(-2);
			Log.d(tag, "last_level");
			break;
		case R.id.button_note_name:
			changeText();
			Log.d(tag, "button_note_name");
			Log.d(tag, cbList.size()+"");
			break;
		case R.id.w1:
			w16.setChecked(!w16.isChecked());
			break;
		default:
			break;
		}
	}

	/**
	 * 重置所有按键
	 */
	public void resetKey() {
//		
		for (int i = 0; i < allCheckBoxList.size(); i++) {
			allCheckBoxList.get(i).setOnCheckedChangeListener(null);
			allCheckBoxList.get(i).setChecked(false);
		}
		cbList.clear();
	}

	/**
	 * 点击play响应的事件
	 * @throws Exception 
	 */
	private void playEvent() throws Exception {
		isPlay = true;
		resetKey();
		initEvent();
		String s = playerThread.play();
		tv_reminder.setText("现在是答题模式：请点击音符键后点'Ok'键！！！" + s);
		
		userName = ((CustomApplcation) getApplication())
				.getUser().getUserName();
		
		String api = "api/user/log";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("str_itemInfo", strItemMode + "_" + strItemType + "_"+ tv_itemInfo.getText());
		jsonObject.put("userName", userName);

		StringEntity stringEntity = new StringEntity(jsonObject.toString(),
				"utf-8");
		
		GeiliRestClient.post(this, api, stringEntity,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {

						try {
							//handleWriteLog();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("e.getCause()"+e.getCause());
						}

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {
						Log.i("LoginActivity", "failure" + "\narg0:" + arg0 + 
								" \narg1:"+arg1+"\narg2:" + arg2 +"\narg3:"+arg3.getCause());
						
					}
				});
	}

	/**
	 * 点击4个移动键各代表的事件
	 * 
	 * @param dir
	 */
	public void moveevent(int dir) {
		String str;
		resetKey();
		initEvent();
		// play.setText("Play");
		tv_reminder.setText("请点击'Play键听音'！！！");
		isPlay = false;
		str_itemInfo = playerThread.setnext(dir);
		tv_itemInfo.setText(str_itemInfo);
		
	}

	/**
	 * 点击ok键响应的事件
	 */
	public void okevent() {
		String str;
		if (!isPlay) {
		} else {
			boolean r = playerThread.ok(cbList);
			resetKey();
			initEvent();
			// 进行判断，如果正确，进行下一题
			if (r) {
				tv_reminder.setText("恭喜你，答对了:请点击'Play键听音'！！！");
				
				String api = "api/user/answer";
				JSONObject jsonObject = new JSONObject();
				StringEntity stringEntity = null;
				try {
					jsonObject.put("str_itemInfo", "答对了!!!");
					jsonObject.put("userName", userName);

					stringEntity = new StringEntity(jsonObject.toString(),
							"utf-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				GeiliRestClient.post(this, api, stringEntity,
						new TextHttpResponseHandler() {

							@Override
							public void onSuccess(int arg0, Header[] arg1, String arg2) {

								try {
									//handleWriteLog();
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

							@Override
							public void onFailure(int arg0, Header[] arg1, String arg2,
									Throwable arg3) {
								Log.i("LoginActivity", "failure" + "\narg0:" + arg0 + 
										" \narg1:"+arg1+"\narg2:" + arg2 +"\narg3:"+arg3.getCause());
								
							}
						});
				
				
				isPlay = false;// 设置不是play模式
				str_itemInfo = playerThread.setnext(1);// 下一题，并返回
				tv_itemInfo.setText(str_itemInfo);
			} else {
				
				String api = "api/user/answer";
				JSONObject jsonObject = new JSONObject();
				StringEntity stringEntity = null;
				try {
					jsonObject.put("str_itemInfo", "答错了!!!");
					jsonObject.put("userName", userName);

					stringEntity = new StringEntity(jsonObject.toString(),
							"utf-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				GeiliRestClient.post(this, api, stringEntity,
						new TextHttpResponseHandler() {

							@Override
							public void onSuccess(int arg0, Header[] arg1, String arg2) {

								try {
									//handleWriteLog();
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

							@Override
							public void onFailure(int arg0, Header[] arg1, String arg2,
									Throwable arg3) {
								Log.i("LoginActivity", "failure" + "\narg0:" + arg0 + 
										" \narg1:"+arg1+"\narg2:" + arg2 +"\narg3:"+arg3.getCause());
								
							}
						});
				
				isPlay = true;
				tv_reminder.setText("不好意思，答错了:请点击音符键后点确认键！！！");
			}
		}
	}

}
