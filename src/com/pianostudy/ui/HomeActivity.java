package com.pianostudy.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.piano.adapter.LeftMenuAdapter;
import com.piano.adapter.LeftMenuItem;
import com.piano.bean.User;
import com.piano.dialog.ProcessDialog;
import com.piano.fragment.AnalyzeFragment;
import com.piano.fragment.MusicFragment;
import com.piano.fragment.VolumeFragment;
import com.piano.view.CustomApplcation;
import com.pianostudy.R;
import com.pianostudy.ui.util.CommonUtil;
import com.pianostudy.ui.util.SharedPreferencesUtil;

public class HomeActivity extends FragmentActivity implements OnClickListener,
		OnItemClickListener, OnMenuItemClickListener {

	private MusicFragment musicFragment;
	private AnalyzeFragment analyzeFragment;
	private VolumeFragment volumeFragment;
	private Fragment[] fragments;
	private Button[] mTabs;
	private int index;
	private int currentTabIndex;
	private static long firstTime;
	private ListView menuList;
	private SlidingMenu menu;
	private LeftMenuAdapter menuAdapter;
	private List<LeftMenuItem> leftMenu = new ArrayList<LeftMenuItem>();
	private TextView account_text;
	private ImageView imageView, menu_right;
	private ProcessDialog processDialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);

		initLeftSlide();
		initView();
		initTab();
		initLeftMenu();
	}

	private void initView() {

		// 上侧点击展开侧边栏的图标
		imageView = (ImageView) findViewById(R.id.slide_left);
		menu_right = (ImageView) findViewById(R.id.menu_right);

		mTabs = new Button[3];
		mTabs[0] = (Button) findViewById(R.id.tab_music);
		mTabs[1] = (Button) findViewById(R.id.tab_analyze);
		mTabs[2] = (Button) findViewById(R.id.tab_volume);

		mTabs[0].setOnClickListener(this);
		mTabs[1].setOnClickListener(this);
		mTabs[2].setOnClickListener(this);
		imageView.setOnClickListener(this);
		menu_right.setOnClickListener(this);

		account_text = (TextView) findViewById(R.id.account_text);
		account_text.setText(((CustomApplcation) getApplication()).getUser()
				.getUserName());

		currentTabIndex = 1;
		mTabs[currentTabIndex].setSelected(true);

		menuList = (ListView) findViewById(R.id.menu_list);
		
	}

	private void initTab() {

		musicFragment = new MusicFragment();
		analyzeFragment = new AnalyzeFragment();
		volumeFragment = new VolumeFragment();
		fragments = new Fragment[] { musicFragment, analyzeFragment,
				volumeFragment };

		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, analyzeFragment)
				.show(analyzeFragment).commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tab_music:
			index = 0;
			break;
		case R.id.tab_analyze:
			index = 1;
			break;
		case R.id.tab_volume:
			index = 2;
			break;
		case R.id.slide_left:
			menu.toggle();
			break;
		case R.id.menu_right:
			showPopup(v);
			break;
		}

		if (currentTabIndex != index) {
			FragmentTransaction trx = getSupportFragmentManager()
					.beginTransaction();
			trx.remove(fragments[currentTabIndex]);
			if (!fragments[index].isAdded()) {
				trx.replace(R.id.fragment_container, fragments[index]);
			}
			trx.show(fragments[index]).commit();
		}
		mTabs[currentTabIndex].setSelected(false);
		mTabs[index].setSelected(true);
		currentTabIndex = index;

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

	}

	private void initLeftMenu() {

		LeftMenuItem account = new LeftMenuItem("用户统计");
		leftMenu.add(account);

		LeftMenuItem score = new LeftMenuItem("我的成绩");
		leftMenu.add(score);
		
		LeftMenuItem authorize = new LeftMenuItem("授权");	
		
		String userName = CustomApplcation.getInstance().getUser()
				.getUserName();
		if ("root".equals(userName)) {
			leftMenu.add(authorize);
			leftMenu.remove(score);
		}

		LeftMenuItem about = new LeftMenuItem("关于超级耳朵");
		leftMenu.add(about);

		menuAdapter = new LeftMenuAdapter(HomeActivity.this,
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
			if(!CommonUtil.isNetworkAvailable(this)){
				Toast.makeText(this, "网络不可用,请检查网络", Toast.LENGTH_SHORT).show();
				return;
			}else{
				startActivity(new Intent(HomeActivity.this,	UserStatisticsActivity.class));
			}
			break;

		case "授权":
			if(!CommonUtil.isNetworkAvailable(this)){
				Toast.makeText(this, "网络不可用,请检查网络", Toast.LENGTH_SHORT).show();
			}else{
				startActivity(new Intent(HomeActivity.this, AuthorizeActivity.class));
			}
			break;

		case "我的成绩":
			startActivity(new Intent(HomeActivity.this, MyScoreActivity.class));
			break;

		case "关于超级耳朵":
			startActivity(new Intent(HomeActivity.this, AboutActivity.class));
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
			((CustomApplcation) getApplication()).setUser(user);
			SharedPreferencesUtil.writeString(SharedPreferencesUtil
					.getSharedPreference(getApplicationContext(), "login"),
					"userName", user.getUserName());

			startActivity(new Intent(HomeActivity.this, LoginActivity.class));
			finish();
			return true;
		default:
			return false;
		}
	}

}
