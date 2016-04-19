package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;

public class Discovery implements Serializable{
	private String replyHeadPortrait;
	private String forwardType;//0：跳转到问问，1：跳转到关注，2跳转到提醒，""无新消息提示
	public String getReplyHeadPortrait() {
		return replyHeadPortrait;
	}
	public void setReplyHeadPortrait(String replyHeadPortrait) {
		this.replyHeadPortrait = replyHeadPortrait;
	}
	public String getForwardType() {
		return forwardType;
	}
	public void setForwardType(String forwardType) {
		this.forwardType = forwardType;
	}
	
}
