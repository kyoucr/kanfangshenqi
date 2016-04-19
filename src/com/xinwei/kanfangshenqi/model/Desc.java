package com.xinwei.kanfangshenqi.model;

public class Desc {
	private String key;
	private String value;
	private boolean isShowAll;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean isShowAll() {
		return isShowAll;
	}
	public void setShowAll(boolean isShowAll) {
		this.isShowAll = isShowAll;
	}
	@Override
	public String toString() {
		return "Desc [key=" + key + ", value=" + value + ", isShowAll=" + isShowAll + "]";
	}
	
}
