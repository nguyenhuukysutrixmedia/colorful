package com.culun.game.colorful;

import com.culun.game.colorful.model.DataModel;

import android.app.Application;

public class MyApplication extends Application {

	private static DataModel mDataModel;
	
	@Override
	public void onCreate() {
		mDataModel = new DataModel();
		super.onCreate();
	}

	public static DataModel getDataModel() {
		synchronized (mDataModel) {
			return mDataModel;
		}
	}
	
	public static void setDataModel(DataModel dataModel) {
		synchronized (mDataModel) {
			mDataModel = dataModel;
		}
	}

}
