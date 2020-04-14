package com.zz.util;

public class JieYouIWX implements IWXPayDomain {

	@Override
	public void report(String domain, long elapsedTimeMillis, Exception ex) {
		// TODO Auto-generated method stub

	}

	@Override
	public DomainInfo getDomain(WXPayConfig config) {
		return new DomainInfo("api.mch.weixin.qq.com",true);
	}

}
