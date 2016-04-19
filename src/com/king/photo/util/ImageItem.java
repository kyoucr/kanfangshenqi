package com.king.photo.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.graphics.Bitmap;
import android.widget.AutoCompleteTextView.Validator;


public class ImageItem implements Serializable {
	public String imageId;
	public String thumbnailPath;
	public String imagePath;
	private Bitmap bitmap;
	public boolean isSelected = false;
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getThumbnailPath() {
		return thumbnailPath;
	}
	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
//	public void saveLocal(){
//		Utils.logCN("imagePathSaveLocal:"+imagePath);
//		if(ValidatorUtil.isValidString(imagePath))
//			PhotoFileUtils.saveLocalPic(new File(imagePath));
//	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public Bitmap getBitmap() {		
		if(bitmap == null){
			try {
				bitmap = Bimp.revitionImageSize(imagePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	@Override
	public String toString() {
		return "ImageItem [imageId=" + imageId + ", thumbnailPath="
				+ thumbnailPath + ", imagePath=" + imagePath + ", bitmap="
				+ bitmap + ", isSelected=" + isSelected + "]";
	}
	
	
	
}