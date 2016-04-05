package com.pianostudy.ui.info;

public class TestType {

	/**
	 * 题目的模式为分析
	 */
	public static final int ITEM_MODE_ANALYSY = 1;
	/**
	 * 题目的模式为音高
	 */
	public static final int ITEM_MODE_PITCH = 2;
	/**
	 * 题目的类型， 分析下面的子菜单的名称数组，共7个
	 */
	public static final String menuListAna[] = { "旋律音程", "和声音程", "分解三和弦",
			"三和弦", "分解七和弦", "七和弦", "音阶" };

	/**
	 * 题目的类型，音高下面的子菜单的名称数组，共8个
	 */
	public static final String menuListPitch[] = { "单音", "旋律音程", "和声音程",
			"分解三和弦", "三和弦", "分解七和弦", "七和弦", "音阶" };

	String TestTypeName;
	String TestChoiceString;
	String TestChoice[];
	int num; // 题目数量
	int current;
	String description; // 解释

	ItemInfo items[]; // 7

	final static int harmoney = 1;// 和声
	final static int melody = 2;// 旋律

	// final static String analysisTest[]=
	// {"旋律音程","和声音程","分解三和弦","三和弦","分解七和弦","七和弦","音阶"};//7
	public final static String toneChoice[] = { "小二度", "大二度", "小三度", "大三度",
			"纯四度", "三全音", "纯五度", "小六度", "大六度", "小七度", "大七度", "纯一度", "纯八度" };// 13}
	public final static String threeChordChoice[] = { "大三和弦", "大六和弦", "大四六和弦",
			"小三和弦", "小六和弦", "小四六和弦", "减三和弦", "减六和弦", "减四六和弦", "增三和弦" }; // 10
	public final static int threeChordInt[] = { 43, 35, 54, 34, 45, 53, 33, 36,
			63, 44 };

	public final static String sevenChordChoice[] = { "大小7", "大小56", "大小34",
			"大小2", "小小7", "小小56", "小小34", "小小2", "减小7", "减小56", "减小34", "减小2",
			"减减7", "大大7", "大大56", "大大34", "大大2", "小大7", "小大56", "小大34", "小大2",
			"增大7", "增大56", "增大34", "增大2" }; // 28
	public final static int sevenChordInt[] = { 433, 332, 324, 243, 343, 432,
			323, 234, 334, 342, 423, 233, 333, 434, 341, 414, 143, 344, 441,
			413, 134, 443, 431, 314, 144 };

	public final static String scaleChoice1[] = { "自然大调", "和声大调", "旋律大调",
			"自然小调", "和声小调", "旋律小调" }; // 6
	public final static int scaleInt1[] = { 2212221, 2212131, 2212122, 2122122,
			2122131, 2122221 };
	public final static String scaleChoice2[] = { "宫调", "商调", "角调", "徽调", "羽调" }; // 5
	public final static int scaleInt2[] = { 22323, 23232, 32322, 23223, 32232 };
	public final static String scaleChoice3[] = { "多里亚", "弗里吉亚", "利底亚",
			"密克索利底亚", "洛克利亚" }; // 5
	public final static int scaleInt3[] = { 2122212, 1222122, 2221221, 2212212,
			1221222 };
}
