package com.zz.util;

import com.zz.config.WeChatConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
@Slf4j
public class WeChatUtil {
	/**
	 * 
	 * 在微信接入的时候，需要对三个参数，1.字典排序  2. sha1加密。 
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 * @throws AesException
	 * @author bsea
	 */
	public static String getSHA1(String token, String timestamp, String nonce) throws AesException
	  {
			try {
				String[] array = new String[] { token, timestamp, nonce };
				StringBuffer sb = new StringBuffer();
				// 字符串排序
				Arrays.sort(array);
				for (int i = 0; i < 3; i++) {
					sb.append(array[i]);
				}
				String str = sb.toString();
				// SHA1签名生成
				MessageDigest md = MessageDigest.getInstance("SHA-1");
				md.update(str.getBytes());
				byte[] digest = md.digest();
			
				StringBuffer hexstr = new StringBuffer();
				String shaHex = "";
				for (int i = 0; i < digest.length; i++) {
					shaHex = Integer.toHexString(digest[i] & 0xFF);
					if (shaHex.length() < 2) {
						hexstr.append(0);
					}
					hexstr.append(shaHex);
				}
				return hexstr.toString();
			} catch (Exception e) {
				e.printStackTrace();
				throw new AesException(AesException.ComputeSignatureError);
			}
	  }
	
	public  static Map xmlToMap(InputStream input) throws DocumentException {
		Map map=new HashMap();
		SAXReader reader=new SAXReader();
		Document doc=reader.read(input);
		Element root=doc.getRootElement();
		List<Element> elements=root.elements();
		for(Element e: elements){
			String name=e.getName();
			String content=e.getText(); 
			log.info("节点名={}  节点内容={}",name,content);
			map.put(e.getName(), e.getText());
		}
		
		return map;
	}
	public  static String MapToXml(Map map) throws DocumentException, IOException{
		String res="<xml>"+
				"<ToUserName>"+map.get("ToUserName")
				+ "</ToUserName>"
				+ "<FromUserName>"+map.get("FromUserName")+"</FromUserName>"+
				
					"<CreateTime>"+map.get("CreateTime")+"</CreateTime><MsgType>"+map.get("MsgType")
					+ "</MsgType><Content>"+map.get("Content")+"</Content>"+
					"</xml>"; 
		return res;
	}
	
