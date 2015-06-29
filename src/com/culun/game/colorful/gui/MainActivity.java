package com.culun.game.colorful.gui;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;

import com.culun.game.colorful.R;
import com.culun.game.colorful.controller.GameWorld;

public class MainActivity extends BaseActivity {

	private LinearLayout mBoxLayout;
	private GameWorld mGameWorld;
	private int gameWidth, gameHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		gameWidth = size.x;
		gameHeight = gameWidth;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				reloadBoxs();
			}
		}, 2000);
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView() {
		mBoxLayout = (LinearLayout) findViewById(R.id.main_activity_box_layout);
	}

	@Override
	public void onClick(View v) {

	}

	private void reloadBoxs() {
		if (mGameWorld == null) {
			mGameWorld = new GameWorld(mContext, gameWidth, gameHeight);
			mBoxLayout = mGameWorld.generateListBoxs(mBoxLayout);
		}

	}

}
