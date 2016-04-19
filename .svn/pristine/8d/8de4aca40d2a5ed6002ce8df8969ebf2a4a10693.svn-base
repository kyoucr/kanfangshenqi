package com.xinwei.kanfangshenqi.request;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.xinwei.kanfangshenqi.network.KfsqHttpRequest;
import com.xinwei.kanfangshenqi.response.HttpResponse;


public class AddPlansRequest extends KfsqHttpRequest<HttpResponse> {
	//这个是网络请求地址
	private static final String APIPATH = "/app/v1/plan";
	private String buildingId;
	private String transid;
	private String appagent;
	private String osver;
	private String token;
	public AddPlansRequest(int method, String url,
			Listener<HttpResponse> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}
	/**
	 * 
	 * @param listener
	 * @param errorListener
	 */
	public AddPlansRequest(Listener<HttpResponse> listener,
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
		 map.put("buildingId", buildingId);
		 return map;
	}
	@Override
    public Map<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("transId", transid);
        headers.put("appAgent", appagent);
        headers.put("OSVer", osver);   
        headers.put("token", token);
        return headers;
    }
	@Override
	public Class<HttpResponse> getResponseClass() {
		return HttpResponse.class;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	
}
