package com.culun.game.colorful.gui;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.culun.game.colorful.R;
import com.culun.game.colorful.gui.custom.GameBoardSurfaceView;

public class MainActivity extends BaseActivity {

	private SurfaceView mGameBoardView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView() {
		mGameBoardView = (GameBoardSurfaceView) findViewById(R.id.main_activity_gameboard_surface_view);
		mGameBoardView.setZOrderOnTop(true); // necessary
		SurfaceHolder sfhTrack = mGameBoardView.getHolder();
		sfhTrack.setFormat(PixelFormat.TRANSLUCENT);
	}

}
