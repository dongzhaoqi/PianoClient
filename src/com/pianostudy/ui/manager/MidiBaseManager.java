package com.pianostudy.ui.manager;

import com.pianostudy.ui.info.ItemInfo;

/**
 * 题目的等级控制类 获得当前题目的等级，关卡 设置下一关
 * 
 * @author lizhao
 * 
 */
public class MidiBaseManager {
	/**
	 * 等级
	 */
	private int level;
	/**
	 * 题目
	 */
	private int lastItem;

	private TestManager testManager;

	/**
	 * 构造函数
	 */
	public MidiBaseManager() {
		testManager = new TestManager();
	}

	/**
	 * 初始化
	 * 
	 * @param testClass
	 *            要测试的方式，analysis或者pitch
	 * @param mainevel
	 *            选中子菜单里面的某一个模式
	 * @param lev
	 *            等级
	 * @param last
	 *            条目
	 */
	public void init(int testClass, int mainLevel, int lev, int last) {
		level = lev;
		lastItem = last;
		// 初始化模式
		testManager.init(testClass, mainLevel);
	}

	/**
	 * 获得等级
	 * 
	 * @return
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * 获得题目的序号
	 * 
	 * @return
	 */
	public int getLastitem() {
		return lastItem;
	}

	/**
	 * 
	 * @return 当前的题目
	 */
	public ItemInfo getTest() {
		return testManager.gettest(level, lastItem);
		// play(t);
	}

	final static int isNextItem = 1;
	final static int isNextLevel = 2;
	final static int isAllDone = 3;

	/**
	 * 设置前进一个等级
	 * 
	 * @return
	 */
	public int setnext() {
		ItemInfo tlev[] = testManager.testOflev[level];
		lastItem++;
		if (lastItem < tlev.length) {
			return isNextItem;
		}
		lastItem = 0;
		level++;
		if (level < testManager.maxlev) {
			return isNextLevel;
		}
		level = 0;
		return isAllDone;
	}

	/**
	 * 设置下一题
	 * 
	 * @param dir
	 *            用来判断的值 -2后退一个等级 -1后退一个条目 2前进一个等级 1就调用setNext方法
	 * @return 是下一题还是下一级别还是全部做完
	 */
	public int setnext(int dir) {
		if (dir == -1) {
			if (lastItem > 0) {
				lastItem--;
				return 0;
			}
			level--;
			if (level < 0)
				level = testManager.maxlev-1;
			return 0;
		}
		// 先清空item在降等级
		if (dir == -2) {
			if (lastItem > 0) {
				lastItem = 0;
				return 0;
			}
			level--;
			if (level < 0)
				level = testManager.maxlev-1;
			return 0;
		}
		if (dir == 2) {
			lastItem = 0;
			level++;
			if (level < testManager.maxlev) {
				return isNextLevel;
			}
			level = 0;
			return isAllDone;
		}
		return setnext();
	}
}