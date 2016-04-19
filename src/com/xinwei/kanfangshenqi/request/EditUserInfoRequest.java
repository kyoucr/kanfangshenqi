package com.xinwei.kanfangshenqi.request;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.xinwei.kanfangshenqi.network.KfsqHttpRequest;
import com.xinwei.kanfangshenqi.response.EditUserInfoResponse;

public class EditUserInfoRequest extends KfsqHttpRequest<EditUserInfoResponse> {
	
	private static final String APIPATH = "/app/v1/member/m";
	private String password;
	private String phone;
	private String nickName;
	private String sex;
	private String companyAddr;
	private String companyLongitude;
	private String companyLatitude;
	private String homeAddr;
	private String homeLongitude;
	private String homeLatitude;
	private String transid;
	private String appagent;
	private String osver;
	private String token;
	
	public EditUserInfoRequest(int method, String url,
			Listener<EditUserInfoResponse> listener, ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}
	/**
	 * 
	 * @param listener
	 * @param errorListener
	 */
	public EditUserInfoRequest(Listener<EditUserInfoResponse> listener,
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
		 map.put("password", password);
		 map.put("nickName", nickName);
		 map.put("sex", sex);
		 map.put("companyAddr", companyAddr);
		 map.put("companyLongitude", companyLongitude);
		 map.put("companyLatitude", companyLatitude);
		 map.put("homeAddr", homeAddr);
		 map.put("homeLongitude", homeLongitude);
		 map.put("homeLatitude", homeLatitude);
		 map.put("phone", phone);
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
	public Class<EditUserInfoResponse> getResponseClass() {
		return EditUserInfoResponse.class;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCompanyAddr() {
		return companyAddr;
	}
	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}
	public String getCompanyLongitude() {
		return companyLongitude;
	}
	public void setCompanyLongitude(String companyLongitude) {
		this.companyLongitude = companyLongitude;
	}
	public String getCompanyLatitude() {
		return companyLatitude;
	}
	public void setCompanyLatitude(String companyLatitude) {
		this.companyLatitude = companyLatitude;
	}
	public String getHomeAddr() {
		return homeAddr;
	}
	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}
	public String getHomeLongitude() {
		return homeLongitude;
	}
	public void setHomeLongitude(String homeLongitude) {
		this.homeLongitude = homeLongitude;
	}
	public String getHomeLatitude() {
		return homeLatitude;
	}
	public void setHomeLatitude(String homeLatitude) {
		this.homeLatitude = homeLatitude;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
