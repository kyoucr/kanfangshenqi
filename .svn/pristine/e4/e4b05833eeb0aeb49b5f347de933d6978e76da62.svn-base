package com.xinwei.kanfangshenqi.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

import com.xinwei.kanfangshenqi.app.XWApplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;


public class FileUtils {
	private static FileUtils fileCache;
	private File cacheFile;
	private Context context;
	private File downloadFile;
	private File crashFile;
	private File photoTempFile;

	private FileUtils(Context context) {
		this.context = context;
		// 如果有SD卡则在SD卡中建一个目录存放缓存的图片
		// 没有SD卡就放在系统的缓存目录中
		try {
			if (hasSDCard()) {
				cacheFile = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/kanfangshenqi/Cache/");
				downloadFile = new File(Environment
						.getExternalStorageDirectory().getAbsolutePath()
						+ "/kanfangshenqi/Download/");
				crashFile = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/kanfangshenqi/Crash/");
				photoTempFile = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/kanfangshenqi/PhotoTemp/");
				if (!downloadFile.exists()) {
					downloadFile.mkdirs();
				}
			} else {
				cacheFile = context.getCacheDir();
				downloadFile = context.getCacheDir();
				crashFile = context.getCacheDir();
				photoTempFile = context.getCacheDir();
			}
			if (!cacheFile.exists()) {
				cacheFile.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FileUtils getInstance() {
		if (fileCache == null) {
			fileCache = new FileUtils(XWApplication.getInstance());
		}
		return fileCache;
	}

	// 获得缓存目录
	public File getCacheFile() {
		return cacheFile;
	}

	// 获得缓存路径
	public String getCachePathName() {
		return cacheFile.getAbsolutePath();
	}

	public String getCrashPathName(){
		return crashFile.getAbsolutePath();
	}
	// 获得下载路径
	public String getDownloadPathName() {
		if (downloadFile != null)
			return downloadFile.getAbsolutePath();
		return null;
	}
	// 获得下载路径
	public String getPhotoTempPathName() {
		if (photoTempFile != null)
			return photoTempFile.getAbsolutePath();
		return null;
	}

	// 是否有SD卡
	public boolean hasSDCard() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}

	// 按照文件夹获取该文件夹大小
	public String getCacheFileSizeByFolder(String folder) {
		try {
			return formatFileSize(getFileSize(new File(folder)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}

	// 获得缓存文件夹大小
	public String getCacheFileSize() throws Exception {
		return formatFileSize(getFileSize(cacheFile));
	}

	/**
	 * 获得文件夹下的文件
	 * 
	 * 
	 * @param f
	 * @return
	 */
	public File[] getFilesByFolder(File f) {
		File flist[] = f.listFiles();
		return flist;
	}

	// 递归
	public long getFileSize(File f) throws Exception// 取得文件夹大小
	{
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	public String formatFileSize(long fileS) {// 转换文件大小
		if (fileS == 0)
			return "";
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 ********************
	 * 清空制定缓存文件夹
	 * @param folder
	 ********************
	 */
	public void clearCacheFile(String folder) {
		try {
			File[] files = new File(cacheFile.getAbsolutePath() + "/" + folder)
					.listFiles();
			if (files == null)
				return;
			for (File f : files)
				f.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 ********************
	 *清空所有缓存文件夹
	 ********************
	 */
	public void clearCache() {
		File[] files = cacheFile.listFiles();
		if (files == null)
			return;
		for (File f : files)
			f.delete();
	}

	/**
	 * 把文件拷贝到某一目录下,并删除源文件
	 * 
	 * @param srcFile
	 * @param destDir
	 * @return
	 */
	public boolean moveFileToDir(String srcFile, String destDir) {
		File fileDir = new File(destDir);// 目标文件
		File fileSrc = new File(srcFile);// 源文件
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}
		String destFile = destDir + "/" + fileSrc.getName();
		try {
			InputStream streamFrom = new FileInputStream(srcFile);
			OutputStream streamTo = new FileOutputStream(destFile);
			byte buffer[] = new byte[1024];
			int len;
			while ((len = streamFrom.read(buffer)) > 0) {
				streamTo.write(buffer, 0, len);
			}
			streamFrom.close();
			streamTo.close();
			fileSrc.delete();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	/**
	 * 保存并且获取图片路径
	 * @param bm
	 * @param picName
	 * @return
	 */
	public String saveGetUrl(Bitmap bm,String picName){
		String path=null;
		try {
			File f = new File(photoTempFile, picName + ".JPEG"); 
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
			path=f.getPath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			path=null;
		} catch (IOException e) {
			e.printStackTrace();
			path=null;
		}
		return path;
	}
}
