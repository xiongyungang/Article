package com.xyg.utils.note.com.dahantc.sss.sdk;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoiceCore {

	public static String generateRequestParam(String accountSid,String authToken,List<Map<String,String>> phoneMaplist){
		Map<String,Object>requestParamMap=new HashMap<String, Object>();
		requestParamMap.put("accountSid", accountSid);
		requestParamMap.put("authToken", authToken);
		requestParamMap.put("voiceRequestType", "1");
		requestParamMap.put("data", phoneMaplist);
		Gson gson=new Gson();
  		String jsonstr=gson.toJson(requestParamMap);
		return jsonstr;
	}
	
	
	
	/**
	 * 
	 * 后台访问接口请求的方法。       原则上只要向大汉云通讯语音接口 发送ajax请求 即可，实现方式自己选择。 
	 * 
	 * @param requestURL   post请求的url
	 * @param data         请求参数
	 * @return             返回json字符串
	 * 
	 */
    public static String sendInfo(String requestURL, String data) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(requestURL);
        StringEntity myEntity = new StringEntity(data,
                ContentType.APPLICATION_JSON);// 构造请求数据
        post.setEntity(myEntity);// 设置请求体
        String responseContent = null; // 响应内容
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                responseContent = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null)
                    response.close();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (client != null)
                        client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseContent;
    }

}
