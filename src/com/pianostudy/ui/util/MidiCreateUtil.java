package com.pianostudy.ui.util;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.util.Log;

import com.pianostudy.ui.info.ItemInfo;

/**
 * 创建midi格式的文件
 * 
 * @Description TODO
 * @author lizhao
 * @date 2015-11-8 上午12:34:57
 */
public class MidiCreateUtil {
	byte[] a = { 52, 51, 48, 28, 58, 64, 98, -127, 56, 52, 51, 48, 28, 58, 64,
			98, 64, 98, 13, 2, 56, -4, 62, -116, 0, -59, 39, -116, 0, -84, 64,
			98, 13, 2, 56, -4, 62, -116, 0, -59, 39, -116, 0, -84, 64, 98, 13,
			2, 56, -4, 62, -116, 0, -59, 39, -116, 0, -84, 64, 98, 13, 2, 56,
			-4, 62, -116, 0, -59, 39, -116, 0, -84, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, -65 };
	/**
	 * 钢琴键的名字
	 */
	public static String notename[] = { "F3", "#F3", "G3", "#G3", "A3", "#A3",
			"B3", "C4", "#C4", "D4", "#D4", "E4", "F4", "#F4", "G4", "#G4",
			"A4", "#A4", "B4", "C5", "#C5", "D5", "#D5", "E5", "F5" };
	static byte[] twocordMidi = { (byte) 0x4D, (byte) 0x54, (byte) 0x68,
			(byte) 0x64, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06,
			(byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x04,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x23, (byte) 0x00,
			(byte) 0xFF, (byte) 0x54, (byte) 0x05, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF,
			(byte) 0x58, (byte) 0x04, (byte) 0x04, (byte) 0x02, (byte) 0x18,
			(byte) 0x08, (byte) 0x00, (byte) 0xFF, (byte) 0x59, (byte) 0x02,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x51,
			(byte) 0x03, (byte) 0x07, (byte) 0xA1, (byte) 0x1F, (byte) 0x9F,
			(byte) 0x7F, (byte) 0xFF, (byte) 0x2F, (byte) 0x00, (byte) 0x4D,
			(byte) 0x54, (byte) 0x72, (byte) 0x6B, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x54, (byte) 0x00, (byte) 0xFF, (byte) 0x09,
			(byte) 0x04, (byte) 0x28, (byte) 0xCE, (byte) 0xDE, (byte) 0x29,
			(byte) 0x00, (byte) 0xFF, (byte) 0x03, (byte) 0x14, (byte) 0x53,
			(byte) 0x6D, (byte) 0x61, (byte) 0x72, (byte) 0x74, (byte) 0x4D,
			(byte) 0x75, (byte) 0x73, (byte) 0x69, (byte) 0x63, (byte) 0x20,
			(byte) 0x53, (byte) 0x6F, (byte) 0x66, (byte) 0x74, (byte) 0x53,
			(byte) 0x79, (byte) 0x6E, (byte) 0x74, (byte) 0x68, (byte) 0x00,
			(byte) 0xB0, (byte) 0x00, (byte) 0x79, (byte) 0x00, (byte) 0xB0,
			(byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0xC0, (byte) 0x00,
			(byte) 0x00, (byte) 0xB0, (byte) 0x07, (byte) 0x65, (byte) 0x00,
			(byte) 0xB0, (byte) 0x0A, (byte) 0x40, (byte) 0x00, (byte) 0xB0,
			(byte) 0x07, (byte) 0x66, (byte) 0x00, (byte) 0xB0, (byte) 0x07,
			(byte) 0x6E, (byte) 0x0A, (byte) 0xB0, (byte) 0x07, (byte) 0x66,
			(byte) 0x00, (byte) 0x90, (byte) 0x39, (byte) 0x38, (byte) 0x00,
			(byte) 0x90, (byte) 0x3B, (byte) 0x46, (byte) 0x9F, (byte) 0x75,
			(byte) 0x80, (byte) 0x39, (byte) 0x00, (byte) 0x00, (byte) 0x80,
			(byte) 0x3B, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x2F,
			(byte) 0x00 };
	static byte[] twoanaMidi = { (byte) 0x4D, (byte) 0x54, (byte) 0x68,
			(byte) 0x64, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06,
			(byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x04,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x2A, (byte) 0x00,
			(byte) 0xFF, (byte) 0x54, (byte) 0x05, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF,
			(byte) 0x58, (byte) 0x04, (byte) 0x04, (byte) 0x02, (byte) 0x18,
			(byte) 0x08, (byte) 0x00, (byte) 0xFF, (byte) 0x59, (byte) 0x02,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x51,
			(byte) 0x03, (byte) 0x03, (byte) 0xD0, (byte) 0x90, (byte) 0x00,
			(byte) 0xFF, (byte) 0x51, (byte) 0x03, (byte) 0x03, (byte) 0xD0,
			(byte) 0x90, (byte) 0xBF, (byte) 0x7F, (byte) 0xFF, (byte) 0x2F,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x55, (byte) 0x00,
			(byte) 0xFF, (byte) 0x09, (byte) 0x04, (byte) 0x28, (byte) 0xCE,
			(byte) 0xDE, (byte) 0x29, (byte) 0x00, (byte) 0xFF, (byte) 0x03,
			(byte) 0x14, (byte) 0x53, (byte) 0x6D, (byte) 0x61, (byte) 0x72,
			(byte) 0x74, (byte) 0x4D, (byte) 0x75, (byte) 0x73, (byte) 0x69,
			(byte) 0x63, (byte) 0x20, (byte) 0x53, (byte) 0x6F, (byte) 0x66,
			(byte) 0x74, (byte) 0x53, (byte) 0x79, (byte) 0x6E, (byte) 0x74,
			(byte) 0x68, (byte) 0x00, (byte) 0xB0, (byte) 0x00, (byte) 0x79,
			(byte) 0x00, (byte) 0xB0, (byte) 0x20, (byte) 0x00, (byte) 0x00,
			(byte) 0xC0, (byte) 0x00, (byte) 0x00, (byte) 0xB0, (byte) 0x07,
			(byte) 0x65, (byte) 0x00, (byte) 0xB0, (byte) 0x0A, (byte) 0x40,
			(byte) 0x00, (byte) 0xB0, (byte) 0x07, (byte) 0x66, (byte) 0x00,
			(byte) 0xB0, (byte) 0x07, (byte) 0x6E, (byte) 0x0A, (byte) 0xB0,
			(byte) 0x07, (byte) 0x66, (byte) 0x00, (byte) 0x90, (byte) 0x39,
			(byte) 0x40, (byte) 0x9F, (byte) 0x75, (byte) 0x80, (byte) 0x39,
			(byte) 0x00, (byte) 0x05, (byte) 0x90, (byte) 0x3B, (byte) 0x40,
			(byte) 0x9F, (byte) 0x7B, (byte) 0x80, (byte) 0x3B, (byte) 0x00,
			(byte) 0x00, (byte) 0xFF, (byte) 0x2F, (byte) 0x00 };
	static byte[] threecordMidi = { (byte) 0x4D, (byte) 0x54, (byte) 0x68,
			(byte) 0x64, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06,
			(byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x04,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x23, (byte) 0x00,
			(byte) 0xFF, (byte) 0x54, (byte) 0x05, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF,
			(byte) 0x58, (byte) 0x04, (byte) 0x04, (byte) 0x02, (byte) 0x18,
			(byte) 0x08, (byte) 0x00, (byte) 0xFF, (byte) 0x59, (byte) 0x02,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x51,
			(byte) 0x03, (byte) 0x03, (byte) 0xD0, (byte) 0x90, (byte) 0x9F,
			(byte) 0x7F, (byte) 0xFF, (byte) 0x2F, (byte) 0x00, (byte) 0x4D,
			(byte) 0x54, (byte) 0x72, (byte) 0x6B, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x58, (byte) 0x00, (byte) 0xFF, (byte) 0x09,
			(byte) 0x04, (byte) 0x28, (byte) 0xCE, (byte) 0xDE, (byte) 0x29,
			(byte) 0x00, (byte) 0xFF, (byte) 0x03, (byte) 0x14, (byte) 0x53,
			(byte) 0x6D, (byte) 0x61, (byte) 0x72, (byte) 0x74, (byte) 0x4D,
			(byte) 0x75, (byte) 0x73, (byte) 0x69, (byte) 0x63, (byte) 0x20,
			(byte) 0x53, (byte) 0x6F, (byte) 0x66, (byte) 0x74, (byte) 0x53,
			(byte) 0x79, (byte) 0x6E, (byte) 0x74, (byte) 0x68, (byte) 0x00,
			(byte) 0xB0, (byte) 0x00, (byte) 0x79, (byte) 0x00, (byte) 0xB0,
			(byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0xC0, (byte) 0x00,
			(byte) 0x00, (byte) 0xB0, (byte) 0x07, (byte) 0x65, (byte) 0x00,
			(byte) 0xB0, (byte) 0x0A, (byte) 0x40, (byte) 0x00, (byte) 0xB0,
			(byte) 0x07, (byte) 0x66, (byte) 0x00, (byte) 0xB0, (byte) 0x07,
			(byte) 0x6E, (byte) 0x0A, (byte) 0x90, (byte) 0x3C, (byte) 0x38,
			(byte) 0x00, (byte) 0x90, (byte) 0x40, (byte) 0x38, (byte) 0x00,
			(byte) 0x90, (byte) 0x43, (byte) 0x46, (byte) 0x9F, (byte) 0x75,
			(byte) 0x80, (byte) 0x3C, (byte) 0x00, (byte) 0x00, (byte) 0x80,
			(byte) 0x40, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x43,
			(byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x2F, (byte) 0x00 };
	static byte[] threeanaMidi = { (byte) 0x4D, (byte) 0x54, (byte) 0x68,
			(byte) 0x64, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06,
			(byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x04,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x2A, (byte) 0x00,
			(byte) 0xFF, (byte) 0x54, (byte) 0x05, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF,
			(byte) 0x58, (byte) 0x04, (byte) 0x04, (byte) 0x02, (byte) 0x18,
			(byte) 0x08, (byte) 0x00, (byte) 0xFF, (byte) 0x59, (byte) 0x02,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x51,
			(byte) 0x03, (byte) 0x03, (byte) 0xD0, (byte) 0x90, (byte) 0x00,
			(byte) 0xFF, (byte) 0x51, (byte) 0x03, (byte) 0x03, (byte) 0xD0,
			(byte) 0x90, (byte) 0xDF, (byte) 0x7F, (byte) 0xFF, (byte) 0x2F,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x5A, (byte) 0x00,
			(byte) 0xFF, (byte) 0x09, (byte) 0x04, (byte) 0x28, (byte) 0xCE,
			(byte) 0xDE, (byte) 0x29, (byte) 0x00, (byte) 0xFF, (byte) 0x03,
			(byte) 0x14, (byte) 0x53, (byte) 0x6D, (byte) 0x61, (byte) 0x72,
			(byte) 0x74, (byte) 0x4D, (byte) 0x75, (byte) 0x73, (byte) 0x69,
			(byte) 0x63, (byte) 0x20, (byte) 0x53, (byte) 0x6F, (byte) 0x66,
			(byte) 0x74, (byte) 0x53, (byte) 0x79, (byte) 0x6E, (byte) 0x74,
			(byte) 0x68, (byte) 0x00, (byte) 0xB0, (byte) 0x00, (byte) 0x79,
			(byte) 0x00, (byte) 0xB0, (byte) 0x20, (byte) 0x00, (byte) 0x00,
			(byte) 0xC0, (byte) 0x00, (byte) 0x00, (byte) 0xB0, (byte) 0x07,
			(byte) 0x65, (byte) 0x00, (byte) 0xB0, (byte) 0x0A, (byte) 0x40,
			(byte) 0x00, (byte) 0xB0, (byte) 0x07, (byte) 0x66, (byte) 0x00,
			(byte) 0xB0, (byte) 0x07, (byte) 0x6E, (byte) 0x0A, (byte) 0x90,
			(byte) 0x3C, (byte) 0x40, (byte) 0x9F, (byte) 0x75, (byte) 0x80,
			(byte) 0x3C, (byte) 0x00, (byte) 0x05, (byte) 0x90, (byte) 0x40,
			(byte) 0x40, (byte) 0x9F, (byte) 0x7B, (byte) 0x80, (byte) 0x40,
			(byte) 0x00, (byte) 0x05, (byte) 0x90, (byte) 0x43, (byte) 0x40,
			(byte) 0x9F, (byte) 0x7B, (byte) 0x80, (byte) 0x43, (byte) 0x00,
			(byte) 0x00, (byte) 0xFF, (byte) 0x2F, (byte) 0x00 };

	static byte[] fourcordMidi = { (byte) 0x4D, (byte) 0x54, (byte) 0x68,
			(byte) 0x64, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06,
			(byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x04,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x23, (byte) 0x00,
			(byte) 0xFF, (byte) 0x54, (byte) 0x05, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF,
			(byte) 0x58, (byte) 0x04, (byte) 0x04, (byte) 0x02, (byte) 0x18,
			(byte) 0x08, (byte) 0x00, (byte) 0xFF, (byte) 0x59, (byte) 0x02,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x51,
			(byte) 0x03, (byte) 0x03, (byte) 0xD0, (byte) 0x90, (byte) 0x9F,
			(byte) 0x7F, (byte) 0xFF, (byte) 0x2F, (byte) 0x00, (byte) 0x4D,
			(byte) 0x54, (byte) 0x72, (byte) 0x6B, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x60, (byte) 0x00, (byte) 0xFF, (byte) 0x09,
			(byte) 0x04, (byte) 0x28, (byte) 0xCE, (byte) 0xDE, (byte) 0x29,
			(byte) 0x00, (byte) 0xFF, (byte) 0x03, (byte) 0x14, (byte) 0x53,
			(byte) 0x6D, (byte) 0x61, (byte) 0x72, (byte) 0x74, (byte) 0x4D,
			(byte) 0x75, (byte) 0x73, (byte) 0x69, (byte) 0x63, (byte) 0x20,
			(byte) 0x53, (byte) 0x6F, (byte) 0x66, (byte) 0x74, (byte) 0x53,
			(byte) 0x79, (byte) 0x6E, (byte) 0x74, (byte) 0x68, (byte) 0x00,
			(byte) 0xB0, (byte) 0x00, (byte) 0x79, (byte) 0x00, (byte) 0xB0,
			(byte) 0x20, (byte) 0x00, (byte) 0x00, (byte) 0xC0, (byte) 0x00,
			(byte) 0x00, (byte) 0xB0, (byte) 0x07, (byte) 0x65, (byte) 0x00,
			(byte) 0xB0, (byte) 0x0A, (byte) 0x40, (byte) 0x00, (byte) 0xB0,
			(byte) 0x07, (byte) 0x66, (byte) 0x00, (byte) 0xB0, (byte) 0x07,
			(byte) 0x6E, (byte) 0x0A, (byte) 0x90, (byte) 0x3B, (byte) 0x38,
			(byte) 0x00, (byte) 0x90, (byte) 0x3E, (byte) 0x38, (byte) 0x00,
			(byte) 0x90, (byte) 0x41, (byte) 0x38, (byte) 0x00, (byte) 0x90,
			(byte) 0x45, (byte) 0x46, (byte) 0x9F, (byte) 0x75, (byte) 0x80,
			(byte) 0x3B, (byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x3E,
			(byte) 0x00, (byte) 0x00, (byte) 0x80, (byte) 0x41, (byte) 0x00,
			(byte) 0x00, (byte) 0x80, (byte) 0x45, (byte) 0x00, (byte) 0x00,
			(byte) 0xFF, (byte) 0x2F, (byte) 0x00 };
	static byte[] fouranaMidi = { (byte) 0x4D, (byte) 0x54, (byte) 0x68,
			(byte) 0x64, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06,
			(byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x04,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x2B, (byte) 0x00,
			(byte) 0xFF, (byte) 0x54, (byte) 0x05, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF,
			(byte) 0x58, (byte) 0x04, (byte) 0x04, (byte) 0x02, (byte) 0x18,
			(byte) 0x08, (byte) 0x00, (byte) 0xFF, (byte) 0x59, (byte) 0x02,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x51,
			(byte) 0x03, (byte) 0x02, (byte) 0x8B, (byte) 0x0B, (byte) 0x00,
			(byte) 0xFF, (byte) 0x51, (byte) 0x03, (byte) 0x02, (byte) 0x8B,
			(byte) 0x0B, (byte) 0x81, (byte) 0x80, (byte) 0x02, (byte) 0xFF,
			(byte) 0x2F, (byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72,
			(byte) 0x6B, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x63,
			(byte) 0x00, (byte) 0xFF, (byte) 0x09, (byte) 0x04, (byte) 0x28,
			(byte) 0xCE, (byte) 0xDE, (byte) 0x29, (byte) 0x00, (byte) 0xFF,
			(byte) 0x03, (byte) 0x14, (byte) 0x53, (byte) 0x6D, (byte) 0x61,
			(byte) 0x72, (byte) 0x74, (byte) 0x4D, (byte) 0x75, (byte) 0x73,
			(byte) 0x69, (byte) 0x63, (byte) 0x20, (byte) 0x53, (byte) 0x6F,
			(byte) 0x66, (byte) 0x74, (byte) 0x53, (byte) 0x79, (byte) 0x6E,
			(byte) 0x74, (byte) 0x68, (byte) 0x00, (byte) 0xB0, (byte) 0x00,
			(byte) 0x79, (byte) 0x00, (byte) 0xB0, (byte) 0x20, (byte) 0x00,
			(byte) 0x00, (byte) 0xC0, (byte) 0x00, (byte) 0x00, (byte) 0xB0,
			(byte) 0x07, (byte) 0x65, (byte) 0x00, (byte) 0xB0, (byte) 0x0A,
			(byte) 0x40, (byte) 0x00, (byte) 0xB0, (byte) 0x07, (byte) 0x66,
			(byte) 0x00, (byte) 0xB0, (byte) 0x07, (byte) 0x6E, (byte) 0x0A,
			(byte) 0x90, (byte) 0x3B, (byte) 0x40, (byte) 0x9F, (byte) 0x75,
			(byte) 0x80, (byte) 0x3B, (byte) 0x00, (byte) 0x05, (byte) 0x90,
			(byte) 0x3E, (byte) 0x40, (byte) 0x9F, (byte) 0x7B, (byte) 0x80,
			(byte) 0x3E, (byte) 0x00, (byte) 0x05, (byte) 0x90, (byte) 0x41,
			(byte) 0x40, (byte) 0x9F, (byte) 0x7B, (byte) 0x80, (byte) 0x41,
			(byte) 0x00, (byte) 0x05, (byte) 0x90, (byte) 0x45, (byte) 0x40,
			(byte) 0x9F, (byte) 0x7B, (byte) 0x80, (byte) 0x45, (byte) 0x00,
			(byte) 0x03, (byte) 0xFF, (byte) 0x2F, (byte) 0x00 };
	static byte[] sixanaMidi = { (byte) 0x4D, (byte) 0x54, (byte) 0x68,
			(byte) 0x64, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06,
			(byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x04,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x2A, (byte) 0x00,
			(byte) 0xFF, (byte) 0x54, (byte) 0x05, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF,
			(byte) 0x58, (byte) 0x04, (byte) 0x04, (byte) 0x02, (byte) 0x18,
			(byte) 0x08, (byte) 0x00, (byte) 0xFF, (byte) 0x59, (byte) 0x02,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x51,
			(byte) 0x03, (byte) 0x03, (byte) 0xD0, (byte) 0x90, (byte) 0x00,
			(byte) 0xFF, (byte) 0x51, (byte) 0x03, (byte) 0x03, (byte) 0xD0,
			(byte) 0x90, (byte) 0xDF, (byte) 0x7F, (byte) 0xFF, (byte) 0x2F,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x75, (byte) 0x00,
			(byte) 0xFF, (byte) 0x09, (byte) 0x04, (byte) 0x28, (byte) 0xCE,
			(byte) 0xDE, (byte) 0x29, (byte) 0x00, (byte) 0xFF, (byte) 0x03,
			(byte) 0x14, (byte) 0x53, (byte) 0x6D, (byte) 0x61, (byte) 0x72,
			(byte) 0x74, (byte) 0x4D, (byte) 0x75, (byte) 0x73, (byte) 0x69,
			(byte) 0x63, (byte) 0x20, (byte) 0x53, (byte) 0x6F, (byte) 0x66,
			(byte) 0x74, (byte) 0x53, (byte) 0x79, (byte) 0x6E, (byte) 0x74,
			(byte) 0x68, (byte) 0x00, (byte) 0xB0, (byte) 0x00, (byte) 0x79,
			(byte) 0x00, (byte) 0xB0, (byte) 0x20, (byte) 0x00, (byte) 0x00,
			(byte) 0xC0, (byte) 0x00, (byte) 0x00, (byte) 0xB0, (byte) 0x07,
			(byte) 0x65, (byte) 0x00, (byte) 0xB0, (byte) 0x0A, (byte) 0x40,
			(byte) 0x00, (byte) 0xB0, (byte) 0x07, (byte) 0x66, (byte) 0x00,
			(byte) 0xB0, (byte) 0x07, (byte) 0x6E, (byte) 0x0A, (byte) 0x90,
			(byte) 0x3C, (byte) 0x45, (byte) 0x90, (byte) 0x0A, (byte) 0x80,
			(byte) 0x3C, (byte) 0x00, (byte) 0x01, (byte) 0x90, (byte) 0x3E,
			(byte) 0x43, (byte) 0x8F, (byte) 0x6A, (byte) 0x80, (byte) 0x3E,
			(byte) 0x00, (byte) 0x05, (byte) 0x90, (byte) 0x40, (byte) 0x45,
			(byte) 0x90, (byte) 0x10, (byte) 0x80, (byte) 0x40, (byte) 0x00,
			(byte) 0x01, (byte) 0x90, (byte) 0x43, (byte) 0x43, (byte) 0x8F,
			(byte) 0x6A, (byte) 0x80, (byte) 0x43, (byte) 0x00, (byte) 0x05,
			(byte) 0x90, (byte) 0x45, (byte) 0x45, (byte) 0x90, (byte) 0x10,
			(byte) 0x80, (byte) 0x45, (byte) 0x00, (byte) 0x01, (byte) 0x90,
			(byte) 0x48, (byte) 0x43, (byte) 0x8F, (byte) 0x6A, (byte) 0x80,
			(byte) 0x48, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x2F,
			(byte) 0x00 };
	static byte[] eightanaMidi = { (byte) 0x4D, (byte) 0x54, (byte) 0x68,
			(byte) 0x64, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x06,
			(byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x02, (byte) 0x04,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x2A, (byte) 0x00,
			(byte) 0xFF, (byte) 0x54, (byte) 0x05, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF,
			(byte) 0x58, (byte) 0x04, (byte) 0x04, (byte) 0x02, (byte) 0x18,
			(byte) 0x08, (byte) 0x00, (byte) 0xFF, (byte) 0x59, (byte) 0x02,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xFF, (byte) 0x51,
			(byte) 0x03, (byte) 0x03, (byte) 0xD0, (byte) 0x90, (byte) 0x00,
			(byte) 0xFF, (byte) 0x51, (byte) 0x03, (byte) 0x03, (byte) 0xD0,
			(byte) 0x90, (byte) 0xFF, (byte) 0x7F, (byte) 0xFF, (byte) 0x2F,
			(byte) 0x00, (byte) 0x4D, (byte) 0x54, (byte) 0x72, (byte) 0x6B,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x87, (byte) 0x00,
			(byte) 0xFF, (byte) 0x09, (byte) 0x04, (byte) 0x28, (byte) 0xCE,
			(byte) 0xDE, (byte) 0x29, (byte) 0x00, (byte) 0xFF, (byte) 0x03,
			(byte) 0x14, (byte) 0x53, (byte) 0x6D, (byte) 0x61, (byte) 0x72,
			(byte) 0x74, (byte) 0x4D, (byte) 0x75, (byte) 0x73, (byte) 0x69,
			(byte) 0x63, (byte) 0x20, (byte) 0x53, (byte) 0x6F, (byte) 0x66,
			(byte) 0x74, (byte) 0x53, (byte) 0x79, (byte) 0x6E, (byte) 0x74,
			(byte) 0x68, (byte) 0x00, (byte) 0xB0, (byte) 0x00, (byte) 0x79,
			(byte) 0x00, (byte) 0xB0, (byte) 0x20, (byte) 0x00, (byte) 0x00,
			(byte) 0xC0, (byte) 0x00, (byte) 0x00, (byte) 0xB0, (byte) 0x07,
			(byte) 0x65, (byte) 0x00, (byte) 0xB0, (byte) 0x0A, (byte) 0x40,
			(byte) 0x00, (byte) 0xB0, (byte) 0x07, (byte) 0x66, (byte) 0x00,
			(byte) 0xB0, (byte) 0x07, (byte) 0x6E, (byte) 0x0A, (byte) 0x90,
			(byte) 0x3E, (byte) 0x45, (byte) 0x90, (byte) 0x0A, (byte) 0x80,
			(byte) 0x3E, (byte) 0x00, (byte) 0x01, (byte) 0x90, (byte) 0x40,
			(byte) 0x43, (byte) 0x8F, (byte) 0x6A, (byte) 0x80, (byte) 0x40,
			(byte) 0x00, (byte) 0x05, (byte) 0x90, (byte) 0x41, (byte) 0x45,
			(byte) 0x90, (byte) 0x10, (byte) 0x80, (byte) 0x41, (byte) 0x00,
			(byte) 0x01, (byte) 0x90, (byte) 0x43, (byte) 0x43, (byte) 0x8F,
			(byte) 0x6A, (byte) 0x80, (byte) 0x43, (byte) 0x00, (byte) 0x05,
			(byte) 0x90, (byte) 0x45, (byte) 0x45, (byte) 0x90, (byte) 0x10,
			(byte) 0x80, (byte) 0x45, (byte) 0x00, (byte) 0x01, (byte) 0x90,
			(byte) 0x47, (byte) 0x43, (byte) 0x8F, (byte) 0x6A, (byte) 0x80,
			(byte) 0x47, (byte) 0x00, (byte) 0x05, (byte) 0x90, (byte) 0x48,
			(byte) 0x45, (byte) 0x90, (byte) 0x10, (byte) 0x80, (byte) 0x48,
			(byte) 0x00, (byte) 0x01, (byte) 0x90, (byte) 0x4A, (byte) 0x43,
			(byte) 0x8F, (byte) 0x6A, (byte) 0x80, (byte) 0x4A, (byte) 0x00,
			(byte) 0x00, (byte) 0xFF, (byte) 0x2F, (byte) 0x00 };

	// static int
	// pos[][]={{0},{0x75,0x79,0x7e,0x82},{0x75,0x79,0x7d,0x82,0x86,0x8a},{0x75,0x79,0x7d,0x81,0x86,0x8a,0x8e,0x92}};
	static int posCord[][] = { { 0 }, { 0x82, 0x86, 0x8b, 0x8f },
			{ 0x7e, 0x82, 0x86, 0x8b, 0x8f, 0x93 },
			{ 0x7e, 0x82, 0x86, 0x8a, 0x8f, 0x93, 0x97, 0x9b } };
	static int posAna[][] = {
			{ 0 },
			{ 0x89, 0x8e, 0x92, 0x97 },
			{ 0x85, 0x8a, 0x8e, 0x93, 0x97, 0x9c },
			{ 0x86, 0x8b, 0x8f, 0x94, 0x98, 0x9d, 0xa1, 0xa6 },
			{ 0 },
			{ 0x85, 0x8a, 0x8e, 0x93, 0x97, 0x9c, 0xa0, 0xa5, 0xa9, 0xae, 0xb2,
					0xb7 },
			{ 0 },
			{ 0x85, 0x8a, 0x8e, 0x93, 0x97, 0x9c, 0xa0, 0xa5, 0xa9, 0xae, 0xb2,
					0xb7, 0xbb, 0xc0, 0xc4, 0xca } };
	static byte[][] excord = new byte[8][];
	static byte[][] exana = new byte[8][];

	public static void init() {
		excord[0] = twocordMidi;
		excord[1] = twocordMidi;
		excord[2] = threecordMidi;
		excord[3] = fourcordMidi;

		exana[1] = twoanaMidi;
		exana[2] = threeanaMidi;
		exana[3] = fouranaMidi;
		exana[5] = sixanaMidi;
		exana[7] = eightanaMidi;
	}

	/**
	 * 调试打印用
	 */
	private static String tag = "MidiCreateUtil";

	/**
	 * 写入mid文件
	 * 
	 * @param context
	 *            上下文
	 * @param fileName
	 *            文件名称
	 * @param m
	 * @throws Exception
	 */
	public static void write(Context context, String fileName, int m)
			throws Exception {
		File file = new File(context.getFilesDir().getPath() + "/" + fileName
				+ ".mid");
		Log.d(tag, file.toString());
		// "/data/data/com.example.testmediaplayer/files/temp.mid");
		FileOutputStream fos = new FileOutputStream(file);
		byte[] b = twocordMidi;
		int[][] pos = posCord;
		for (int i = 0; i < 4; i++) {
			b[pos[1][i]] = (byte) (53 + m);
		}
		fos.write(b);
		fos.close();
		Log.d(tag, "成功");
	}
	/**
	 * 选择性的写入题目
	 * 
	 * @param context
	 * @param string
	 * @param t
	 */
	public static void writeSelect(Context context, String string, ItemInfo t) {
		if (t.isCord) {
			writeAll(context, string, t);
		} else {
			writeSpit(context, string, t);
		}
	}
	/**
	 * 该方法用于一次性把题目播放完毕
	 * 
	 * @param context
	 * @param fileName
	 * @param t
	 */
	public static void writeAll(Context context, String fileName, ItemInfo t) {
		 if (t.num == 1) {
			 try {
				writeKey(context,fileName,t.notes[0]);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		int[][] pos = posCord;
		try {
			File file = new File(context.getFilesDir().getPath() + "/"
					+ fileName + ".mid");
			Log.d(tag, file.toString());
			// "/data/data/com.example.testmediaplayer/files/temp.mid");
			FileOutputStream fos = new FileOutputStream(file);
			int n = t.num;
			int ind = t.num - 1;
			byte b[] = excord[ind];
			b[pos[ind][0]] = (byte) t.notes[0];
			b[pos[ind][n]] = (byte) t.notes[0];
			b[pos[ind][1]] = (byte) t.notes[1];
			b[pos[ind][n + 1]] = (byte) t.notes[1];
			if (n > 2) {
				b[pos[ind][2]] = (byte) t.notes[2];
				b[pos[ind][n + 2]] = (byte) t.notes[2];
			}
			if (n > 3) {
				b[pos[ind][3]] = (byte) t.notes[3];
				b[pos[ind][n + 3]] = (byte) t.notes[3];
			}
			fos.write(b);
			fos.close();
			Log.d(tag, "成功");
			System.out.println(bytesToHexString(b));

		} catch (Exception ex) {
			ex.printStackTrace();
			Log.d(tag, "出错了");
		}

	}

	/**
	 * 该方法写出的题目是分解之后de
	 * 
	 * @param context
	 * @param fileName
	 * @param t
	 */
	public static void writeSpit(Context context, String fileName, ItemInfo t) {
		int[][] pos = posAna;

		try {
			File file = new File(context.getFilesDir().getPath() + "/"
					+ fileName + ".mid");
			Log.d(tag, file.toString());
			FileOutputStream fos = new FileOutputStream(file);
			int n = t.num;
			int ind = t.num - 1;
			byte b[] = exana[ind];

			for (int i = 0; i < n; i++) {
				b[pos[ind][2 * i]] = (byte) t.notes[i];
				b[pos[ind][2 * i + 1]] = (byte) t.notes[i];
			}
			fos.write(b);
			fos.close();
			Log.d(tag, "成功");
			System.out.println(bytesToHexString(b));
		} catch (Exception ex) {
			Log.d(tag, "出错了");
		}
	}

	/**
	 * 传入一个byte数组，用于打印调试使用
	 * 
	 * @param src
	 *            byte数组
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	public static void writeKey(Context context, String fileName, int m)
			throws Exception {
		File file = new File(context.getFilesDir().getPath() + "/" + fileName
				+ ".mid");
		Log.d(tag, file.toString());
		// "/data/data/com.example.testmediaplayer/files/temp.mid");
		FileOutputStream fos = new FileOutputStream(file);
		byte[] b = twocordMidi;
		int[][] pos = posCord;
		for (int i = 0; i < 4; i++) {
			b[pos[1][i]] = (byte) (m);
		}
		fos.write(b);
		fos.close();
		Log.d(tag, "成功");
	}

}