	public static String getAccessToken() throws IOException{
		if(WeChatConfig.accessToken==null){
			String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+WeChatConfig.APP_ID+"&secret="+WeChatConfig.APP_SECRET;
			log.info("url={}",url);
			URL url2=new URL(url);
			HttpsURLConnection con=(HttpsURLConnection) url2.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
			con.setDoOutput(true); 
			con.setDoInput(true);
			
			con.connect();
			InputStream input=con.getInputStream();
			byte[] c=new byte[input.available()];
			input.read(c);
			// String有一个构造方法，可以接受一个，字节的数组，来创建一个String.
			//这个过程，就实现了，字节数组转化为String的过程。
			String message=new String(c,"UTF-8");
			input.close();
			con.disconnect();
			
			System.out.println("message==="+message);
			JSONObject demoJson;
			try {
				demoJson = new JSONObject(message);
				WeChatConfig.accessToken=demoJson.getString("access_token");
				log.info("accessToken={}",WeChatConfig.accessToken);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return WeChatConfig.accessToken;
		
	}
	
	/**
	 * 获取微信用户资料
	 * @param code
	 * @return
	 * openid	用户的唯一标识
		nickname	用户昵称
		sex	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
		province	用户个人资料填写的省份
		city	普通用户个人资料填写的城市
		country	国家，如中国为CN
		headimgurl	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
		privilege	用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
		unionid	只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	 */
	public static Map<String,String> getUnionId(String code){
		Map<String,String> data=new HashMap<String,String>();
		Map<String,String> map = getOpenId(code);
		String url="https://api.weixin.qq.com/sns/userinfo?access_token="+map.get("access_token")+"&openid="+map.get("openid")+"&lang=zh_CN";
		log.info("url={}",url);
		URL urlGet;
		try {
			 urlGet = new URL(url);
			 HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection(); 

			 http.setRequestMethod("GET"); //必须是get方式请求 
			 http.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
			 http.setDoOutput(true); 
			 http.setDoInput(true);
//			 System.setProperty("javax.net.ssl.keyStore", "");
//			 System.setProperty("javax.net.ssl.keyStorePassword", "");
//			 System.setProperty("javax.net.ssl.trustStore", "");
//			 System.setProperty("javax.net.ssl.trustStorePassword","");
			 System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			 System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
			 http.connect();
			 InputStream is =http.getInputStream();
			 int size =is.available();
			 log.info("size={}",size);
			 byte[] jsonBytes =new byte[size];
			 log.info("jsonBytes={}",jsonBytes);
			 is.read(jsonBytes);
			 String message=new String(jsonBytes,"UTF-8");
			 log.info("message={}",message);
			 JSONObject demoJson = new JSONObject(message);
			 data.put("openid", demoJson.getString("openid"));
			 data.put("nickname", demoJson.getString("nickname"));
			 data.put("sex", String.valueOf(demoJson.getInt("sex")));
			 data.put("province", demoJson.getString("province"));
			 data.put("city", demoJson.getString("city"));
			 data.put("country", demoJson.getString("country"));
			 data.put("headimgurl", demoJson.getString("headimgurl"));
			 data.put("privilege", demoJson.getJSONArray("privilege").toString());
//			 data.put("unionid", demoJson.getString("unionid"));
		} catch (MalformedURLException e) {
//			log.catching(e);
			log.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		return data;
	}
	
	/**
	 * 获取openId
	 * @param code
	 * @return access_token	网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
				expires_in	access_token接口调用凭证超时时间，单位（秒）
				refresh_token	用户刷新access_token
				openid	用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
				scope  用户授权的作用域，使用逗号（,）分隔
	 */
	public static Map<String,String> getOpenId(String code){
		Map<String,String> data=new HashMap<String,String>();
		log.info("code={}",code);
		String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+WeChatConfig.APP_ID+"&secret="+WeChatConfig.APP_SECRET+"&code="+code+"&grant_type=authorization_code";
		log.info("url={}",url);
		URL urlGet;
		try {
			 urlGet = new URL(url);
			 HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection(); 

			 http.setRequestMethod("GET"); //必须是get方式请求 
			 http.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
			 http.setDoOutput(true); 
			 http.setDoInput(true);
//			 System.setProperty("javax.net.ssl.keyStore", "");
//			 System.setProperty("javax.net.ssl.keyStorePassword", "");
//			 System.setProperty("javax.net.ssl.trustStore", "");
//			 System.setProperty("javax.net.ssl.trustStorePassword","");
			 System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			 System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
			 http.connect();
			 InputStream is =http.getInputStream();
			 int size =is.available();
			 log.info("size={}",size);
			 byte[] jsonBytes =new byte[size];
			 log.info("jsonBytes={}",jsonBytes);
			 is.read(jsonBytes);
			 String message=new String(jsonBytes,"UTF-8");
			 log.info("message={}",message);
			 JSONObject demoJson = new JSONObject(message);
			 log.info("demoJson={}",demoJson.toString());
			 data.put("access_token", demoJson.getString("access_token"));
			 data.put("openid", demoJson.getString("openid"));
			 data.put("expires_in", String.valueOf(demoJson.getInt("expires_in")));
			 data.put("refresh_token", demoJson.getString("refresh_token"));
			 data.put("scope", demoJson.getString("scope"));
		} catch (MalformedURLException e) {
//			log.catching(e);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
//			log.catching(e);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		return data;
	}
	
	public static String getJsapiTicket() throws IOException{
		if(WeChatConfig.jsapiTicket==null){
			String accessToken=WeChatUtil.getAccessToken();
			String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
			URL urlGet;
			try {
				 urlGet = new URL(url);
				 HttpsURLConnection http = (HttpsURLConnection) urlGet.openConnection(); 

				 http.setRequestMethod("GET"); //必须是get方式请求 
				 http.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
				 http.setDoOutput(true); 
				 http.setDoInput(true);
//				 System.setProperty("javax.net.ssl.keyStore", "");
//				 System.setProperty("javax.net.ssl.keyStorePassword", "");
//				 System.setProperty("javax.net.ssl.trustStore", "");
//				 System.setProperty("javax.net.ssl.trustStorePassword","");
				 System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
				 System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
				 http.connect();
				 InputStream is =http.getInputStream();
				 int size =is.available();
				 byte[] jsonBytes =new byte[size];
				 is.read(jsonBytes);
				 String message=new String(jsonBytes,"UTF-8");
				 JSONObject demoJson = new JSONObject(message);
				 WeChatConfig.jsapiTicket=demoJson.get("ticket").toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return WeChatConfig.jsapiTicket;
	}
	
	/**
     * 生成随机字符串
     */ 
    public static String getNonceStr() {
        String currTime = getCurrTime();  
        String strTime = currTime.substring(8, currTime.length());  
        String strRandom = buildRandom(4) + "";  
        return strTime + strRandom;
    }
    
    /** 
     * 获取当前时间 yyyyMMddHHmmss 
     */  
    public static String getCurrTime() {  
        Date now = new Date();  
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
        String s = outFormat.format(now);  
        return s;  
    }
    
    /** 
     * 取出一个指定长度大小的随机正整数. 
     * @param length 
     *            int 设定所取出随机数的长度。length小于11 
     * @return int 返回生成的随机数。 
     */  
    public static int buildRandom(int length) {  
        int num = 1;  
        double random = Math.random();  
        if (random < 0.1) {  
            random = random + 0.1;  
        }  
        for (int i = 0; i < length; i++) {  
            num = num * 10;  
        }  
        return (int) ((random * num));  
    }


}
