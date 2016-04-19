package com.xinwei.kanfangshenqi.app;

import java.io.File;

import org.xutils.x;

import com.android.volley.VolleyLog;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.xinwei.kanfangshenqi.activity.WelcomeActivity;
import com.xinwei.kanfangshenqi.common.CrashHandler;
import com.xinwei.kanfangshenqi.util.FileUtils;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import cn.jpush.android.api.JPushInterface;

public class XWApplication extends Application {
	private Class<? extends Activity> classT;
	private static XWApplication instance;
	private CrashHandler crashHandler;
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		init();
	}
	private void init() {
		crashHandler = CrashHandler.getInstance();
		crashHandler.init(this);
		CrashHandler.setLogPath(FileUtils.getInstance().getCrashPathName());
		crashHandler.setRestartClass(WelcomeActivity.class);
		VolleyManager.init(this);
		VolleyLog.DEBUG = false;
		initImageLoader();
		JPushInterface.setDebugMode(false);
	    JPushInterface.init(this);
		x.Ext.init(this);
	}
	private void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs()
				.discCache(
						new UnlimitedDiscCache(
										new File(FileUtils.getInstance().getCachePathName()))).build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	public static XWApplication getInstance() {
		return instance;
	}
	/**
	 *******************
	 * 设置当前需要登录才能访问的class
	 * @param classT
	 *******************
	 */
	public void setNeedLoginClass(Class<? extends Activity> classT){
		this.classT = classT;
	}
	/**
	 *******************
	 * 获取当前需要登录才能访问的class
	 * @return
	 *******************
	 */
	public Class<? extends Activity> getNeedLoginClass(){
		return this.classT;
	}
}
