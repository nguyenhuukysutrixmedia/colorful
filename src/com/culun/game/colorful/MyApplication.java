package com.culun.game.colorful;

import android.app.Application;
import android.content.Context;

import com.culun.game.colorful.model.DataModel;
import com.culun.game.colorful.utils.MyUtils;

public class MyApplication extends Application {

	
	
	private static Context mContext;
	private static Object mLockObj;
	private static DataModel mDataModel;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mLockObj = new Object();
		mContext = this;
	}
	
	public static Context getAppContext(){
		return mContext;
	}

	public static DataModel getDataModel() {
		synchronized (mLockObj) {
			if(mDataModel == null){
				mDataModel = MyUtils.loadDataModel(mContext);
			}
			return mDataModel;
		}
	}
	
	public static void setDataModel(DataModel dataModel) {
		synchronized (mLockObj) {
			mDataModel = dataModel;
		}
	}

}
