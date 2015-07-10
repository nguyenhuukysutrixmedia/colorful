package com.culun.game.colorful.gui;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.culun.game.colorful.R;
import com.culun.game.colorful.adsmob.MyAdMobHelper;
import com.culun.game.colorful.gui.custom.GameBoardSurfaceView;
import com.culun.game.colorful.gui.custom.MyDialog;
import com.culun.game.colorful.utils.MyResoucres;
import com.culun.game.colorful.utils.MyToast;
import com.google.android.gms.ads.AdView;

public class MainActivity extends BaseActivity {

	private SurfaceView mGameBoardView;

	private FrameLayout mFrameAds;
	private AdView mBannerAdView;
	private ImageButton mBtnCloseAds;
	private MyToast mToast;

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
	protected void initDataObject() {

	}

	@Override
	protected void initView() {

		//
		mToast = new MyToast(mContext);
		//
		mGameBoardView = (GameBoardSurfaceView) findViewById(R.id.main_activity_gameboard_surface_view);
		//
		mFrameAds = (FrameLayout) findViewById(R.id.main_activity_ads_layout);
		mFrameAds.bringToFront();
		//
		mBtnCloseAds = (ImageButton) findViewById(R.id.main_activity_btn_close_ads);
		mBtnCloseAds.setOnClickListener(this);
		//
		initAdModBanner();
	}

	/**
	 * 
	 */
	private void initAdModBanner() {
		if (MyAdMobHelper.isAdsShowable()) {
			mBannerAdView = new AdView(mContext);
			MyAdMobHelper.loadBannerAdMob(mContext, mBannerAdView);
			mFrameAds.removeAllViews();
			mFrameAds.addView(mBannerAdView);
			mFrameAds.addView(mBtnCloseAds);
		}
	}

	/** Called when returning to the activity */
	@Override
	public void onResume() {
		super.onResume();
		resumeBannerAdView();
	}

	/**
	 * 
	 */
	private void resumeBannerAdView() {

		if (MyAdMobHelper.isAdsShowable()) {
			mFrameAds.setVisibility(View.VISIBLE);
			if (mBannerAdView != null) {
				mBannerAdView.resume();
			} else {
				initAdModBanner();
				mBannerAdView.resume();
			}
		} else {
			mFrameAds.setVisibility(View.GONE);
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
			mBannerAdView = null;
		}
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.main_activity_btn_close_ads :
				showHideAdsDialog();
				break;
			default :
				break;
		}
	}

	/**
	 * 
	 */
	private void showHideAdsDialog() {
		String title = "ajfdkas";
		String message = " asfjansj ";
		MyDialog.showDialog(mContext, title, message, true, null);
	}
}
