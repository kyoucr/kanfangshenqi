package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;
import java.util.List;

import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.Ask.ImgAsk;

public class BuildingDetail {
	private int dataCount;
	private int pageRowCnt;
	private String buildingId;
	private String buildingName;
	private String banner;
	private int pageCount;
	private String isLook;
	private List<Building> dataList;
	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public int getPageRowCnt() {
		return pageRowCnt;
	}

	public void setPageRowCnt(int pageRowCnt) {
		this.pageRowCnt = pageRowCnt;
	}

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

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	
	public List<Building> getDataList() {
		return dataList;
	}

	public void setDataList(List<Building> dataList) {
		this.dataList = dataList;
	}
	public boolean getIsLook() {
		if(Const.YES.equals(isLook))
			return true;
		else
			return false;
	}

	public void setIsLook(boolean isLook) {
		if(isLook)
			this.isLook = Const.YES;
		else
			this.isLook = Const.NO;
	}

	@Override
	public String toString() {
		return "BuildingDetail [dataCount=" + dataCount + ", pageRowCnt="
				+ pageRowCnt + ", buildingId=" + buildingId + ", buildingName="
				+ buildingName + ", banner=" + banner + ", pageCount="
				+ pageCount + ", isLook=" + isLook + ", dataList=" + dataList
				+ "]";
	}

	public class Building implements Serializable {
		private String commentId;
		private String content;
		private String headPortrait;
		private String commentCount;
		private String planTime;
		private List<ImgAsk> commentImgs;
		private String memberName;
		private String commentTime;
		private String createTime;
		public String getCommentId() {
			return commentId;
		}

		public void setCommentId(String commentId) {
			this.commentId = commentId;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getHeadPortrait() {
			return headPortrait;
		}

		public void setHeadPortrait(String headPortrait) {
			this.headPortrait = headPortrait;
		}

		public String getCommentCount() {
			return commentCount;
		}

		public void setCommentCount(String commentCount) {
			this.commentCount = commentCount;
		}

		public String getPlanTime() {
			return planTime;
		}

		public void setPlanTime(String planTime) {
			this.planTime = planTime;
		}

		public List<ImgAsk> getCommentImgs() {
			return commentImgs;
		}

		public void setCommentImgs(List<ImgAsk> commentImgs) {
			this.commentImgs = commentImgs;
		}

		public String getMemberName() {
			return memberName;
		}

		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}

		public String getCommentTime() {
			return commentTime;
		}

		public void setCommentTime(String commentTime) {
			this.commentTime = commentTime;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		@Override
		public String toString() {
			return "Building [commentId=" + commentId + ", content=" + content + ", headPortrait=" + headPortrait
					+ ", commentCount=" + commentCount + ", planTime=" + planTime + ", commentImgs=" + commentImgs
					+ ", memberName=" + memberName + ", commentTime=" + commentTime + ", createTime=" + createTime
					+ "]";
		}

	}
}
