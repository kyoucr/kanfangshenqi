package com.xinwei.kanfangshenqi.request;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.xinwei.kanfangshenqi.network.KfsqHttpRequest;
import com.xinwei.kanfangshenqi.response.UserInfoResponse;

public class UserInfoRequest extends KfsqHttpRequest<UserInfoResponse> {
	
	private static final String APIPATH = "index.php/appsever/me/getinfo";
	
	private String token;
	
	public UserInfoRequest(int method, String url,
			Listener<UserInfoResponse> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}
	/**
	 * 
	 * @param listener
	 * @param errorListener
	 */
	public UserInfoRequest(Listener<UserInfoResponse> listener,
			ErrorListener errorListener) {
		super(Method.POST, APIPATH, listener, errorListener);
	}

	@Override
	public String GetApiPath() {
//		return Constants.SERVER_URL + Constants.Api.Http.TEST;
		return APIPATH;
	}

	@Override
	public Map<String, String> GetParameters() {
		 Map<String, String> map =  new HashMap<String, String>();
		 map.put("token", token);
		 return map;
	}

	@Override
	public Class<UserInfoResponse> getResponseClass() {
		return UserInfoResponse.class;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
