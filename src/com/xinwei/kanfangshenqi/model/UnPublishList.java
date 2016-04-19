package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;
import java.util.List;
/**
 ********************
 * 待发表
 * @author cn
 ********************
 */
public class UnPublishList implements Serializable{
	private int pageRowCnt;
	private int pageCount;
	private int dataCount;
	private List<UnPublish> dataList;
	
	public int getPageRowCnt() {
		return pageRowCnt;
	}

	public void setPageRowCnt(int pageRowCnt) {
		this.pageRowCnt = pageRowCnt;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public List<UnPublish> getDataList() {
		return dataList;
	}

	public void setDataList(List<UnPublish> dataList) {
		this.dataList = dataList;
	}
	
	@Override
	public String toString() {
		return "UnPublishList [pageRowCnt=" + pageRowCnt + ", pageCount="
				+ pageCount + ", dataCount=" + dataCount + ", dataList="
				+ dataList + "]";
	}

	public class UnPublish implements Serializable{
		private int buildingId;
		private String buildingName;
		private String buildingImg;
		private String smallBuildingImg;
		private String planTime;
		public int getBuildingId() {
			return buildingId;
		}
		public void setBuildingId(int buildingId) {
			this.buildingId = buildingId;
		}
		public String getBuildingName() {
			return buildingName;
		}
		public void setBuildingName(String buildingName) {
			this.buildingName = buildingName;
		}
		public String getBuildingImg() {
			return buildingImg;
		}
		public void setBuildingImg(String buildingImg) {
			this.buildingImg = buildingImg;
		}
		public String getPlanTime() {
			return planTime;
		}
		public void setPlanTime(String planTime) {
			this.planTime = planTime;
		}
		
		public String getSmallBuildingImg() {
			return smallBuildingImg;
		}
		public void setSmallBuildingImg(String smallBuildingImg) {
			this.smallBuildingImg = smallBuildingImg;
		}
		@Override
		public String toString() {
			return "UnPublish [buildingId=" + buildingId + ", buildingName=" + buildingName + ", buildingImg="
					+ buildingImg + ", smallBuildingImg=" + smallBuildingImg + ", planTime=" + planTime + "]";
		}
	}
}
