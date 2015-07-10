package com.culun.game.colorful.model;

import java.io.Serializable;

public class DataModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private int version;
	private AdsModel adsmob;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public AdsModel getAdsMod() {
		return adsmob;
	}

	public void setAds(AdsModel adsmob) {
		this.adsmob = adsmob;
	}

}
