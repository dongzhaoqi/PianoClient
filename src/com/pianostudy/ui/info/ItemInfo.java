package com.pianostudy.ui.info;

import com.pianostudy.ui.util.MidiCreateUtil;


/**
 * 每个条目的所有信息
 * @Description TODO
 * @author lizhao
 * @date 2015-11-8 下午7:37:22
 */
public class ItemInfo {
	/**
	 * 该题目播放出的音符总数
	 */
	public short num = 0; // length of notes
	
	/**
	 * 等级-1
	 */
	public short factor = 0;
	/**
	 * 用于判断的下标
	 */
	public short index = -1; // index to names
	/**
	 * 是否一次性播放完
	 */
	public boolean isCord = false;
	/**
	 * 所玩方式，分析还是音高
	 */
	public short testClass;
	public short notes[];
	public String names[];
	public int key = 0;

	// byte 3 - test class, byte 2 - mainlevel, byte 0/1 -
	// index of the test
	/**
	 * 设置键值
	 * 
	 * @param mainlevel
	 *            所玩模式
	 * @param ind
	 */
	public void setKey(int mainlevel, int ind) {
		key = (testClass << 24) + (mainlevel << 16) + ind;
	}

	/**
	 * 相当于toString方法
	 * 输出 "名称  -0-F3  A3" 名称-用names[index]判断，等级和第一个字符码，其他字符码
	 * @return
	 */
	public String toStr() {
		//输出 "-0-F3" 等级 和 第一个字符码
		String str = "-" + factor+1 + "-"+ MidiCreateUtil.notename[notes[0] - TestGenerator.noteStart];
		//如果不是单音模式，在前面补上名称“大大 + 。。。”
		if (index >= 0) {
			str = names[index] + str;
		}

		//如果有多个音符，则在后面补上
		for (int i = 1; i < num; i++) {
			str = str
					+ ":"
					+ MidiCreateUtil.notename[notes[i] - TestGenerator.noteStart];
		}
		return str;
	}
	
	public String toStringNoteName(){
		String str = MidiCreateUtil.notename[notes[0] - TestGenerator.noteStart];
		

		//如果有多个音符，则在后面补上
		for (int i = 1; i < num; i++) {
			str = str
					+ ":"
					+ MidiCreateUtil.notename[notes[i] - TestGenerator.noteStart];
		}
		return str;
	}

	/**
	 * TestItem的构造方法
	 * @param note
	 */
	public ItemInfo(short note) {
		notes = new short[1];
		notes[0] = note;
		num = 1;
	}

	public ItemInfo(int ns[]) {
		num = (short) ns.length;
		for (int i = 0; i < num; i++) {
			notes[i] = (short) ns[i];
		}
	}
	/**
	 * TestItem的构造方法，克隆自身，获得isCord
	 * @param clone
	 * @param is_Cord
	 */
	public ItemInfo(ItemInfo clone, boolean is_Cord) {
		num = clone.num;
		factor = clone.factor;
		index = clone.index;
		isCord = is_Cord;
		testClass = clone.testClass;
		notes = clone.notes;
		names = clone.names;
	}

	public ItemInfo(String choices[], int ind, int f, short ns[]) {
		names = choices;
		index = (short) ind;
		factor = (short) f;
		notes = new short[2];
		notes = ns;
		num = (short) ns.length;
	}

	public ItemInfo(String choices[], int ind, int f, short note0, short note1) {
		names = choices;
		index = (short) ind;
		factor = (short) f;
		notes = new short[2];
		notes[0] = note0;
		notes[1] = note1;
		num = 2;
	}

	public ItemInfo(String choices[], int ind, int f, short note0, short note1,
			short note2) {
		names = choices;
		index = (short) ind;
		factor = (short) f;
		notes = new short[3];
		notes[0] = note0;
		notes[1] = note1;
		notes[2] = note2;
		num = 3;
	}

	public ItemInfo(String choices[], int ind, int f, short note0, short note1,
			short note2, short note3) {
		names = choices;
		index = (short) ind;
		factor = (short) f;
		notes = new short[4];
		notes[0] = note0;
		notes[1] = note1;
		notes[2] = note2;
		notes[3] = note3;
		num = 4;
	}

}
