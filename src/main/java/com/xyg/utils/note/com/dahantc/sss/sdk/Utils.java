package com.xyg.utils.note.com.dahantc.sss.sdk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author wuhandahantc
 * @version 1.0
 * @createTime 2017/6/26
 */
public class Utils {

	// 随机生成一个N位数注册码
	public static String productRandom(int n) {
		Random random = new Random();
		StringBuffer strb=new StringBuffer();
		for (int i = 0; i < n; i++) {
			strb.append(random.nextInt(10));
		}
		return strb.toString();
	}

    //获取一个范围内的随机数 重载方法
    public static int getRandomNum(int begain,int end){
    	int range=end-begain;
        return  (randomNum()%range)+begain;
    }

    //获取一个系统的随机数，这个随机数是不固定的，只是根据不同的随机种子生成了数据
    public static int randomNum(){
        SimpleDateFormat sdf = new SimpleDateFormat("mmssSSSS");
        Integer nowValue = Integer.parseInt(sdf.format(new Date()));
        Random sedRandom = new Random(Math.abs(System.nanoTime() + nowValue));
        int sed = sedRandom.nextInt(nowValue);
        Random valueRandom = new Random(System.currentTimeMillis());
        return Math.abs(valueRandom.nextInt(nowValue + sed));
    }

}
