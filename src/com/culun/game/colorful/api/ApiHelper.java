package com.culun.game.colorful.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ApiHelper implements UrlDefines {

	public static final String TAG = "Colorful_API_Tag";

	private static RequestQueue mQueue;
	private static Context mContext;

	/**
	 * 
	 * @param context
	 */
	public static void getJsonData(Context context, Listener<String> Listener, ErrorListener ErrorListener) {
		mContext = context;
		mQueue = Volley.newRequestQueue(mContext);

		// Request a string response from the provided URL.
		StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_DATA_URL, Listener, ErrorListener);
		// stringRequest.setTag(TAG);
		stringRequest.setShouldCache(false);
		// Add the request to the RequestQueue.
		mQueue.add(stringRequest);
		mQueue.start();
	}
}
