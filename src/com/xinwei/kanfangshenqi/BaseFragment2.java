package com.xinwei.kanfangshenqi;

import org.xutils.x;

import com.xinwei.kanfangshenqi.app.VolleyManager;
import com.xinwei.kanfangshenqi.app.XWApplication;
import com.xinwei.kanfangshenqi.view.AlertWidget;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 *******************
 * fragment基类
 * @author cn
 *
 *******************
 */
public abstract class BaseFragment2 extends Fragment{
	protected View parentView;
	protected View childView;
	private RelativeLayout rltContent;
	protected RelativeLayout rltBase;
	public RelativeLayout rltError;
	private RelativeLayout rltCustom;
	private Button btnCtrl;
	public RelativeLayout rltEmpty;
	private ImageView imgEmpty;
	private TextView txtEmpty;
	private RelativeLayout rltTitle;
	private RelativeLayout rltLeft;
	private TextView txtTitle;
	protected TextView txtLeft;
	private AlertWidget bar;
	protected Activity activity;
	private ImageView imgTitleCutLine;
	private TextView txtRight;
	/** Fragment当前状态是否可见 */
	protected boolean isVisible;
	/** 标志位，标志已经初始化完成 */
	protected boolean isPrepared;
	/** 是否已被加载过一次，第二次就不再去请求数据了 */
	protected boolean mHasLoadedOnce;

