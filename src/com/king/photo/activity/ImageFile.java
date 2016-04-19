package com.king.photo.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.king.photo.adapter.FolderAdapter;
import com.king.photo.util.Bimp;
import com.king.photo.util.PublicWay;
import com.king.photo.util.Res;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;

/**
 * 这个类主要是用来进行显示包含图片的文件夹
 *
 * @author Alan
 */
public class ImageFile extends BaseActivity {

	private FolderAdapter folderAdapter;
	private TextView bt_cancel;
	private Context mContext;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.photo_plugin_camera_image_file);
		mContext = this;
	}

	private class CancelListener implements OnClickListener {// 取消按钮的监听
		public void onClick(View v) {
			//清空选择的图片
//			Bimp.tempSelectBitmap.clear();
//			Intent intent = new Intent();
//			intent.setClass(mContext, MainActivity.class);
//			startActivity(intent);
			finish();
		}
	}

	@Override
	public void onChildViewLoaded() {
		isShowTitle(false);
		bt_cancel = (TextView) findViewById(Res.getWidgetID("cancel"));
		bt_cancel.setOnClickListener(new CancelListener());
		GridView gridView = (GridView) findViewById(Res.getWidgetID("fileGridView"));
		TextView textView = (TextView) findViewById(Res.getWidgetID("headerTitle"));
		textView.setText(Res.getString("photo"));
		folderAdapter = new FolderAdapter(this);
		gridView.setAdapter(folderAdapter);
	}

	@Override
	public void onReloadData() {
		
	}

	@Override
	public String getRequestTag() {
		return null;
	}


}
