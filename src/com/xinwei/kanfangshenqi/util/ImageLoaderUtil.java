package com.xinwei.kanfangshenqi.util;

import org.xutils.ImageManager;
import org.xutils.x;
import org.xutils.common.Callback;
import org.xutils.common.util.FileUtil;
import org.xutils.image.ImageOptions;

import com.king.photo.zoom.PhotoView;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.app.XWApplication;
import com.xinwei.kanfangshenqi.view.TouchImageView;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 ********************
 * 加载网络图片
 * @author cn
 ********************
 */
public class ImageLoaderUtil {
	private static ImageManager imageManager;
//	private static ImageOptions imageManagerFitXY;
//	private static ImageOptions imageOptionsHead;
//	private static ImageOptions imageOptionsCenterCrop;
//	private static ImageOptions imageOptionsFitCenter;
	private static ImageLoaderUtil imageLoaderUtil;
	private ImageLoaderUtil(){
		imageManager = x.image();
		FileUtil.setCacheDir(FileUtils.getInstance().getCachePathName());
//		imageManagerFitXY = new ImageOptions.Builder()
//		.setUseMemCache(true).setAutoRotate(true)
//                .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
//        .setImageScaleType(ImageView.ScaleType.FIT_XY)
//        .setLoadingDrawableId(R.drawable.ic_default_loading)
//        .setFailureDrawableId(R.drawable.ic_default_loading)
//        .setIgnoreGif(false)
//        .build();
//
//		imageOptionsCenterCrop = new ImageOptions.Builder()
//		.setUseMemCache(true).setAutoRotate(true)
//                .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
//        .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
//        .setLoadingDrawableId(R.drawable.ic_default_loading)
//        .setFailureDrawableId(R.drawable.ic_default_loading)
//        .setIgnoreGif(false)
//        .build();
//		imageOptionsFitCenter = new ImageOptions.Builder()
//				.setUseMemCache(true).setAutoRotate(true)
//		                .setPlaceholderScaleType(ImageView.ScaleType.CENTER)
//		        .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
//		        .setLoadingDrawableId(R.drawable.ic_default_loading_normal)
//		        .setFailureDrawableId(R.drawable.ic_default_loading_normal)
//		        .setIgnoreGif(false)
//		        .build();
//		imageOptionsHead = new ImageOptions.Builder()
//				.setUseMemCache(true).setAutoRotate(true)
//				.setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
//        .setImageScaleType(ImageView.ScaleType.FIT_XY)
//				.setLoadingDrawableId(R.drawable.not_logged_in_headpic)
//				.setFailureDrawableId(R.drawable.not_logged_in_headpic)
//				.setIgnoreGif(false)
//				.build();
	}
	public static ImageLoaderUtil getInstance(){
		if(imageLoaderUtil == null)
			imageLoaderUtil = new ImageLoaderUtil();
		return imageLoaderUtil;
	}
	/**
	 *******************
	 * 平铺图片到控件尺寸
	 * @param imgView
	 * @param url
	 *******************
	 */
	public void bindImgFixXY(ImageView imgView,String url){
		if(imgView == null)
			return;
//		if(imgView.getTag()==null||!imgView.getTag().equals(url))
			imageManager.bind(imgView, url,new ImageOptions.Builder()
					.setUseMemCache(true).setAutoRotate(true)
	                .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
	        .setImageScaleType(ImageView.ScaleType.FIT_XY)
	        .setLoadingDrawableId(R.drawable.ic_default_loading)
	        .setFailureDrawableId(R.drawable.ic_default_loading)
	        .setIgnoreGif(false)
	        .build());
	}
	/**
	 *******************
	 * 不带加载成功标签，并且开启新的ImageOptions进行加载
	 * @param imgView
	 * @param url
	 *******************
	 */
	public void bindImgFitXYWithoutTagAndImageOptionsCache(ImageView imgView,String url){
		if(imgView == null)
			return;
			imageManager.bind(imgView, url,new ImageOptions.Builder()
					.setUseMemCache(true).setAutoRotate(true)
	                .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
	        .setImageScaleType(ImageView.ScaleType.FIT_XY)
	        .setLoadingDrawableId(R.drawable.ic_default_loading)
	        .setFailureDrawableId(R.drawable.ic_default_loading)
	        .setIgnoreGif(false)
	        .setSize(imgView.getWidth(), imgView.getHeight())
	        .build());
	}
	/**
	 *******************
	 * 带有加载监听回调，和是否开启加载图片动画
	 * @param imgView
	 * @param url
	 * @param onLoadProgressListener
	 * @param hasAnim
	 *******************
	 */
	public void bindImgFitCenter(ImageView imgView,String url,OnLoadProgressListener onLoadProgressListener,boolean hasAnim){
		if(imgView == null)
			return;
		if(imgView.getTag()==null||!imgView.getTag().equals(url))
			imageManager.bind(imgView, url,new ImageOptions.Builder()
					.setUseMemCache(true).setAutoRotate(true)
	                .setPlaceholderScaleType(ImageView.ScaleType.CENTER)
	        .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
	        .setLoadingDrawableId(R.drawable.ic_default_loading_normal)
	        .setFailureDrawableId(R.drawable.ic_default_loading_normal)
	        .setIgnoreGif(false)
	        .build(),new CustomBitmapLoadCallBack(imgView, url,onLoadProgressListener,hasAnim));
	}
	/**
	 *******************
	 * 居中显示全部图片
	 * @param imgView
	 * @param url
	 *******************
	 */
	public void bindImgFitCenter(ImageView imgView,String url){
		if(imgView == null)
			return;
		if(imgView.getTag()==null||!imgView.getTag().equals(url))
			imageManager.bind(imgView, url,new ImageOptions.Builder()
					.setUseMemCache(true).setAutoRotate(true)
	                .setPlaceholderScaleType(ImageView.ScaleType.CENTER)
	        .setImageScaleType(ImageView.ScaleType.FIT_CENTER)
	        .setLoadingDrawableId(R.drawable.ic_default_loading_normal)
	        .setFailureDrawableId(R.drawable.ic_default_loading_normal)
	        .setIgnoreGif(false)
	        .build());
	}
	/**
	 *******************
	 * 图片超出控件尺寸，只显示图片中间部分
	 * @param imgView
	 * @param url
	 *******************
	 */
	public void bindImgCenterCrop(ImageView imgView,String url){
		if(imgView == null)
			return;
//		if(imgView.getTag()==null||!imgView.getTag().equals(url))
			imageManager.bind(imgView, url,new ImageOptions.Builder()
					.setUseMemCache(true).setAutoRotate(true)
	                .setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
	        .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
	        .setLoadingDrawableId(R.drawable.ic_default_loading)
	        .setFailureDrawableId(R.drawable.ic_default_loading)
	        .setIgnoreGif(false)
	        .build());
	}
	public void bindHeadImg(ImageView imgView,String url){
		if(imgView == null)
			return;
//		if(imgView.getTag()==null||!imgView.getTag().equals(url))
			imageManager.bind(imgView, url,new ImageOptions.Builder()
					.setUseMemCache(true).setAutoRotate(true)
					.setPlaceholderScaleType(ImageView.ScaleType.FIT_XY)
//	        .setImageScaleType(ImageView.ScaleType.CENTER_INSIDE)
					.setLoadingDrawableId(R.drawable.not_logged_in_headpic)
					.setFailureDrawableId(R.drawable.not_logged_in_headpic)
					.setIgnoreGif(false)
					.build());
	}
	public interface OnLoadProgressListener{
		public void onStarted();
		public void onLoading(long total, long current, boolean isDownloading);
		public void onFinished();
	}
	//需要对图片加载过程进行监控时，注册该监听回调
	public class CustomBitmapLoadCallBack implements
			Callback.ProgressCallback<Drawable> {
		private ImageView imgView;
		private String url;
		private boolean hasAnim;
		private OnLoadProgressListener onLoadProgressListener;
		public CustomBitmapLoadCallBack(ImageView imgView,String url) {
			this.imgView = imgView;
			this.url = url;
		}
		public CustomBitmapLoadCallBack(ImageView imgView, String url, OnLoadProgressListener onLoadProgressListener,boolean hasAnim) {
			this.imgView = imgView;
			this.url = url;
			this.hasAnim = hasAnim;
			this.onLoadProgressListener = onLoadProgressListener;
		}
		@Override
		public void onWaiting() {
		}
		@Override
		public void onStarted() {
			if(onLoadProgressListener!=null)
				onLoadProgressListener.onStarted();
		}
		@Override
		public void onLoading(long total, long current, boolean isDownloading) {
			if(onLoadProgressListener!=null)
				onLoadProgressListener.onLoading(total, current, isDownloading);
		}
		@Override
		public void onSuccess(Drawable result) {
			if(imgView!=null&&url!=null)
				imgView.setTag(url);
			if(hasAnim)
				animsDisplay(imgView);
		}
		@Override
		public void onError(Throwable ex, boolean isOnCallback) {
		}
		@Override
		public void onCancelled(CancelledException cex) {
		}
		@Override
		public void onFinished() {
			if(onLoadProgressListener!=null)
				onLoadProgressListener.onFinished();
		}
	}
	private void animsDisplay(ImageView imageView){
		Animation anim = AnimationUtils.loadAnimation(XWApplication.getInstance(), R.anim.img_show_anim_detail);
		anim.setFillAfter(true);
		imageView.startAnimation(anim);
	}

}
