package com.culun.game.colorful.gui;

import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.culun.game.colorful.MyApplication;
import com.culun.game.colorful.R;
import com.culun.game.colorful.api.ApiHelper;
import com.culun.game.colorful.constant.MyConstants.AdMobConstant;
import com.culun.game.colorful.utils.MyFileHelper;
import com.culun.game.colorful.utils.MyLog;
import com.culun.game.colorful.utils.MyUtils;

public class SplashActivity extends BaseActivity {

	private static final int SPLASH_TIME_OUT = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		findViewById(R.id.splash_activity_root_view).setBackgroundColor(randomColor());

		splashProcess();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initDataObject() {

	}

	@Override
	protected void initView() {

	}

	private int randomColor() {

		Random random = new Random(System.currentTimeMillis());
		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);

		return Color.argb(255, r, g, b);
	}

	private void splashProcess() {

		Listener<String> getJsonDataListener = new Listener<String>() {
			@Override
			public void onResponse(String response) {
				MyLog.iGeneral("Response getJsonData: " + response);
				MyFileHelper.writeStringToFilesDir(mContext, AdMobConstant.JSON_DATA_FILE_PATH, response);
				loadDataModel();
				startMainActivity();
			}
		};

		ErrorListener errorListener = new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				MyLog.iGeneral("VolleyError getJsonData: " + error);
				loadDataModel();
				startMainActivity();
			}
		};
		ApiHelper.getJsonData(mContext, getJsonDataListener, errorListener);

	}

	/**
	 * 
	 */
	private void loadDataModel() {
		MyApplication.setDataModel(MyUtils.loadDataModel(mContext));
	}

	/**
	 * 
	 */
	private void startMainActivity() {
		Intent intent = new Intent(mContext, MainActivity.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
	}

	@Override
	public void onClick(View v) {
		
	}

}
