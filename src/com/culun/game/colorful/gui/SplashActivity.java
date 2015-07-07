package com.culun.game.colorful.gui;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.culun.game.colorful.R;
import com.culun.game.colorful.utils.MyLog;

public class SplashActivity extends BaseActivity {

	public static final String TAG = "colorfulTag";
	// Instantiate the RequestQueue.
	private RequestQueue mQueue;
	private static String url = "https://www.google.com/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		splashProcess();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.activity_splash;
	}

	@Override
	protected void initDataObject() {
		mQueue = Volley.newRequestQueue(this);
	}
	
	@Override
	protected void initView() {

	}

	private void splashProcess() {
		
		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				
				MyLog.iGeneral("Response: " + response);
				startMainActivity();
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// mTextView.setText("That didn't work!");
			}
		});
		stringRequest.setTag(TAG);
		// Add the request to the RequestQueue.
		mQueue.add(stringRequest);
		mQueue.start();
		
	}

	private void startMainActivity(){
		Intent intent = new Intent(mContext,MainActivity.class);
		startActivity(intent);
	}
	
	
}
