package com.xinwei.kanfangshenqi.model;

import java.util.List;
/**
 ********************
 * 通知（推送消息）
 * @author cn
 ********************
 */
public class NotifyList {
	private int dataCount;
	private int pageRowCnt;
	private int pageCount;
	private List<Notify> dataList;
	
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

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<Notify> getDataList() {
		return dataList;
	}

	public void setDataList(List<Notify> dataList) {
		this.dataList = dataList;
	}
	
	@Override
	public String toString() {
		return "NotifyList [dataCount=" + dataCount + ", pageRowCnt="
				+ pageRowCnt + ", pageCount=" + pageCount + ", dataList="
				+ dataList + "]";
	}

	public class Notify{
		private int pushId;
		private String title;
		private String content;
		private String pushTime;
		public int getPushId() {
			return pushId;
		}
		public void setPushId(int pushId) {
			this.pushId = pushId;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getPushTime() {
			return pushTime;
		}
		public void setPushTime(String pushTime) {
			this.pushTime = pushTime;
		}
		@Override
		public String toString() {
			return "Notify [pushId=" + pushId + ", title=" + title
					+ ", content=" + content + ", pushTime=" + pushTime + "]";
		}
		
	}
}
