package com.culun.game.colorful.eventhandle;

import com.google.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public interface AdListener {
	public void onReceiveAd(AdView ad);

	public void onFailedToReceiveAd(AdView ad, AdRequest.ErrorCode error);

	public void onPresentScreen(AdView ad);

	public void onDismissScreen(AdView ad);

	public void onLeaveApplication(AdView ad);
}
