package com.culun.game.colorful.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.culun.game.colorful.constant.MyConstants;

public class MyResoucres {

	public static Typeface myTypeFace;

	public static void loadResources(Context context) {
		myTypeFace = Typeface.createFromAsset(context.getAssets(), MyConstants.FONT_PATH);
	}
}
