package com.culun.game.colorful.gui;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutId());
		initView();

	}

	abstract protected int getLayoutId();
	abstract protected void initView();

}
