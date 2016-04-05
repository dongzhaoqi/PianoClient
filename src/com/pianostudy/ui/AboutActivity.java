package com.pianostudy.ui;

import com.pianostudy.R;

import android.os.Bundle;
import android.view.Window;

/**
 * ��ҳ��
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-11-5 ����9:46:01
 */
public class AboutActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);

		initTopBarForLeft("关于超级耳朵");
	}

}
