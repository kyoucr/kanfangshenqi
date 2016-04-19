package com.xinwei.kanfangshenqi.view;

import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.model.BasicSlideItem;
/**
 * 上拉刷新 上拉加载更多，左右滑动菜单listview
 * @author wangzhijun
 *
 */
public class SlideListView extends ListView implements OnScrollListener {
	private final static int RELEASE_To_REFRESH = 0;
	private final static int PULL_To_REFRESH = 1;
	private final static int REFRESHING = 2;
	private final static int DONE = 3;
	private final static int LOADING = 4;

	// 实际的padding的距离与界面上偏移距离的比例
	private final static int RATIO = 3;

	private LayoutInflater inflater;

	private LinearLayout headView;

	private TextView tipsTextview;
	private TextView lastUpdatedTextView;
	private ImageView arrowImageView;
	private ProgressBar progressBar;

	private RotateAnimation animation;
	private RotateAnimation reverseAnimation;

	// 用于保证startY的值在一个完整的touch事件中只被记录一次
	private boolean isRecored;

	// private int headContentWidth;
	private int headContentHeight;

	private int startY;
	private int firstItemIndex;
	private int state;
	private boolean isBack;
	private OnRefreshListener refreshListener;
	
	private OnLoadMoreListener loadMoreListener;

	private boolean isRefreshable;
	private boolean isPush;

	private int visibleLastIndex;
	private int visibleItemCount;

	private static final String TAG = "ListViewCompat";

	private SlideView mFocusedItemView;

	private boolean lockLeft;// 左滑动

	private boolean lockRight = true;// 默认屏蔽右滑动

	private RelativeLayout mFooterView;
	
	private TextView mLoadMore;

	private ProgressBar moreProgressBar;

	public SlideListView(Context context) {
		super(context);
		init(context);
	}

	public SlideListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		inflater = LayoutInflater.from(context);
		headView = (LinearLayout) inflater.inflate(R.layout.slidelistview_head,
				null);
		arrowImageView = (ImageView) headView
				.findViewById(R.id.head_arrowImageView);
		// arrowImageView.setMinimumWidth(70);
		// arrowImageView.setMinimumHeight(50);
		progressBar = (ProgressBar) headView
				.findViewById(R.id.head_progressBar);
		tipsTextview = (TextView) headView.findViewById(R.id.head_tipsTextView);
		lastUpdatedTextView = (TextView) headView
				.findViewById(R.id.head_lastUpdatedTextView);

		measureView(headView);
		headContentHeight = headView.getMeasuredHeight();
		// headContentWidth = headView.getMeasuredWidth();

		headView.setPadding(0, -1 * headContentHeight, 0, 0);
		headView.invalidate();

//		addHeaderView(headView, null, false);
		
		setOnScrollListener(this);

		animation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(250);
		animation.setFillAfter(true);

		reverseAnimation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		reverseAnimation.setInterpolator(new LinearInterpolator());
		reverseAnimation.setDuration(200);
		reverseAnimation.setFillAfter(true);

