package com.xinwei.kanfangshenqi.common;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.Utils;
/**
 ********************
 * 微信分享
 * @author cn
 ********************
 */
public class ShareWeiXin {
	public static final String APPID_WEI_XIN = "wx772a2ebec78402f7";
	private IWXAPI api;
	private Activity activity;
	private static ShareWeiXin weiXinShare;
	private String weiXinPackage = "com.tencent.mm";
	private ShareWeiXin(Activity activity) {
		this.activity = activity;
		api = WXAPIFactory.createWXAPI(activity, APPID_WEI_XIN,true);
		api.registerApp(APPID_WEI_XIN);
	}
	public static ShareWeiXin getInstance(Activity activity){
		if(weiXinShare == null)
			weiXinShare = new ShareWeiXin(activity);
		return weiXinShare;
	}
	public void shareCircle(){
		if(!Utils.isAppInstalled(weiXinPackage)){
			throw(new RuntimeException("WeiXin is not installed"));
		}
		WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl = activity.getString(R.string.txt_share_url);
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = activity.getString(R.string.txt_share_title);
		msg.description = activity.getString(R.string.txt_share_desc);
		Bitmap thumb = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_rect_logo);
		msg.thumbData = Utils.bmpToByteArray(thumb, true);
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("webpage");
		req.message = msg;
		req.scene =  SendMessageToWX.Req.WXSceneTimeline;
		api.sendReq(req);
	}
	public void shareFriend(){
		if(!Utils.isAppInstalled(weiXinPackage)){
			throw(new RuntimeException("WeiXin is not installed"));
		}
		WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl = activity.getString(R.string.txt_share_url);
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = activity.getString(R.string.txt_share_title);
		msg.description = activity.getString(R.string.txt_share_desc);
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("webpage");
		req.message = msg;
		req.scene =  SendMessageToWX.Req.WXSceneSession ;
		api.sendReq(req);
	}
	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}
	
}
