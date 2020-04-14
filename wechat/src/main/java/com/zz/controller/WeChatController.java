package com.zz.controller;


import com.alibaba.fastjson.JSONObject;
import com.zz.config.WeChatConfig;
import com.zz.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
@RequestMapping("wechat")
public class WeChatController {

	private static Logger logger = LogManager.getLogger(WeChatController.class);

//	@Autowired
//	OrderService orderService;

	/*
	 * 
	 * 接入微信公众平台（成为了微信公众平台的开发者）
	 * 
	 */
	@GetMapping("init")
	public String init(HttpServletRequest req) {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String param4 = req.getParameter("echostr");

		logger.info("initWeiChat timestamp====" + timestamp);
		logger.info("initWeiChat nonce=" + nonce);
		logger.info("initWeiChat signature=" + signature);
		logger.info("initWeiChat echostr=" + param4);
		boolean result = false;
		try {
			if (signature.equals(WeChatUtil.getSHA1(WeChatConfig.TOKEN, timestamp, nonce))) {
				result = true;

			}
			;
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (result) {
			return param4;

		} else {

			return "error";
		}

	}

	/*
	 * 1. 接收用户给微信公众平台发的消息
	 * 
	 */

	@PostMapping("init")
	public String message(HttpServletRequest req, HttpServletResponse response) throws IOException, DocumentException {
		logger.info(req);
		logger.info("收到操作");
		String accessToken = WeChatUtil.getAccessToken();
		// todo 获取素材列表
		InputStream inst = req.getInputStream();
		Map map = WeChatUtil.xmlToMap(inst);
		Map mapres = new HashMap();
		// logger.info("FromUserName="+map.get("FromUserName"));
		// logger.info("Content="+map.get("Content"));
		// logger.info("MsgType="+map.get("MsgType"));
		String msgType = map.get("MsgType").toString();
		String resString="欢迎光临";
		switch (msgType) {
		// 事件推送
		case "event":
			String event = map.get("Event").toString();
			switch (event) {
			// 关注事件
			case "subscribe":
				 mapres.put("FromUserName", map.get("ToUserName"));
				 mapres.put("ToUserName", map.get("FromUserName"));
				 mapres.put("Content", "欢迎关注期投大师！\n\n\n您可以点击");
//				 \"开户/绑定\"-->\"绑定账户\"
//				 来将您的微信账号与您已有的交易账号进行绑定或者改绑！\n\n\n若您没有交易账号，可以点击
//				 \"开户/绑定\"-->\"实盘开户\" 来注册您的交易账号！\n\n\n期投大师很高兴为您服务！");
				 mapres.put("MsgType", "text");
				 mapres.put("CreateTime", map.get("CreateTime"));
				 return WeChatUtil.MapToXml(mapres);
			}
			break;
		// 文本消息
		case "text":
			String reqtext=(String)map.get("Content");
			if("百度".equals(reqtext)){
				resString="https://www.baidu.com/";
			}else{
				resString="https://www.163.com/";
			}
			break;
		// 图片消息
		case "image":

			break;
		// 语音消息
		case "voice":

			break;
		// 视频消息
		case "video":

			break;
		// 小视频消息
		case "shortvideo":

			break;
		// 地理位置消息
		case "location":

			break;
		// 链接消息
		case "link":

			break;
		}

        mapres.put("FromUserName", map.get("ToUserName"));
        mapres.put("ToUserName", map.get("FromUserName"));
        mapres.put("Content", resString);
        mapres.put("MsgType", "text");
        mapres.put("CreateTime", map.get("CreateTime"));
        return WeChatUtil.MapToXml(mapres);


	}

	@RequestMapping("jsapiTicket")
	public Map<String, String> getJsapiTicket(@RequestParam("noncestr") String noncestr,
                                              @RequestParam("timestamp") String timestamp, @RequestParam("url") String url)
			throws IOException, AesException {
		Map<String, String> map = new HashMap<String, String>();
		String jsapiTicket = WeChatUtil.getJsapiTicket();
		map.put("jsapi_ticket", jsapiTicket);
		String value = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
				+ url;
		//to be enable
//		map.put("signature", DigestUtils.sha1DigestAsHex(value));
		return map;
	}

	/**
	 * 支付回调
	 * 
	 * @param request
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@RequestMapping("dealwith")
	public String dealwith(HttpServletRequest request) throws NumberFormatException, Exception {
		// 读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while ((s = in.readLine()) != null) {
			sb.append(s);
		}
		in.close();
		inputStream.close();

		// 解析xml成map
		Map<String, String> m = new HashMap<String, String>();
		m = XMLUtil.doXMLParse(sb.toString());

		// 过滤空 设置 TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String parameter = (String) it.next();
			String parameterValue = m.get(parameter);

			String v = "";
			if (null != parameterValue) {
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		// 账号信息
		WXPayConfig config = new JieYouWeChat();
		String key = config.getKey(); // key

		System.out.println(packageParams);
		// 判断签名是否正确
		if (PayCommonUtil.isTenpaySign("UTF-8", packageParams, key)) {
			// ------------------------------
			// 处理业务开始
			// ------------------------------
			String resXml = "";
			if ("SUCCESS".equals((String) packageParams.get("result_code"))) {
				// 这里是支付成功
				////////// 执行自己的业务逻辑////////////////

				System.out.println("验签成功");
				String orderId = (String) packageParams.get("out_trade_no");
				String transactionId = (String) packageParams.get("transaction_id");
				if (orderId != null) {
//					ReturnBody body = orderService.pay(orderId, transactionId, "微信支付");
				}

				// 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
				resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
						+ "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

			} else {
				System.out.println("支付失败,错误信息：" + packageParams.get("err_code"));
				resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
			}
			// ------------------------------
			// 处理业务完毕
			// ------------------------------
			return resXml;
		} else {
			System.out.println("通知签名验证失败");
		}

		return "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>"
				+ "</xml>";
	}

	public static SortedMap<String, String> dom4jXMLParse(String strXML) throws DocumentException {
		SortedMap<String, String> smap = new TreeMap<String, String>();
		Document doc = DocumentHelper.parseText(strXML);
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			smap.put(e.getName(), e.getText());
		}
		return smap;
	}

	public static boolean isWechatSign(SortedMap<String, String> smap, String apiKey) {
		StringBuffer sb = new StringBuffer();
		Set es = smap.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (!"sign".equals(k) && null != v && !"".equals(v) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + apiKey);
		/** 验证的签名 */
		String sign = org.apache.commons.codec.digest.DigestUtils.md5Hex(Verify.getBytes(sb.toString(), "UTF-8"))
				.toUpperCase();
		/** 微信端返回的合法签名 */
		String validSign = ((String) smap.get("sign")).toUpperCase();
		return validSign.equals(sign);
	}

	@RequestMapping("getEncryptJsapiTicket")
	public Map<String,Object> getEncryptJsapiTicket( HttpServletRequest req ,  HttpServletResponse resp, @RequestParam("url") String url) throws IOException, AesException, URISyntaxException {
		String ticket=WeChatUtil.getJsapiTicket();
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//随机生成的时间戳
		String noncestr = WeChatUtil.getNonceStr();//生成的16位随机码
		String url1= new URI(url).getPath();
		System.out.println("url"+url1);
		String str = "jsapi_ticket=" + ticket +
				"&noncestr=" + noncestr +
				"&timestamp=" + timestamp +
				"&url=" + url1;
		String signature = SHA1(str);
		Map<String,Object> map = new HashMap<>();
		map.put("nonceStr",noncestr);
		map.put("signature",signature);
		map.put("timestamp",timestamp);
		System.out.println(map);
		return map;
	}

	public String SHA1(String str) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexStr = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexStr.append(0);
				}
				hexStr.append(shaHex);
			}
			return hexStr.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}


}
