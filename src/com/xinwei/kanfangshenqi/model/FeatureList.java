package com.xinwei.kanfangshenqi.model;

import java.io.Serializable;

public class FeatureList implements Serializable {
	private String featureId;
	private String featureName;
	public String getFeatureId() {
		return featureId;
	}
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}
	@Override
	public String toString() {
		return "FeatureList [featureId=" + featureId + ", featureName=" + featureName + "]";
	}
	
	
}
