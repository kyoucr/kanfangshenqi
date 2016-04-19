package com.xinwei.kanfangshenqi.common;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;

import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.utils.Utility;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;

public class ShareXinLang {
	private static final String APPID_XINLANG_WEIBO = "1144972455";
	private static IWeiboShareAPI mWeiboShareAPI = null;
	private static ShareXinLang instance;
	private Activity activity;
	private String xinLangPackage = "com.sina.weibo";
	private ShareXinLang(Activity activity) {
		this.activity = activity;
		mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(activity,
				APPID_XINLANG_WEIBO);
		// NOTE：请务必提前注册，即界面初始化的时候或是应用程序初始化时，进行注册
		mWeiboShareAPI.registerApp();
	}

	public static ShareXinLang getInstance(Activity activity) {
		if (instance == null)
			instance = new ShareXinLang(activity);
		return instance;
	}

	/**
	 * 第三方应用发送请求消息到微博，唤起微博分享界面。 注意：当
	 * {@link IWeiboShareAPI#getWeiboAppSupportAPI()} >= 10351 时，支持同时分享多条消息，
	 * 同时可以分享文本、图片以及其它媒体资源（网页、音乐、视频、声音中的一种）。
	 */
	public void shareWB() {
		if(!Utils.isAppInstalled(xinLangPackage)){
			throw(new RuntimeException("WeiXin is not installed"));
		}
		// 1. 初始化微博的分享消息
		// WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
//		WeiboMessage weiboMessage = new WeiboMessage();
		WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
		// 用户可以分享其它媒体资源（网页、音乐、视频、声音中的一种）
//		weiboMessage.mediaObject = getWebpageObj();
		weiboMessage.imageObject = getImageObj();
		weiboMessage.textObject = getTextObj();
		// 2. 初始化从第三方到微博的消息请求
//		SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
		SendMultiMessageToWeiboRequest request = new SendMultiMessageToWeiboRequest();
		// 用transaction唯一标识一个请求
		request.transaction = String.valueOf(System.currentTimeMillis());
//		request.message = weiboMessage;
		request.multiMessage = weiboMessage;
		// 3. 发送请求消息到微博，唤起微博分享界面
		if (mWeiboShareAPI.isWeiboAppSupportAPI()) {
			mWeiboShareAPI.sendRequest(activity, request);
		} else {
			ToastUtil.show(activity, activity
					.getString(R.string.weibosdk_demo_not_support_api_hint));
		}
	}

	/**
	 * 创建多媒体（网页）消息对象。
	 * 
	 * @return 多媒体（网页）消息对象。
	 */
	private WebpageObject getWebpageObj() {
		WebpageObject mediaObject = new WebpageObject();
		mediaObject.identify = Utility.generateGUID();
		mediaObject.title = activity.getString(R.string.txt_share_title);
		mediaObject.description = activity.getString(R.string.txt_share_desc);

		Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.ic_rect_logo);
		mediaObject.setThumbImage(bitmap);
		// 设置 Bitmap 类型的图片到视频对象里 设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
		mediaObject.thumbData = Utils.bmpToByteArray(bitmap, true);
		mediaObject.actionUrl = activity.getString(R.string.txt_share_url);
		mediaObject.defaultText = "Webpage 默认文案";
		return mediaObject;
	}
	 /**
     * 创建文本消息对象。
     * 
     * @return 文本消息对象。
     */
    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getSharedText();
        return textObject;
    }
    private String getSharedText(){
    	String desc = activity.getString(R.string.txt_share_desc);
    	return desc += activity.getString(R.string.txt_share_url);
    }
	/**
     * 创建图片消息对象。
     * 
     * @return 图片消息对象。
     */
    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        //        设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        Bitmap  bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_rect_logo);
        imageObject.setImageObject(bitmap);
        return imageObject;
    }
	private void share() {
		Intent weiboIntent = new Intent(Intent.ACTION_SEND);
		weiboIntent.setType("text/plain");
		PackageManager pm = activity.getPackageManager();
		List<ResolveInfo> matches = pm.queryIntentActivities(weiboIntent,
				PackageManager.MATCH_DEFAULT_ONLY);
		String packageName = "com.sina.weibo";
		ResolveInfo info = null;
		for (ResolveInfo each : matches) {
			String pkgName = each.activityInfo.applicationInfo.packageName;
			if (packageName.equals(pkgName)) {
				info = each;
				break;
			}
		}
		if (info == null) {
			new AlertDialog.Builder(activity)
					.setTitle("提示")
					.setMessage("您还没有安装新浪微博，是否前往浏览器下载？")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Uri uri = Uri
											.parse("http://app.sina.cn/appdetail.php?appID=84560");
									Intent intent = new Intent(
											Intent.ACTION_VIEW, uri);
									activity.startActivity(intent);
									dialog.dismiss();
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.dismiss();
								}
							}).show();
		} else {
			weiboIntent.setClassName(packageName, info.activityInfo.name);
			weiboIntent.putExtra(Intent.EXTRA_TEXT, "看房神器"
					+ "http://www.baidu.com");
		}
		activity.startActivity(weiboIntent);
	}
}
