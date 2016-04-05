package com.pianostudy.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.piano.adapter.LeftMenuAdapter;
import com.piano.adapter.LeftMenuItem;
import com.piano.bean.User;
import com.piano.view.CustomApplcation;
import com.piano.view.HeaderLayout.onLeftImageButtonClickListener;
import com.piano.view.HeaderLayout.onRightImageButtonClickListener;
import com.pianostudy.R;
import com.pianostudy.ui.util.SharedPreferencesUtil;

/**
 * ���ߵ�ҳ��
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-11-5 ����10:06:57
 */
public class PitchActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener,OnMenuItemClickListener{
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button b5;
	private Button b6;
	private Button b7;
	private Button b8;
	private TextView account_text;

	private ListView menuList;
	private SlidingMenu menu;
	private LeftMenuAdapter menuAdapter;
	private List<LeftMenuItem> leftMenu = new ArrayList<LeftMenuItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pitch);

		initLeftSlide();
		initView();
		initLeftMenu();
	}

	/**
	 * ��ʼ��
	 */
	private void initView() {

		initTopBarForBothBtn("音高", R.drawable.icon_menu,
				new onLeftImageButtonClickListener() {

					@Override
					public void onClick() {
						menu.toggle();
					}
				}, R.drawable.menu_right,
				new onRightImageButtonClickListener() {

					@Override
					public void onClick(View v) {
						showPopup(v);
					}
				});

		b1 = (Button) findViewById(R.id.volume_1);
		b2 = (Button) findViewById(R.id.volume_2);
		b3 = (Button) findViewById(R.id.volume_3);
		b4 = (Button) findViewById(R.id.volume_4);
		b5 = (Button) findViewById(R.id.volume_5);
		b6 = (Button) findViewById(R.id.volume_6);
		b7 = (Button) findViewById(R.id.volume_7);
		b8 = (Button) findViewById(R.id.volume_8);

		// user = (ImageView) findViewById(R.id.base_activity_back);
		// user.setOnClickListener(this);

		// btn_register = (Button) findViewById(R.id.btn_register);
		// btn_register.setOnClickListener(this);

		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);

		menuList = (ListView) findViewById(R.id.menu_list);

	}

	/**
	 * ��д����¼�
	 */
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, PitchPortraitButtonActivity.class);
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
		// case R.id.btn_register:
		// new RegisterDialog(PitchActivity.this).show();
		// break;
		// case R.id.base_activity_back:
		// menu.toggle();
		// break;
		default:
			break;
		}

	}
	
	public void showPopup(View v) {
	    PopupMenu popup = new PopupMenu(this, v);
	    MenuInflater inflater = popup.getMenuInflater();
	    inflater.inflate(R.menu.main, popup.getMenu());
	    popup.show();
	    popup.setOnMenuItemClickListener(this);
	}

	private void initLeftSlide() {

		// configure the SlidingMenu
		menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.LEFT);
		// ���ô�����Ļ��ģʽ
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);

		// ���û����˵���ͼ�Ŀ��
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// ���ý��뽥��Ч���ֵ
		menu.setFadeDegree(0.35f);
		/**
		 * SLIDING_WINDOW will include the Title/ActionBar in the content
		 * section of the SlidingMenu, while SLIDING_CONTENT does not.
		 */
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		// Ϊ�໬�˵����ò���
		menu.setMenu(R.layout.leftmenu);
		
		account_text = (TextView) findViewById(R.id.account_text);
		account_text.setText(((CustomApplcation) getApplication()).getUser()
				.getUserName());

	}

	private void initLeftMenu() {

		LeftMenuItem account = new LeftMenuItem("用户统计");
		leftMenu.add(account);

		LeftMenuItem myTrace = new LeftMenuItem("授权");
		leftMenu.add(myTrace);

		LeftMenuItem set = new LeftMenuItem("我的成绩");
		leftMenu.add(set);

		LeftMenuItem about = new LeftMenuItem("关于超级耳朵");
		leftMenu.add(about);

		menuAdapter = new LeftMenuAdapter(PitchActivity.this,
				R.layout.menu_list_item, leftMenu);
		menuList.setAdapter(menuAdapter);
		menuList.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		LeftMenuItem menuItem = leftMenu.get(position);
		String menuName = menuItem.getLeftText();
		switch (menuName) {
		case "用户统计":
			startActivity(new Intent(PitchActivity.this, UserStatisticsActivity.class));
			break;

		case "授权":
			startActivity(new Intent(PitchActivity.this, AuthorizeActivity.class));
			break;

		case "我的成绩":
			startActivity(new Intent(PitchActivity.this, HomeActivity.class));
			break;

		case "关于超级耳朵":
			startActivity(new Intent(PitchActivity.this, AboutActivity.class));
			break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_logout:
			User user = new User();
			user.setUserName("0");
			((CustomApplcation)getApplication()).setUser(user);
			SharedPreferencesUtil.writeString(SharedPreferencesUtil.getSharedPreference(getApplicationContext(), "login"),"userName",user.getUserName());
			
			this.finish();
			//startActivity(new Intent(PitchActivity.this,LoginActivity.class));
			return true;
		default:
			return false;
		}
	}

}
