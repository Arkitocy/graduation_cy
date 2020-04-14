package com.zz.util;

import com.zz.config.WeChatConfig;
import com.zz.config.SystemConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MenuUtil {

	/**
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * 创建Menu
	* @Title: createMenu
	* @Description: 创建Menu
	* @param @return
	* @param @throws IOException 设定文件
	* @return int 返回类型
	* @throws
	 */
	 public static String createMenu() throws IOException {
	
		 
//		 String url1="https://open.weixin.qq.com/connect/qrconnect?appid="+WeChatConfig.APP_ID_KF+"&redirect_uri=";  //网页授权
		 String url1="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ WeChatConfig.APP_ID+"&redirect_uri=";  //微信授权
		 //String url2="&redirect_uri=";
//		 String url2="&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";  //网页授权
		 String url21="&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";  //微信授权-有授权页面
		 //String url2="&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";  //微信授权-没有授权页面
		 
		 //首页
		 String indexUrl=SystemConfig.DOMAIN+"url/index";		 
		 indexUrl=URLEncoder.encode(indexUrl, "utf-8");
		 
		 
		 
//		 String menu ="{\"button\":[{\"name\":\"开户/绑定\",\"sub_button\":[{\"type\":\"view\",\"name\":\"实盘开户\",\"url\":\""+url1+registerUrl+url21+"\"},{\"type\":\"view\",\"name\":\"模拟开户\",\"url\":\""+url1+moniRegisterUrl+url21+"\"},{\"type\":\"view\",\"name\":\"绑定账户\",\"url\":\""+url1+bindUrl+url21+"\"}]},{\"name\":\"期货交易\",\"sub_button\":[{\"type\":\"view\",\"name\":\"APP下载\",\"url\":\""+url1+registerUrl+url21+"\"},{\"type\":\"view\",\"name\":\"关于我们\",\"url\":\""+url1+registerUrl+url21+"\"},{\"type\":\"view\",\"name\":\"意见反馈\",\"url\":\""+url1+registerUrl+url21+"\"}]},{\"name\":\"个人中心\",\"sub_button\":[{\"type\":\"view\",\"name\":\"总资产\",\"url\":\""+url1+mineUrl+url21+"\"},{\"type\":\"view\",\"name\":\"资金明细\",\"url\":\""+url1+detailUrl+url21+"\"},{\"type\":\"view\",\"name\":\"账户设置\",\"url\":\""+url1+setAccountUrl+url21+"\"},{\"type\":\"view\",\"name\":\"密码设置\",\"url\":\""+url1+setPasswordUrl+url21+"\"},{\"type\":\"view\",\"name\":\"我的邀请码\",\"url\":\""+url1+inviteCodeUrl+url21+"\"}]}]}";
//		 String menu ="{\"button\":[{\"name\":\"开户/绑定\",\"sub_button\":[{\"type\":\"view\",\"name\":\"实盘开户\",\"url\":\""+url1+registerUrl+url21+"\"},{\"type\":\"view\",\"name\":\"绑定账户\",\"url\":\""+url1+bindUrl+url21+"\"}]},{\"name\":\"个人中心\",\"sub_button\":[{\"type\":\"view\",\"name\":\"总资产\",\"url\":\""+url1+mineUrl+url21+"\"},{\"type\":\"view\",\"name\":\"资金明细\",\"url\":\""+url1+detailUrl+url21+"\"},{\"type\":\"view\",\"name\":\"账户设置\",\"url\":\""+url1+setAccountUrl+url21+"\"},{\"type\":\"view\",\"name\":\"密码设置\",\"url\":\""+url1+setPasswordUrl+url21+"\"},{\"type\":\"view\",\"name\":\"我的邀请码\",\"url\":\""+url1+inviteCodeUrl+url21+"\"}]}]}";
		 String menu ="{\"button\":[{\"name\":\"商城\",\"type\":\"view\",\"url\":\""+url1+indexUrl+url21+"\"}]}";
//		 String menu ="{\"button\":[{\"name\":\"余额\",\"sub_button\":[{\"type\":\"view\",\"name\":\"总计\",\"url\":\""+url1+""+v11str+""+url2+"\"},{\"type\":\"view\",\"name\":\"直投\",\"url\":\""+url1+""+v21str+""+url2+"\"},{\"type\":\"view\",\"name\":\"提现\",\"url\":\""+url1+""+v31str+""+url2+"\"}]},{\"name\":\"股票\", \"sub_button\":[{\"type\":\"view\",\"name\":\"股总计\",\"url\":\""+url1+""+v41str+""+url2+"\"}, {\"type\":\"view\",\"name\":\"预投\",\"url\":\""+url1+""+v42str+""+url2+"\"}, {\"type\":\"view\",\"name\":\"配投\",\"url\":\""+url1+""+v43str+""+url2+"\"}, {\"type\":\"view\",\"name\":\"受让\",\"url\":\""+url1+""+v44str+""+url2+"\"}, {\"type\":\"view\",\"name\":\"转让\",\"url\":\""+url1+""+v45str+""+url2+"\"}  ]},  {\"name\":\"个人中心\", \"sub_button\":[ {\"type\":\"view\",\"name\":\"注册\",\"url\":\""+url1+""+v5str+""+url2+"\"},{\"type\":\"view\",\"name\":\"股池\",\"url\":\""+url1+""+v1str+""+url2+"\"},{\"type\":\"view\",\"name\":\"分红\",\"url\":\""+url1+""+v2str+""+url2+"\"},{\"type\":\"view\",\"name\":\"密码管理\",\"url\":\""+url1+""+v3str+""+url2+"\"}, {\"type\":\"view\",\"name\":\"常见问题\",\"url\":\""+url1+""+v4str+""+url2+"\"} ]} ]}";
		 //此处改为自己想要的结构体，替换即可
	 String access_token= WeChatUtil.getAccessToken();
	 System.out.println("menu=="+menu);
	 String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
	 try {
	 URL url = new URL(action);
	 HttpURLConnection http = (HttpURLConnection) url.openConnection(); 

	 http.setRequestMethod("POST"); 
	 http.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
	 http.setDoOutput(true); 
	 http.setDoInput(true);
	 System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
	 System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
	 http.connect();
	 OutputStream os= http.getOutputStream(); 
	 os.write(menu.getBytes("UTF-8"));//传入参数 
	 os.flush();
	 os.close();

	 InputStream is =http.getInputStream();
	 int size =is.available();
	 byte[] jsonBytes =new byte[size];
	 is.read(jsonBytes);
	 String message=new String(jsonBytes,"UTF-8");
	 return "返回信息"+message;
	 } catch (MalformedURLException e) {
	 e.printStackTrace();
	 } catch (IOException e) {
	 e.printStackTrace();
	 } 
	 return "createMenu 失败";
	 }
	 /**
	 * @throws IOException 
	 * 删除当前Menu
	 * @Title: deleteMenu
	 * @Description: 删除当前Menu
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	 public static String deleteMenu() throws IOException{
		 String access_token= WeChatUtil.getAccessToken();
		 String action = "https://api.weixin.qq.com/cgi-bin/menu/delete? access_token="+access_token;
		 try {
			 URL url = new URL(action);
			 HttpURLConnection http = (HttpURLConnection) url.openConnection(); 
		
			 http.setRequestMethod("GET"); 
			 http.setRequestProperty("Content-Type","application/x-www-form-urlencoded"); 
			 http.setDoOutput(true); 
			 http.setDoInput(true);
			 System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
			 System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
			 http.connect();
			 OutputStream os= http.getOutputStream(); 
			 os.flush();
			 os.close();
		
			 InputStream is =http.getInputStream();
			 int size =is.available();
			 byte[] jsonBytes =new byte[size];
			 is.read(jsonBytes);
			 String message=new String(jsonBytes,"UTF-8");
			 return "deleteMenu返回信息:"+message;
		 } catch (MalformedURLException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 return "deleteMenu 失败"; 
	 }
	public static void main(String[] args) throws IOException {

		//System.out.println(deleteMenu());
		System.out.println(createMenu());
	}
		
}
