package com.culun.game.colorful.adsmob;

import android.content.Context;

import com.culun.game.colorful.MyApplication;
import com.culun.game.colorful.R;
import com.culun.game.colorful.utils.MyLog;
import com.culun.game.colorful.utils.MyPreferenceUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MyAdMobHelper {

	// public static AdView mAdView;
	// private static DataModel mDataModel;

	// public static final long HIDE_ADS_CIRCLE_TIME = 1 * 60 * 60 * 1000;
	public static final long HIDE_ADS_CIRCLE_TIME = 20 * 1000;
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

			@Override
			public void onAdClosed() {
				super.onAdClosed();
				MyLog.iGeneral("AdView - onAdClosed");
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				super.onAdFailedToLoad(errorCode);
				MyLog.iGeneral("AdView - onAdFailedToLoad");
			}

			@Override
			public void onAdLeftApplication() {
				super.onAdLeftApplication();
				MyLog.iGeneral("AdView - onAdLeftApplication");
			}

			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
				MyLog.iGeneral("AdView - onAdLoaded");
			}

			@Override
			public void onAdOpened() {
				super.onAdOpened();
				MyLog.iGeneral("AdView - onAdOpened");
				MyPreferenceUtils.setLastClickAds(MyApplication.getAppContext(), System.currentTimeMillis());
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

		mAdView.setAdUnitId(getBannerAdMobId(context));
		mAdView.setAdSize(AdSize.SMART_BANNER);

		if (mAdView.getAdSize() != null || mAdView.getAdUnitId() != null)
			mAdView.loadAd(adRequest);
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	private static String getBannerAdMobId(Context context) {

		String barnerId = context.getString(R.string.banner_ad_unit_id);
		try {
			barnerId = MyApplication.getDataModel().getAdsMod().getBanners().get(0);
		} catch (Exception e) {
		}
		MyLog.iGeneral("BannerId: " + barnerId);
		return barnerId;
	}

	/**
	 * 
	 * @param context
	 * @return
	 */
	private static String getFullAdMobId(Context context) {
		String fullId = context.getString(R.string.full_ad_unit_id);
		try {
			fullId = MyApplication.getDataModel().getAdsMod().getInterstitials().get(0);
		} catch (Exception e) {
		}
		MyLog.iGeneral("FullId: " + fullId);
		return fullId;
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

	public static boolean isAdsShowable() {

		long lastClickAds = MyPreferenceUtils.getLastClickAds(MyApplication.getAppContext());
		long currentTime = System.currentTimeMillis();
		if (lastClickAds > currentTime) {
			lastClickAds = 0;
			MyPreferenceUtils.setLastClickAds(MyApplication.getAppContext(), 0);
			return true;
		}

		return (currentTime - HIDE_ADS_CIRCLE_TIME) > lastClickAds;
	}
}