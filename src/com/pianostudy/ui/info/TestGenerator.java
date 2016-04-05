package com.pianostudy.ui.info;


public class TestGenerator {
	static int threeChordInts[][];
	static int sevenChordInts[][];
	static int scaleInts1[][];
	static int scaleInts2[][];
	static int scaleInts3[][];

	private void init_split() {
		int n = TestType.threeChordChoice.length;
		threeChordInts = new int[n][];
		for (int i = 0; i < n; i++) {
			threeChordInts[i] = split2(TestType.threeChordInt[i]);
		}

		n = TestType.sevenChordChoice.length;
		sevenChordInts = new int[n][];
		for (int i = 0; i < n; i++) {
			sevenChordInts[i] = split3(TestType.sevenChordInt[i]);
		}

		n = TestType.scaleChoice1.length;
		scaleInts1 = new int[n][];
		for (int i = 0; i < n; i++) {
			scaleInts1[i] = splitn(TestType.scaleInt1[i], 7);
		}

		n = TestType.scaleChoice2.length;
		scaleInts2 = new int[n][];
		for (int i = 0; i < n; i++) {
			scaleInts2[i] = splitn(TestType.scaleInt2[i], 5);
		}

		n = TestType.scaleChoice3.length;
		scaleInts3 = new int[n][];
		for (int i = 0; i < n; i++) {
			scaleInts3[i] = splitn(TestType.scaleInt3[i], 7);
		}
	}

	private int[] split2(int v) {
		int vv[] = new int[2];
		vv[0] = v / 10;
		vv[1] = v % 10;
		return vv;
	}

	private int[] split3(int v) {
		int vv[] = new int[3];
		vv[0] = v / 100;
		vv[1] = (v / 10) % 10;
		vv[2] = v % 10;
		return vv;
	}

	private int[] splitn(int v, int n) {
		int vv[] = new int[n];
		int v2 = v;
		for (int i = n - 1; i >= 0; i--) {
			vv[i] = v2 % 10;
			v2 = v2 / 10;
		}
		return vv;
	}

	public static int noteStart = 53;

	public ItemInfo onenote_tests[][] = new ItemInfo[2][];
	public ItemInfo twonote_tests[][] = new ItemInfo[3][];
	public ItemInfo threeChord_tests[][] = new ItemInfo[3][];
	public ItemInfo sevenChord_tests[][] = new ItemInfo[6][];
	public ItemInfo scale_tests[][] = new ItemInfo[6][];

	int numoflev[] = new int[6];

	public final int maxonenote = 25; // 钢琴键数量
	public short onenote[] = new short[maxonenote];
	public short blacknum_onenote[] = new short[maxonenote]; // 是否黑键

	public final int maxtwonote = 222;
	public ItemInfo twonote[] = new ItemInfo[maxtwonote];
	// public short blacknum_twonote[] = new short[maxtwonote];

	public final int maxthreenote = 170;
	public ItemInfo threenote[] = new ItemInfo[maxthreenote];
	// public short level_threenote[] = new short[maxthreenote];

	public final int maxfournote = 400;
	public ItemInfo fournote[] = new ItemInfo[maxfournote];
	// public short level_fournote[] = new short[maxfournote];
	public int maxscalenote = 1000;
	private int cntscalenote = 0;
	public ItemInfo scalenote[] = new ItemInfo[maxscalenote];

	public TestGenerator() { // testmgr
		init();
	}

	public void init() {
		init_split();

		init_onenote();
		init_twonote();
		init_threenote();
		init_fournote();
		init_scales();
	}

	private void init_onenote() {
		for (short i = 0; i < maxonenote; i++) {
			onenote[i] = (short) (53 + i);
			blacknum_onenote[i] = 0;
		}
		blacknum_onenote[54 - 53] = 1;
		blacknum_onenote[56 - 53] = 1;
		blacknum_onenote[58 - 53] = 1;

		blacknum_onenote[61 - 53] = 1;
		blacknum_onenote[63 - 53] = 1;

		blacknum_onenote[66 - 53] = 1;
		blacknum_onenote[68 - 53] = 1;
		blacknum_onenote[70 - 53] = 1;

		blacknum_onenote[73 - 53] = 1;
		blacknum_onenote[75 - 53] = 1;

		int num = 0;
		ItemInfo toflev[];

		// initialize level 1，单音，白键
		for (int i = 0; i < maxonenote; i++) {
			num = num + blacknum_onenote[i];
		}
		numoflev[0] = maxonenote - num; // 白键数量
		numoflev[1] = num; // 黑键数量
		toflev = new ItemInfo[numoflev[0]];
		int j = 0;
		for (int i = 0; i < maxonenote; i++) {
			if (blacknum_onenote[i] == 0) {
				toflev[j] = new ItemInfo(onenote[i]);
				j++;
			}
		}
		onenote_tests[0] = toflev;
		System.out.print("Num of one note,level 1:" + j + "\n");

		// initialize level 2，单音，黑键
		toflev = new ItemInfo[numoflev[1]];
		j = 0;
		for (int i = 0; i < maxonenote; i++) {
			if (blacknum_onenote[i] > 0) {
				toflev[j] = new ItemInfo(onenote[i]);
				j++;
			}
		}
		onenote_tests[1] = toflev;
		System.out.print("Num of one note,level 2:" + j + "\n");
	}

