package com.xyg.utils.note.com.dahantc.sss.sdk;


/**
 *语音消息类
 *目前只开放了voiceRequestType=1 时的验证码外呼（系统默认提示音）接口，供大汉云通讯平台用户免费使用！
 **/

public class Voice {
   
	
	
	
	
    /**
     * 大汉云通讯平台 用户登录后 生成的 accountSid，和短信接口一致
     */
	private String accountSid;
	/**
	 *  大汉云通讯平台 用户登录后 生成的 authToken，和短信接口一致
	 */
	private String authToken;
	/**
	 * 语音呼叫号码
	 */
    private String phone;
    //
    /**
     * 当voiceRequestType=1 时，text只有4-8位数字 
     */    
    private String text;
      /**播放的媒体文件名称 
       */
    private String mediaName;

    /**voiceRequestType  为请求类型，数据库表中不存储
     * 1表示      验证码外呼（系统默认提示音）
     * 2    验证码外呼（自定义提示音）
     * 3    文本外呼（TTS）   
     * 4    语音文件外呼
     * 5    获取外呼语音状态报告
     * 
     */
    @SuppressWarnings("unused")
	private Voice(){}
    
    
    public Voice(String accountSid, String authToken, String phone, String text, int voiceRequestType) {
		super();
		this.accountSid = accountSid;
		this.authToken = authToken;
		this.phone = phone;
		this.text = text;
		this.voiceRequestType = voiceRequestType;
	}


	private int voiceRequestType;

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}



	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public int getVoiceRequestType() {
		return voiceRequestType;
	}

	public void setVoiceRequestType(int voiceRequestType) {
		this.voiceRequestType = voiceRequestType;
	}
   

    
    
    
   }
