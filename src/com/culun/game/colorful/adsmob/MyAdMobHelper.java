package com.culun.game.colorful.adsmob;

import android.content.Context;

import com.culun.game.colorful.R;
import com.culun.game.colorful.utils.MyLog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MyAdMobHelper {

	// public static AdView mAdView;

	// private static DataModel mDataModel;

	public static String PRE_BANNER_LOADED_TIME_KEY = "PRE_BANNER_LOADED_TIME_KEY";
	public static String PRE_FULL_LOADED_TIME_KEY = "PRE_FULL_LOADED_TIME_KEY";
	public static String PRE_FULL_LOADED_COUNT_KEY = "PRE_FULL_LOADED_COUNT_KEY";

	private static InterstitialAd mInterstitialAd;

	public static void loadBannerAdMob(Context context, AdView mAdView) {

		AdRequest adRequest = null;
		if (context.getResources().getBoolean(R.bool.is_test)) {
			adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
		} else {
			adRequest = new AdRequest.Builder().build();
		}

		// String AD_UNIT_ID = getBannerAdMobId(context);
		// mAdView.setAdUnitId(AD_UNIT_ID);
		if (mAdView.getAdSize() != null || mAdView.getAdUnitId() != null)
			mAdView.loadAd(adRequest);
	}

	public static void loadFullAdMob(Context context) {

		// Create the InterstitialAd and set the adUnitId.
		mInterstitialAd = new InterstitialAd(context);
		// Defined in res/values/strings.xml
		mInterstitialAd.setAdUnitId(context.getString(R.string.full_ad_unit_id));

		AdRequest adRequest = null;
		if (context.getResources().getBoolean(R.bool.is_test)) {
			adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
		} else {
			adRequest = new AdRequest.Builder().build();
		}
		mInterstitialAd.loadAd(adRequest);

	}

	public static void showFullAdMob(Context context) {

		if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
			mInterstitialAd.show();
		} else {
			MyLog.iGeneral("Full admod is nul");
		}
	}
}