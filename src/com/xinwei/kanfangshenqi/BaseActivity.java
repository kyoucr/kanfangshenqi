package com.xinwei.kanfangshenqi;

import org.xutils.x;

import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.xinwei.kanfangshenqi.app.VolleyManager;
import com.xinwei.kanfangshenqi.app.XWActivityManager;
import com.xinwei.kanfangshenqi.exception.TokenInvalid;
import com.xinwei.kanfangshenqi.util.Logger;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.view.AlertWidget;
import com.xinwei.kanfangshenqi.view.CustomDialog;
import com.xinwei.kanfangshenqi.view.TitleBarView.TitleBarListerer;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public abstract class BaseActivity extends Activity implements Response.ErrorListener, TitleBarListerer {

	private CustomDialog dialog;
	protected View childView;
	protected Activity activity;
	protected RelativeLayout rltBase;
	private RelativeLayout rltContent;
	private RelativeLayout rltError;
	private Button btnCtrl;
	private RelativeLayout rltEmpty;
	private ImageView imgEmpty;
	private TextView txtEmpty;
	private RelativeLayout rltTitle;
	private RelativeLayout rltLeft;
	private ImageView imgTitleCutLine;
	protected TextView txtRight;

	private TextView txtTitle;
	private ImageButton btnLeft;
	protected ImageButton imgBtnRight;
	protected TextView txtLeft;
	private AlertWidget bar;
	private AlertWidget alert;
	protected int mBeginPage = 1;

	/**
	 ******************** 
	 * 获取提示框组件
	 * 
	 * @return
	 ******************** 
	 */
	public AlertWidget getAlert() {
		if (alert == null)
			alert = new AlertWidget(activity);
		return alert;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activity = this;
		XWActivityManager.getInstance().pushOneActivity(activity);
		addBaseView();
	}

	private void addBaseView() {
		setContentView(R.layout.activity_base);
		rltBase = (RelativeLayout) findViewById(R.id.rltBase);
		rltTitle = (RelativeLayout) findViewById(R.id.rltTitle);
		rltContent = (RelativeLayout) findViewById(R.id.rltContent);
		rltError = (RelativeLayout) findViewById(R.id.rltError);
		btnCtrl = (Button) findViewById(R.id.btnCtrl);
		rltEmpty = (RelativeLayout) findViewById(R.id.rltEmpty);
		imgEmpty = (ImageView) findViewById(R.id.imgEmpty);
		txtEmpty = (TextView) findViewById(R.id.txtEmpty);
		rltLeft = (RelativeLayout) findViewById(R.id.rltLeft);
		btnLeft = (ImageButton) findViewById(R.id.btnLeft);
		imgBtnRight = (ImageButton) findViewById(R.id.imgBtnRight);
		txtLeft = (TextView) findViewById(R.id.txtLeft);
		txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtRight = (TextView) findViewById(R.id.txtRight);
		imgTitleCutLine = (ImageView) findViewById(R.id.imgTitleCutLine);
		rltLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (activity != null) {
					activity.finish();
				}

			}
		});
		btnCtrl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onReloadData();
			}
		});
		rltTitle.setVisibility(View.VISIBLE);
	}

	/**
	 *******************
	 * 添加自定义标题
	 * 
	 * @param resId
	 *******************
	 */
	public View addCustomTitle(int resId) {
		rltTitle.removeAllViews();
		View viewTitle = LayoutInflater.from(this).inflate(resId, null);
		rltTitle.addView(viewTitle);
		return viewTitle;
	}

	public int getTitleHeight() {
		return rltTitle.getHeight();
	}

	public int getInformationBarheight() {
		return getAreaOne().mHeight - getAreaTwo(this).mHeight;
	}

	public Dimension getAreaOne() {
		Dimension dimen = new Dimension();
		Display disp = getWindowManager().getDefaultDisplay();
		Point outP = new Point();
		disp.getSize(outP);
		dimen.mWidth = outP.x;
		dimen.mHeight = outP.y;
		return dimen;
	}

	public Dimension getAreaTwo(Activity activity) {
		Dimension dimen = new Dimension();
		Rect outRect = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
		dimen.mWidth = outRect.width();
		dimen.mHeight = outRect.height();
		return dimen;
	}

	/**
	 ******************* 
	 * 设置左标题，上一界面标题
	 * 
	 * @param titleLeft
	 ******************* 
	 */
	public void setLeftTitle(String titleLeft) {
		txtLeft.setText(titleLeft);
	}

	/**
	 ******************* 
	 * 设置左标题，上一界面标题
	 * 
	 * @param resId
	 ******************* 
	 */
	public void setLeftTitle(int resId) {
		txtLeft.setText(resId);
	}

	/**
	 ******************* 
	 * 显示或者隐藏标题
	 ******************* 
	 */
	public void isShowTitle(boolean isShow) {
		if (rltTitle == null)
			rltTitle = (RelativeLayout) findViewById(R.id.rltTitle);
		if (isShow)
			rltTitle.setVisibility(View.VISIBLE);
		else
			rltTitle.setVisibility(View.GONE);
	}

	/**
	 ******************* 
	 * 设置标题文字
	 * 
	 * @param resId
	 ******************* 
	 */
	public void setTitleTxt(int resId) {
		if (txtTitle == null)
			txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtTitle.setText(resId);
	}

	/**
	 ******************* 
	 * 设置标题文字
	 * 
	 * @param title
	 ******************* 
	 */
	public void setTitleTxt(String title) {
		if (title == null)
			return;
		if (txtTitle == null)
			txtTitle = (TextView) findViewById(R.id.txtTitle);
		txtTitle.setText(title);
	}

	/**
	 ******************* 
	 * 是否显示标题左侧控制栏（回退键）
	 * 
	 * @param isShow
	 ******************* 
	 */
	public void isShowLeft(boolean isShow) {
		if (isShow)
			rltLeft.setVisibility(View.VISIBLE);
		else
			rltLeft.setVisibility(View.GONE);
	}

	/**
	 ******************** 
	 * 设置右文字
	 * 
	 * @param txtRight
	 ******************** 
	 */
	public void setRightTxt(String txt) {
		if (txt == null)
			return;
		txtRight.setText(txt);
	}

	/**
	 ******************** 
	 * 设置右文字
	 * 
	 * @param txtRight
	 ******************** 
	 */
	public void setRightTxt(int resId) {
		txtRight.setText(resId);
	}

	/**
	 ******************** 
	 * 获取右控件
	 * 
	 * @param resId
	 ******************** 
	 */
	public TextView getRightTxt() {
		return this.txtRight;
	}

	/**
	 ******************* 
	 * 显示或者隐藏子view
	 * 
	 * @param isShow
	 ******************* 
	 */
	public void isShowContent(boolean isShow) {
		if (isShow) {
			rltContent.setVisibility(View.VISIBLE);
			rltError.setVisibility(View.GONE);
			rltEmpty.setVisibility(View.GONE);
		} else {
			rltContent.setVisibility(View.GONE);
		}
		closeBar();
	}

	/**
	 ******************* 
	 * 是否显示错误界面
	 * 
	 * @param isShow
	 ******************* 
	 */
	public void isShowError(boolean isShow) {
		if (isShow) {
			rltError.setVisibility(View.VISIBLE);
			rltContent.setVisibility(View.GONE);
			rltEmpty.setVisibility(View.GONE);
		} else
			rltError.setVisibility(View.GONE);
		closeBar();
	}

	/**
	 ******************* 
	 * 是否显示无数据界面
	 * 
	 * @param isShow
	 ******************* 
	 */
	public void isShowEmpty(boolean isShow) {
		if (isShow) {
			rltEmpty.setVisibility(View.VISIBLE);
			rltContent.setVisibility(View.GONE);
			rltError.setVisibility(View.GONE);
		} else
			rltEmpty.setVisibility(View.GONE);
		closeBar();
	}

	/**
	 ******************** 
	 * 设置空界面
	 * 
	 * @param imgRes
	 * @param txtRes
	 ******************** 
	 */
	public void setEmptyPage(int imgRes, int txtRes) {
		if (imgRes == 0)
			imgEmpty.setVisibility(View.GONE);
		else
			imgEmpty.setImageBitmap(((BitmapDrawable) getResources().getDrawable(imgRes)).getBitmap());
		if (txtRes == 0)
			txtEmpty.setVisibility(View.GONE);
		else
			txtEmpty.setText(txtRes);
	}

	/**
	 ******************** 
	 * 设置标题下划线是否可见
	 * 
	 * @param isShow
	 ******************** 
	 */
	public void isShowTitleCutline(boolean isShow) {
		if (isShow)
			imgTitleCutLine.setVisibility(View.VISIBLE);
		else
			imgTitleCutLine.setVisibility(View.GONE);
	}

	/**
	 ******************** 
	 * 设置base背景
	 * 
	 * @param resId
	 ******************** 
	 */
	public void setBaseResourceBg(int resId) {
		rltBase.setBackgroundResource(resId);
	}

	/**
	 ******************** 
	 * 设置标题颜色背景
	 ******************** 
	 */
	public void setTitleColorBg(int resId) {
		rltTitle.setBackgroundColor(resId);
	}

	/**
	 ******************** 
	 * 设置标题resource背景
	 * 
	 * @param resId
	 ******************** 
	 */
	public void setTitleResourceBg(int resId) {
		rltTitle.setBackgroundResource(resId);
	}

	/**
	 ******************* 
	 * 显示loading的progressbar
	 ******************* 
	 */
	public void showBar() {
		if (bar == null)
			bar = new AlertWidget(this);
		bar.showBar();
	}

	/**
	 ******************* 
	 * 关闭bar
	 ******************* 
	 */
	public void closeBar() {
		if (bar != null)
			bar.close();
	}

	/**
	 ******************* 
	 * 设置bar取消事件（点击返回键取消bar显示） 在showbar方法后调用
	 * 
	 * @param listener
	 ******************* 
	 */
	public void setOnCancelListener(OnCancelListener listener) {
		bar.setOnCancelListener(listener);
	}

	/**
	 ******************** 
	 * 设置bar是否可取消
	 * 
	 * @param cancelable
	 ******************** 
	 */
	public void setBarCancelable(boolean cancelable) {
		if (bar != null)
			bar.setCancelable(cancelable);
	}

	/**
	 *******************
	 * 判断登录状态 ，并弹提示
	 * 
	 * @return
	 *******************
	 */
	public boolean isLogin() {
		if (null == Utils.getHeaderParamsOnly()) {
			getAlert().showLogin();
			return false;
		}
		return true;
	}

	/**
	 ******************* 
	 * 添加子view
	 * 
	 * @param resId
	 ******************* 
	 */
	public void addChildView(int resId) {
		childView = LayoutInflater.from(activity).inflate(resId, null);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		rltContent.addView(childView, params);
		childView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				if (childView.getWidth() > 0) {
					childView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//					ViewUtils.inject(activity);
					x.view().inject(activity);
					onChildViewLoaded();
				}
			}
		});
	}

	/**
	 ******************* 
	 * 子view的UI加载完成（ViewInject绑定结束）时调用
	 * 
	 ******************* 
	 */
	public abstract void onChildViewLoaded();

	/**
	 ******************* 
	 * 点击error界面重新加载数据
	 ******************* 
	 */
	public abstract void onReloadData();

	/**
	 ******************* 
	 * 获取网络请求tag
	 * 
	 * @return
	 ******************* 
	 */
	public abstract String getRequestTag();

	@Override
	public void onErrorResponse(VolleyError error) {
		requestError(error);
	}

	@Override
	public void onClickLeftComponent() {
		finish();
	}

	@Override
	public void onClickRightComponent() {
	}

	public void requestError(VolleyError error) {
		Utils.closeDialog();
		if (error instanceof TimeoutError || error instanceof NetworkError) {
			// HhkdApplication.getInstance().setTipMsg("网络异常,请检查您的网络");
			Utils.makeToastAndShow(getApplicationContext(), "网络异常,请检查您的网络", Toast.LENGTH_SHORT);
		} else if (error instanceof TokenInvalid) {
			CustomDialog.Builder customBuilder = new CustomDialog.Builder(this);
			customBuilder.setTitle("提示").setMessage("用户登录已失效,请重新登录").setLineGONE(View.GONE).setNegativeButton("确定",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent();
							intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							// intent.setClass(getBaseContext(),
							// LoginActivity.class);
							intent.putExtra("cleanall", true);
							getBaseContext().startActivity(intent);
							dialog.dismiss();
						}
					});
			dialog = customBuilder.create();
			dialog.show();
		}

		error.printStackTrace();
		Logger.e("HHDK", error.getMessage());
	};

	@Override
	public Resources getResources() {
		Resources res = super.getResources();
		Configuration config = new Configuration();
		config.setToDefaults();
		res.updateConfiguration(config, res.getDisplayMetrics());
		return res;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (getRequestTag() != null)
			VolleyManager.getRequestQueue().cancelAll(getRequestTag());
		XWActivityManager.getInstance().popOneActivity(activity);
	}

	private class Dimension {
		public int mWidth;
		public int mHeight;

		public Dimension() {
		}
	}
}
