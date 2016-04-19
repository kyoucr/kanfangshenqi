package com.xinwei.kanfangshenqi.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.king.photo.util.HttpXUtils;
import com.xinwei.kanfangshenqi.app.XWActivityManager;
import com.xinwei.kanfangshenqi.app.XWApplication;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.Utils;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.
 * 
 * @author cn
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {
	protected static String mPath = Environment.getExternalStorageDirectory()
			.getPath();

	// 系统默认的UncaughtException处理类
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	// CrashHandler实例
	private static CrashHandler mInstance = null;
	// 程序的Context对象
	protected Context mContext;
	// 用来存储设备信息和异常信息
	private Map<String, String> mInfos = new HashMap<String, String>();
	// 用于格式化日期,作为日志文件名的一部分
	@SuppressLint("SimpleDateFormat")
	private DateFormat mDateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");

	/** 保证只有一个CrashHandler实例 */
	private CrashHandler() {
	}

	/** 获取CrashHandler实例 ,单例模式 */
	public static CrashHandler getInstance() {
		if (mInstance == null) {
			mInstance = new CrashHandler();
		}
		return mInstance;
	}

	public static void setLogPath(String path) {
		mPath = path;
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 */
	public void init(Context context) {
		mContext = context;
		// 获取系统默认的UncaughtException处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置该CrashHandler为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		if (!handleException(ex) && mDefaultHandler != null) {
			// 如果用户没有处理则让系统默认的异常处理器来处理
			mDefaultHandler.uncaughtException(thread, ex);
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			if (onCrash()) {
				Intent intent = new Intent(mContext, restartClass);
				PendingIntent restartIntent = PendingIntent.getActivity(
						mContext, 0, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
				// 设置重新启动机制
				AlarmManager mgr = (AlarmManager) mContext
						.getSystemService(Context.ALARM_SERVICE);
				mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
						restartIntent); // 1秒钟后重启应用
				// 退出程序
				XWActivityManager.getInstance().finishAllActivity();
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(0);
			}
		}
	}

	private Class<?> restartClass;

	/**
	 ************************* 
	 * 程序崩溃时执行 如重写该方法，则需将返回值设为false（不会执行默认方法） 如果不重写该类，则执行默认的方法，
	 * 此时需要调用setRestartClass方法， 来设置自动重启后开启的activity类
	 ************************* 
	 */
	public boolean onCrash() {
		return true;
	}

	/**
	 ************************* 
	 * 设置程序崩溃后，重启的activity类
	 * 
	 * @param restartClass
	 ************************* 
	 */
	public void setRestartClass(Class<?> restartClass) {
		this.restartClass = restartClass;
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 * 
	 * @param ex
	 * @return true:如果处理了该异常信息;否则返回false.
	 */
	protected boolean handleException(Throwable ex) {
		if (ex == null) {
			return false;
		}
		// 收集设备参数信息
		collectDeviceInfo(mContext);
		// 保存日志文件
		saveCrashInfo2File(ex);

		return true;
	}

	/**
	 * 收集设备参数信息
	 * 
	 * @param ctx
	 */
	public void collectDeviceInfo(Context ctx) {
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				String versionName = pi.versionName == null ? "null"
						: pi.versionName;
				String versionCode = pi.versionCode + "";
				mInfos.put("versionName", versionName);
				mInfos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {
		}
		Field[] fields = Build.class.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				mInfos.put(field.getName(), field.get(null).toString());
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 保存错误信息到文件中
	 * 
	 * @param ex
	 * @return 返回文件名称,便于将文件传送到服务器
	 */
	protected String saveCrashInfo2File(Throwable ex) {
		if (mPath == null)
			return null;
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, String> entry : mInfos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append(key + "=" + value + "\n");
		}

		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		Throwable cause = ex.getCause();
		while (cause != null) {
			cause.printStackTrace(printWriter);
			cause = cause.getCause();
		}
		printWriter.close();
		String result = writer.toString();
		sb.append(result);
		try {
			long timestamp = System.currentTimeMillis();
			String time = mDateFormatter.format(new Date());
			String fileName = "crash-" + time + "-" + timestamp + ".log";
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				File dir = new File(mPath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				FileOutputStream fos = new FileOutputStream(mPath + "/"
						+ fileName);
				fos.write(sb.toString().getBytes());
				fos.close();
			}
//			onSaveCrashInfoDone(mPath + "/" + fileName);
			return fileName;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 ************************* 
	 * 崩溃log信息文件保存到本地后, 需要执行的代码，例如服务器上传
	 ************************* 
	 */
	public void onSaveCrashInfoDone(String filePath) {
		if (Utils.isWifiConnected()) {
			File file = new File(filePath);
			if (file.exists()) {
				final List<File> listFile = new ArrayList<File>();
				listFile.add(file);
				HttpXUtils.upload(null, Const.URL_UPLOAD_ERROR_LOG, listFile,
						null, null, new RequestListener() {

							@Override
							public void onSuccess(String url,
									String responseResult) {
								if (listFile != null) {
									try {
										for (File file : listFile) {
											file.delete();
										}
										listFile.clear();
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}

							@Override
							public void onFailure(String url, String errorInfo) {
							}

							@Override
							public void onError(String url,
									String responseResult) {
							}
						}, false);
			}
		}
	}
}