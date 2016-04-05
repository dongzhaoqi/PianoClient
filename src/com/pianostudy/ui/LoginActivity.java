package com.pianostudy.ui;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.piano.bean.User;
import com.piano.dialog.RegisterDialog;
import com.piano.view.CustomApplcation;
import com.pianostudy.R;
import com.pianostudy.ui.util.CommonUtil;
import com.pianostudy.ui.util.GeiliRestClient;
import com.pianostudy.ui.util.SharedPreferencesUtil;

public class LoginActivity extends BaseActivity implements OnClickListener {

	private EditText userNameEdit, passwordEdit;
	private Button login;
	private Button register;
	private static long firstTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_login);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.login_title_bar);
		initView();
	}

	private void initView() {
		userNameEdit = (EditText) findViewById(R.id.userName);
		passwordEdit = (EditText) findViewById(R.id.password);
		register = (Button) findViewById(R.id.register);

		login = (Button) findViewById(R.id.login);

		login.setOnClickListener(this);
		register.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		String userName = null;
		String password = null;
		int id = v.getId();
		if (id == R.id.login) {
			userName = userNameEdit.getText().toString();
			password = passwordEdit.getText().toString();

			try {
				login(userName, password);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (id == R.id.register) {
			new RegisterDialog(LoginActivity.this).show();
		}
	}

	private void login(String name, String password) throws Exception {

		showProcessDialog();
		if (TextUtils.isEmpty(name)) {
			dismissProcessDialog();
			ShowToast(R.string.toast_error_username_null);
			return;
		}

		if (TextUtils.isEmpty(password)) {
			dismissProcessDialog();
			ShowToast(R.string.toast_error_password_null);
			return;
		}

		if(!CommonUtil.isNetworkAvailable(this)){
			Toast.makeText(this, "网络不可用,请检查网络", Toast.LENGTH_SHORT).show();
			dismissProcessDialog();
			return;
		}
		
		/*
		 * boolean isNetConnected = CommonUtil.isNetworkAvailable(this); if
		 * (!isNetConnected) { ShowToast(R.string.network_tips); return; }
		 */

		Log.i("LoginActivity", "username:" + name + " password:" + password);

		String api = "api/user/login";
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userName", name);
		jsonObject.put("password", password);

		StringEntity stringEntity = new StringEntity(jsonObject.toString(),
				"utf-8");

		GeiliRestClient.post(this, api, stringEntity,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {

						try {
							handleLogin(arg2);
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {
						Log.i("LoginActivity", "failure" + "\narg0:" + arg0 + 
								" \narg1:"+arg1+"\narg2:" + arg2 +"\narg3:"+arg3.getCause());
						dismissProcessDialog();
					}
				});

	}

	public void handleLogin(String arg2) throws JSONException {

		JSONObject jsonObject2 = new JSONObject(arg2);
		System.out.println(arg2);

		int code = Integer.parseInt(jsonObject2.getString("errorCode"));
		String errorMsg = jsonObject2.getString("errorMsg");
		Log.i("LoginActivity", "errorCode:" + code + " errorMsg:" + errorMsg);

		JSONObject jsonObject3 = jsonObject2.optJSONObject("result");

		if (code == 0) {

			User user = new User();
			user.setUserName(jsonObject3.getString("userName"));
			((CustomApplcation) getApplication()).setUser(user);

			Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG)
			.show();
			
			Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
			startActivity(intent);
			LoginActivity.this.finish();

			Log.i("LoginActivity", ((CustomApplcation) getApplication())
					.getUser().getUserName());

			SharedPreferencesUtil.writeString(SharedPreferencesUtil
					.getSharedPreference(getApplicationContext(), "login"),
					"userName", user.getUserName());
			

		} else {

			Log.i("LoginActivity", "error");
			Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_LONG)
					.show();
			dismissProcessDialog();
		}

	}

	/*
	 * @Override public void onBackPressed() { if (firstTime + 2000 >
	 * System.currentTimeMillis()) { super.onBackPressed(); } else {
	 * Toast.makeText(LoginActivity.this, "再次点击退出", Toast.LENGTH_LONG) .show();
	 * } firstTime = System.currentTimeMillis(); }
	 */
}
