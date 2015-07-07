package com.culun.game.colorful.gui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
	
	protected Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(getLayoutId());
		initDataObject();
		initView();

	}

	abstract protected int getLayoutId();
	abstract protected void initView();
	abstract protected void initDataObject();

}
