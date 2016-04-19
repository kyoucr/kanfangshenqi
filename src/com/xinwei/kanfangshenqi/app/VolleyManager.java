package com.xinwei.kanfangshenqi.app;

import android.app.ActivityManager;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.xinwei.kanfangshenqi.network.KfsqHttpRequest;
import com.xinwei.kanfangshenqi.util.Logger;
import com.xinwei.kanfangshenqi.util.StringUtils;
import com.xinwei.kanfangshenqi.util.WebUtils;


public class VolleyManager {
    private static RequestQueue mRequestQueue;
    private static ImageLoader mImageLoader;
    /**
     * 初始化队列及图片加载器
     * @param context
     */
    public static void init(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);

        int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
                .getMemoryClass();
        // Use 1/8th of the available memory for this memory cache.
        int cacheSize = 1024 * 1024 * memClass / 8;
        mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache(cacheSize));
    }
    /**
     * 添加request to queue
     * @param request
     */
    public static <T> void addToQueue(KfsqHttpRequest<?> request){
		Logger.d("VolleyManager", "addToQueue-->" + request.requestUrl() +"?"+ WebUtils.buildQuery(request.GetParameters()));
    	mRequestQueue.add(request);
    }
    /**
     * 添加request并设置tag
     * @param request
     * @param tag
     */
    public static <T> void addToQueue(KfsqHttpRequest<?> request, String tag){
		Logger.d("VolleyManager", "addToQueue-->" + request.requestUrl() +"?"+ WebUtils.buildQuery(request.GetParameters()));
		request.setTag(StringUtils.isEmpty(tag) ? KfsqHttpRequest.class.getSimpleName() : tag);
		mRequestQueue.add(request);
    }
    
    public static RequestQueue getRequestQueue() {
        if (mRequestQueue != null) {
            return mRequestQueue;
        } else {
//            throw new IllegalStateException("RequestQueue not initialized");
        	init(XWApplication.getInstance().getApplicationContext());
        	return mRequestQueue;
        }
    }
    /**
     * 取消指定tag 的请求
     * @param tag
     */
    public static void cancel(Object tag){
    	mRequestQueue.cancelAll(tag);
    }
    
    public static ImageLoader getImageLoader() {
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            throw new IllegalStateException("ImageLoader not initialized");
        }
    }
    
}
