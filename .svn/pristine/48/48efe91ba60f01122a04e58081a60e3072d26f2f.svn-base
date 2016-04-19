package com.king.photo.activity;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.king.photo.adapter.AlbumGridViewAdapter;
import com.king.photo.util.Bimp;
import com.king.photo.util.ImageItem;
import com.king.photo.util.PublicWay;
import com.king.photo.util.Res;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.util.ToastUtil;
/**
 * 这个是显示一个文件夹里面的所有图片时的界面
 *
 * @author Alan
 */
public class ShowAllPhoto extends BaseActivity {
	private GridView gridView;
	private ProgressBar progressBar;
	private AlbumGridViewAdapter gridImageAdapter;
	// 完成按钮
	private TextView okButton;
	// 预览按钮
	private TextView preview;
	// 返回按钮
	private TextView back;
	// 取消按钮
	private TextView cancel;
	// 标题
	private TextView headTitle;
	private Intent intent;
	private Context mContext;
	public static ArrayList<ImageItem> dataList = new ArrayList<ImageItem>();
	IntentFilter filter =null;  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.photo_plugin_camera_show_all_photo);
		mContext = this;
	}
	
	BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {  
		  
        @Override  
        public void onReceive(Context context, Intent intent) {  
            // TODO Auto-generated method stub  
        	gridImageAdapter.notifyDataSetChanged();
        }  
    };  

	private class PreviewListener implements OnClickListener {
		public void onClick(View v) {
			if (Bimp.tempSelectBitmap.size() > 0) {
				intent.putExtra("position", "2");
				intent.setClass(ShowAllPhoto.this, GalleryActivity.class);
				startActivity(intent);
			}
		}

	}

	private class BackListener implements OnClickListener {// 返回按钮监听
		Intent intent;

		public BackListener(Intent intent) {
			this.intent = intent;
		}

		public void onClick(View v) {
//			intent.setClass(ShowAllPhoto.this, ImageFile.class);
//			startActivity(intent);
			finish();
		}

	}

	private class CancelListener implements OnClickListener {// 取消按钮的监听
		public void onClick(View v) {
			//清空选择的图片
//			Bimp.tempSelectBitmap.clear();
//			intent.setClass(mContext, MainActivity.class);
//			startActivity(intent);
			finish();
		}
	}
	
	@Override
    protected void onResume() {
            super.onResume();
//            if (broadcastReceiver == null) {
            	    filter = new IntentFilter("data.broadcast.action"); 
                    filter.addCategory(Intent.CATEGORY_DEFAULT);
                    registerReceiver(broadcastReceiver, filter);
//            }

    }      

    @Override
    protected void onPause() {
            super.onPause();
            if (broadcastReceiver != null) {
                    unregisterReceiver(broadcastReceiver);
                    broadcastReceiver = null;
            }



    }
	
	

	private void init() {
		
		progressBar = (ProgressBar) findViewById(Res.getWidgetID("showallphoto_progressbar"));
		progressBar.setVisibility(View.GONE);
		gridView = (GridView) findViewById(Res.getWidgetID("showallphoto_myGrid"));
		gridImageAdapter = new AlbumGridViewAdapter(this,dataList,
				Bimp.tempSelectBitmap);
		gridView.setAdapter(gridImageAdapter);
		okButton = (TextView) findViewById(Res.getWidgetID("showallphoto_ok_button"));
	}

	private void initListener() {

		gridImageAdapter
				.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {
					public void onItemClick(final ToggleButton toggleButton,
							int position, boolean isChecked,
							Button button) {
						if (Bimp.tempSelectBitmap.size() >= PublicWay.num&&isChecked) {
							button.setVisibility(View.GONE);
							toggleButton.setChecked(false);
							ToastUtil.show(mContext, mContext.getString(R.string.only_choose_num));
							return;
						}

						if (isChecked) {
							button.setVisibility(View.VISIBLE);
							Bimp.tempSelectBitmap.add(dataList.get(position));
							okButton.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size()
									+ "/"+PublicWay.num+")");
						} else {
							button.setVisibility(View.GONE);
							Bimp.tempSelectBitmap.remove(dataList.get(position));
							okButton.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
						}
						isShowOkBt();
					}
				});

		okButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				okButton.setClickable(false);
//				if (PublicWay.photoService != null) {
//					PublicWay.selectedDataList.addAll(Bimp.tempSelectBitmap);
//					Bimp.tempSelectBitmap.clear();
//					PublicWay.photoService.onActivityResult(0, -2,
//							intent);
//				}
//				intent.setClass(mContext, PublishActivity.class);
//				startActivity(intent);
				// Intent intent = new Intent();
				// Bundle bundle = new Bundle();
				// bundle.putStringArrayList("selectedDataList",
				// selectedDataList);
				// intent.putExtras(bundle);
				// intent.setClass(ShowAllPhoto.this, UploadPhoto.class);
				// startActivity(intent);
				finish();

			}
		});

	}

	public void isShowOkBt() {
		if (Bimp.tempSelectBitmap.size() > 0) {
			okButton.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
			preview.setEnabled(true);
			okButton.setEnabled(true);
		} else {
			okButton.setText(Res.getString("finish")+"(" + Bimp.tempSelectBitmap.size() + "/"+PublicWay.num+")");
			preview.setEnabled(false);
			okButton.setEnabled(false);
		}
	}


	@Override
	protected void onRestart() {
		isShowOkBt();
		super.onRestart();
	}

	@Override
	public void onChildViewLoaded() {
		isShowTitle(false);
		back = (TextView) findViewById(Res.getWidgetID("showallphoto_back"));
		cancel = (TextView) findViewById(Res.getWidgetID("showallphoto_cancel"));
		preview = (TextView) findViewById(Res.getWidgetID("showallphoto_preview"));
		okButton = (TextView) findViewById(Res.getWidgetID("showallphoto_ok_button"));
		headTitle = (TextView) findViewById(Res.getWidgetID("showallphoto_headtitle"));
		this.intent = getIntent();
		String folderName = intent.getStringExtra("folderName");
		if (folderName.length() > 8) {
			folderName = folderName.substring(0, 9) + "...";
		}
		headTitle.setText(folderName);
		cancel.setOnClickListener(new CancelListener());
		back.setOnClickListener(new BackListener(intent));
		preview.setOnClickListener(new PreviewListener());
		init();
		initListener();
		isShowOkBt();		
	}

	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		// TODO Auto-generated method stub
		return null;
	}

}
