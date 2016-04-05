package com.pianostudy.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.piano.bean.User;
import com.piano.view.CustomApplcation;
import com.pianostudy.R;
import com.pianostudy.ui.util.SharedPreferencesUtil;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		RelativeLayout relative = (RelativeLayout) findViewById(R.id.splash_relative);
		startAnimation(relative);
	}
	/**
	 * ���Ŷ��� ��ת/����/����
	 * 
	 * @param relative
	 */
	private void startAnimation(RelativeLayout relative) {
		// װ����������
		AnimationSet set = new AnimationSet(false);

		// ��ת�Ķ���
		RotateAnimation rotate = new RotateAnimation(0, 360,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		rotate.setDuration(1000);// ʱ��
		rotate.setFillAfter(true);// �Ƿ񱣳ֶ���

		// ���ŵĶ���
		ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1,
				Animation.RELATIVE_TO_SELF, 0.3f, Animation.RELATIVE_TO_SELF,
				0.3f);
		scale.setDuration(1000);// ʱ��
		scale.setFillAfter(true);// �Ƿ񱣳ֶ���

		// ����Ķ���
		AlphaAnimation alphe = new AlphaAnimation(0, 1);
		alphe.setDuration(2000);// ʱ��
		alphe.setFillAfter(true);// �Ƿ񱣳ֶ���

		set.addAnimation(rotate);
		set.addAnimation(scale);
		set.addAnimation(alphe);

		set.setAnimationListener(new AnimationListener() {

			// ������ʼ��ʱ��
			@Override
			public void onAnimationStart(Animation animation) {
			}

			// ���ŵ�ʱ��
			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			// ��������
			@Override
			public void onAnimationEnd(Animation animation) {
				// ������ҳ�������ҳ��
				/*Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
				startActivity(intent);
				finish();*/
				handler.sendEmptyMessageDelayed(1, 1000);
			}

		});
		relative.startAnimation(set);
	}

	private Handler handler = new Handler(){
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case 1:
				String userName = SharedPreferencesUtil.readString(
						SharedPreferencesUtil.getSharedPreference(
								getApplicationContext(), "login"), "userName");
	
				Log.e("GO_HOME", userName);
				if (!userName.equals("0")) {

					User user = new User();
					user.setUserName(userName);
					((CustomApplcation)getApplication()).setUser(user);
					startActivity(new Intent(SplashActivity.this,HomeActivity.class));
					finish();
	
				} else {
					startActivity(new Intent(SplashActivity.this,LoginActivity.class));
					finish();
				}
				//startActivity(new Intent(SplashActivity.this,HomeActivity.class));
				finish();
			}
		};
	};
	
}