	private void init_twonote() {
		int k = 0;
		for (int i = 0; i < 24; i++) {
			for (int j = i + 1; (j < 25) & (j - i <= 12); j++) {
				short n1 = (short) (i + 53);
				short n2 = (short) (j + 53);
				short f = (short) (blacknum_onenote[i] + blacknum_onenote[j]);
				twonote[k] = new ItemInfo(TestType.toneChoice, j - i - 1, f,
						n1, n2);// (n2<<8)+n1;;
				k++;
			}
		}
		System.out.print("Num of two note:" + k + "\n");

		int num = 0;
		ItemInfo toflev[];
		// initialize level 3，双音-白键
		int num2 = 0;
		for (int i = 0; i < maxtwonote; i++) {
			if (twonote[i].factor == 1)
				num = num + 1;
			if (twonote[i].factor == 2)
				num2 = num2 + 1;
		}
		numoflev[0] = maxtwonote - num - num2;
		numoflev[1] = num;
		numoflev[2] = num2;
		toflev = new ItemInfo[numoflev[0]];
		int j = 0;
		for (int i = 0; i < maxtwonote; i++) {
			if (twonote[i].factor == 0) {
				toflev[j] = twonote[i];
				j++;
			}
		}
		twonote_tests[0] = toflev;
		System.out.print("Num of two note,level 1:" + j + "\n");

		// initialize level 4 //双音-白黑
		toflev = new ItemInfo[numoflev[1]];
		j = 0;
		for (int i = 0; i < maxtwonote; i++) {
			if (twonote[i].factor == 1) {
				toflev[j] = twonote[i];
				j++;
			}
		}
		twonote_tests[1] = toflev;
		System.out.print("Num of two note,level 2:" + j + "\n");

		// initialize level 5 //双音-黑
		toflev = new ItemInfo[numoflev[2]];
		j = 0;
		for (int i = 0; i < maxtwonote; i++) {
			if (twonote[i].factor == 2) {
				toflev[j] = twonote[i];
				j++;
			}
		}
		twonote_tests[2] = toflev;
		System.out.print("Num of two note,level 3:" + j + "\n");
	}

