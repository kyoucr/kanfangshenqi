package com.xinwei.kanfangshenqi.model;

public class EncyclopediaTitle {
	/*"firstClassList":[{"classId":"1","className":"看房"},
	                  {"classId":"2","className":"买房"},
	                  {"classId":"3","className":"购房"},
	                  {"classId":"4","className":"业主"}],*/
	private String classId;
	private String className;
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "EncyclopediaTitle [classId=" + classId + ", className=" + className + "]";
	}
	
}
