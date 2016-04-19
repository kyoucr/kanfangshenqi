package com.xinwei.kanfangshenqi.request;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.xinwei.kanfangshenqi.network.KfsqHttpRequest;
import com.xinwei.kanfangshenqi.response.LoginResponse;


public class LoginRequest extends KfsqHttpRequest<LoginResponse> {
	//这个是网络请求地址
	private static final String APIPATH = "/app/v1/login";
	
	private String telphone;

	private String password;
	
	private String transid;
	
	private String appagent;
	
	private String osver;
	
	public LoginRequest(int method, String url,
			Listener<LoginResponse> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}
	/**
	 * 
	 * @param listener
	 * @param errorListener
	 */
	public LoginRequest(Listener<LoginResponse> listener,
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
		 //需要添加的参数
		 map.put("account", telphone);
		 map.put("password",password);
		 return map;
	}
	@Override
    public Map<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("transId", transid);
        headers.put("appAgent", appagent);
        headers.put("OSVer", osver);         
        return headers;
    }
	@Override
	public Class<LoginResponse> getResponseClass() {
		return LoginResponse.class;
	}
	
	public String gettelphone() {
		return telphone;
	}
	public void settelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getTransid() {
		return transid;
	}
	public void setTransid(String transid) {
		this.transid = transid;
	}
	public String getAppagent() {
		return appagent;
	}
	public void setAppagent(String appagent) {
		this.appagent = appagent;
	}
	public String getOsver() {
		return osver;
	}
	public void setOsver(String osver) {
		this.osver = osver;
	}

}
