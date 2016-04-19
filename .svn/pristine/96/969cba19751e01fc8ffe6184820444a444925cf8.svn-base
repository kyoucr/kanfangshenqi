package com.king.photo.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xutils.HttpManager;
import org.xutils.x;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.ProgressCallback;
import org.xutils.http.RequestParams;

import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.LoginActivity;
import com.xinwei.kanfangshenqi.app.XWApplication;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.GetCodeResponse;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;

import android.app.Activity;
import android.os.Bundle;

public class HttpXUtils {
	private static final String SUCCESS = "1";
	private static final String UN_LOGIN = "2";
	public static Cancelable upload(final Activity activity, final String url,
			List<File> listFile, Map<String, String> paramsContent,
			Map<String, String> paramsHeader,
			final RequestListener requestListener, final boolean isJudgeLogin) {
		RequestParams params = new RequestParams(url);
		params.setMultipart(true);
		if (paramsHeader != null) {
			Iterator<String> iterator = paramsHeader.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String value = paramsHeader.get(key);
				params.addHeader(key, value);
			}
		}
		for (int i = 0; i < listFile.size(); i++) {
			params.addBodyParameter("file", listFile.get(i));
		}
		if (paramsContent != null) {
			Iterator<String> iterator = paramsContent.keySet().iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				String value = paramsContent.get(key);
				params.addBodyParameter(key, value);
			}
		}
		HttpManager http = x.http();
		return http.post(params, new ProgressCallback<String>() {
			@Override
			public void onLoading(long total, long current,
					boolean isDownloading) {
			}

			@Override
			public void onSuccess(String response) {
				Utils.logCN("response:"+response);
				Gson gson = new Gson();
				GetCodeResponse result = gson.fromJson(response,
						GetCodeResponse.class);
					if (requestListener != null) {
						if (SUCCESS.equals(result.getStatus())) {
							requestListener.onSuccess(url, response);
						} else {
							if (UN_LOGIN.equals(result.getStatus())) {
								if (isJudgeLogin&&activity!=null) {
									activity.finish();
									Bundle data = new Bundle();
									data.putBoolean(Const.FLAG_UNLOGIN,
											true);
									Utils.moveTo(activity,
											LoginActivity.class, false,
											data);
								}
								requestListener.onError(url, response);
							} else {
								requestListener.onError(url, response);
							}
							if (result.getMsg() != null) {
								if (isJudgeLogin)
									ToastUtil.show(
											XWApplication.getInstance(),
											result.getMsg());
							}

						}
					}
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {
				if (requestListener != null)
					requestListener.onFailure(url, ex.getMessage());
				if (isJudgeLogin&&activity!=null)
					ToastUtil.show(XWApplication.getInstance(),
							activity.getString(R.string.hint_error_net));
			}

			@Override
			public void onCancelled(CancelledException cex) {
			}

			@Override
			public void onFinished() {
			}

			@Override
			public void onWaiting() {
			}

			@Override
			public void onStarted() {
			}

		});
	}
//	public static HttpHandler upload(final Activity activity, final String url,
//			List<File> listFile, Map<String, String> paramsContent,
//			Map<String, String> paramsHeader,
//			final RequestListener requestListener, final boolean isJudgeLogin) {
//		RequestParams params = new RequestParams();
//		if (paramsHeader != null) {
//			Iterator<String> iterator = paramsHeader.keySet().iterator();
//			while (iterator.hasNext()) {
//				String key = iterator.next();
//				String value = paramsHeader.get(key);
//				params.addHeader(key, value);
//			}
//		}
//		for (int i = 0; i < listFile.size(); i++) {
//			File file = null;
//			if(listFile.get(i).length()>1024*1024){
//				file = PhotoFileUtils.saveLocalPic(listFile.get(i));
//				params.addFileParam("file", file);
//				Utils.logCN(file.getName()+":"+file.length());
//			}else{
//				params.addFileParam("file", listFile.get(i));
//			} 
//			
//		}
//		if (paramsContent != null) {
//			Iterator<String> iterator = paramsContent.keySet().iterator();
//			while (iterator.hasNext()) {
//				String key = iterator.next();
//				String value = paramsContent.get(key);
//				params.addBodyParameter(key, value);
//			}
//		}
//		HttpUtils http = new HttpUtils();
//		return http.send(HttpRequest.HttpMethod.POST, url, params,
//				new RequestCallBack<String>() {
//					@Override
//					public void onStart() {
//					}
//
//					@Override
//					public void onLoading(long total, long current,
//							boolean isUploading) {
//						Utils.logCN("total:"+total);
//						Utils.logCN("current:"+current);
//					}
//
//					@Override
//					public void onSuccess(ResponseInfo<String> responseInfo) {
//						String response = responseInfo.result;
//						Utils.logCN("response:"+response);
//						Gson gson = new Gson();
//						GetCodeResponse result = gson.fromJson(response,
//								GetCodeResponse.class);
//						if (requestListener != null) {
//							if (SUCCESS.equals(result.getStatus())) {
//								requestListener.onSuccess(url, response);
//							} else {
//								if (UN_LOGIN.equals(result.getStatus())) {
//									if (isJudgeLogin&&activity!=null) {
//										activity.finish();
//										Bundle data = new Bundle();
//										data.putBoolean(Const.FLAG_UNLOGIN,
//												true);
//										Utils.moveTo(activity,
//												LoginActivity.class, false,
//												data);
//									}
//									requestListener.onError(url, response);
//								} else {
//									requestListener.onError(url, response);
//								}
//								if (result.getMsg() != null) {
//									if (isJudgeLogin)
//										ToastUtil.show(
//												XWApplication.getInstance(),
//												result.getMsg());
//								}
//
//							}
//						}
//					}
//
//					@Override
//					public void onFailure(HttpException error, String msg) {
//						if (requestListener != null)
//							requestListener.onFailure(url, error.getMessage());
//						if (isJudgeLogin&&activity!=null)
//							ToastUtil.show(XWApplication.getInstance(),
//									activity.getString(R.string.hint_error_net));
//					}
//				});
//	}
}
