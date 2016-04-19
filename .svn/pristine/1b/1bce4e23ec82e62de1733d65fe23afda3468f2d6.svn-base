package com.king.photo.activity;

import java.util.ArrayList;
import java.util.List;

import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.king.photo.util.Bimp;
import com.king.photo.util.PublicWay;
import com.king.photo.util.Res;
import com.king.photo.zoom.PhotoView;
import com.king.photo.zoom.ViewPagerFixed;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.view.ExtendedViewPager;

/**
 * 这个是用于进行图片浏览时的界面
 *
 * @author Alan
 */
public class GalleryActivity extends BaseActivity {
	private Intent intent;
    // 返回按钮
	@ViewInject(R.id.gallery_back)
    private TextView back_bt;
	// 发送按钮
	@ViewInject(R.id.send_button)
	private Button send_bt;
	//删除按钮
	@ViewInject(R.id.gallery_del)
	private TextView del_bt;
	//顶部显示预览图片位置的textview
	private TextView positionTextView;
	//当前的位置
	private int location = 0;
	
	private ArrayList<View> listViews = null;
	private ViewPagerFixed pager;
	private MyPageAdapter adapter;

	public List<Bitmap> bmp = new ArrayList<Bitmap>();
	public List<String> drr = new ArrayList<String>();
	public List<String> del = new ArrayList<String>();
	

	RelativeLayout photo_relativeLayout;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.photo_plugin_camera_gallery);// 切屏到主界面
	}
	
	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {
			location = arg0;
			((PhotoView)listViews.get(arg0)).setImageBitmap(Bimp.tempSelectBitmap.get(arg0).getBitmap());
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageScrollStateChanged(int arg0) {

		}
	};
	
	private void initListViews(Bitmap bm) {
		if (listViews == null)
			listViews = new ArrayList<View>();
		PhotoView img = new PhotoView(this);
		img.setBackgroundColor(0xff000000);
		img.setImageBitmap(bm);
		img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		listViews.add(img);
	}
	
	// 返回按钮添加的监听器
	private class BackListener implements OnClickListener {

		public void onClick(View v) {
			finish();
		}
	}
	
	// 删除按钮添加的监听器
	private class DelListener implements OnClickListener {

		public void onClick(View v) {
			if (listViews.size() == 1) {
				Bimp.tempSelectBitmap.clear();
				Bimp.max = 0;
				send_bt.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
				Intent intent = new Intent("data.broadcast.action");  
                sendBroadcast(intent);  
				finish();
			} else {
				Bimp.tempSelectBitmap.remove(location);
				Bimp.max--;
				listViews.remove(location);
				pager.setAdapter(new MyPageAdapter(listViews));
				if(location >=Bimp.tempSelectBitmap.size()-1){
					pager.setCurrentItem(Bimp.tempSelectBitmap.size()-1);
				}else{
					pager.setCurrentItem(location);
				}
				
				send_bt.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
			}
		}
	}

	// 完成按钮的监听
	private class GallerySendListener implements OnClickListener {
		public void onClick(View v) {
			finish();
		}

	}

	public void isShowOkBt() {
		if (Bimp.tempSelectBitmap.size() > 0) {
			send_bt.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
			send_bt.setEnabled(true);
		} else {
			send_bt.setEnabled(false);
		}
	}

	
	
	class MyPageAdapter extends PagerAdapter {

		private ArrayList<View> listViews;

		public MyPageAdapter(ArrayList<View> listViews) {
			this.listViews = listViews;
		}

		public int getCount() {
			return listViews == null ? 0 : listViews.size();
		}

		public void destroyItem(View arg0, int arg1, Object arg2) {
			try {
				((ViewPagerFixed) arg0).removeView(listViews.get(arg1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public Object instantiateItem(View arg0, int arg1) {
			try {
				((ViewPagerFixed) arg0).addView(listViews.get(arg1),
						new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			} catch (Exception e) {
			}
			return listViews.get(arg1);
		}

		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}

	@Override
	public void onChildViewLoaded() {
		isShowTitle(false);
		back_bt.setOnClickListener(new BackListener());
		send_bt.setOnClickListener(new GallerySendListener());
		del_bt.setOnClickListener(new DelListener());
		intent = getIntent();
		Bundle bundle = intent.getExtras();
		isShowOkBt();
		// 为发送按钮设置文字
		pager = (ViewPagerFixed) findViewById(Res.getWidgetID("gallery01"));
		pager.setOnPageChangeListener(pageChangeListener);
		for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
			initListViews( Bimp.tempSelectBitmap.get(i).getBitmap() );
		}
		
		adapter = new MyPageAdapter(listViews);
		pager.setAdapter(adapter);
		pager.setPageMargin((int)getResources().getDimensionPixelOffset(R.dimen.margin_normal));
		int id = intent.getIntExtra("ID", 0);
		pager.setCurrentItem(id);		
	}

	@Override
	public void onReloadData() {
		
	}

	@Override
	public String getRequestTag() {
		return null;
	}
}
