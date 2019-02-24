package com.xyg.utils.note;

public class SendVoiceDemo {

    /**
     * (1)系统默认提示语音验证码参数说明
     * accountSid 和 authToken  为 平台  http://www.dahancloud.com  平台登录后生成的字段，从平台网页获取。对用户来说是不变的。
     * phone 为  语音呼叫号码
     * text  为语音验证码内容   4-8位数字
     * voiceRequestType=1      1表示    系统默认提示语音验证码语音验证码。 此处值必须为1
     * (2) 系统默认提示语音验证码功能说明
     * 语音接口支持批量调用：可以传入多个手机号和对应的验证码
     * List<Map<String,String>>中传入多个phoneMap,每个phoneMap传入自定义的手机号码和自定义生成的验证码
     * (3) 用户接入语音接口实质：向大汉云通讯平台语音接口url，发送Ajax请求(传入请求参数)
     * (4)语音接口依赖 jar包说明：commons-logging-1.2.jar   httpclient-4.5.3.jar  httpcore-4.4.6.jar gson-2.2.4.jar
     * (5)语音接口调用说明：只需引入上面依赖jar包，仅需使用此VoiceCore.java文件即可。
     *
     * @param args
     */
/*    public static void main(String[] args) {

        *//**
         * 参数构造
         *//*
        String requestURL = "http://www.dahancloud.com/voice/submitVoice";//url固定不变
        List<Map<String, String>> phoneMaplist = new ArrayList<>();
        Map<String, String> phoneMap = new HashMap<String, String>();
        phoneMap.put("phone", "被呼叫语音电话号码");
        phoneMap.put("text", "发送的验证码，生成规则为4-8位数字");
        phoneMaplist.add(phoneMap);
        String requestJsonParam = VoiceCore.generateRequestParam("用户accountSid,登录大汉云通讯平台获取",
                "用户authToken,登录大汉云通讯平台获取",
                phoneMaplist);


        *//**
         * 发送请求，获取返回值
         *//*
        String result = VoiceCore.sendInfo(requestURL, requestJsonParam);//可以看出用户接入语音接口的实质，其余的可以不参照默认的实现

        *//**
         * 根据返回值，确定成功 和失败分别怎么办
         *//*
        if (result.contains("DH:0000")) {
            //todo
            System.out.println("语音发送成功");

        } else {
            //todo
            System.out.println("语音发送失败");
        }


    }*/
}
