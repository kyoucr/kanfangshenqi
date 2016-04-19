package com.xinwei.kanfangshenqi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.activity.CashRedPacketActivity;
import com.xinwei.kanfangshenqi.activity.LouPanActivity;
import com.xinwei.kanfangshenqi.activity.MainActivity;
import com.xinwei.kanfangshenqi.activity.NotifyDetailInfoActivity;
import com.xinwei.kanfangshenqi.activity.PublishGiftActivity;
import com.xinwei.kanfangshenqi.activity.ShowRanslationActivity;
import com.xinwei.kanfangshenqi.activity.TradeRecordActivity;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.response.LoginResponse;
import com.xinwei.kanfangshenqi.util.StringUtils;
import com.xinwei.kanfangshenqi.util.Utils;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class JPushReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
		
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            //send the Registration Id to your server...
                        
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
        	if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
        		
        	}
//        	processCustomMessage(context, bundle);
        
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
        	
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            StringBuilder sb = new StringBuilder();
    		for (String key : bundle.keySet()) {
    			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
    			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
    			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
    				if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
    					continue;
    				}

    				try {
    					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
    					JSONObject jjson = new JSONObject(json.getString("pushType"));
    					if("autoPushType".equals(jjson.getString("pushType"))){
    						if("0".equals(jjson.getString("autoPushType"))){
    				        	//打开现金红包页面
    				        	Intent i = new Intent(context, CashRedPacketActivity.class);
    				        	i.putExtras(bundle);
    				        	//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
    				        	context.startActivity(i);
    						}
    						if("1".equals(jjson.getString("autoPushType"))){
    							Intent i = new Intent(context, PublishGiftActivity.class);
    				        	i.putExtras(bundle);
    				        	//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
    				        	context.startActivity(i);						
    						}
    						if("2".equals(jjson.getString("autoPushType"))){
//    							((MainActivity)context).showJiHuaFragment();
    							Intent i = new Intent(context, LouPanActivity.class);
    				        	i.putExtras(bundle);
    				        	//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
    				        	context.startActivity(i);
    						}
    						if("3".equals(jjson.getString("autoPushType"))){
    							Intent i = new Intent(context, TradeRecordActivity.class);
    				        	i.putExtras(bundle);
    				        	//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    				        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
    				        	context.startActivity(i);
    						}
    					}
    					if("manualPushType".equals(jjson.getString("pushType"))){
    						Intent i = new Intent(context, NotifyDetailInfoActivity.class);
    						bundle.putString(NotifyDetailInfoActivity.class.getSimpleName(), jjson.getString("pushId"));
    						i.putExtras(bundle);
    						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
				        	context.startActivity(i);
    					}
    				} catch (JSONException e) {
    				}

    			} else {
    			}
    		}
        	//打开自定义的Activity
//        	Intent i = new Intent(context, ShowRanslationActivity.class);
//        	i.putExtras(bundle);
//        	//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
//        	context.startActivity(i);
        	
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
        	
        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
        	boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        } else {
        }
	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					JSONObject jjson = new JSONObject(json.getString("pushType"));
					if("autoPushType".equals(jjson.getString("pushType"))){
						if("0".equals(jjson.getString("autoPushType"))){
							
						}
						if("0".equals(jjson.getString("autoPushType"))){
													
						}
						if("0".equals(jjson.getString("autoPushType"))){
							
						}
						if("0".equals(jjson.getString("autoPushType"))){
							
						}
					}
					if("manualPushType".equals(jjson.getString("pushType"))){
						
					}
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next().toString();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to MainActivity
//	private void processCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!StringUtils.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (null != extraJson && extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
//				} catch (JSONException e) {
//
//				}
//
//			}
//			context.sendBroadcast(msgIntent);
//		}
//	}
}
