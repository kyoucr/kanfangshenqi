package com.xinwei.kanfangshenqi.request;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.AskDetailNewActivity;
import com.xinwei.kanfangshenqi.activity.LoginActivity;
import com.xinwei.kanfangshenqi.activity.SeeHouseCircleActivity;
import com.xinwei.kanfangshenqi.activity.SpeedinessLoginActivity;
import com.xinwei.kanfangshenqi.app.VolleyManager;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.Invalidation;
import com.xinwei.kanfangshenqi.response.GetCodeResponse;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

/**
 ************************* 
 * http请求
 * 
 * @author cn
 ************************* 
 */
public class HttpRequest {
	private static final int TIME_OUT = 15000;
	private static final int RETRY_TIMES = 0;
	public static final String SUCCESS = "1";
	public static final String UN_LOGIN = "2";

	public static Request post(Context context, String url, Object tag, Map<String, String> paramsContent,
			RequestListener requestListener) {
		return post(context, url, tag, paramsContent, null, requestListener, true);
	}

	public static Request post(final Context context, final String url, Object tag,
			final Map<String, String> paramsContent, final Map<String, String> paramsHead,
			final RequestListener requestListener) {
		return post(context, url, tag, paramsContent, paramsHead, requestListener, false);
	}

	public static Request post(Context context, String url, Object tag, Map<String, String> paramsContent,
			RequestListener requestListener, boolean isFinishUnLogin) {
		return post(context, url, tag, paramsContent, null, requestListener, isFinishUnLogin);
	}

	public static Request get(Context context, String url, Object tag, Map<String, String> paramsContent,
			RequestListener requestListener) {
		return get(context, url, tag, paramsContent, null, requestListener, true,true);
	}

	public static Request get(Context context, String url, Object tag, Map<String, String> paramsContent,
			Map<String, String> paramsHead, RequestListener requestListener) {
		return get(context, url, tag, paramsContent, paramsHead, requestListener, false,true);
	}

	public static Request get(Context context, String url, Object tag, Map<String, String> paramsContent,
			RequestListener requestListener, boolean isFinishLogin) {
		return get(context, url, tag, paramsContent, null, requestListener, isFinishLogin,true);
	}
	public static Request get(Context context, String url, Object tag, Map<String, String> paramsContent,
			RequestListener requestListener, boolean isFinishLogin,boolean isToLogin) {
		return get(context, url, tag, paramsContent, null, requestListener, isFinishLogin,isToLogin);
	}
	public static Request get(Context context, String url, Object tag,
			Map<String, String> paramsContent, Map<String, String> paramsHead,
			RequestListener requestListener, boolean isFinishUnLogin){
		return get(context, url, tag, paramsContent, paramsHead, requestListener, isFinishUnLogin,true); 
	}
	public static Request rest(Context context, String url, Object tag, List<Map<String, String>> paramsContent,
			RequestListener requestListener) {
		return rest(context, url, tag, paramsContent, null, requestListener);
	}

	/**
	 ******************* 
	 * post请求
	 * 
	 * @param context
	 * @param url
	 * @param tag
	 * @param params
	 * @param headers
	 * @param requestListener
	 * @return
	 ******************* 
	 */
	public static Request post(final Context context, final String url, Object tag,
			final Map<String, String> paramsContent, final Map<String, String> paramsHead,
			final RequestListener requestListener, final boolean isFinishUnLogin) {
		Utils.logCN("paramsContent:" + paramsContent);
		Utils.logCN("paramsHead:" + paramsHead);
		RequestQueue mVolleyQueue = VolleyManager.getRequestQueue();
		StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Gson gson = new Gson();
				try {
					Utils.logCN("onSuccess:" + new JSONObject(response));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					GetCodeResponse result = gson.fromJson(response, GetCodeResponse.class);
					if (requestListener != null) {
						if (SUCCESS.equals(result.getStatus())) {
							if(context instanceof LoginActivity||context instanceof SpeedinessLoginActivity){
								if(SeeHouseCircleActivity.INSTANCE!=null)
									SeeHouseCircleActivity.INSTANCE.finish();
							}
							requestListener.onSuccess(url, response);
						} else {
							if (UN_LOGIN.equals(result.getStatus())) {
								EventBus.getDefault().post(new Invalidation());
								if (context instanceof Activity) {
									Activity activity = ((Activity) context);
									Bundle data = new Bundle();
									data.putBoolean(Const.FLAG_UNLOGIN, true);
									if (isFinishUnLogin)
										Utils.moveTo(activity, LoginActivity.class, true, data);
									else {
										requestListener.onError(url, response);
										Utils.moveTo(activity, LoginActivity.class, false, data);
									}
									// activity.overridePendingTransition(
									// R.anim.abc_slide_in_bottom,
									// 0);
								}
							} else {
								requestListener.onError(url, response);
							}
							if (ValidatorUtil.isValidString(result.getMsg()) && context != null) {
								ToastUtil.show(context, result.getMsg());
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (context != null) {
						ToastUtil.show(context, context.getString(R.string.hint_service_net));
					}
					if (requestListener != null)
						requestListener.onFailure(url, e.getMessage());
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Utils.logCN("error:" + error.getMessage());
				if (requestListener != null)
					requestListener.onFailure(url, error.getMessage());
				if (context != null)
					ToastUtil.show(context, context.getString(R.string.hint_error_net));
			}
		}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return paramsContent;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (paramsHead == null)
					return super.getHeaders();
				return paramsHead;
			}
		};
		request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT, RETRY_TIMES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		request.setShouldCache(false);
		if (tag != null) {
			request.setTag(tag);
		}
		mVolleyQueue.add(request);
		return request;
	}

