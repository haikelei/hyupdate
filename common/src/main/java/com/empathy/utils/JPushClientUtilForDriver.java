package com.empathy.utils;


import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushClientUtilForDriver {
    /*private static final String appKey = "2e4c2b010a75a81f6fb427ea";
    private static final String masterSecret = "3bc013ed90a434a9692b0911";*/
    private static final String appKey = "8e038b489711d030ead98870";
    private static final String masterSecret = "604b83521d578067d79433c5";
    public static final String TITLE = "标题";
    public static final String ALERT = "警告";
    public static final String MSG_CONTENT = "内容122211";
    public static final String REGISTRATION_ID = "041afde4789";
    public static final String TAG = "tag_api";

    public static void main(String[] args) {
        //JPushClientUtil.pushMessage("系统消息","有用户下单了","161a3797c8333b45748","adj");
        //JPushClientUtil.pushMessage("系统消息", "您的订单已被用户确认完成", "120c83f7601305a8fcf");
        //JPushClientUtil.pushMessage("系统消息", "您的订单已被骑士确认完成", "120c83f7601305a8fcf");
    }

    public static void pushMessage(String content) {
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        // JPushClient jpushClient = new JPushClient("11ed32d43d6da996fd990532", "3817014794ab0b3fb3f5b573");
        PushPayload payload = buildPushObject_all_all_alert(content);// buildPushObject_android_and_ios(title,content,registration_id);
        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result);
            // System.out.println("q=");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*	public static void pushMessage(String title,String content,String registration_id) {
           JPushClient jpushClient = new JPushClient(masterSecret, appKey);
	       // PushPayload payload =buildPushObject_all_all_alert();//
	       PushPayload payload =  buildPushObject_android_and_ios(title,content,registration_id);
	        try {
	            PushResult result = jpushClient.sendPush(payload);
	            System.out.println(result);
	        } catch (APIConnectionException e) {
	        	e.printStackTrace();

	        } catch (APIRequestException e) {
	           e.printStackTrace();
	        }
		}
	public static void pushMessage(String title,String content,String registration_id) {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		// PushPayload payload =buildPushObject_all_all_alert();//
		PushPayload payload =  buildPushObject_android_and_ios2(title,content,registration_id);
		try {
			PushResult result = jpushClient.sendPush(payload);
			System.out.println(result);
		} catch (APIConnectionException e) {
			e.printStackTrace();

		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}*/


    public static void pushMessageCap(String title, String content, String registration_id) {
        // JPushClient jpushClient = new JPushClient(masterSecretCap, appKeyCap);
        JPushClient jpushClient = new JPushClient(masterSecret, appKey);
        PushPayload payload = buildPushObject_android_and_ios(title, content, registration_id);

        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    //	public static void pushMessageCap(String title,String content,String registration_id) {
//        JPushClient jpushClient = new JPushClient(masterSecretCap, appKeyCap);
//
//        PushPayload payload = buildPushObject_android_and_ios(title,content,registration_id);
//
//        try {
//            PushResult result = jpushClient.sendPush(payload);
//            System.out.println(result);
//        } catch (APIConnectionException e) {
//        	e.printStackTrace();
//
//        } catch (APIRequestException e) {
//           e.printStackTrace();
//        }
//	}
    public static PushPayload buildPushObject_all_all_alert(String content) {
        return PushPayload.alertAll(content);
    }
//	  return PushPayload.newBuilder()
//		         .setPlatform(Platform.all())//所有平台
//		         .setAudience(Audience.alias(alias))//向选定的人推送
//		         .setNotification(Notification.alert(content))//消息内容
//		         .build();}

    //	  return PushPayload.newBuilder().setPlatform(Platform.android())
//              .setAudience(Audience.tag(tag))//向指定的组推送
//              .setNotification(Notification.android(message, title, null)).build();
    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

//    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.android())
//                .setAudience(Audience.tag("tag1"))
//                .setNotification(Notification.android(ALERT, TITLE, null))
//                .build();
//    }

    public static PushPayload buildPushObject_android_and_ios(String title, String content, String registration_id) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())//Platform.android_ios()
                .setAudience(Audience.registrationId(registration_id))
                .setNotification(Notification.newBuilder()
                        .setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(title).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setSound("happy")
                                .incrBadge(1)
                                //.addExtra("extra_key", "extra_value")  箱、自定义字段
                                .build())
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent("")
                        //.addExtra("from", "JPush")
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)//.setApnsProduction(false)
                        .build())
                .build();
    }

	/*public static PushPayload buildPushObject_android_and_ios1(String title,String content,String registration_id,JpushVo jpushVo) {
        Integer code = jpushVo.getCode();
		Long parentId=  jpushVo.getParentId();
		return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())//Platform.android_ios()
				.setAudience(Audience.registrationId(registration_id))
				.setNotification(Notification.newBuilder()
						.setAlert(content)
						.addPlatformNotification(AndroidNotification.newBuilder()
								.setTitle(title).build())
						.addPlatformNotification(IosNotification.newBuilder()
								.setSound("happy")
								.incrBadge(1)
								.addExtra("code", "code")
								.addExtra("parentId", "parentId")
								.build())
						.build())
				.setMessage(Message.newBuilder()
						.setMsgContent("")
						//.addExtra("from", "JPush")
						.build())
				.setOptions(Options.newBuilder()
						.setApnsProduction(false)//.setApnsProduction(false)
						.build())
				.build();
	}*/


    public static PushPayload buildPushObject_android_and_ios2(String title, String content, String alias, Integer breakId) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())//Platform.android_ios()
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .setAlert(content)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setTitle(title).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setSound("happy")
                                .incrBadge(1)
                                .addExtra("breakId", breakId) // 箱、自定义字段
                                .addExtra("haha", breakId)
                                .build())
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)//.setMsgContent(content)
                        //.addExtra("from", "orderid")
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)//.setApnsProduction(false)
                        .build())
                .build();
    }


    //wym
    public static void pushMessage3(String title, String content, String alias, Integer breakId) throws InterruptedException {
        try {
            JPushClient jpushClient = new JPushClient(masterSecret, appKey);

            // PushPayload payload =buildPushObject_all_all_alert();//
            PushPayload payload = buildPushObject_android_and_ios2(title, content, alias, breakId);

            Thread.sleep(1000);

            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //wym
    public static void pushMessage2(String alias, String content) throws InterruptedException {
        try {
            JPushClient jpushClient = new JPushClient(masterSecret, appKey);

            // PushPayload payload =buildPushObject_all_all_alert();//
            PushPayload payload = buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(alias, content);

            Thread.sleep(1000);

            PushResult result = jpushClient.sendPush(payload);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PushPayload buildPushObject_android_tag_alertWithTitle(String title, String content, String alias) {


        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder().setAlert(content).addPlatformNotification(AndroidNotification.newBuilder().setTitle(title)
                        .build()).addPlatformNotification(IosNotification.newBuilder().setSound("happy").incrBadge(1).build()).build()).setMessage(Message.newBuilder().setMsgContent(content)
                        .build()).setOptions(Options.newBuilder().setApnsProduction(true).build())
                .build();
    }


    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String alias, String content) {


        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(content)
                                .setBadge(5)
                                .setSound("happy")
                                .build())
                        .build())
                .setMessage(Message.content(content))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }


}

