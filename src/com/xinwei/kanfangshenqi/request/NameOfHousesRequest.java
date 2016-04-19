package com.xinwei.kanfangshenqi.request;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.xinwei.kanfangshenqi.network.KfsqHttpRequest;
import com.xinwei.kanfangshenqi.response.NameOfHousesResponse;
//app001002多条件分页查询楼盘
public class NameOfHousesRequest extends KfsqHttpRequest<NameOfHousesResponse> {
	// 这个是网络请求地址
	private static final String APIPATH = "/app/v1/buildings";
	private String areaId;
	private String priceId;
	private String featureId;
	private String position;
	private String developer;
	private String buildingName;
	private String longitude;
	private String latitude;
	private String longitudeSpan;
	private String latitudeSpan;
	private String isPage;
	private String pageNum;
	private String pageRowCnt;
	private String isComprehensive;

	public NameOfHousesRequest(int method, String url, Listener<NameOfHousesResponse> listener,
			ErrorListener errorListener) {
		super(method, url, listener, errorListener);
	}

	/**
	 * 
	 * @param listener
	 * @param errorListener
	 */
	public NameOfHousesRequest(Listener<NameOfHousesResponse> listener, ErrorListener errorListener) {
		super(Method.GET, APIPATH, listener, errorListener);
	}

	@Override
	public String GetApiPath() {
		// return Constants.SERVER_URL + Constants.Api.Http.TEST;
		return APIPATH;
	}

	@Override
	public Map<String, String> GetParameters() {
		 Map<String, String> map =  new HashMap<String, String>();
		 //需要添加的参数
//		 map.put("areaId",areaId);
//		 map.put("priceId",priceId);
//		 map.put("featureId",featureId);
//		 map.put("position",position);
//		 map.put("developer",developer);
		 map.put("buildingName",buildingName);
//		 map.put("longitude",longitude);
//		 map.put("latitude",latitude);
//		 map.put("longitudeSpan",longitudeSpan);
//		 map.put("latitudeSpan",latitudeSpan);
//		 map.put("isPage",isPage);
//		 map.put("pageNum",pageNum);
//		 map.put("pageRowCnt",pageRowCnt);
//		 map.put("isComprehensive",isComprehensive);

		 return map;
	}

	@Override
	public Map<String, String> getHeaders() {
		HashMap<String, String> headers = new HashMap<String, String>();
		// headers.put("transId", transid);
		// headers.put("appAgent", appagent);
		// headers.put("OSVer", osver);
		return headers;
	}

	@Override
	public Class<NameOfHousesResponse> getResponseClass() {
		return NameOfHousesResponse.class;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getPriceId() {
		return priceId;
	}

	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	public String getFeatureId() {
		return featureId;
	}

	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitudeSpan() {
		return longitudeSpan;
	}

	public void setLongitudeSpan(String longitudeSpan) {
		this.longitudeSpan = longitudeSpan;
	}

	public String getLatitudeSpan() {
		return latitudeSpan;
	}

	public void setLatitudeSpan(String latitudeSpan) {
		this.latitudeSpan = latitudeSpan;
	}

	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getPageRowCnt() {
		return pageRowCnt;
	}

	public void setPageRowCnt(String pageRowCnt) {
		this.pageRowCnt = pageRowCnt;
	}

	public String getIsComprehensive() {
		return isComprehensive;
	}

	public void setIsComprehensive(String isComprehensive) {
		this.isComprehensive = isComprehensive;
	}
	
}
