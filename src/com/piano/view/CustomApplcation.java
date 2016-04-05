package com.piano.view;

import android.app.Application;
import android.util.Log;

import com.piano.bean.User;
import com.pianostudy.ui.util.CommonUtil;

/**
 * �Զ���ȫ��Applcation��
 * 
 * @ClassName: CustomApplcation
 * @Description: TODO
 * @author smile
 * @date 2014-5-19 ����3:25:00
 */
public class CustomApplcation extends Application {

	public static CustomApplcation mInstance;
	public User user = new User();
	public static int mScreenWidth;
	public static int mScreenHight;
	

	public static CustomApplcation getmInstance() {
		return mInstance;
	}

	public static void setmInstance(CustomApplcation mInstance) {
		CustomApplcation.mInstance = mInstance;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.e("", "application");
		mInstance = this;
	}

	public static CustomApplcation getInstance() {

		return mInstance;

	}

}
