package com.xinwei.kanfangshenqi.request;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.xinwei.kanfangshenqi.network.KfsqHttpRequest;
import com.xinwei.kanfangshenqi.response.GetCodeResponse;

public class GetCodeRequest extends KfsqHttpRequest<GetCodeResponse>{
	//这个是网络请求地址
		private static final String APIPATH = "/app/v1/member/sendvc";
		private String codeType;
		private String phone;
		
		
		public String getCodeType() {
			return codeType;
		}
		public void setCodeType(String codeType) {
			this.codeType = codeType;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public GetCodeRequest(int method, String url,
				Listener<GetCodeResponse> listener, ErrorListener errorListener) {
			super(method, url, listener, errorListener);
		}
		/**
		 * 
		 * @param listener
		 * @param errorListener
		 */
		public GetCodeRequest(Listener<GetCodeResponse> listener,
				ErrorListener errorListener) {
			super(Method.POST, APIPATH, listener, errorListener);
		}

		@Override
		public String GetApiPath() {
//			return Constants.SERVER_URL + Constants.Api.Http.TEST;
			return APIPATH;
		}
		
		@Override
		public Map<String, String> GetParameters() {
			 Map<String, String> map =  new HashMap<String, String>();
			 //需要添加的参数
			 map.put("codeType", codeType);
			 map.put("phone", phone);
			 return map;
		}
		public Map<String, String> getHeaders() {
	        HashMap<String, String> headers = new HashMap<String, String>();
//	        headers.put("transId", transid);
//	        headers.put("appAgent", appagent);
//	        headers.put("OSVer", osver);   
//	        headers.put("token",token);
	        return headers;
	    }
		public Class<GetCodeResponse> getResponseClass() {
			return GetCodeResponse.class;
		}

}
