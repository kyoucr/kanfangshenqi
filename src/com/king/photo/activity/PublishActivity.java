package com.king.photo.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.greenrobot.eventbus.EventBus;

import com.google.gson.Gson;
import com.king.photo.util.Bimp;
import com.king.photo.util.HttpXUtils;
import com.king.photo.util.ImageItem;
import com.king.photo.util.PhotoFileUtils;
import com.king.photo.util.Res;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.SeeHouseCircleActivity;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.UnPublishFragment;
import com.xinwei.kanfangshenqi.model.BuildingCommentPublish;
import com.xinwei.kanfangshenqi.model.UnPublishList.UnPublish;
import com.xinwei.kanfangshenqi.model.UploadFile;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.FileUtils;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PublishActivity extends BaseActivity {

	private GridView noScrollgridview;
	private GridAdapter adapter;
	private PopupWindow pop = null;
	private LinearLayout ll_popup;
	private TextView btnLeftChild;
	private TextView btnSend;
	private EditText edtTxtContent;
	public static Bitmap bitmap;
	private String buildingId;
	private String url;
	private UnPublish unPublish;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Res.init(this);
		buildingId = getIntent().getStringExtra(Const.PARAM_BUILDING_ID);
		unPublish = (UnPublish) getIntent().getSerializableExtra(UnPublish.class.getSimpleName());
		if (buildingId != null || unPublish != null)
			url = Const.URL_BUILDING_COMMENT;
		else
			url = Const.URL_CHATCOMMENT;
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.btn_add_pic);
		addChildView(R.layout.photo_activity_selectimg);
	}

	public void Init() {

		pop = new PopupWindow(PublishActivity.this);

		View view = getLayoutInflater().inflate(R.layout.photo_item_popupwindows, null);

		ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

		pop.setWidth(LayoutParams.MATCH_PARENT);
		pop.setHeight(LayoutParams.WRAP_CONTENT);
		pop.setBackgroundDrawable(new BitmapDrawable());
		pop.setFocusable(true);
		pop.setOutsideTouchable(true);
		pop.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		btnLeftChild = (TextView) findViewById(R.id.btnLeftChild);
		btnLeftChild.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btnSend = (TextView) findViewById(R.id.btnSend);
		btnSend.setOnClickListener(publishClick);
		edtTxtContent = (EditText) findViewById(R.id.edtTxtContent);
		Button bt1 = (Button) view.findViewById(R.id.item_popupwindows_camera);
		Button bt2 = (Button) view.findViewById(R.id.item_popupwindows_Photo);
		Button bt3 = (Button) view.findViewById(R.id.item_popupwindows_cancel);
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				photo();
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		bt2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(PublishActivity.this, ShowAlbumActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});
		bt3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				pop.dismiss();
				ll_popup.clearAnimation();
			}
		});

		noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
		noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
		adapter = new GridAdapter(this);
		// adapter.update();
		noScrollgridview.setAdapter(adapter);
		// noScrollgridview.setOnItemClickListener(new OnItemClickListener() {
		//
		// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		// long arg3) {
		// if (arg2 == Bimp.tempSelectBitmap.size()) {
		// Utils.hideInput(edtTxtContent);
		// ll_popup.startAnimation(AnimationUtils.loadAnimation(
		// PublishActivity.this, R.anim.activity_translate_in));
		// pop.showAtLocation(childView, Gravity.BOTTOM, 0, 0);
		// } else {
		// Intent intent = new Intent(PublishActivity.this,
		// GalleryActivity.class);
		// intent.putExtra("position", "1");
		// intent.putExtra("ID", arg2);
		// startActivity(intent);
		// }
		// }
		// });

	}

	// private AlertWidget bar;
	private OnClickListener publishClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Map<String, String> paramsHead = Utils.getHeaderParamsOnly();
			if (paramsHead == null) {
				btnSend.setEnabled(true);
				ToastUtil.show(PublishActivity.this, "您的登录已过期，请重新登录后再试！");
				return;
			}
			String content = edtTxtContent.getText().toString().trim();
			if ((Bimp.tempSelectBitmap == null || Bimp.tempSelectBitmap.size() == 0)
					&& (content == null || "".equals(content))) {
				ToastUtil.show(PublishActivity.this, "发表信息不能为空！");
				return;
			}
			// if (bar == null)
			// bar = new AlertWidget(PublishActivity.this);
			// bar.showBar();
			// bar.setCancelable(false);
			showBar();
			setBarCancelable(false);
			btnSend.setEnabled(false);
			if (Bimp.tempSelectBitmap == null || Bimp.tempSelectBitmap.size() == 0)
				submit(null);
			else
				upload();
		}
	};

	private void upload() {
		new Thread() {
			public void run() {
				List<File> listFile = new ArrayList<File>();
				if (Bimp.tempSelectBitmap != null) {
					for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
						ImageItem imageItem = Bimp.tempSelectBitmap.get(i);
						listFile.add(new File(imageItem.getImagePath()));
					}
				}
				HttpXUtils.upload(PublishActivity.this, Const.URL_UPLOAD_COMMENTIMG, listFile, null,
						Utils.getHeaderParamsOnly(), new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {
						submit(new Gson().fromJson(responseResult, UploadFile.class).getPath());
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						btnSend.setEnabled(true);
						closeBar();
					}

					@Override
					public void onError(String url, String responseResult) {
						btnSend.setEnabled(true);
						closeBar();
					}
				}, true);
			};
		}.start();
	}

	private void submit(String imgs) {
		String content = edtTxtContent.getText().toString().trim();
		Map<String, String> paramsContent = new HashMap<String, String>();
		paramsContent.put(Const.PARAM_CONTENT, content);
		if (buildingId != null)
			paramsContent.put(Const.PARAM_BUILDING_ID, buildingId);
		if (unPublish != null)
			paramsContent.put(Const.PARAM_BUILDING_ID, String.valueOf(unPublish.getBuildingId()));
		if (imgs != null)
			paramsContent.put(Const.PARAM_COMMENTPATHS, imgs);
		HttpRequest.post(activity, url, getRequestTag(), paramsContent, Utils.getHeaderParamsOnly(),
				new RequestListener() {

					@Override
					public void onSuccess(String url, String responseResult) {

						if (buildingId != null) {
							EventBus.getDefault().post(new BuildingCommentPublish());
						} else {
							if (unPublish != null) {
								if (UnPublishFragment.OnPublishedListener != null)
									UnPublishFragment.OnPublishedListener.onPulished();
							} else {
								if (SeeHouseCircleActivity.OnPublishedListener != null)
									SeeHouseCircleActivity.OnPublishedListener.onPulished();
							}
						}
						finish();
						ToastUtil.show(getApplicationContext(), "发表成功！");
					}

					@Override
					public void onFailure(String url, String errorInfo) {
						btnSend.setEnabled(true);
						closeBar();
						ToastUtil.show(getApplicationContext(), "发表失败！");
					}

					@Override
					public void onError(String url, String responseResult) {
						btnSend.setEnabled(true);
						closeBar();
						ToastUtil.show(getApplicationContext(), responseResult);
					}
				}, false);
	}

	public class GridAdapter extends BaseAdapter {
		private LayoutInflater inflater;
		private int selectedPosition = -1;
		private boolean shape;

		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public GridAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public void update() {
			loading();
		}

		public int getCount() {
			if (Bimp.tempSelectBitmap.size() == 9) {
				return 9;
			}
			return (Bimp.tempSelectBitmap.size() + 1);
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.photo_item_published_grida, parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
				holder.imgDel = (ImageView) convertView.findViewById(R.id.imgDel);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (position == Bimp.tempSelectBitmap.size()) {
				holder.image.setImageResource(R.drawable.btn_add_pic);
				// holder.image.setImageBitmap(BitmapFactory.decodeResource(
				// getResources(), R.drawable.btn_add_pic));
				holder.imgDel.setVisibility(View.GONE);
				if (position == 9) {
					holder.image.setVisibility(View.GONE);
					holder.imgDel.setVisibility(View.GONE);
				}
			} else {
				holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
				holder.imgDel.setVisibility(View.VISIBLE);
			}
			holder.image.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (position == Bimp.tempSelectBitmap.size()) {
						Utils.hideInput(edtTxtContent);
						ll_popup.startAnimation(
								AnimationUtils.loadAnimation(PublishActivity.this, R.anim.activity_translate_in));
						pop.showAtLocation(childView, Gravity.BOTTOM, 0, 0);
					} else {
						Intent intent = new Intent(PublishActivity.this, GalleryActivity.class);
						intent.putExtra("position", "1");
						intent.putExtra("ID", position);
						startActivity(intent);
					}
				}
			});
			holder.imgDel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Bimp.tempSelectBitmap.remove(position);
					notifyDataSetChanged();
				}
			});
			return convertView;
		}

		public class ViewHolder {
			public ImageView image;
			public ImageView imgDel;
		}

		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					adapter.notifyDataSetChanged();
					break;
				}
				super.handleMessage(msg);
			}
		};

		public void loading() {
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						if (Bimp.max == Bimp.tempSelectBitmap.size()) {
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
							break;
						} else {
							Bimp.max += 1;
							Message message = new Message();
							message.what = 1;
							handler.sendMessage(message);
						}
					}
				}
			}).start();
		}
	}

	public String getString(String s) {
		String path = null;
		if (s == null)
			return "";
		for (int i = s.length() - 1; i > 0; i++) {
			s.charAt(i);
		}
		return path;
	}

	protected void onStart() {
		super.onStart();
		if (adapter != null)
			adapter.notifyDataSetChanged();
	}

	private static final int TAKE_PICTURE = 0x000001;
	private File filePhoto;
	private Bitmap bitmapPhoto;

	public void photo() {
		// Intent openCameraIntent = new
		// Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		String fileName = String.valueOf(System.currentTimeMillis());
		fileName = FileUtils.getInstance().getPhotoTempPathName() + "/" + fileName + ".JPEG";
		filePhoto = new File(fileName);
		if (!filePhoto.exists())
			filePhoto.getParentFile().mkdirs();
		startActivityForResult(
				new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(filePhoto)),
				TAKE_PICTURE);
		// openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new
		// File(fileName)));
		// startActivityForResult(openCameraIntent, TAKE_PICTURE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case TAKE_PICTURE:
			if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {
				String path = filePhoto.getAbsolutePath();
				bitmapPhoto = BitmapFactory.decodeFile(path);
				bitmapPhoto = PhotoFileUtils.savePhoto(bitmapPhoto, filePhoto);
				ImageItem takePhoto = new ImageItem();
				takePhoto.setImagePath(path);
				takePhoto.setBitmap(bitmapPhoto);
				Bimp.tempSelectBitmap.add(takePhoto);
			}
			break;
		}
	}

	@Override
	protected void onDestroy() {
		if (Bimp.tempSelectBitmap != null)
			Bimp.tempSelectBitmap.clear();
		bitmap = null;
		ShowAlbumActivity.bitmap = null;
		ShowAlbumActivity.contentList = null;
		ShowAllPhoto.dataList = null;
		PhotoFileUtils.deleteDir();
		Bimp.max = 0;
		super.onDestroy();
	}

	@Override
	public void onChildViewLoaded() {
		isShowTitle(false);
		Init();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		edtTxtContent.requestFocus();
	}

	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		return PublishActivity.class.getSimpleName();
	}

}
