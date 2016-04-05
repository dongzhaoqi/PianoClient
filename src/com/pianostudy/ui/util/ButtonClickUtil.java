package com.pianostudy.ui.util;

/**
 * 防止按钮被连续点击
 * @author lizhao
 */
public class ButtonClickUtil {
	private static long lastClickTime;

	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		if (time - lastClickTime < 500) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

}
