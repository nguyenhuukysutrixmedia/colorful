package com.culun.game.colorful.api;

public interface UrlDefines {

	/**
	 * Dropbox
	 */
	public static final String DROPBOX_DIRECT_DOWNLOAD_URL_PREFIX = "https://dl.dropboxusercontent.com/s/";
	public static final String DROPBOX_SHARED_URL_PREFIX = "https://www.dropbox.com/s/";
	public static final String DROPBOX_SHARED_URL = "https://www.dropbox.com/s/c59yagh7okracw8/data.json?dl=0";
	public static final String JSON_DATA_URL = DROPBOX_SHARED_URL.replace(DROPBOX_SHARED_URL_PREFIX,
			DROPBOX_DIRECT_DOWNLOAD_URL_PREFIX);

}
