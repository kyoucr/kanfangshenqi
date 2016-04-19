package com.xinwei.kanfangshenqi.request;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.xinwei.kanfangshenqi.network.KfsqHttpRequest;
import com.xinwei.kanfangshenqi.response.PlansResponse;


public class PlansRequest extends KfsqHttpRequest<PlansResponse> {
	//这个是网络请求地址
	private static final String APIPATH = "/app/v1/plans";
	private String transid;
	private String appagent;
	private String osver;
	private String token;
	public PlansRequest(int method, String url,
			Listener<PlansResponse> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}
	/**
	 * 
	 * @param listener
	 * @param errorListener
	 */
	public PlansRequest(Listener<PlansResponse> listener,
			ErrorListener errorListener) {
		super(Method.GET, APIPATH, listener, errorListener);
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
		 return map;
	}
	@Override
    public Map<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("transId", transid);
        headers.put("appAgent", appagent);
        headers.put("OSVer", osver);      
        headers.put("token",token);
        return headers;
    }
	@Override
	public Class<PlansResponse> getResponseClass() {
		return PlansResponse.class;
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
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
