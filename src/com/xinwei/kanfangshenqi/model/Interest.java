package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;

/**
 ********************
 * 关注
 * @author cn
 ********************
 */
public class Interest implements Serializable{
	private String buildingId;
	private String buildingName;
	private String banner;//楼盘图
	private String smallBanner;//楼盘图
	private String commentCount;
	private String followId;
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}
	
	public String getFollowId() {
		return followId;
	}
	public void setFollowId(String followId) {
		this.followId = followId;
	}
	public String getSmallBanner() {
		return smallBanner;
	}
	public void setSmallBanner(String smallBanner) {
		this.smallBanner = smallBanner;
	}
	@Override
	public String toString() {
		return "Interest [buildingId=" + buildingId + ", buildingName=" + buildingName + ", banner=" + banner
				+ ", smallBanner=" + smallBanner + ", commentCount=" + commentCount + ", followId=" + followId + "]";
	}
	
}
