package com.culun.game.colorful.adsmob;

import android.content.Context;

import com.culun.game.colorful.R;
import com.culun.game.colorful.utils.MyLog;
import com.google.ads.AdRequest.ErrorCode;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MyAdMobHelper {

	// public static AdView mAdView;
	// private static DataModel mDataModel;

	public static String PRE_BANNER_LOADED_TIME_KEY = "PRE_BANNER_LOADED_TIME_KEY";
	public static String PRE_FULL_LOADED_TIME_KEY = "PRE_FULL_LOADED_TIME_KEY";
	public static String PRE_FULL_LOADED_COUNT_KEY = "PRE_FULL_LOADED_COUNT_KEY";

	private static InterstitialAd mInterstitialAd;

	public static void readAdsKey() {
		
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	private static AdRequest createAdRequest(Context context) {
		AdRequest adRequest = null;
		AdRequest.Builder builder = new AdRequest.Builder();
		// add my devices to test device list
		for (String deviceId : context.getResources().getStringArray(R.array.ads_my_devices)) {
			builder.addTestDevice(deviceId);
		}

		// add test device when debugs
		if (context.getResources().getBoolean(R.bool.is_test)) {
			builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
			for (String deviceId : context.getResources().getStringArray(R.array.ads_test_devices)) {
				builder.addTestDevice(deviceId);
			}
		}
		//
		adRequest = builder.build();
		return adRequest;
	}

	static private void addListener(AdView adView) {
		adView.setAdListener(new AdListener() {
			public void onDismissScreen(AdView arg0) {
				MyLog.iGeneral("Ads - back to app");
			}

			public void onFailedToReceiveAd(AdView arg0, ErrorCode arg1) {
				MyLog.iGeneral("Ads - Error loading");
			}

			public void onLeaveApplication(AdView arg0) {
				MyLog.iGeneral("Ads - Left app");
			}

			public void onPresentScreen(AdView arg0) {
				MyLog.iGeneral("Ads - Sumthin sumthin");
			}

			public void onReceiveAd(AdView arg0) {
				MyLog.iGeneral("Ads - Ad Received");
			}
		});
	}

	/**
	 * 
	 * @param context
	 * @param mAdView
	 */
	public static void loadBannerAdMob(Context context, AdView mAdView) {

		AdRequest adRequest = createAdRequest(context);
		addListener(mAdView);

		// String AD_UNIT_ID = getBannerAdMobId(context);
		// mAdView.setAdUnitId(AD_UNIT_ID);
		if (mAdView.getAdSize() != null || mAdView.getAdUnitId() != null)
			mAdView.loadAd(adRequest);
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	private static String getBannerAdMobId(Context context) {
		return context.getString(R.string.banner_ad_unit_id);
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	private static String getFullAdMobId(Context context) {
		return context.getString(R.string.full_ad_unit_id);
	}

	public static void loadFullAdMob(Context context) {

		// Create the InterstitialAd and set the adUnitId.
		mInterstitialAd = new InterstitialAd(context);
		// Defined in res/values/strings.xml
		mInterstitialAd.setAdUnitId(getFullAdMobId(context));

		AdRequest adRequest = createAdRequest(context);
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