	private AlertWidget alert;
	private View viewCustom;
	protected int mBeginPage = 1;
	/**
	 ********************
	 * 获取提示框组件
	 * @return
	 ********************
	 */
	public AlertWidget getAlert(){
		if(alert==null)
			alert = new AlertWidget(activity);
		return alert;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return parentView;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		parentView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_base, null);
		rltTitle = (RelativeLayout) parentView.findViewById(R.id.rltTitle);
		rltBase = (RelativeLayout) parentView.findViewById(R.id.rltBase);
		rltContent = (RelativeLayout) parentView.findViewById(R.id.rltContent);
		txtLeft = (TextView) parentView.findViewById(R.id.txtLeft);
		rltEmpty = (RelativeLayout) parentView.findViewById(R.id.rltEmpty);
		rltCustom = (RelativeLayout) parentView.findViewById(R.id.rltCustom);
		imgEmpty = (ImageView) parentView.findViewById(R.id.imgEmpty);
		txtEmpty = (TextView) parentView.findViewById(R.id.txtEmpty);
		rltError = (RelativeLayout) parentView.findViewById(R.id.rltError);
		rltLeft = (RelativeLayout) parentView.findViewById(R.id.rltLeft);
		txtTitle = (TextView) parentView.findViewById(R.id.txtTitle);
		imgTitleCutLine = (ImageView) parentView.findViewById(R.id.imgTitleCutLine);
		txtRight = (TextView) parentView.findViewById(R.id.txtRight);
		btnCtrl = (Button) parentView.findViewById(R.id.btnCtrl);
		btnCtrl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onReloadData();
			}
		});
		//默认隐藏标题
		isShowTitle(false);
		activity = getActivity();
	}
	/**
	 *******************
	 * 添加自定义标题
	 * @param resId
	 *******************
	 */
	public View addCustomTitle(int resId){
		rltTitle.removeAllViews();
		View viewTitle = LayoutInflater.from(XWApplication.getInstance()).inflate(resId, null);
		rltTitle.addView(viewTitle);
		return viewTitle;
	}
	/**
	 *******************
	 * 显示或者隐藏标题
	 *******************
	 */
	public void isShowTitle(boolean isShow) {
		if (rltTitle == null)
			rltTitle = (RelativeLayout) parentView.findViewById(R.id.rltTitle);
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
			txtTitle = (TextView) parentView.findViewById(R.id.txtTitle);
		txtTitle.setText(resId);
	}
	/**
	 *******************
	 * 设置标题文字
	 * 
	 * @param title
	 *******************
	 */
	public void setTitleTxt(String title){
		if(title == null)
			return;
		if (txtTitle == null)
			txtTitle = (TextView) parentView.findViewById(R.id.txtTitle);
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
	 * @param txtRight
	 ********************
	 */
	public void setRightTxt(String txt){
		if(txt == null)
			return;
		txtRight.setText(txt);
	}
	/**
	 ********************
	 * 设置右文字
	 * @param txtRight
	 ********************
	 */
	public void setRightTxt(int resId){
		txtRight.setText(resId);
	}
	/**
	 ********************
	 * 获取右按钮
	 * @param resId
	 ********************
	 */
	public TextView getRightTxt(){
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
		if (isShow){
			rltContent.setVisibility(View.VISIBLE);
			rltError.setVisibility(View.GONE);
			rltEmpty.setVisibility(View.GONE);
			rltCustom.setVisibility(View.GONE);
		}else{
			rltContent.setVisibility(View.GONE);
		}
		closeBar();
	}

	/**
	 *******************
	 * 是否显示错误界面
	 * @param isShow
	 *******************
	 */
	public void isShowError(boolean isShow) {
		if (isShow){
			rltError.setVisibility(View.VISIBLE);
			rltContent.setVisibility(View.GONE);
			rltEmpty.setVisibility(View.GONE);
			rltCustom.setVisibility(View.GONE);
		}else
			rltError.setVisibility(View.GONE);
		closeBar();
	}
	/**
	 *******************
	 * 是否显示无数据界面
	 * @param isShow
	 *******************
	 */
	public void isShowEmpty(boolean isShow){
		if (isShow){
			rltEmpty.setVisibility(View.VISIBLE);
			rltContent.setVisibility(View.GONE);
			rltError.setVisibility(View.GONE);
			rltCustom.setVisibility(View.GONE);
		}else
			rltEmpty.setVisibility(View.GONE);
		closeBar();
	}
	/**
	 ********************
	 * 添加自定义layout进行显示
	 * @param resId
	 * @return
	 ********************
	 */
	public View showCustom(int resId){
		rltCustom.setVisibility(View.VISIBLE);
		rltEmpty.setVisibility(View.GONE);
		rltContent.setVisibility(View.GONE);
		rltError.setVisibility(View.GONE);
		if(viewCustom == null){
			viewCustom = LayoutInflater.from(activity).inflate(resId, null);
			rltCustom.addView(viewCustom, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		}
		closeBar();
		return viewCustom;
	}
	/**
	 ********************
	 * 设置空界面
	 * @param imgRes
	 * @param txtRes
	 ********************
	 */
	public void setEmptyPage(int imgRes,int txtRes){
		if(imgRes == 0)
			imgEmpty.setVisibility(View.GONE);
		else
			imgEmpty.setBackgroundResource(imgRes);
		if(txtRes == 0)
			txtEmpty.setVisibility(View.GONE);
		else
			txtEmpty.setText(txtRes);
	}
	/**
	 ********************
	 * 设置标题下划线是否可见 
	 * @param isShow
	 ********************
	 */
	public void isShowTitleCutline(boolean isShow){
		if(isShow)
			imgTitleCutLine.setVisibility(View.VISIBLE);
		else
			imgTitleCutLine.setVisibility(View.GONE);
	}
	public int getInformationBarheight() {
		return getAreaOne().mHeight - getAreaTwo().mHeight;
	}

	public Dimension getAreaOne() {
		Dimension dimen = new Dimension();
		Display disp = activity.getWindowManager().getDefaultDisplay();
		Point outP = new Point();
		disp.getSize(outP);
		dimen.mWidth = outP.x;
		dimen.mHeight = outP.y;
		return dimen;
	}

	public Dimension getAreaTwo() {
		Dimension dimen = new Dimension();
		Rect outRect = new Rect();
		activity.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(outRect);
		dimen.mWidth = outRect.width();
		dimen.mHeight = outRect.height();
		return dimen;
	}
	/**
	 ********************
	 * 设置base背景
	 * @param resId
	 ********************
	 */
	public void setBaseResourceBg(int resId){
		rltBase.setBackgroundResource(resId);
	}
	/**
	 ********************
	 * 设置标题颜色背景
	 ********************
	 */
	public void setTitleColorBg(int resId){
		rltTitle.setBackgroundColor(resId);
	}
	/**
	 ********************
	 * 设置标题resource背景
	 * @param resId
	 ********************
	 */
	public void setTitleResourceBg(int resId){
		rltTitle.setBackgroundResource(resId);
	}
	/**
	 *******************
	 * 显示loading的progressbar
	 *******************
	 */
	public void showBar(){
		if(bar == null)
			bar = new AlertWidget(getActivity());
		bar.showBar();
	}
	/**
	 *******************
	 * 关闭bar
	 *******************
	 */
	public void closeBar(){
		if(bar!=null)
			bar.close();
	}

	/**
	 ********************
	 * 设置bar是否可取消
	 * @param cancelable
	 ********************
	 */
	public void setBarCancelable(boolean cancelable){
		if(bar!=null)
			bar.setCancelable(cancelable);
	}
	@SuppressLint("NewApi")
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		
		if(getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}
	/**
	 ********************
	 * 当前fragment为可见状态，
	 * 可在可见时，再去加载网络数据
	 ********************
	 */
	protected void onVisible() {
	}
	
	/**
	 ********************
	 *当前fragment为不可见状态
	 ********************
	 */
	protected void onInvisible() {
	}
	/**
	 ********************
	 * 当前fragment有无操作消费
	 * （例：外部需要获取是否还有拉动等操作消费）
	 * @return
	 ********************
	 */
	public boolean isControlling(){
		return false;
	}
	/**
	 *******************
	 * 添加子view
	 * @param resId
	 *******************
	 */
	public void addChildView(int resId){
		childView = LayoutInflater.from(getActivity()).inflate(resId, null);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		rltContent.addView(childView,params);
		childView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				if(childView.getWidth()>0){
					childView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//					ViewUtils.inject(BaseFragment2.this, parentView);
					x.view().inject(BaseFragment2.this, parentView);
					isPrepared = true;
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
	 **************************
	 * 所有继承BaseFragment的子类都将
	 * 在这个方法中实现物理Back键按下后的逻辑，
	 * FragmentActivity捕捉到物理返回键点击事件后
	 * 会首先询问Fragment是否消费该事件，
	 * 如果没有Fragment消息时FragmentActivity自己才会消费该事件
	 **************************
	 */
	public abstract boolean onBackPressed();

	/**
	 *******************
	 * 获取网络请求tag
	 * @return
	 *******************
	 */
	public abstract String getRequestTag();
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(getRequestTag()!=null)
			VolleyManager.getRequestQueue().cancelAll(getRequestTag());
	}
	private class Dimension {
		public int mWidth;
		public int mHeight;

		public Dimension() {
		}
	}
}
