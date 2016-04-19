package com.king.photo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.xinwei.kanfangshenqi.util.ArithUtil;
import com.xinwei.kanfangshenqi.util.FileUtils;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;

public class PhotoFileUtils {
	public static String SDPATH = FileUtils.getInstance().getPhotoTempPathName();

	/**
	 * 保存图片
	 * 
	 * @param bm
	 * @param picName
	 */
	public static void saveBitmap(Bitmap bm, String picName) {
		if (SDPATH == null)
			SDPATH = FileUtils.getInstance().getPhotoTempPathName();
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存并且获取图片路径
	 * 
	 * @param bm
	 * @param picName
	 * @return
	 */
	public static String saveGetUrl(Bitmap bm, String picName) {
		if (SDPATH == null)
			SDPATH = FileUtils.getInstance().getPhotoTempPathName();
		String path = null;
		try {
			if (!isFileExist("")) {
				File tempf = createSDDir("");
			}
			File f = new File(SDPATH, picName + ".JPEG");
			if (f.exists()) {
				f.delete();
			}
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.JPEG, 80, out);
			out.flush();
			out.close();
			path = f.getPath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			path = null;
		} catch (IOException e) {
			e.printStackTrace();
			path = null;
		}
		return path;
	}
	/**
	 *******************
	 * 对照相机拍照后的图片进行压缩
	 * @param bm
	 * @param picFile
	 * @return
	 *******************
	 */
	public static Bitmap savePhoto(Bitmap bm, File picFile) {
		int degree = readPictureDegree(picFile.getAbsolutePath());
		if(degree>0){
			bm = rotateBitmap(degree,bm);
		}
//		String path = null;
		try {
			int quality = 100;
			try {
				long size = picFile.length();
				int sizeUnit = 1024 * 1024;
				if(size>sizeUnit){
					quality = (int)(ArithUtil.div(sizeUnit, size)*quality);
				}
			} catch (Exception e) {
			}
			if (picFile.exists()) {
				picFile.delete();
			}
			FileOutputStream out = new FileOutputStream(picFile);
			bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
			out.flush();
			out.close();
//			path = picFile.getPath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			bm = null;
		} catch (IOException e) {
			e.printStackTrace();
			bm = null;
		}
		return bm;
	}
	/**
     * 读取照片exif信息中的旋转角度
     * 
     * @param path
     *            照片路径
     * @return角度  获取从相册中选中图片的角度
     */
    public static int readPictureDegree(String path) {
        if (!ValidatorUtil.isValidString(path)) {
            return 0;
        }
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                degree = 90;
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                degree = 180;
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                degree = 270;
                break;
            }
        } catch (Exception e) {
        }
        return degree;
    }
	/**
	 * 将图片纠正到正确方向
	 * 
	 * @param degree ： 图片被系统旋转的角度
	 * @param bitmap ： 需纠正方向的图片
	 * @return 纠向后的图片
	 */
	public static Bitmap rotateBitmap(int degree, Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);

		Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		return bm;
	}
	/**
	 *******************
	 * 从本地相册选的图片，进行压缩
	 * @param picFile
	 * @return
	 *******************
	 */
	public static File saveLocalPic(File picFile) {
		File fileTo = null;
		Bitmap bm = null;
		try {
			int quality = 100;
			try {
				bm = BitmapFactory.decodeFile(picFile.getAbsolutePath());
				if(bm == null)
					return null;
				long size = picFile.length();
				int sizeUnit = 1024 * 1024;
				if(size>sizeUnit){
					quality = (int)(ArithUtil.mul(quality,ArithUtil.div(sizeUnit, size)));
				}
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			fileTo = new File(FileUtils.getInstance().getPhotoTempPathName()+"/"+System.currentTimeMillis()+".jpg");
			if (fileTo.exists()) {
				fileTo.delete();
			}
			if(!fileTo.exists())
				fileTo.getParentFile().mkdirs();
			FileOutputStream out = new FileOutputStream(fileTo);
			bm.compress(Bitmap.CompressFormat.JPEG, quality, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fileTo = null;
		} catch (IOException e) {
			e.printStackTrace();
			fileTo = null;
		}
		return fileTo;
	}
	public static File createSDDir(String dirName) throws IOException {
		File dir = new File(SDPATH + dirName);
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			dir.mkdir();
		}
		return dir;
	}

	public static boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		file.isFile();
		return file.exists();
	}

	public static void delFile(String fileName) {
		File file = new File(SDPATH + fileName);
		if (file.isFile()) {
			file.delete();
		}
		file.exists();
	}

	public static void deleteDir() {
		File dir = new File(SDPATH);
		if (dir == null || !dir.exists() || !dir.isDirectory())
			return;

		for (File file : dir.listFiles()) {
			if (file.isFile())
				file.delete();
			else if (file.isDirectory())
				deleteDir();
		}
		dir.delete();
	}

	public static boolean fileIsExists(String path) {
		try {
			File f = new File(path);
			if (!f.exists()) {
				return false;
			}
		} catch (Exception e) {

			return false;
		}
		return true;
	}

}
