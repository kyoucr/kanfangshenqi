package com.xinwei.kanfangshenqi.model;

public class EncyclopediaData {
//	{"encyclopediaId":1,"title":"什么样的户型后悔！"}
	private String encyclopediaId;
	private String title;
	public String getEncyclopediaId() {
		return encyclopediaId;
	}
	public void setEncyclopediaId(String encyclopediaId) {
		this.encyclopediaId = encyclopediaId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "EncyclopediaData [encyclopediaId=" + encyclopediaId + ", title=" + title + "]";
	}
	
}
