package com.xinwei.kanfangshenqi.network;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.xinwei.kanfangshenqi.app.XWApplication;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.exception.TokenInvalid;
import com.xinwei.kanfangshenqi.util.Logger;
import com.xinwei.kanfangshenqi.util.StringUtils;
import com.xinwei.kanfangshenqi.util.WebUtils;
/**
 * 
 * @author 
 * http发送请求基类
 * @param <T>HhkdHttpResponse 的子类
 */
public abstract class KfsqHttpRequest<T extends KfsqHttpResponse> extends
		Request<T> {

	private final Gson mGson;
	private final Listener<T> mListener;
	
	private static final int NOTJSON = -1;
	private static final int JSONERROR = -2;
	
	/**
	 * 
	 * @param method 
	 *   int DEPRECATED_GET_OR_POST = -1;
     *   int GET = 0;
     *   int POST = 1;
     *   int PUT = 2;
     *   int DELETE = 3;
     *   int HEAD = 4;
     *   int OPTIONS = 5;
     *   int TRACE = 6;
     *   int PATCH = 7;
	 * @param partUrl
	 * @param listener
	 * @param errorListener
	 */
	public KfsqHttpRequest(int method, String partUrl, Listener<T> listener,
			ErrorListener errorListener) {
		super(method, Const.SERVER_URL + partUrl, errorListener);
		this.mListener = listener;
		//TODO:
//		setShouldCache(false);
		mGson = new Gson();
	}
	
	public String requestUrl(){
		return Const.SERVER_URL + GetApiPath();
	}
	/**
	 * part url
	 * 
	 * @return
	 */
	abstract public String GetApiPath();

	/**
	 * 参数列表
	 * 
	 * @return
	 */
	abstract public Map<String, String> GetParameters();

	/**
	 * 应答对象，范型
	 * 
	 * @return
	 */
	abstract public Class<T> getResponseClass();
	
	/**
	 * 重写此方法以添加http请求参数
	 */
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return GetParameters();
	}
	/**
	 * 处理响应
	 */
	@Override
	protected void deliverResponse(T response) {
//		if(response != null && !StringUtils.isEmpty(response.getToken())){
//			HhkdApplication.getInstance().setToken(response.getToken());
//			Log.e("token != null", response.getToken());
//		}
//		Log.e("token ", response.getToken());
		mListener.onResponse(response);
		
	}
	/**
	 * 解析请求返回结果
	 */
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		try
        {
            Logger.d("KfsqHttpRequest",
                    requestUrl() + "?" + WebUtils.buildQuery(GetParameters()));
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Logger.d("KfsqHttpRequest", StringUtils.unicodeToChinese(json));
            return Response.success(mGson.fromJson(json, getResponseClass()),
                    HttpHeaderParser.parseCacheHeaders(response));
        }
        catch (UnsupportedEncodingException e)
        {
            return Response.error(new ParseError(e));
        }
        catch (JsonSyntaxException e)
        {
            return Response.error(new ParseError(e));
        }
	}
}
