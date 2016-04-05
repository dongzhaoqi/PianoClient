package com.piano.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.pianostudy.R;

public class ProcessDialog extends Dialog {

	public ProcessDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);

		initView(context);
	}

	public ProcessDialog(Context context, int theme) {
		super(context, theme);

		initView(context);
	}

	public ProcessDialog(Context context) {
		super(context, R.style.CustomDialog2);

		initView(context);
	}

	private void initView(Context context) {
		super.setContentView(R.layout.process_dialog);
		ProgressBar ps = (ProgressBar) findViewById(R.id.progressBar1);
		// ps.setIndeterminate(true);
		WindowManager.LayoutParams lp = this.getWindow().getAttributes();
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
		// WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		lp.alpha = 0.9f;
		lp.dimAmount = 1.0f;
		this.getWindow().setAttributes(lp);

	}

}
