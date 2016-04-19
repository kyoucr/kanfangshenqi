package com.xinwei.kanfangshenqi.model;

import java.util.ArrayList;

public class PlansModel {
	private String appointmentTime;
	private ArrayList<PlansInfo> data;
	public String getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public ArrayList<PlansInfo> getData() {
		return data;
	}
	public void setData(ArrayList<PlansInfo> data) {
		this.data = data;
	}
	
}
