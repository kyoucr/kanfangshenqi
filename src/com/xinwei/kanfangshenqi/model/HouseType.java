package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;

public class HouseType implements Serializable{
	private String unitMapId;
	private String imgDes;
	private String imgSmallPath;
	private String imgPath;
	private String imgName;
	public String getUnitMapId() {
		return unitMapId;
	}
	public void setUnitMapId(String unitMapId) {
		this.unitMapId = unitMapId;
	}
	public String getImgDes() {
		return imgDes;
	}
	public void setImgDes(String imgDes) {
		this.imgDes = imgDes;
	}
	public String getImgSmallPath() {
		return imgSmallPath;
	}
	public void setImgSmallPath(String imgSmallPath) {
		this.imgSmallPath = imgSmallPath;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	@Override
	public String toString() {
		return "HouseType [unitMapId=" + unitMapId + ", imgDes=" + imgDes + ", imgSmallPath=" + imgSmallPath
				+ ", imgPath=" + imgPath + ", imgName=" + imgName + "]";
	}
	
}
