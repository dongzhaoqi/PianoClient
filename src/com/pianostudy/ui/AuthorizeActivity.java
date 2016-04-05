package com.pianostudy.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.loopj.android.http.TextHttpResponseHandler;
import com.piano.adapter.VerifiedAdapter;
import com.piano.bean.User;
import com.piano.bean.VerifiedUser;
import com.pianostudy.R;
import com.pianostudy.ui.util.CommonUtil;
import com.pianostudy.ui.util.GeiliRestClient;

public class AuthorizeActivity extends BaseActivity implements OnClickListener {

	private ListView verified_list;
	private VerifiedAdapter myAdapter;
	public ArrayList<HashMap<String, Object>> dlist;
	private TextView userName_txt;
	private Button btn_authorize_selected, btn_authorize_all;
	private CheckBox verified_checkBox;
	private Boolean[] test = { false, true };
	private int length;
	private Boolean isVerified;
	private String userName,school,gender,birthdate,lastLoginTime;
	private String[] userArray;
	private int[] userIdList;
	private int[] isVerifiedList;
	private ArrayList<VerifiedUser> list = new ArrayList<VerifiedUser>();

	private ScrollView scroll_test;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_authorize);

		initTopBarForLeft("用户列表");
		showProcessDialog();
		try {
			getData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initView();
		
	}

	private void initView() {
		
		verified_list = (ListView) findViewById(R.id.verified_list);

		btn_authorize_selected = (Button) findViewById(R.id.btn_authorize_selected);
		btn_authorize_all = (Button) findViewById(R.id.btn_authorize_all);
		btn_authorize_selected.setOnClickListener(this);
		btn_authorize_all.setOnClickListener(this);

		myAdapter = new VerifiedAdapter(AuthorizeActivity.this, list);

		verified_list.setAdapter(myAdapter);
		
		verified_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				VerifiedUser user = (VerifiedUser) myAdapter.getItem(position);
				
				Intent intent = new Intent(AuthorizeActivity.this,UserInfoActivity.class);
				intent.putExtra("user", user);
				startActivity(intent);
			}
		});

	}

	public ArrayList<VerifiedUser> getData() throws IOException {
		dlist = new ArrayList<HashMap<String, Object>>();
		String api = "api/user/get";
		JSONObject jsonObject = new JSONObject();
		StringEntity stringEntity = new StringEntity(jsonObject.toString(),
				"utf-8");
		GeiliRestClient.post(this, api, stringEntity,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {

						try {
							getData2(arg2);
							dismissProcessDialog();
							myAdapter.notifyDataSetChanged();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {

					}
				});

		/**
		 * 测试数据
		 */
		/*
		 * dlist = new ArrayList<HashMap<String, Object>>(); for(int i = 0; i <
		 * 20; i++){ HashMap<String, Object> map = new HashMap<String,
		 * Object>(); map.put("userName", "dong"+i); map.put("isVerified",
		 * test[new Random().nextInt(2)]); //System.out.println("0 or 1:" + new
		 * Random().nextInt(2)); dlist.add(map); }
		 */

		return list;
	}

	public void getData2(String arg2) throws JSONException {

		JSONObject jsonObject2 = new JSONObject(arg2);
		JSONArray jsonArray = jsonObject2.getJSONArray("result");
		length = jsonArray.length();
		/*
		 * for(int i = 0; i < length; i++){ HashMap<String, Object> map = new
		 * HashMap<String, Object>(); map.put("userName",
		 * jsonArray.getJSONObject(i).get("userName")); map.put("isVerified",
		 * test[(int) jsonArray.getJSONObject(i).get("isVerified")]);
		 * dlist.add(map); }
		 */
		userArray = new String[length];
		for (int i = 0; i < length; i++) {
			userName = jsonArray.optJSONObject(i).getString("userName");
			school = jsonArray.optJSONObject(i).getString("school");
			gender = jsonArray.optJSONObject(i).getString("gender");
			birthdate = jsonArray.optJSONObject(i).getString("birthday");
			lastLoginTime = jsonArray.optJSONObject(i).getString("lastLoginTime");
			
			userArray[i] = userName;
			isVerified = test[(int) jsonArray.getJSONObject(i)
					.get("isVerified")];
			list.add(new VerifiedUser(userName,school,birthdate,gender,lastLoginTime,isVerified));
		}

		myAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.btn_authorize_selected:

			try {
				authorize();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ShowToast("已授权选中用户!");
			break;
		case R.id.btn_authorize_all:
			ShowToast("选中了" + length + "个用户");
			list.clear();
			for (int i = 0; i < length; i++) {
				isVerified = true;
				list.add(new VerifiedUser(userArray[i],school,birthdate,gender,lastLoginTime, isVerified));
			}
			myAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}

	public void authorize() throws Exception{

		ShowToast("authorize");
		String api = "api/user/authorize";
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObject2;
		ArrayList<JSONObject> listArray = new ArrayList<>();
		
		for(int i = 0; i < length; i++){
			jsonObject2 = new JSONObject();
			jsonObject2.put("userName", list.get(i).getUserName());
			jsonObject2.put("isVerified", list.get(i).isSelected());
			listArray.add(jsonObject2);
		}
		
		jsonObject.put("list", listArray);
		StringEntity stringEntity = new StringEntity(jsonObject.toString(),
				"utf-8");

		GeiliRestClient.post(this, api, stringEntity,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {
						//ShowToast("已改");
						myAdapter.notifyDataSetChanged();
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {

					}
				});

	}

}
