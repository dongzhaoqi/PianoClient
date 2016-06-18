package com.pianostudy.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.piano.bean.VerifiedUser;
import com.pianostudy.R;
import com.pianostudy.ui.util.FileUtil;

public class UserInfoActivity extends BaseActivity {

	private TextView info_name, info_school, info_birthdate, info_gender,
			info_verified_state, info_last_login_time;

	private Button btn_down;
	private TextView txt_file;

	private String result, userName;
	private String txtUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user_info);

		// 主线程访问网络
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		initView();
	}

	private void initView() {
		VerifiedUser user = (VerifiedUser) getIntent().getSerializableExtra(
				"user");
		initTopBarForLeft(user.getUserName() + "详细信息");

		info_name = (TextView) findViewById(R.id.info_name);
		info_school = (TextView) findViewById(R.id.info_school);
		info_birthdate = (TextView) findViewById(R.id.info_birthdate);
		info_gender = (TextView) findViewById(R.id.info_gender);
		info_verified_state = (TextView) findViewById(R.id.info_verified_state);
		info_last_login_time = (TextView) findViewById(R.id.info_last_login_time);

		info_name.setText(user.getUserName());
		info_school.setText(user.getSchool());
		info_birthdate.setText(user.getBitthdate());
		info_gender.setText(user.getGender());
		info_verified_state.setText(user.isSelected() ? "已授权" : "未授权");
		info_last_login_time.setText(user.getLastLoginTime());

		txt_file = (TextView) findViewById(R.id.txt_file);

		userName = user.getUserName();

		btn_down = (Button) findViewById(R.id.btn_down);
		btn_down.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try {
					txtUrl = "http://116.228.3.125/PianoServer/SynTxtDataServlet?userName="
							+ URLEncoder.encode(userName, "UTF-8");

					// 参数中包含空格，所以要用encode编码一下
					// txtUrl =
					// "http://10.1.40.76:8080/PianoServer/SynTxtDataServlet?userName="+URLEncoder.encode(userName,
					// "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

				System.out.println("txtUrl:" + txtUrl);
				try {
					result = FileUtil.readTxtFile(txtUrl, "UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
				}

				// 设置TextView可以滚动
				// txt_file.setMovementMethod(ScrollingMovementMethod.getInstance());

				txt_file.setText(result);
			}
		});
	}
}
