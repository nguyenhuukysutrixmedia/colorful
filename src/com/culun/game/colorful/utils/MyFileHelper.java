package com.culun.game.colorful.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class MyFileHelper {

	/**
	 * 
	 * @param context
	 * @param fileName
	 * @param content
	 */
	public static void writeStringToFilesDir(Context context, String fileName, String content) {
		try {
			File file = new File(context.getFilesDir(), fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			writer.append(content);
			writer.flush();
			writer.close();
			MyLog.iGeneral("writeStringToFilesDir done: " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("writeStringToFilesDir error: " + e);
		}
	}

	/**
	 * 
	 * @param context
	 * @param fileName
	 * @param content
	 */
	public static String readStringFromFilesDir(Context context, String fileName) {

		String content = "";
		try {
			File file = new File(context.getFilesDir(), fileName);
			FileReader reader = new FileReader(file);
			char[] buff = new char[4096];
			int n = 0;
			do {
				n = reader.read(buff);
				content += String.copyValueOf(buff);
			} while (n > 0);
			reader.close();
			MyLog.iGeneral("readStringFromFilesDir done: " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("readStringFromFilesDir error: " + e);
		}
		return content;
	}

	/**
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String readStringFromAsset(Context context, String fileName) {

		String content = "";
		try {
			StringBuilder buf = new StringBuilder();
			InputStream json = context.getAssets().open(fileName);
			BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));
			while ((content = in.readLine()) != null) {
				buf.append(content);
			}

			in.close();
			MyLog.iGeneral("readStringFromAsset done: " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("readStringFromAsset error: " + e);
		}
		return content;
	}
}
