package com.pianostudy.ui.thread;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.widget.CheckBox;

import com.pianostudy.ui.info.ItemInfo;
import com.pianostudy.ui.manager.MidiBaseManager;
import com.pianostudy.ui.util.MidiCreateUtil;
import com.pianostudy.ui.util.MidiPlayer;

/**
 * 一个新的线程，控制播放
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-11-8 下午3:49:37
 */
public class PlayerThread {
	public MidiBaseManager midiBaseManager = new MidiBaseManager();
	// 构造方法
	Context context;

	public PlayerThread(Context context, int itemMode, int itemType) {
		this.context = context;
		midiBaseManager.init(itemMode, itemType, 0, 0);
		// this.start();
	}

	/**
	 * 用flag控制是否播放
	 */
	boolean flag = false;
	/**
	 * 用于调试打印
	 */
	private String tag = "PlayerThread";

	/**
	 * 子线程中运行 当点击琴键的时候，就会运行
	 */
	public void run() {
	}

	/**
	 * 获得播放的键值 无参数传入 自动播放时调用
	 * 
	 * @param itemType
	 * @param itemMode
	 * @return 播放的按键
	 */
	public String play() {
		flag = true;

		ItemInfo t = midiBaseManager.getTest();
		// String nameString = t.names[t.index];
		// System.out.println("name:" + nameString);
		MidiCreateUtil.writeSelect(context, "play", t);
		try {
			Thread.sleep(100);
			MidiPlayer.play(context, "play");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t.toStr();
	}

	public ItemInfo getText() {
		ItemInfo t = midiBaseManager.getTest();
		String nameString = t.names[t.index];
		return t;
	}

	/**
	 * 调用题目管家，进行下一题，并返回题目的等级
	 * 
	 * @param dir
	 * @return 题目的等级
	 */
	public String setnext(int dir) {
		// midiBaseManager.init(itemMode, itemType, 0, 0);
		midiBaseManager.setnext(dir);
		return getnext();
	}

	/**
	 * 获得下一个
	 * 
	 * @return 返回的是等级和题目
	 */
	public String getnext() {
		return "等级 : " + (midiBaseManager.getLevel() + 1) + ",题目 :"
				+ (midiBaseManager.getLastitem() + 1);
	}

	/**
	 * 被okEvent调用，判断是否正确
	 * 
	 * @param isKeyOn
	 *            所有按键是否被点击的数组
	 * @return 是否正确
	 */
	public boolean ok(ArrayList<CheckBox> cbList) {

		// 如果被点击的个数为0，返回false
		ItemInfo t = midiBaseManager.getTest();// 获得当前的题目
		if (cbList.size() == 0) {
			return false;
		} else if (cbList.size() != t.num) {
			return false;
		}
		String noteName = t.toStringNoteName();
		Log.d(tag, noteName);
		String editName = null;
		for (int i = 0; i < cbList.size(); i++) {
			if (i == 0) {
				editName = cbList.get(i).getText().toString();
			} else {
				editName = editName + ":" + cbList.get(i).getText().toString();
			}
		}
		Log.d(tag, editName);
		if (!noteName.equals(editName)) {
			return false;
		}
		return true;
	}

	/**
	 * 被okEvent调用，判断是否正确
	 * 
	 * @param isKeyOn
	 *            所有按键是否被点击的数组
	 * @return 是否正确
	 */
	public boolean okAna(ArrayList<CheckBox> cbList) {

		// 如果被点击的个数为0，返回false
		ItemInfo t = midiBaseManager.getTest();// 获得当前的题目
		if (cbList.size() == 0) {
			return false;
		} else if (cbList.size() != t.num) {
			return false;
		}
		String noteName = t.toStringNoteName();
		Log.d(tag, noteName);
		String editName = null;
		for (int i = 0; i < cbList.size(); i++) {
			if (i == 0) {
				editName = cbList.get(i).getText().toString();
			} else {
				editName = editName + ":" + cbList.get(i).getText().toString();
			}
		}
		Log.d(tag, editName);
		String b = noteName.replaceAll("#", "");
		if (!b.equals(editName)) {
			return false;
		}
		return true;
	}
	/*
	 * public boolean okAna(ArrayList<CheckBox> cbList) {
	 * 
	 * // 如果被点击的个数为0，返回false ItemInfo t = midiBaseManager.getTest();// 获得当前的题目
	 * if (cbList.size() == 0) { return false; } else if (cbList.size() !=
	 * t.num) { return false; } String noteName = t.toStringNoteName();
	 * Log.d(tag, noteName); String editName = null; for (int i = 0; i <
	 * cbList.size(); i++) { if (i == 0) { editName =
	 * cbList.get(i).getText().toString(); } else { editName = editName + ":" +
	 * cbList.get(i).getText().toString(); } } Log.d(tag, editName); String b =
	 * noteName.replaceAll("#", ""); if (!b.equals(editName)) { return false; }
	 * return true; }
	 */
}
