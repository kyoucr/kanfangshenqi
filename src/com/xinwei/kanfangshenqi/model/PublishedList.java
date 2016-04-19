package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;
import java.util.List;
/**
 ********************
 * 已发表
 * @author cn
 ********************
 */
public class PublishedList implements Serializable{
	private int pageRowCnt;
	private int pageCount;
	private int dataCount;
	private List<Published> dataList;
	
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

	public List<Published> getDataList() {
		return dataList;
	}

	public void setDataList(List<Published> dataList) {
		this.dataList = dataList;
	}
	
	@Override
	public String toString() {
		return "PublishedList [pageRowCnt=" + pageRowCnt + ", pageCount="
				+ pageCount + ", dataCount=" + dataCount + ", dataList="
				+ dataList + "]";
	}

	public class Published implements Serializable{
		private String commentId;
		private String buildingName;
		private String content;
		private String createTime;
		private List<Image> imgs;
		
		public String getCommentId() {
			return commentId;
		}

		public void setCommentId(String commentId) {
			this.commentId = commentId;
		}

		public String getBuildingName() {
			return buildingName;
		}

		public void setBuildingName(String buildingName) {
			this.buildingName = buildingName;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public List<Image> getImgs() {
			return imgs;
		}

		public void setImgs(List<Image> imgs) {
			this.imgs = imgs;
		}
		
		@Override
		public String toString() {
			return "Published [commentId=" + commentId + ", buildingName="
					+ buildingName + ", content=" + content + ", createTime="
					+ createTime + ", imgs=" + imgs + "]";
		}

		public class Image implements Serializable{
			private String path;
			private String smallPath;
			public String getPath() {
				return path;
			}

			public void setPath(String path) {
				this.path = path;
			}
			
			public String getSmallPath() {
				return smallPath;
			}

			public void setSmallPath(String smallPath) {
				this.smallPath = smallPath;
			}

			@Override
			public String toString() {
				return "Image [path=" + path + ", smallPath=" + smallPath + "]";
			}
			
		}
		
	}
}
