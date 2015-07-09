package com.culun.game.colorful.model;

import java.io.Serializable;
import java.util.ArrayList;

public class AdsModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<String> banners;
	private ArrayList<String> interstitials;

	public ArrayList<String> getBanners() {
		return banners;
	}

	public void setBanners(ArrayList<String> banners) {
		this.banners = banners;
	}

	public ArrayList<String> getInterstitials() {
		return interstitials;
	}

	public void setInterstitials(ArrayList<String> interstitials) {
		this.interstitials = interstitials;
	}

}