	private void init_threenote() {
		int k = 0;
		int n = TestType.threeChordChoice.length;
		n = threeChordInts.length;
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < n; j++) {
				int key = i + noteStart;
				// System.out.print("n="+n+";i="+i+":j="+j);
				int n1 = key;
				key = threeChordInts[j][0] + key;
				int n2 = key;
				key = threeChordInts[j][1] + key;
				int n3 = key;
				if (n3 <= 77) {
					int f = (short) ((j < 3) ? 0 : ((j < 6) ? 1 : 2));
					// System.out.print("n1:"+n1+"n2:"+n2+"n3:"+n3+":"+"j0:"+threeChordInts[j][0]+"j1:"+threeChordInts[j][1]);
					threenote[k] = new ItemInfo(TestType.threeChordChoice, j,
							f, (short) n1, (short) n2, (short) n3);// (n3<<16)+(n2<<8)+n1;
					// System.out.print(threenote[k].toStr());
					k++;
				}
			}
			// System.out.print("\n");
		}
		System.out.print("Num of three note:" + k + " of " + maxthreenote
				+ "\n");

		for (int i = 0; i < 3; i++) {
			numoflev[i] = 0;
		}
		int lev;
		for (int i = 0; i < maxthreenote; i++) {
			lev = threenote[i].factor;
			numoflev[lev] = numoflev[lev] + 1;
		}

		// testitem toflev[];
		int cnt[] = new int[3];
		for (int i = 0; i < 3; i++) {
			threeChord_tests[i] = new ItemInfo[numoflev[i]];
			cnt[i] = 0;
		}
		for (int i = 0; i < maxthreenote; i++) {
			lev = threenote[i].factor;
			threeChord_tests[lev][cnt[lev]] = threenote[i];
			cnt[lev]++;
		}
		for (int i = 0; i < 3; i++) {
			System.out.print("Num of three cord,level " + i + ": "
					+ numoflev[i] + "\n");
		}
	}

	private void init_fournote() {
		int k = 0;
		int n = TestType.sevenChordChoice.length;
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < n; j++) {
				int key = i + noteStart;
				int n1 = key;
				key = sevenChordInts[j][0] + key;
				int n2 = key;
				key = sevenChordInts[j][1] + key;
				int n3 = key;
				key = sevenChordInts[j][2] + key;
				int n4 = key;
				if (n4 <= 77) {
					int f = (short) ((j < 4) ? 0 : ((j < 8) ? 1 : ((j < 12) ? 2
							: ((j < 17) ? 3 : ((j < 21) ? 4 : 5)))));
					fournote[k] = new ItemInfo(TestType.sevenChordChoice, j, f,
							(short) n1, (short) n2, (short) n3, (short) n4);// (n3<<16)+(n2<<8)+n1;
					// System.out.print(fournote[k].toStr());
					k++;
				}
			}
			// System.out.print("\n");
		}
		System.out.print("Num of four note:" + k + " of " + maxfournote + "\n");

		// int num = 0;
		for (int i = 0; i < 6; i++) {
			numoflev[i] = 0;
		}
		int lev;
		for (int i = 0; i < maxfournote; i++) {
			lev = fournote[i].factor;
			numoflev[lev] = numoflev[lev] + 1;
		}

		// testitem toflev[];
		int cnt[] = new int[6];
		for (int i = 0; i < 6; i++) {
			sevenChord_tests[i] = new ItemInfo[numoflev[i]];
			cnt[i] = 0;
		}
		for (int i = 0; i < maxfournote; i++) {
			lev = fournote[i].factor;
			sevenChord_tests[lev][cnt[lev]] = fournote[i];
			cnt[lev]++;
		}
		for (int i = 0; i < 6; i++) {
			System.out.print("Num of seven cord,level " + i + ": "
					+ numoflev[i] + "\n");
		}
	}

	private void init_scales() {
		cntscalenote = 0;
		cntscalenote = cntscalenote
				+ init_scale(TestType.scaleChoice1, scaleInts1, 7);
		cntscalenote = cntscalenote
				+ init_scale(TestType.scaleChoice2, scaleInts2, 5);
		cntscalenote = cntscalenote
				+ init_scale(TestType.scaleChoice3, scaleInts3, 7);
		maxscalenote = cntscalenote;
		init_scale2();
	}

	private int init_scale(String scaleChoice[], int scaleInts[][], int num) {
		int k = 0;
		int n = scaleChoice.length;
		short nx[] = new short[num + 1];
		for (int i = 0; i < 24; i++) {
			for (int j = 0; j < n; j++) {
				int key = i + noteStart;
				for (int m = 0; m < num; m++) {
					nx[m] = (short) key;
					// System.out.print("-i="+i+":j="+j+":m="+m+":len="+scaleInts.length+"\n");
					key = key + scaleInts[j][m];
				}
				nx[num] = (short) key;
				if (key <= 77) {
					int f = 0;
					for (int l = 0; l <= num; l++) {
						f = f + blacknum_onenote[nx[l] - noteStart];
					}
					f = (f > 5) ? 5 : f;
					scalenote[cntscalenote + k] = new ItemInfo(scaleChoice, j,
							f, nx);// (n3<<16)+(n2<<8)+n1;
					nx = new short[num + 1];
					k++;
				}
			}
			// System.out.print("\n");
		}
		System.out.print("Num of sclae note:" + k + " of " + 0 + "\n");
		return k;
	}

	private void init_scale2() {
		// int num = 0;
		for (int i = 0; i < 6; i++) {
			numoflev[i] = 0;
		}
		int lev;
		for (int i = 0; i < maxscalenote; i++) {
			lev = scalenote[i].factor;
			numoflev[lev] = numoflev[lev] + 1;
		}

		// testitem toflev[];
		int cnt[] = new int[6];
		for (int i = 0; i < 6; i++) {
			scale_tests[i] = new ItemInfo[numoflev[i]];
			cnt[i] = 0;
		}
		for (int i = 0; i < maxscalenote; i++) {
			lev = scalenote[i].factor;
			scale_tests[lev][cnt[lev]] = scalenote[i];
			cnt[lev]++;
		}
		for (int i = 0; i < 6; i++) {
			System.out.print("Num of scale,level " + i + ": " + numoflev[i]
					+ "\n");
		}
	}

}
