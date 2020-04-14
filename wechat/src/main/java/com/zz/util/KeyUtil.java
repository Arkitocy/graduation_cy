package com.zz.util;

import java.util.Random;

/**
 * 生成唯一主键
 *
 * @author linhao
 * @date: 2019/07/02
 */
public class KeyUtil {
	public static  String genUniqueKey() {//synchronized
		Random random=new Random();
		Integer number=random.nextInt(900000)+100000;
		
		return  System.currentTimeMillis()+String.valueOf(number);
		
		

	}
}
