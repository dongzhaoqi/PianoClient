package com.pianostudy.ui;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.piano.view.CustomApplcation;
import com.pianostudy.R;
import com.pianostudy.ui.util.CommonUtil;
import com.pianostudy.ui.util.GeiliRestClient;


public class UserStatisticsActivity extends BaseActivity {
	
	private TextView userName,register_number;
	private String number;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_statistics);

		initTopBarForLeft("用户统计");
		initView();
		
	}
	
	public void initView(){
		userName = (TextView) findViewById(R.id.userName);
		userName.setText(((CustomApplcation) getApplication()).getUser()
					.getUserName());
		
		register_number = (TextView) findViewById(R.id.register_number);
		try {
			countUser();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String countUser() throws IOException{
		String api = "api/user/count";
		
		JSONObject jsonObject = new JSONObject();
		StringEntity stringEntity = new StringEntity(jsonObject.toString(),
				"utf-8");

		GeiliRestClient.post(this, api, stringEntity,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String arg2) {

						System.out.println("arg0:"+arg0+" arg1:"+arg1+" arg2:"+arg2);
						number = arg2;
						register_number.setText(number + "人");
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {

					}
				});
		System.out.println("result:"+number);
		return number;
	}

}
