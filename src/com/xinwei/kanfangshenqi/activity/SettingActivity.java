package com.xinwei.kanfangshenqi.activity;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.fragment.WoDeFragment;
import com.xinwei.kanfangshenqi.model.Invalidation;
import com.xinwei.kanfangshenqi.util.FileUtils;
import com.xinwei.kanfangshenqi.util.PreferenceUtils;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.ToastUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 ******************** 
 * 设置
 * 
 * @author cn
 ******************** 
 */
public class SettingActivity extends BaseActivity {
//	@ViewInject(R.id.lltMsgNotify)
//	private LinearLayout lltMsgNotify;
	@ViewInject(R.id.rltClearCache)
	private RelativeLayout rltClearCache;
	@ViewInject(R.id.lltAboutUs)
	private LinearLayout lltAboutUs;
	@ViewInject(R.id.txtCache)
	private TextView txtCache;
//	@ViewInject(R.id.switchBtn)
//	private SwitchButton switchBtn;
	@ViewInject(R.id.btnQuit)
	private Button btnQuit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addChildView(R.layout.activity_setting);
	}

	@Override
	public void onChildViewLoaded() {
		setTitleTxt(R.string.title_setting);
		setLeftTitle(R.string.tab_bar_ic_wode);
		try {
			TextViewWriterUtil.writeValue(txtCache,
					FileUtils.getInstance().getCacheFileSize());
		} catch (Exception e) {
		}
		if(ValidatorUtil.isValidString(PreferenceUtils.getInstance(activity).getSettingUserToken()))
			btnQuit.setVisibility(View.VISIBLE);
		else
			btnQuit.setVisibility(View.GONE);
	}
//	@OnCompoundButtonCheckedChange(R.id.switchBtn)
//	public void onCheckChanged(CompoundButton buttonView, boolean isChecked){
//	}
		
	
	@Event({ R.id.lltAboutUs, R.id.rltClearCache ,R.id.btnQuit})
	private void onClickEvent(View v) {
		switch (v.getId()) {
		case R.id.lltAboutUs:
			Bundle data = new Bundle();
			data.putString(Const.WEB_TITLE_KEY,
					getString(R.string.txt_about_us));
			data.putString(Const.WEB_URL_KEY, getString(R.string.txt_url_about_us));
			Utils.moveTo(this, WebActivity.class, false, data);
			break;
		case R.id.rltClearCache:
			clearCache();
			break;
		case R.id.btnQuit:
			getAlert().showCustom(null, getString(R.string.alert_logout), null, null, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Utils.removeLoginInfo();
					btnQuit.setVisibility(View.GONE);
					if(WoDeFragment.woDeFragment!=null)
						WoDeFragment.woDeFragment.onChildViewLoaded();
					getAlert().close();
					PreferenceUtils.getInstance().clearUserUnappointplancount();
//					EventBus.getDefault().post(new UnPlan());
					EventBus.getDefault().post(new Invalidation());
					finish();
//					if(MainActivity.INSTANCE!=null)
//						MainActivity.INSTANCE.finish();
//					Utils.moveTo(activity, LoginActivity.class, true, null);
				}
			});
			break;
		}
	}
	//清理缓存
	private void clearCache() {
		try {
			if (ValidatorUtil.isValidString(FileUtils.getInstance()
					.getCacheFileSize())) {
				getAlert().showCustom(null, getString(R.string.alert_clear), null, null, new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						FileUtils.getInstance().clearCache();
						try {
							getAlert().close();
							TextViewWriterUtil.writeValue(txtCache, FileUtils
									.getInstance().getCacheFileSize());
						} catch (Exception e) {
						}
					}
				});
			}else{
				ToastUtil.show(activity, getString(R.string.alert_no_need_clear));
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void onReloadData() {
	}

	@Override
	public String getRequestTag() {
		return SettingActivity.class.getSimpleName();
	}

}
