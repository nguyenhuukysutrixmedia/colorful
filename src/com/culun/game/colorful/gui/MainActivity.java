package com.culun.game.colorful.gui;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.culun.game.colorful.R;
import com.culun.game.colorful.adsmob.MyAdMobHelper;
import com.culun.game.colorful.gui.custom.GameBoardSurfaceView;
import com.culun.game.colorful.utils.MyResoucres;
import com.google.android.gms.ads.AdView;

public class MainActivity extends BaseActivity {

	private SurfaceView mGameBoardView;

	private FrameLayout mFrameAds;
	private AdView mBannerAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyResoucres.loadResources(mContext);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView() {
		mGameBoardView = (GameBoardSurfaceView) findViewById(R.id.main_activity_gameboard_surface_view);
		// mGameBoardView.setZOrderOnTop(true); // necessary
		// SurfaceHolder sfhTrack = mGameBoardView.getHolder();
		// sfhTrack.setFormat(PixelFormat.TRANSLUCENT);

		//
		mFrameAds = (FrameLayout) findViewById(R.id.main_activity_ads_layout);

		//
		mBannerAdView = (AdView) findViewById(R.id.main_activity_banner_ad_view);
		MyAdMobHelper.loadBannerAdMob(mContext, mBannerAdView);
		mFrameAds.bringToFront();
	}

	/** Called when returning to the activity */
	@Override
	public void onResume() {
		super.onResume();
		if (mBannerAdView != null) {
			mBannerAdView.resume();
		}
	}

	/** Called when leaving the activity */
	@Override
	public void onPause() {
		if (mBannerAdView != null) {
			mBannerAdView.pause();
		}
		super.onPause();
	}

	/** Called before the activity is destroyed */
	@Override
	public void onDestroy() {
		if (mBannerAdView != null) {
			mBannerAdView.destroy();
		}
		super.onDestroy();
	}
}
