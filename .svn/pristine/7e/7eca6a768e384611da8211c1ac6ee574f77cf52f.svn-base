package com.xinwei.kanfangshenqi.request;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.xinwei.kanfangshenqi.network.KfsqHttpRequest;
import com.xinwei.kanfangshenqi.response.RegisterResponse;

public class RegisterRequest extends KfsqHttpRequest<RegisterResponse> {
	
	private static final String APIPATH = "/app/v1/member";
	
	private String account;
	
	private String password;
	
	private String code;
	
	public RegisterRequest(int method, String url,
			Listener<RegisterResponse> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}
	/**
	 * 
	 * @param listener
	 * @param errorListener
	 */
	public RegisterRequest(Listener<RegisterResponse> listener,
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
		 map.put("account", account);
		 map.put("code", code);
		 map.put("password", password);
		 return map;
	}

	@Override
	public Class<RegisterResponse> getResponseClass() {
		return RegisterResponse.class;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}


}
