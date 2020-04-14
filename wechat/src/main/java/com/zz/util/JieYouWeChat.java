package com.zz.util;

import com.zz.config.WeChatConfig;

import java.io.FileInputStream;
import java.io.InputStream;

public class JieYouWeChat extends WXPayConfig {

	@Override
	public String getAppID() {
		return WeChatConfig.APP_ID;
	}

	@Override
	String getMchID() {
		// TODO Auto-generated method stub
		return WeChatConfig.MCH_ID;
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return WeChatConfig.MCH_KEY;
	}

	@Override
	InputStream getCertStream() {
		FileInputStream file = null;
		// try {
		// file = new FileInputStream("E:/tmp/apiclient_cert.p12");
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return file;
	}

	@Override
	IWXPayDomain getWXPayDomain() {
		return new JieYouIWX();
	}

}
