package com.pianostudy.ui;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.widget.TextView;

import com.piano.view.CustomApplcation;
import com.pianostudy.R;
import com.pianostudy.ui.util.FileUtil;

public class MyScoreActivity extends BaseActivity {

	private TextView tv_score;
	private String result, userName, txtUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_score);

		// 主线程访问网络
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		initTopBarForLeft("我的成绩");
		init();
	}

	private void init() {
		userName = CustomApplcation.getInstance().getUser().getUserName();
		tv_score = (TextView) findViewById(R.id.tv_score);
		try {
			txtUrl = "http://116.228.3.125/PianoServer/SynTxtDataServlet?userName="
					+ URLEncoder.encode(userName, "UTF-8");

			// 参数中包含空格，所以要用encode编码一下

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

		tv_score.setText(result);
	}
}
