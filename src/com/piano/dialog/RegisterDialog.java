package com.piano.dialog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.pianostudy.R;
import com.pianostudy.ui.LoginActivity;
import com.pianostudy.ui.util.CommonUtil;
import com.pianostudy.ui.util.GeiliRestClient;

public class RegisterDialog extends Dialog {

	private Context mContext;
	private Button confirm_register;
	private EditText username_edit, pass_edit, confirmPass_edit,school_edit, date_edit;
	private String username, password, confirmPass,school;
	private Dialog dialog;
	private RadioGroup group;
	private RadioButton radio;
	private String birthday, gender = "";
	private CustomProgressDialog processDialog = null;
	Calendar myCalendar = Calendar.getInstance();

	public RegisterDialog(Context context, int theme) {
		super(context, theme);
		this.mContext = context;
	}

	public RegisterDialog(Context context) {

		this(context, R.style.dialog_register);
		dialog = new Dialog(context);

		// 主线程访问网络
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_register);

		initView();
	}

	private void initView() {
		username_edit = (EditText) findViewById(R.id.regist_username);
		pass_edit = (EditText) findViewById(R.id.regist_password);
		confirmPass_edit = (EditText) findViewById(R.id.regist_confirmPassword);
		school_edit = (EditText) findViewById(R.id.regist_school);

		group = (RadioGroup) findViewById(R.id.gender_group);
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int radioBtnId = group.getCheckedRadioButtonId();
				radio = (RadioButton) findViewById(radioBtnId);
				gender = radio.getText().toString();
				// Toast.makeText(getContext(),gender,3).show();
			}
		});

		date_edit = (EditText) findViewById(R.id.date_edit);

		date_edit.setOnClickListener(new android.view.View.OnClickListener() {

			@Override
			public void onClick(View v) {

				new DatePickerDialog(getContext(),
						android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
						date, myCalendar.get(Calendar.YEAR), myCalendar
								.get(Calendar.MONTH), myCalendar
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		confirm_register = (Button) findViewById(R.id.confirm_register);
		confirm_register
				.setOnClickListener(new android.view.View.OnClickListener() {

					@Override
					public void onClick(View v) {
						showProcessDialog();
						username = username_edit.getText().toString();
						school = school_edit.getText().toString();
						password = pass_edit.getText().toString();
						confirmPass = confirmPass_edit.getText().toString();
						birthday = date_edit.getText().toString();
						String mGender = gender;

						if ("".equals(username.trim())) {username_edit.setError("用户名不能为空!");
							//Toast.makeText(mContext, "用户名不能为空!", 3).show();
							dismissProcessDialog();
							return;
						}
						
						if ("".equals(password.trim())) {
							Toast.makeText(mContext, "密码不能为空!", 3).show();
							dismissProcessDialog();
							return;
						}
						if ("".equals(confirmPass.trim())) {
							Toast.makeText(mContext, "确定密码不能为空!", 3).show();
							dismissProcessDialog();
							return;
						}
						if (!confirmPass.equals(password)) {
							Toast.makeText(mContext, "两次输入的密码不一样!", 3).show();
							dismissProcessDialog();
							return;
						}
						if("".equals(school.trim())){ 
							Toast.makeText(mContext,"学校不能为空!", 3).show(); 
							dismissProcessDialog();
							return; 
						}
						if ("".equals(birthday)) {
							Toast.makeText(mContext, "请选择出生日期!", 3).show();
							dismissProcessDialog();
							return;
						}
						if ("".equals(mGender)) {
							Toast.makeText(mContext, "请选择性别!", 3).show();
							dismissProcessDialog();
							return;
						}

						/*
						 * try { HttpUtil.run("http://www.baidu.com"); } catch
						 * (IOException e) { e.printStackTrace(); }
						 */
						if(!CommonUtil.isNetworkAvailable(mContext)){
							Toast.makeText(mContext, "网络不可用,请检查网络", Toast.LENGTH_SHORT).show();
							return;
						}

						try {
							register();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				});
	}

	private void register() throws IOException, Exception {

		String api = "api/user/register";

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userName", username);
		jsonObject.put("password", password);
		jsonObject.put("school", school);
		jsonObject.put("birthday", birthday);
		jsonObject.put("gender", gender);

		Log.i("registerDialog", "username1:" + username + " password1:"
				+ password + " birthday:" + birthday + " gender:" + gender);

		StringEntity stringEntity = new StringEntity(jsonObject.toString(),
				"utf-8");

		GeiliRestClient.post(mContext, api, stringEntity,
				new TextHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, String result) {
						try {
							System.out.println("GeiliRestClient:" + result);
							JSONObject jsonObject2 = new JSONObject(result);
							Log.i("registerDialog",
									jsonObject2.getString("errorMsg"));
							Toast.makeText(getContext(),
									jsonObject2.getString("errorMsg"),
									Toast.LENGTH_SHORT).show();
							int code = Integer.parseInt(jsonObject2
									.getString("errorCode"));
							if (code == 0) {
								Intent myIntent = new Intent(((Dialog) dialog)
										.getContext(), LoginActivity.class);
								getContext().startActivity(myIntent);
								dialog.dismiss();
							}
							dismissProcessDialog();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, String arg2,
							Throwable arg3) {
						dismissProcessDialog();
						System.out.println("onFailure");
					}
				});

		/*
		 * String result = HttpUtil.post(api, jsonObject);
		 * System.out.println("result:"+result); JSONObject jsonObject2 = new
		 * JSONObject(result);
		 * 
		 * Log.i("registerDialog", "username2:" + username + " password2:" +
		 * password); Log.i("registerDialog", "jsonObject2--->" + jsonObject2);
		 * //JSONObject result = jsonObject2.getJSONObject("result");
		 * Log.i("registerDialog", jsonObject2.getString("errorMsg"));
		 * Toast.makeText(getContext(), jsonObject2.getString("errorMsg"),
		 * 3).show(); int code =
		 * Integer.parseInt(jsonObject2.getString("errorCode")); if(code == 0){
		 * Intent myIntent = new Intent(((Dialog) dialog).getContext(),
		 * AnalysysActivity.class); getContext().startActivity(myIntent); //new
		 * registerDialog(getContext()).dismiss(); }
		 */
	}

	DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			myCalendar.set(Calendar.YEAR, year);
			myCalendar.set(Calendar.MONTH, monthOfYear);
			myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
		}

	};

	private void updateLabel() {

		String myFormat = "yyyy年MM月dd日"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.CHINA);

		date_edit.setText(sdf.format(myCalendar.getTime()));

		birthday = date_edit.getText().toString();
	}
	
	public void showProcessDialog() {

		if (processDialog == null){
			processDialog = new CustomProgressDialog(mContext,"loading...");
			processDialog.show();
		}
		if (processDialog.isShowing() == false)
			processDialog.show();

	}

	public void dismissProcessDialog() {

		if (processDialog != null)
			processDialog.dismiss();

	}
}
