package com.culun.game.colorful.gui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.culun.game.colorful.MyApplication;
import com.culun.game.colorful.R;
import com.culun.game.colorful.api.ApiHelper;
import com.culun.game.colorful.constant.MyConstants.AdMobConstant;
import com.culun.game.colorful.model.DataModel;
import com.culun.game.colorful.utils.MyFileHelper;
import com.culun.game.colorful.utils.MyLog;
import com.google.gson.Gson;

public class SplashActivity extends BaseActivity {

	private static final int SPLASH_TIME_OUT = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

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
			}
		};
		ApiHelper.getJsonData(mContext, getJsonDataListener, errorListener);

	}

	/**
	 * 
	 */
	private void loadDataModel() {

		Gson gson = new Gson();
		try {
			String jsonData = MyFileHelper.readStringFromFilesDir(mContext, AdMobConstant.JSON_DATA_FILE_PATH);
			DataModel dataModel = gson.fromJson(jsonData, DataModel.class);
			MyApplication.setDataModel(dataModel);
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.eGeneral("Parse data json error: " + e);
			String jsonData = MyFileHelper.readStringFromAsset(mContext, AdMobConstant.JSON_DATA_FILE_PATH);
			DataModel dataModel = gson.fromJson(jsonData, DataModel.class);
			MyApplication.setDataModel(dataModel);
		}

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

}
