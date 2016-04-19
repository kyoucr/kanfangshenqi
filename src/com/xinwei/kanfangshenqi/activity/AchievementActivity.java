package com.xinwei.kanfangshenqi.activity;

import java.util.List;

import org.xutils.view.annotation.ViewInject;

import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.adapter.AchievementAdp;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.AchievementList;
import com.xinwei.kanfangshenqi.model.AchievementList.Achievement;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.CircleImageViewWithBorder;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
/**
 ********************
 * 个人成就
 * @author cn
 ********************
 */
public class AchievementActivity extends BaseActivity{
	@ViewInject(R.id.lViAchievement)
	private ListView lViAchievement;
	@ViewInject(R.id.photo)
	private CircleImageViewWithBorder photo;
	@ViewInject(R.id.imgSex)
	private ImageView imgSex;
	@ViewInject(R.id.txtNickname)
	private TextView txtNickname;
//	@ViewInject(R.id.imgFirstLine)
//	private ImageView imgFirstLine;
	private List<Achievement> achievementDataList;
	private AchievementAdp adp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_achievement);
	}

	@Override
	public void onChildViewLoaded() {
		setLeftTitle(R.string.user);
		setBaseResourceBg(R.drawable.grcj_big_bg);
		setTitleColorBg(getResources().getColor(android.R.color.transparent));
		isShowTitleCutline(false);
		initPersonalView();
		onReloadData();
	}
	private void initPersonalView(){
		PreferenceUtils preferenceUtils = PreferenceUtils.getInstance(activity);
		// 获取用户信息
		String headPic = preferenceUtils.getSettingUserPic();
		//设置用户信息
		ImageLoaderUtil.getInstance().bindHeadImg(photo, headPic);
		String nickname = preferenceUtils.getSettingUserNickName();
		TextViewWriterUtil.writeValue(txtNickname, nickname);
		String sex = preferenceUtils.getSettingUserSex();
		if(Const.MAN.equals(sex))
			imgSex.setImageResource(R.drawable.color_man_icon);
		else if(Const.WOMAN.equals(sex))
			imgSex.setImageResource(R.drawable.color_woman_icon);
		else
			imgSex.setImageResource(R.drawable.ic_mars);
	}
	@Override
	public void onReloadData() {
		showBar();
		HttpRequest.get(this, Const.URL_ACHIEVEMENT, getRequestTag(), null, Utils.getHeaderParamsOnly(), new RequestListener() {
			
			@Override
			public void onSuccess(String url, String responseResult) {
				AchievementList achievementList = new Gson().fromJson(responseResult, AchievementList.class);
				achievementDataList = achievementList.getDataList();
				if(ValidatorUtil.isValidList(achievementDataList)){
					adp = new AchievementAdp(activity, achievementDataList);
					lViAchievement.setAdapter(adp);
//					imgFirstLine.setVisibility(View.VISIBLE);
				}
				isShowContent(true);
			}
			
			@Override
			public void onFailure(String url, String errorInfo) {
				isShowError(true);
			}
			
			@Override
			public void onError(String url, String responseResult) {
				closeBar();
				isShowError(true);
			}
		},true);
	}

	@Override
	public String getRequestTag() {
		return AchievementActivity.class.getSimpleName();
	}
}
