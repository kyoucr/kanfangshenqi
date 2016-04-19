package com.xinwei.kanfangshenqi.model;

import java.util.List;

public class RecommendBuildings {
	private List<DataList> dataList;
	private int pageCount;
	public List<DataList> getDataList() {
		return dataList;
	}
	public void setDataList(List<DataList> dataList) {
		this.dataList = dataList;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	@Override
	public String toString() {
		return "RecommendBuildings [dataList=" + dataList + ", pageCount=" + pageCount + "]";
	} 
	
}
