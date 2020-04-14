package com.zz.util;

import com.zz.config.SystemConfig;
import com.zz.config.WeChatConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MenuUtil2 {

	public static String createMenuURL()  {
		String url4="http://arkitocy.ngrok2.xiaomiqiu.cn/xszadmin/url/t1";

		String url1="http://arkitocy.ngrok2.xiaomiqiu.cn/xszadmin/url/t2";

		String url3="http://arkitocy.ngrok2.xiaomiqiu.cn/xszadmin/url/t3";
		String menuurl=" {     \"button\":[   	{\t               \"type\":\"view\",               \"name\":\"订制\",               \"url\":\""+WeChatConfig.MENUURLP1+url1+WeChatConfig.MENUURLP2+"\"            },{\t               \"type\":\"view\",               \"name\":\"首页\",               \"url\":\""+WeChatConfig.MENUURLP1+url3+WeChatConfig.MENUURLP2+"\"            },	\t   \t    {\t               \"type\":\"view\",               \"name\":\"我的\",               \"url\":\""+WeChatConfig.MENUURLP1+url4+WeChatConfig.MENUURLP2+"\"            }\t   \t   \t   ] }";

//		String menuurl=" {     \"button\":[          {           \"name\":\"订制\",           \"sub_button\":[           {\t               \"type\":\"view\",               \"name\":\"个人订制\",               \"url\":\"http://www.soso.com/\"            },\t\t\t {\t               \"type\":\"view\",               \"name\":\"企业订制\",               \"url\":\"http://www.soso.com/\"            }                       \t\t\t]       },\t   \t   \t     {\t               \"type\":\"view\",               \"name\":\"首页\",               \"url\":\"http://www.soso.com/\"            },\t   \t    {\t               \"type\":\"view\",               \"name\":\"我的\",               \"url\":\""+WeChatConfig.MENUURLP1+url4+WeChatConfig.MENUURLP2+"\"            }\t   \t   \t   ] }";

		return menuurl;
	}


	public static String createMenuCore(String menu) throws IOException {
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

		System.out.println(createMenuCore(createMenuURL()));
//		String t4="http://hjxsz.3kkk.xyz/xszadmin/url/t4";
//		System.out.println(WeChatConfig.MENUURLP1+t4+WeChatConfig.MENUURLP2);
	}


		
}
