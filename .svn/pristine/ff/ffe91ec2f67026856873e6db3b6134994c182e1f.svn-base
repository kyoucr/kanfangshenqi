package com.xinwei.kanfangshenqi.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.FileUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
/**
 ********************
 * QQ分享
 * 好友分享和空间分享均在QQ应用中操作，
 * 空间分享不在空间应用中进行操作
 * @author cn
 ********************
 */
public class ShareQQ {
	private static final String APPID_QQ = "1104942196";
	private Activity activity;
	private static Tencent mTencent;
	private static ShareQQ instance;
	private String imgShareFileName = "/ic_rect_logo.png";
	private String qqPackage = "com.tencent.mobileqq";
	private ShareQQ(Activity activity) {
		this.activity = activity;
		mTencent = Tencent.createInstance(APPID_QQ, activity);
		try {
			File file = new File(FileUtils.getInstance().getCachePathName()+imgShareFileName);
			if(!file.exists()){
				InputStream is = activity.getAssets().open("ic_rect_logo.png");
	            FileOutputStream fos = new FileOutputStream(file);
	            byte[] buffer = new byte[1024];
	            while (true) {
	                int len = is.read(buffer);
	                if (len == -1) {
	                    break;
	                }
	                fos.write(buffer, 0, len);
	            }
	            is.close();
	            fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ShareQQ getInstance(Activity activity) {
		if (instance == null)
			instance = new ShareQQ(activity);
		return instance;
	}

	public void shareQQ() {
		if(!Utils.isAppInstalled(qqPackage)){
			throw(new RuntimeException("QQ is not installed"));
		}
		final Bundle params = new Bundle();
		params.putString(QQShare.SHARE_TO_QQ_TITLE, activity.getString(R.string.txt_share_title));
		params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, activity.getString(R.string.txt_share_url));
		params.putString(QQShare.SHARE_TO_QQ_SUMMARY, activity.getString(R.string.txt_share_desc));
		params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,FileUtils.getInstance().getCachePathName()+imgShareFileName);
//		params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,"/data/app/com.xinwei.kanfangshenqi-1/assets/ic_rect_logo.png");
//		params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"");
		params.putString(QQShare.SHARE_TO_QQ_APP_NAME, activity.getString(R.string.app_name));
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,
				QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
		params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,
				QQShare.SHARE_TO_QQ_FLAG_QZONE_ITEM_HIDE);
		mTencent.shareToQQ(activity, params, new IUiListener() {

			@Override
			public void onCancel() {
			}

			@Override
			public void onError(UiError e) {
			}

			@Override
			public void onComplete(Object response) {
			}
		});
	}
	public void shareQZone() {
		if(!Utils.isAppInstalled(qqPackage)){
			throw(new RuntimeException("QQ is not installed"));
		}
		final Bundle params = new Bundle();
		params.putString(QQShare.SHARE_TO_QQ_TITLE, activity.getString(R.string.txt_share_title));
		params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, activity.getString(R.string.txt_share_url));
		params.putString(QQShare.SHARE_TO_QQ_SUMMARY, activity.getString(R.string.txt_share_desc));
		params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,FileUtils.getInstance().getCachePathName()+imgShareFileName);
//		params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL,"/data/app/com.xinwei.kanfangshenqi-1/assets/ic_rect_logo.png");
		// params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,"");
		params.putString(QQShare.SHARE_TO_QQ_APP_NAME, activity.getString(R.string.app_name));
		params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE,
				QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
		params.putInt(QQShare.SHARE_TO_QQ_EXT_INT,
				QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
		mTencent.shareToQQ(activity, params, new IUiListener() {
			@Override
			public void onCancel() {
			}
			@Override
			public void onError(UiError e) {
			}
			@Override
			public void onComplete(Object response) {
			}
		});
	}

}
