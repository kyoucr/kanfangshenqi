package com.xinwei.kanfangshenqi.model;

import java.util.List;

import com.xinwei.kanfangshenqi.util.ValidatorUtil;

public class AchievementList {
	private int dataCount;
	private List<Achievement> dataList;

	public int getDataCount() {
		return dataCount;
	}

	public void setDataCount(int dataCount) {
		this.dataCount = dataCount;
	}

	public List<Achievement> getDataList() {
		return dataList;
	}

	public void setDataList(List<Achievement> dataList) {
		this.dataList = dataList;
	}

	@Override
	public String toString() {
		return "AchievementList [dataCount=" + dataCount + ", dataList="
				+ dataList + "]";
	}

	// onSuccess:{"status":"1","msg":"查询成功","dataList":[
	// {"actionTime":"2015-10-30","action":"入伙","buildingName":"","banner":""},
	// {"actionTime":"2015-12-08","action":"看房","buildingName":"金地檀溪","banner":"http:\/\/192.168.1.89:9090\/ftp\/images\/building\/loupan.jpg"}],
	// "dataCount":2}
	public class Achievement {
		private String actionTime;
		private String action;
		private String buildingName;
		private String banner;
		private String smallBanner;

		public String getActionTime() {
			return actionTime;
		}

		public void setActionTime(String actionTime) {
			this.actionTime = actionTime;
		}
		
		public String getSmallBanner() {
			return smallBanner;
		}

		public void setSmallBanner(String smallBanner) {
			this.smallBanner = smallBanner;
		}

		/**
		 ********************
		 * 获取日期
		 * @return
		 ********************
		 */
		public String getDate(){
			try {
				String[] strs = actionTime.split("-");
				return strs[2];
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		/**
		 ********************
		 * 获取月份
		 * @return
		 ********************
		 */
		public String getMonth(){
			try {
				String[] strs = actionTime.split("-");
				return strs[1]+"月";
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
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

		@Override
		public String toString() {
			return "Achievement [actionTime=" + actionTime + ", action=" + action + ", buildingName=" + buildingName
					+ ", banner=" + banner + ", smallBanner=" + smallBanner + "]";
		}
		
	}
}