		state = DONE;
		isRefreshable = false;
		isPush = true;
		mFooterView = (RelativeLayout)inflater.inflate(R.layout.slidelistview_foot, null).findViewById(R.id.list_footview);
		mLoadMore = (TextView) mFooterView.findViewById(R.id.text_view);
		moreProgressBar = (ProgressBar) mFooterView.findViewById(R.id.footer_progress);
		// TODO:
//		addFooterView(mFooterView);
//		mFooterView.setVisibility(View.GONE);
	}

	private int lastItemIndex;// 当前ListView中最后一个Item的索引

	@Override
	public void onScroll(AbsListView arg0, int firstVisiableItem, int arg2,
			int arg3) {
		firstItemIndex = firstVisiableItem;
		visibleLastIndex = firstVisiableItem + arg2 - 1;
		visibleItemCount = arg2;
		if (firstItemIndex == 1 && !isPush) {
			setSelection(0);
		}

		// lastItemIndex = firstVisibleItem + visibleItemCount - 1 -1;
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
		// && lastItemIndex == mGoodsAdapter.getCount() - 1) {
		// //加载数据代码，此处省略了
		// }
	}

	public void setSelectionfoot() {
		this.setSelection(visibleLastIndex - visibleItemCount + 1);
	}

	
	int mLastX;
	int mLastY;
	float TAN = 1.25f;
	boolean listViewEvent = false;
	boolean firstInit = true;
	boolean responseOnclickItem = true;
	/*
	 * @Override public boolean onTouchEvent(MotionEvent event) { switch
	 * (event.getAction()) { case MotionEvent.ACTION_DOWN: { int x = (int)
	 * event.getX(); int y = (int) event.getY(); int position =
	 * pointToPosition(x, y); Log.e(TAG, "postion=" + position); if (position !=
	 * INVALID_POSITION) { BasicSlideItem data = (BasicSlideItem)
	 * getItemAtPosition(position); mFocusedItemView = data.getSlideView();
	 * Log.e(TAG, "FocusedItemView=" + mFocusedItemView); } } default: break; }
	 * 
	 * if (mFocusedItemView != null) {
	 * mFocusedItemView.onRequireTouchEvent(event, lockLeft, lockRight); }
	 * 
	 * return super.onTouchEvent(event); }
	 */
	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// if (isRefreshable) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			responseOnclickItem = true;
			if (firstItemIndex == 0 && !isRecored) {
				isRecored = true;
				isPush = true;
				startY = (int) event.getY();
				// Logger.v(TAG, "在down时候记录当前位置‘");
			}
			int position = pointToPosition(x, y);
			if (position != INVALID_POSITION) {
				Object obj = getItemAtPosition(position);
				if(obj != null && obj instanceof BasicSlideItem){
					BasicSlideItem data = (BasicSlideItem) obj;
					mFocusedItemView = data.getSlideView();
				}
			}
			if (mFocusedItemView != null) {
				  mFocusedItemView.onRequireTouchEvent(event, lockLeft, lockRight); }
			break;
		case MotionEvent.ACTION_UP:
			if (state != REFRESHING && state != LOADING) {
				if (state == DONE) {
					// 什么都不做
				}
				if (state == PULL_To_REFRESH) {
					state = DONE;
					changeHeaderViewByState();

					// Logger.v(TAG, "由下拉刷新状态，到done状态");
				}
				if (state == RELEASE_To_REFRESH) {
					state = REFRESHING;
					changeHeaderViewByState();
					onRefresh();

					// Logger.v(TAG, "由松开刷新状态，到done状态");
				}
			}
			if (mFocusedItemView != null) {
				  mFocusedItemView.onRequireTouchEvent(event, lockLeft, lockRight); }
			isRecored = false;
			isBack = false;

			listViewEvent = false;
			firstInit = true;
			
			break;

		case MotionEvent.ACTION_MOVE:
			int tempY = (int) event.getY();
			int deltaX = x - mLastX;
			int deltaY = y - mLastY;
			if(firstInit){
				if (Math.abs(deltaX)>5 || Math.abs(deltaY)>5 ) {//处理上下滑动事件
					if(Math.abs(deltaX) < Math.abs(deltaY) * TAN){
						listViewEvent = true;
						responseOnclickItem = true;
					}else{
						listViewEvent = false;
						responseOnclickItem = false;
					}
					firstInit = false;
				}
			}
			if(!listViewEvent){
				if (mFocusedItemView != null) {
					  mFocusedItemView.onRequireTouchEvent(event, lockLeft, lockRight); }
				return true;
			}
			if(!isRefreshable){
				return super.onTouchEvent(event);
			}
			if (!isRecored && firstItemIndex == 0) {
				// Logger.v(TAG, "在move时候记录下位置");
				isRecored = true;
				startY = tempY;
			}

			if (state != REFRESHING && isRecored && state != LOADING) {

				// 保证在设置padding的过程中，当前的位置一直是在head，否则如果当列表超出屏幕的话，当在上推的时候，列表会同时进行滚动

				// 可以松手去刷新了
				if (state == RELEASE_To_REFRESH) {

					setSelection(0);

					// 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步
					if (((tempY - startY) / RATIO < headContentHeight)
							&& (tempY - startY) > 0) {
						state = PULL_To_REFRESH;
						changeHeaderViewByState();
						//
						// Logger.v(TAG, "由松开刷新状态转变到下拉刷新状态");
					}
					// 一下子推到顶了
					else if (tempY - startY <= 0) {
						state = DONE;
						changeHeaderViewByState();

						// Logger.v(TAG, "由松开刷新状态转变到done状态");
					}
					// 往下拉了，或者还没有上推到屏幕顶部掩盖head的地步
					else {
						// 不用进行特别的操作，只用更新paddingTop的值就行了
					}
				}
				// 还没有到达显示松开刷新的时候,DONE或者是PULL_To_REFRESH状态
				if (state == PULL_To_REFRESH) {

					setSelection(0);

					// 下拉到可以进入RELEASE_TO_REFRESH的状态
					if ((tempY - startY) / RATIO >= headContentHeight) {
						state = RELEASE_To_REFRESH;
						isBack = true;
						changeHeaderViewByState();
						// Logger.v(TAG, "由done或者下拉刷新状态转变到松开刷新");
					}
					// 上推到顶了
					else if (tempY - startY <= 0) {
						state = DONE;
						changeHeaderViewByState();
						isPush = false;
						// Logger.v(TAG, "由DOne或者下拉刷新状态转变到done状态");
					}
				}

				// done状态下
				if (state == DONE) {
					if (tempY - startY > 0) {
						state = PULL_To_REFRESH;
						changeHeaderViewByState();
						// Logger.v(TAG, "done状态下由DOne或者下拉刷新状态转变到done状态");
					}
				}

				// 更新headView的size
				if (state == PULL_To_REFRESH) {
					headView.setPadding(0, -1 * headContentHeight
							+ (tempY - startY) / RATIO, 0, 0);

				}

				// 更新headView的paddingTop
				if (state == RELEASE_To_REFRESH) {
					headView.setPadding(0, (tempY - startY) / RATIO
							- headContentHeight, 0, 0);
				}

			}

			break;
		}
		mLastX = x;
		mLastY = y;
		// }

		return super.onTouchEvent(event);
	}
	
	@Override
	public boolean performItemClick(View view, int position, long id) {
		if(!responseOnclickItem){
			return false;
		}else{
			return super.performItemClick(view, position, id);
		}
	}

	// 当状态改变时候，调用该方法，以更新界面
	private void changeHeaderViewByState() {
		switch (state) {
		case RELEASE_To_REFRESH:
			arrowImageView.setVisibility(View.VISIBLE);
			progressBar.setVisibility(View.GONE);
			tipsTextview.setVisibility(View.VISIBLE);
			lastUpdatedTextView.setVisibility(View.VISIBLE);

			arrowImageView.clearAnimation();
			arrowImageView.startAnimation(animation);

			tipsTextview.setText(getResources().getString(
					R.string.release_to_refresh));

			// Logger.v(TAG, "当前状态，松开刷新");
			break;
		case PULL_To_REFRESH:
			progressBar.setVisibility(View.GONE);
			tipsTextview.setVisibility(View.VISIBLE);
			lastUpdatedTextView.setVisibility(View.VISIBLE);
			arrowImageView.clearAnimation();
			arrowImageView.setVisibility(View.VISIBLE);
			// 是由RELEASE_To_REFRESH状态转变来的
			if (isBack) {
				isBack = false;
				arrowImageView.clearAnimation();
				arrowImageView.startAnimation(reverseAnimation);

				tipsTextview.setText(getResources().getString(
						R.string.pull_to_refresh));
			} else {
				tipsTextview.setText(getResources().getString(
						R.string.pull_to_refresh));
			}
			// Logger.v(TAG, "当前状态，下拉刷新");
			break;

		case REFRESHING:

			headView.setPadding(0, 0, 0, 0);

			progressBar.setVisibility(View.VISIBLE);
			arrowImageView.clearAnimation();
			arrowImageView.setVisibility(View.GONE);
			tipsTextview.setText(getResources().getString(R.string.refreshing));
			lastUpdatedTextView.setVisibility(View.VISIBLE);

			// Logger.v(TAG, "当前状态,正在刷新...");
			break;
		case DONE:
			headView.setPadding(0, -1 * headContentHeight, 0, 0);

			progressBar.setVisibility(View.GONE);
			arrowImageView.clearAnimation();
			arrowImageView.setImageResource(R.drawable.pulltorefresh);
			tipsTextview.setText(getResources().getString(
					R.string.pull_to_refresh));
			lastUpdatedTextView.setVisibility(View.VISIBLE);

			// Logger.v(TAG, "当前状态，done");
			break;
		}
	}

	public void setonRefreshListener(OnRefreshListener refreshListener) {
		this.refreshListener = refreshListener;
		isRefreshable = true;
	}

	public interface OnRefreshListener {
		public void onRefresh();
	}

	public void onLoadMoreComplete() {
//		state = DONE;
//		lastUpdatedTextView.setText(getResources().getString(R.string.updating)
//				+ new Date().toLocaleString());
//		changeHeaderViewByState();
//		invalidateViews();
//		setSelection(0);
	}
	
	public void setonLoadMoreListener(OnLoadMoreListener loadMoreListener) {
		this.loadMoreListener = loadMoreListener;
		if(loadMoreListener != null){
			mFooterView.setVisibility(View.VISIBLE);
		}
		mLoadMore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(SlideListView.this.loadMoreListener != null){
					SlideListView.this.loadMoreListener.onLoadMore();
				}
			}
		});
	}
	
	public void setFootViewVisible(boolean visible){
		if(visible){
			mFooterView.setVisibility(View.VISIBLE);
		}else{
			mFooterView.setVisibility(View.GONE);
		}
	}

	public interface OnLoadMoreListener {
		public void onLoadMore();
	}

	public void onRefreshComplete() {
		state = DONE;
		lastUpdatedTextView.setText(getResources().getString(R.string.updating)
				+ new Date().toLocaleString());
		changeHeaderViewByState();
		invalidateViews();
		setSelection(0);
	}

	private void onRefresh() {
		if (refreshListener != null) {
			refreshListener.onRefresh();
		}
	}

	public void clickToRefresh() {
		state = REFRESHING;
		changeHeaderViewByState();
	}

	// 此方法直接照搬自网络上的一个下拉刷新的demo，此处是“估计”headView的width以及height
	private void measureView(View child) {
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}

	public void setAdapter(BaseAdapter adapter) {
		lastUpdatedTextView.setText(getResources().getString(R.string.updating)
				+ new Date().toLocaleString());
		super.setAdapter(adapter);
	}

	public SlideListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void shrinkListItem(int position) {
		View item = getChildAt(position);

		if (item != null) {
			try {
				((SlideView) item).shrink();
			} catch (ClassCastException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isLockLeft() {
		return lockLeft;
	}

	public void setLockLeft(boolean lockLeft) {
		this.lockLeft = lockLeft;
	}

	public boolean isLockRight() {
		return lockRight;
	}

	public void setLockRight(boolean lockRight) {
		this.lockRight = lockRight;
	}

}
