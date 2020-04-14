package com.zz.config;

public class WeChatConfig {
	// 公众平台数据
	// 开发者ID
	public static final String APP_ID = "wx16c4ea9bf1f2a099";
	// 开发者密码
	public static final String APP_SECRET = "12df2e25217be168650f5e86aaf98868";
	// Token
	public static final String TOKEN = "Aa1145141919810";
	// EncodingAESKey
	public static final String ENCODING_AES_KEY = "tVAVDeKcGfeD3sMCazGM8jnsRKSZlJ4aeGG8aEWDdI4";
	// AccessToken
	public static String accessToken = null;
	// JsapiTicket
	public static String jsapiTicket = null;

	// 商户（支付）配置
	// 商户ID
	public static final String MCH_ID = "1512987621";
	// 商户KEY
	public static final String MCH_KEY = "daPGHYhjhj821ja1V5asRq8mBpo0uJA9";

	// 开放平台数据
	// AppID
//	public static final String APP_ID_KF = "wx5ec9150b53ef3325";
	// 开发者密码
//	public static final String APP_SECRET_KF = "ecaf7b28331ef88d522e749e90262cff";

	// 回调路径
	public static final String RETURN_URL = "https://域名/wechat/dealwith";



	public static final String MENUURLP1="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WeChatConfig.APP_ID+"&redirect_uri=";  //微信授权
	public static final String MENUURLP2="&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";  //微信授权-有授权页面

}
