package com.pianostudy.ui.manager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.renderscript.Type;
import android.util.Log;

import com.pianostudy.ui.info.ItemInfo;
import com.pianostudy.ui.info.TestGenerator;
import com.pianostudy.ui.util.MidiCreateUtil;

public class TestManager {
	/**
	 * 最高等级
	 */
	public int maxlev = 100;

	public ItemInfo testOflev[][];

	static public ItemInfo anaTests[][][] = new ItemInfo[7][][];

	static public ItemInfo pitchTests[][][] = new ItemInfo[8][][];

	TestGenerator test_gen = new TestGenerator();

	private String tag = "TestManager";

	/**
	 * 构造方法
	 */
	public TestManager() {
		MidiCreateUtil.init();
	}

	/**
	 * 获得一个测试题目
	 * 
	 * @param testno
	 *            等级
	 * @param itemno
	 *            题目
	 * @另外一个输出的值 n = estOflev.length为长度，就是模式值
	 * @return 返回的是一个TestItem类
	 */
	public ItemInfo gettest(int testno, int itemno) {
		System.out.print("gettest i=" + testno + ",j=" + itemno + ":n="
				+ testOflev.length + "\n");// 打印当前的题目信息
		System.out.println("len:" + testOflev[testno].length);
		int testLen = testOflev[testno].length; // 该等级的题目数
		System.out.println("len2:" + (itemno + new Random().nextInt(10))
				% testLen);
		ItemInfo d = testOflev[testno][itemno];// 从该数组中拿出题目
		return d;
	}

	/**
	 * 在TestManager中初始化测试的模式
	 * 
	 * @param testclass
	 *            选的是哪个方式
	 * @param mainlevel
	 *            选的是哪个模式
	 */
	public void init(int testclass, int mainlevel) {
		if (testclass == 1) {
			initAna(mainlevel);
			testOflev = anaTests[mainlevel];
			maxlev = testOflev.length;// 刚开始为0
		} else if (testclass == 2) {
			initPitch(mainlevel);
			testOflev = pitchTests[mainlevel];
			maxlev = testOflev.length;
		}
		Log.d(tag, "testmanager-init:" + testclass + "换行" + "mainlevel"
				+ mainlevel + "换行" + "maxlev:" + testOflev.length);
	}

	//
	//
	// TestItem[] gen_tests(TestItem[] from, boolean is_Cord) {
	// int m = from.length;
	// TestItem[] to = new TestItem[m];
	// for (int j = 0; j < m; j++) {
	// to[j] = new TestItem(from[j], is_Cord);
	// }
	// return to;
	// }

	/**
	 * 重置分析页面
	 * 
	 * @param mainlevel
	 *            模式
	 */
	void initAna(int mainlevel) {
		switch (mainlevel) {
		case 0:
			init(anaTests, mainlevel, test_gen.twonote_tests, false);
			break;
		case 1:
			init(anaTests, mainlevel, test_gen.twonote_tests, true);
			break;
		case 2:
			init(anaTests, mainlevel, test_gen.threeChord_tests, false);
			break;
		case 3:
			init(anaTests, mainlevel, test_gen.threeChord_tests, true);
			break;
		case 4:
			init(anaTests, mainlevel, test_gen.sevenChord_tests, false);
			break;
		case 5:
			init(anaTests, mainlevel, test_gen.sevenChord_tests, true);
			break;
		case 6:
			init(anaTests, mainlevel, test_gen.scale_tests, false);
			break;
		}
	}

	/**
	 * 重置高音页面
	 * 
	 * @param mainlevel
	 *            模式
	 */
	void initPitch(int mainlevel) {
		switch (mainlevel) {
		case 0:
			init(pitchTests, mainlevel, test_gen.onenote_tests, true);
			break;
		case 1:
			init(pitchTests, mainlevel, test_gen.twonote_tests, false);
			break;
		case 2:
			init(pitchTests, mainlevel, test_gen.twonote_tests, true);
			break;
		case 3:
			init(pitchTests, mainlevel, test_gen.threeChord_tests, false);
			break;
		case 4:
			init(pitchTests, mainlevel, test_gen.threeChord_tests, true);
			break;
		case 5:
			init(pitchTests, mainlevel, test_gen.sevenChord_tests, false);
			break;
		case 6:
			init(pitchTests, mainlevel, test_gen.sevenChord_tests, true);
			break;
		case 7:
			init(pitchTests, mainlevel, test_gen.scale_tests, false);
			break;
		}
	}

	/**
	 * 初始化题目，把题目给填满
	 * 
	 * @param testss
	 *            一个三位数组
	 * @param mainlevel
	 *            模式
	 * @param tests
	 *            二维数组,具体的题目
	 * @param is_Cord
	 *            是否是一次性播放
	 */
	private void init(ItemInfo[][][] testss, int mainlevel, ItemInfo tests[][],
			boolean is_Cord) {
		// 如果为空就结束
		if (testss[mainlevel] != null) {
			testss[mainlevel]=null;
		}
		// 获得二维数组的长度
		int n = tests.length;
		// 这个等级的值是一个二维数组
		testss[mainlevel] = new ItemInfo[n][];
		for (int i = 0; i < n; i++) {
			int m = tests[i].length;
			ItemInfo[] to = new ItemInfo[m];

//			java.util.Random ran = new java.util.Random();
//			java.util.Set<Integer> set = new java.util.HashSet<Integer>();// set中的元素是不能重复的
//			while (set.size() < m) {
//				int f = ran.nextInt(m);// 产生0-9之间的随机数
//				set.add(f);// 添加到set中。set中元素不能重复，因此重复的元素添加不进去
//			}
//			Iterator iterator = set.iterator();// 先迭代出来
//			while (iterator.hasNext()) {// 遍历
//				Log.d("sdadas", "dsadsad");
//				System.out.println(iterator.next());
//			}

			for (int j = 0; j < m; j++) {
				to[j] = new ItemInfo(tests[i][j], is_Cord);
				to[j].setKey(mainlevel, j);
			}
			//ArrayList<ItemInfo> list = new ArrayList<ItemInfo>();
			List<ItemInfo> list = java.util.Arrays.asList(to);
			Collections.shuffle(list);
			to = (ItemInfo[]) list.toArray(to);
			// to还是一个一维数组？但是里面已经有了值
			testss[mainlevel][i] = to;
		}

	}

	// public static Array MixArray(ItemInfo[] arr, Type type) {
	// Array goal = Array.CreateInstance(type, arr.Length);
	// var rnd = new Random(); // 实例化一个伪随机数生成器
	// for (int i = 0; i < arr.Length; i++) {
	// // 循环要处理的数组
	// // 随机生成一个数组索引号，注意每循环一次，将范围缩小一个
	// int pos = rnd.Next(0, arr.Length - i - 1);
	// // 将随机的索引号pos所在的值 赋给输出数组中的当前循环索引（i）
	// goal.SetValue(arr.GetValue(pos), i);
	// // 由于每次循环，范围都缩小了一个，而在该范围外的一个值，可能会丢掉了。
	// // 所以要将原数组pos位置的值更改为该范围外的那个值，当前位置的值已传给输出数组了
	// arr.SetValue(arr.GetValue(arr.Length - 1 - i), pos);
	// }
	//
	// return goal;
	// }
}
