package com.xinwei.kanfangshenqi.request;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.xinwei.kanfangshenqi.network.KfsqHttpRequest;
import com.xinwei.kanfangshenqi.response.UpHeadPortraitResponse;


public class UpHeadPortraitRequest extends KfsqHttpRequest<UpHeadPortraitResponse> {
	//这个是网络请求地址
//	private static final String APIPATH = "/app/v1/upload/headPortrait";
	private static final String APIPATH = "/app/v1/upload/buildingBanner";
	private String file;

	
	public UpHeadPortraitRequest(int method, String url,
			Listener<UpHeadPortraitResponse> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}
	/**
	 * 
	 * @param listener
	 * @param errorListener
	 */
	public UpHeadPortraitRequest(Listener<UpHeadPortraitResponse> listener,
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
		 map.put("file", file);
		 return map;
	}
	@Override
    public Map<String, String> getHeaders() {
        HashMap<String, String> headers = new HashMap<String, String>();
//        headers.put("transId", transid);
//        headers.put("appAgent", appagent);
//        headers.put("OSVer", osver);         
        return headers;
    }
	@Override
	public Class<UpHeadPortraitResponse> getResponseClass() {
		return UpHeadPortraitResponse.class;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}