	/**
	 ******************* 
	 * get请求
	 * 
	 * @param context
	 * @param url
	 * @param tag
	 * @param params
	 * @param headers
	 * @param requestListener
	 * @return
	 ******************* 
	 */
	public static Request get(final Context context, final String url, Object tag,
			final Map<String, String> paramsContent, final Map<String, String> paramsHead,
			final RequestListener requestListener, final boolean isFinishUnLogin, final boolean isToLogin) {
		Utils.logCN("url:" + url);
		Utils.logCN("paramsContent:" + paramsContent);
		Utils.logCN("paramsHead:" + paramsHead);
		String urlTemp = url;
		RequestQueue mVolleyQueue = VolleyManager.getRequestQueue();
		if (paramsContent != null) {
			urlTemp += createGetParameters(paramsContent);
		}
		final String urlGet = urlTemp;
		StringRequest request = new StringRequest(Request.Method.GET, urlGet, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Gson gson = new Gson();
				try {
					Utils.logCN("onSuccess:" + new JSONObject(response));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					GetCodeResponse result = gson.fromJson(response, GetCodeResponse.class);
					if (requestListener != null) {
						if (SUCCESS.equals(result.getStatus())) {
							requestListener.onSuccess(url, response);
						} else {
							if (UN_LOGIN.equals(result.getStatus())) {
								EventBus.getDefault().post(new Invalidation());
								if (context instanceof Activity) {
									Activity activity = ((Activity) context);
									Bundle data = new Bundle();
									data.putBoolean(Const.FLAG_UNLOGIN, true);
									if (isToLogin) {
										if (isFinishUnLogin)
											Utils.moveTo(activity, LoginActivity.class, true, data);
										else {
											requestListener.onError(url, response);
											Utils.moveTo(activity, LoginActivity.class, false, data);
										}
									} else {
										requestListener.onError(url, response);
									}
									// activity.overridePendingTransition(
									// R.anim.abc_slide_in_bottom,
									// 0);
								}
							} else {
								requestListener.onError(url, response);
							}
							if (ValidatorUtil.isValidString(result.getMsg()) && context != null) {
								if (!(urlGet.contains(Const.URL_DISCOVERY)
										|| urlGet.contains(Const.URL_NAME_OF_HOUSES)))
									ToastUtil.show(context, result.getMsg());
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (context != null) {
						if (!(url.contains(Const.URL_DISCOVERY) || urlGet.contains(Const.URL_NAME_OF_HOUSES)))
							ToastUtil.show(context, context.getString(R.string.hint_service_net));
					}
					if (requestListener != null)
						requestListener.onFailure(url, e.getMessage());
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Utils.logCN("error:" + error.getMessage());
				if (requestListener != null)
					requestListener.onFailure(url, error.getMessage());
				if (context != null)
					if (!(url.contains(Const.URL_DISCOVERY) || urlGet.contains(Const.URL_NAME_OF_HOUSES)))
						ToastUtil.show(context, context.getString(R.string.hint_error_net));
			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (paramsHead == null)
					return super.getHeaders();
				return paramsHead;
			}
		};
		request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT, RETRY_TIMES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		request.setShouldCache(false);
		if (tag != null) {
			request.setTag(tag);
		}
		mVolleyQueue.add(request);
		return request;
	}

	/**
	 ******************* 
	 * get请求
	 * 
	 * @param context
	 * @param url
	 * @param tag
	 * @param params
	 * @param headers
	 * @param requestListener
	 * @return
	 ******************* 
	 */
	public static Request rest(final Context context, final String url, Object tag,
			final List<Map<String, String>> paramsContent, final Map<String, String> paramsHead,
			final RequestListener requestListener) {
		Utils.logCN("paramsContent:" + paramsContent);
		Utils.logCN("paramsHead:" + paramsHead);
		String urlRest = url;
		RequestQueue mVolleyQueue = VolleyManager.getRequestQueue();
		if (paramsContent != null) {
			urlRest += createRestParameters(paramsContent);
		}
		StringRequest request = new StringRequest(Request.Method.GET, urlRest, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				Gson gson = new Gson();
				try {
					Utils.logCN("onSuccess:" + new JSONObject(response));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					GetCodeResponse result = gson.fromJson(response, GetCodeResponse.class);
					if (requestListener != null) {
						if (SUCCESS.equals(result.getStatus())) {
							requestListener.onSuccess(url, response);
						} else {
							if (UN_LOGIN.equals(result.getStatus())) {
								EventBus.getDefault().post(new Invalidation());
								if (context instanceof Activity) {
									if(context instanceof AskDetailNewActivity){
										if(SeeHouseCircleActivity.INSTANCE!=null)
											SeeHouseCircleActivity.INSTANCE.finish();
									}
									Activity activity = ((Activity) context);
									Bundle data = new Bundle();
									data.putBoolean(Const.FLAG_UNLOGIN, true);
									// Intent intent = new Intent();
									// intent.putExtras(data);
									// activity.startActivity(intent);
									// activity.finish();
									Utils.moveTo(activity, LoginActivity.class, true, data);
									// activity.overridePendingTransition(
									// R.anim.abc_slide_in_bottom,
									// 0);
								}
							} else {
								requestListener.onError(url, response);
							}
							if (ValidatorUtil.isValidString(result.getMsg()) && context != null) {
								ToastUtil.show(context, result.getMsg());
							}

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					if (context != null) {
						ToastUtil.show(context, context.getString(R.string.hint_service_net));
					}
					if (requestListener != null)
						requestListener.onFailure(url, e.getMessage());
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Utils.logCN("error:" + error.getMessage());
				if (requestListener != null)
					requestListener.onFailure(url, error.getMessage());
				if (context != null)
					ToastUtil.show(context, context.getString(R.string.hint_error_net));
			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				if (paramsHead == null)
					return super.getHeaders();
				return paramsHead;
			}
		};
		request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT, RETRY_TIMES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		request.setShouldCache(false);
		if (tag != null) {
			request.setTag(tag);
		}
		mVolleyQueue.add(request);
		return request;
	}

	private static String createGetParameters(Map<String, String> parameter) {
		if (parameter != null) {
			StringBuffer linkParameters = new StringBuffer("?");
			for (Map.Entry<String, String> entry : parameter.entrySet()) {
				try {
					linkParameters.append(entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), "UTF-8") + "&");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return linkParameters.deleteCharAt(linkParameters.length() - 1).toString().trim();
		} else {
			return "";
		}
	}

	private static String createRestParameters(List<Map<String, String>> paramsContent) {
		StringBuffer linkParameters = new StringBuffer("");
		if (paramsContent != null) {
			for (int i = 0; i < paramsContent.size(); i++) {
				Map<String, String> parameter = paramsContent.get(i);
				for (Map.Entry<String, String> entry : parameter.entrySet()) {
					try {
						linkParameters
								.append(entry.getKey() + "/" + URLEncoder.encode(entry.getValue(), "UTF-8") + "/");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return linkParameters.deleteCharAt(linkParameters.length() - 1).toString().trim();
	}

	/**
	 * 访问结果接听
	 * 
	 * @author cn
	 * 
	 */
	public interface RequestListener {
		/**
		 ************************* 
		 * 接口访问成功，返回正确结果
		 * 
		 * @param url
		 * @param responseResult
		 ************************* 
		 */
		public void onSuccess(String url, String responseResult);

		/**
		 ************************* 
		 * 接口访问成功，返回错误结果
		 * 
		 * @param url
		 * @param responseResult
		 ************************* 
		 */
		public void onError(String url, String responseResult);

		/**
		 ************************* 
		 * 接口访问失败，无响应
		 * 
		 * @param url
		 * @param errorInfo
		 ************************* 
		 */
		public void onFailure(String url, String errorInfo);
	}

}