package com.pianostudy.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

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

import com.loopj.android.http.TextHttpResponseHandler;
import com.piano.view.CustomApplcation;
import com.pianostudy.R;
import com.pianostudy.ui.info.ItemInfo;
import com.pianostudy.ui.info.TestType;
import com.pianostudy.ui.manager.MidiBaseManager;
import com.pianostudy.ui.thread.PlayerThread;
import com.pianostudy.ui.util.GeiliRestClient;

/**
 * 分析页面显示的键盘
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-11-10 上午1:13:47
 */
public class AnalysysPortraitButtonActivity extends Activity implements
		OnClickListener {

	/**
	 * 十六个白键
	 */
	private CheckBox w9, w10, w11, w12, w13, w14, w15, w16;
	// /**
	// * 十个黑键
	// */
	// private CheckBox b6, b7, b8, b9, b10;
	/**
	 * 所有的钢琴键
	 */
	public ArrayList<CheckBox> allCheckBoxList;

	private Button btn_menu1, btn_menu2, btn_menu3, btn_menu4, btn_menu5,
			btn_menu6, btn_menu7, btn_menu8;
	private Button []btns = new Button[]{btn_menu1, btn_menu2, btn_menu3, btn_menu4, btn_menu5,
		btn_menu6, btn_menu7, btn_menu8};
	private String rightText,s;
	private Object[] values;
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
	//private Button okButton;
	/**
	 * play按钮
	 */
	private Button playButton;
	/**
	 * 提示信息，TextView
	 */
	private TextView tv_reminder,txt_tips;
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
	private String userName;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_analysysportraitbutton);
		// cbList = new ArrayList<CheckBox>();
		initData();
		initView();
		// resetKey();
		// initEvent();
		initButtonEvent();
		// initText();
		playerThread = new PlayerThread(this, itemMode, itemType);
		initBtnText();
	}

	/**
	 * 实例化所有的钢琴键
	 */
	private void initView() {
		button_note_name = (Button) findViewById(R.id.button_note_name);
		// b6 = (CheckBox) findViewById(R.id.b6);
		// b7 = (CheckBox) findViewById(R.id.b7);
		// b8 = (CheckBox) findViewById(R.id.b8);
		// b9 = (CheckBox) findViewById(R.id.b9);
		// b10 = (CheckBox) findViewById(R.id.b10);
		// w9 = (CheckBox) findViewById(R.id.w9);
		// w10 = (CheckBox) findViewById(R.id.w10);
		// w11 = (CheckBox) findViewById(R.id.w11);
		// w12 = (CheckBox) findViewById(R.id.w12);
		// w13 = (CheckBox) findViewById(R.id.w13);
		// w14 = (CheckBox) findViewById(R.id.w14);
		// w15 = (CheckBox) findViewById(R.id.w15);
		// w16 = (CheckBox) findViewById(R.id.w16);

		// 按音符值大小将钢琴键添加至allCheckBoxList数组之中
		// allCheckBoxList = new ArrayList<CheckBox>();
		// allCheckBoxList.add(w9);
		// allCheckBoxList.add(b6);
		// allCheckBoxList.add(w10);
		// allCheckBoxList.add(b7);
		// allCheckBoxList.add(w11);
		// allCheckBoxList.add(b8);
		// allCheckBoxList.add(w12);

		// allCheckBoxList.add(w13);
		// allCheckBoxList.add(b9);
		// allCheckBoxList.add(w14);
		// allCheckBoxList.add(b10);
		// allCheckBoxList.add(w15);
		// allCheckBoxList.add(w16);

		/*btn_menu1 = (Button) findViewById(R.id.btn_menu1);
		btn_menu2 = (Button) findViewById(R.id.btn_menu2);
		btn_menu3 = (Button) findViewById(R.id.btn_menu3);
		btn_menu4 = (Button) findViewById(R.id.btn_menu4);
		btn_menu5 = (Button) findViewById(R.id.btn_menu5);
		btn_menu6 = (Button) findViewById(R.id.btn_menu6);
		btn_menu7 = (Button) findViewById(R.id.btn_menu7);
		btn_menu8 = (Button) findViewById(R.id.btn_menu8);

		btn_menu1.setOnClickListener(this);
		btn_menu2.setOnClickListener(this);
		btn_menu3.setOnClickListener(this);
		btn_menu4.setOnClickListener(this);
		btn_menu5.setOnClickListener(this);
		btn_menu6.setOnClickListener(this);
		btn_menu7.setOnClickListener(this);
		btn_menu8.setOnClickListener(this);*/
		
		btns[0] = (Button) findViewById(R.id.btn_menu1);
		btns[1] = (Button) findViewById(R.id.btn_menu2);
		btns[2] = (Button) findViewById(R.id.btn_menu3);
		btns[3] = (Button) findViewById(R.id.btn_menu4);
		btns[4] = (Button) findViewById(R.id.btn_menu5);
		btns[5] = (Button) findViewById(R.id.btn_menu6);
		btns[6] = (Button) findViewById(R.id.btn_menu7);
		btns[7] = (Button) findViewById(R.id.btn_menu8);
		
		btns[0].setOnClickListener(this);
		btns[1].setOnClickListener(this);
		btns[2].setOnClickListener(this);
		btns[3].setOnClickListener(this);
		btns[4].setOnClickListener(this);
		btns[5].setOnClickListener(this);
		btns[6].setOnClickListener(this);
		btns[7].setOnClickListener(this);
		

		tv_itemInfo = (TextView) findViewById(R.id.tv_item_info);
		tv_reminder = (TextView) findViewById(R.id.tv_reminder);
		txt_tips = (TextView) findViewById(R.id.txt_tips);
		playButton = (Button) findViewById(R.id.play);
		//okButton = (Button) findViewById(R.id.ok);
		nextItemButton = (Button) findViewById(R.id.next_item);
		nextLevelButton = (Button) findViewById(R.id.next_level);
		lastItemButton = (Button) findViewById(R.id.last_item);
		lastLevelButton = (Button) findViewById(R.id.last_level);

	}

	private void initBtnText() {
		ItemInfo info = playerThread.getText();
		int len = info.names.length;
		int index = info.index;
		rightText = info.names[index];
		// System.out.println("text:" + text + " index:" + index);
		int k = new Random().nextInt(8);
		btns[k].setText(info.names[index]);
		for(int i = (k+1)%8,j=1; j <= 7; i++,j++){
			if(i == 8){
				i = 0;
			}
			btns[i].setText(info.names[(index + j) % len]);
		}
		
		/*btn_menu1.setText(info.names[index]);
		btn_menu2.setText(info.names[(index + 1) % len]);
		btn_menu3.setText(info.names[(index + 2) % len]);
		btn_menu4.setText(info.names[(index + 3) % len]);
		btn_menu5.setText(info.names[(index + 4) % len]);
		btn_menu6.setText(info.names[(index + 5) % len]);
		btn_menu7.setText(info.names[(index + 6) % len]);
		btn_menu8.setText(info.names[(index + 7) % len]);*/

	}

	/**
	 * 设置当前的题目数据
	 */
	private void initData() {
		itemType = getIntent().getIntExtra("itemType", 0);
		itemMode = TestType.ITEM_MODE_ANALYSY;
		// midiBaseManager = new MidiBaseManager();
		// midiBaseManager.init(itemMode, itemType, 0, 0);
		// Log.d(tag, "itemMode"+itemMode + "itemType:"+itemType);
		
		switch (itemType) {
		case 0:
			strItemType = "旋律音程";
			break;
		case 1:
			strItemType = "和声音程";
			break;
		case 2:
			strItemType = "分解三和弦";
			break;
		case 3:
			strItemType = "三和弦";
			break;
		case 4:
			strItemType = "分解七和弦";
			break;
		case 5:
			strItemType = "七和弦";
			break;
		case 6:
			strItemType = "音阶";
			break;
		default:
			break;
		}
		
		strItemMode = "分析";

	}

	/**
	 * 初始化钢琴键的选择事件 给他们添加选择变化事件
	 */
	/*
	 * public void initEvent() {
	 * 
	 * for (int i = 0; i < allCheckBoxList.size(); i++) {
	 * allCheckBoxList.get(i).setOnCheckedChangeListener( new
	 * ProtraitButtonOnTouchListener(this, i, cbList)); }
	 * 
	 * }
	 */

	/**
	 * 设置下面的菜单项按钮的点击事件
	 */
	public void initButtonEvent() {
		playButton.setOnClickListener(this);
		//okButton.setOnClickListener(this);
		nextItemButton.setOnClickListener(this);
		nextLevelButton.setOnClickListener(this);
		lastItemButton.setOnClickListener(this);
		lastLevelButton.setOnClickListener(this);
		button_note_name.setOnClickListener(this);
	}

	/**
	 * 设置钢琴键的名字
	 */
	/*
	 * private void initText() {
	 * 
	 * for (int i = 0; i < allCheckBoxList.size(); i++) {
	 * allCheckBoxList.get(i).setText(MidiCreateUtil.notename[i]);
	 * allCheckBoxList.get(i).setTextSize(10); } for (int i = 0; i <
	 * allCheckBoxList.size(); i++) {
	 * allCheckBoxList.get(i).setTextColor(Color.TRANSPARENT); } }
	 */

	private boolean isShowName = false;

	/**
	 * 改变文本
	 */
	private void changeText() {
		if (isShowName) {
			isShowName = false;
			for (int i = 0; i < allCheckBoxList.size(); i++) {
				allCheckBoxList.get(i).setTextColor(Color.TRANSPARENT);
			}
		} else {
			isShowName = true;
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
		// changeText();
			if(isPlay){
				txt_tips.setText(s);
			}
			Log.d(tag, "button_note_name");
			break;
		case R.id.btn_menu1:
			if (rightText.equals(btns[0].getText())) {
				okevent();
			} else {
				notOk();
			}
			break;

		case R.id.btn_menu2:
			if (rightText.equals(btns[1].getText())) {
				okevent();
			} else {
				notOk();
			}
			break;

		case R.id.btn_menu3:
			if (rightText.equals(btns[2].getText())) {
				okevent();
			} else {
				notOk();
			}
			break;

		case R.id.btn_menu4:
			if (rightText.equals(btns[3].getText())) {
				okevent();
			} else {
				notOk();
			}
			break;

		case R.id.btn_menu5:
			if (rightText.equals(btns[4].getText())) {
				okevent();
			} else {
				notOk();
			}
			break;

		case R.id.btn_menu6:
			if (rightText.equals(btns[5].getText())) {
				okevent();
			} else {
				notOk();
			}
			break;

		case R.id.btn_menu7:
			if (rightText.equals(btns[6].getText())) {
				okevent();
			} else {
				notOk();
			}
			break;

		case R.id.btn_menu8:
			if (rightText.equals(btns[7].getText())) {
				okevent();
			} else {
				notOk();
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 点击play响应的事件
	 * @throws Exception 
	 */
	private void playEvent() throws Exception {
		isPlay = true;
		// resetKey();
		// initEvent();
		s = playerThread.play();
		tv_reminder.setText("请选择正确答案！！！");
		txt_tips.setText("");
		
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
		// resetKey();
		// initEvent();
		// play.setText("Play");
		tv_reminder.setText("请点击'Play键听音'！！！");
		txt_tips.setText("");
		isPlay = false;
		str = playerThread.setnext(dir);
		tv_itemInfo.setText(str);
		initBtnText();
	}

	/**
	 * 重置所有按键
	 */
	/*
	 * public void resetKey() { for (int i = 0; i < allCheckBoxList.size(); i++)
	 * { allCheckBoxList.get(i).setOnCheckedChangeListener(null);
	 * allCheckBoxList.get(i).setChecked(false); } cbList.clear(); }
	 */

	/**
	 * 点击ok键响应的事件
	 */
	public void okevent() {
		String str;
		if (!isPlay) {
		} else {
			// boolean r = playerThread.okAna(cbList);
			// boolean r = playerThread.okAna(cbList);
			// resetKey();
			// initEvent();
			// 进行判断，如果正确，进行下一题

			tv_reminder.setText("恭喜您，答对了！ 请点击'Play'键听音！！");
			
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
			
			txt_tips.setText("");
			isPlay = false;// 设置不是play模式
			str = playerThread.setnext(1);// 下一题，并返回
			tv_itemInfo.setText(str);
		}
		initBtnText();
	}

	public void notOk() {
		isPlay = true;
		tv_reminder.setText("不好意思，答错了！ 请点击'play'键重新听音！！");
		
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
		
		txt_tips.setText("");
	}

	public Object[] generateRandom(int len){
		Random random = new Random();
        values = new Object[len];
        HashSet<Integer> hashSet = new HashSet<Integer>();
        
        // 生成随机数字并存入HashSet
        while(hashSet.size() < len){
            hashSet.add(random.nextInt(len) + 1);
        }
        
        values = hashSet.toArray();
        return values;
	}
	
	/*
	 * public void okevent() { String str; if (!isPlay) { } else { // boolean r
	 * = playerThread.okAna(cbList); boolean r = playerThread.okAna(cbList); //
	 * resetKey(); // initEvent(); // 进行判断，如果正确，进行下一题 if (r) {
	 * tv_reminder.setText("恭喜你，答对了:请点击'Play键听音'！！！"); isPlay = false;//
	 * 设置不是play模式 str = playerThread.setnext(1);// 下一题，并返回
	 * tv_itemInfo.setText(str); } else { isPlay = true;
	 * tv_reminder.setText("不好意思，答错了:请点击音符键后点确认键！！！"); } } }
	 */
}
