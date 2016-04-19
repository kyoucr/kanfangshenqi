/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xinwei.kanfangshenqi.util;

import com.xinwei.kanfangshenqi.app.XWApplication;
import com.xinwei.kanfangshenqi.common.Const;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

	/**
	 * 保存Preference的name
	 */
	public static final String PREFERENCE_NAME = "saveInfo";
	private static SharedPreferences mSharedPreferences;
	private static PreferenceUtils mPreferenceUtils;
	private static SharedPreferences.Editor editor;

	public String SHARED_KEY_SETTING_WLANMACADDRESS = "shared_key_setting_wlanmacaddress";

	/************ 设置用户信息 *************/
	public static final String SHARED_KEY_SETTING_USER_NICKNAME = "shared_key_setting_user_nickname";
	public static final String SHARED_KEY_SETTING_USER_PIC = "shared_key_setting_user_pic";
	public static final String SHARED_KEY_SETTING_USER_SEX = "shared_key_setting_user_sex";
	public static final String SHARED_KEY_SETTING_USER_ACCOUNT = "shared_key_setting_user_account";
	public static final String SHARED_KEY_SETTING_USER_PASSWORD = "shared_key_setting_user_password";

	public static final String SHARED_KEY_SETTING_USER_COMPANYADDR = "shared_key_setting_user_companyaddr";
	public static final String SHARED_KEY_SETTING_USER_COMPANYLONGITUDE = "shared_key_setting_user_companylongitude";
	public static final String SHARED_KEY_SETTING_USER_COMPANYLATITUDE = "shared_key_setting_user_companylatitude";

	public static final String SHARED_KEY_SETTING_USER_HOMEADDR = "shared_key_setting_user_homeaddr";
	public static final String SHARED_KEY_SETTING_USER_HOMELONGITUDE = "shared_key_setting_user_homelongitude";
	public static final String SHARED_KEY_SETTING_USER_HOMELATITUDE = "shared_key_setting_user_homelatitude";

	public static final String SHARED_KEY_SETTING_USER_ALIPAYACOUNTNAME = "shared_key_setting_user_alipayacountName";
	public static final String SHARED_KEY_SETTING_USER_ALIPAYACOUNTID = "shared_key_setting_user_alipayAcountId";

	public static final String SHARED_KEY_SETTING_USER_UNAPPOINTPLANCOUNT = "shared_key_setting_user_unappointPlanCount";

	/************ 设置用户验证信息 *************/
	public static final String SHARED_KEY_SETTING_USER_TRANSID = "shared_key_setting_user_transid";
	public static final String SHARED_KEY_SETTING_USER_APPAGENT = "shared_key_setting_user_appagent";
	public static final String SHARED_KEY_SETTING_USER_OSVER = "shared_key_setting_user_osver";
	public static final String SHARED_KEY_SETTING_USER_TOKEN = "shared_key_setting_user_token";

	public static final String IS_FIRST_JIHUA_IS_DATA = "is_first_jihua_is_data";
	public static final String IS_FIRST_LOUPAN_IS_DATA = "is_first_loupan_is_data";
	public static final String IS_FIRST_ZHAOFANG_LOUPAN_IS_DATA = "is_first_zhaofang_loupan_is_data";
	public static final String IS_FIRST_SHOW_RED_BAG = "is_first_show_red_bag";
	public static final String IS_FIRST_NOT_SHOW_RED_BAG = "is_first_not_show_red_bag";

	private PreferenceUtils(Context cxt) {
		// mSharedPreferences =
		// cxt.getSharedPreferences(DemoApplication.getInstance().getUser()+PREFERENCE_NAME,
		// Context.MODE_PRIVATE);
		mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * 单例模式，获取instance实例
	 * 
	 * @param cxt
	 * @return
	 */
	public static PreferenceUtils getInstance(Context cxt) {
		if (mPreferenceUtils == null) {
			mPreferenceUtils = new PreferenceUtils(cxt);
		}
		editor = mSharedPreferences.edit();
		return mPreferenceUtils;
	}

	/**
	 * 单例模式，获取instance实例
	 * 
	 * @param cxt
	 * @return
	 */
	public static PreferenceUtils getInstance() {
		return getInstance(XWApplication.getInstance());
	}

	/**
	 * 设置物理地址
	 * 
	 * @param wlanmacaddress
	 */
	public void setSettingWlanMacAddress(String wlanmacaddress) {
		editor.putString(SHARED_KEY_SETTING_WLANMACADDRESS, wlanmacaddress);
		editor.commit();
	}

	/**
	 * 获取物理地址
	 * 
	 * @return
	 */
	public String getSettingWlanMacAddress() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_WLANMACADDRESS, "");
	}

	/**
	 * 设置用户昵称
	 * 
	 * @param UserNickName
	 */
	public void setSettingUserNickName(String UserNickName) {
		editor.putString(SHARED_KEY_SETTING_USER_NICKNAME, UserNickName);
		editor.commit();
	}

	/**
	 * 获取用户昵称
	 */
	public String getSettingUserNickName() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_NICKNAME, "看房星人");
	}

	/**
	 * 设置用户头像
	 * 
	 * @param UserPic
	 */
	public void setSettingUserPic(String UserPic) {
		editor.putString(SHARED_KEY_SETTING_USER_PIC, UserPic);
		editor.commit();
	}

	/**
	 * 获取用户头像
	 */
	public String getSettingUserPic() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_PIC, "");
	}

	/**
	 * 设置用户性别
	 * 
	 * @param UserSex
	 */
	public void setSettingUserSex(String UserSex) {
		editor.putString(SHARED_KEY_SETTING_USER_SEX, UserSex);
		editor.commit();
	}

	/**
	 * 获取用户性别
	 */
	public String getSettingUserSex() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_SEX, "女");
	}

	/**
	 * 设置用户token
	 * 
	 * @param token
	 */
	public void setSettingUserToken(String token) {
		editor.putString(SHARED_KEY_SETTING_USER_TOKEN, token);
		editor.commit();
	}

	/**
	 * 获取用户token
	 */
	public String getSettingUserToken() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_TOKEN, "");
	}

	/**
	 * 删除token
	 */
	public void removeToken() {
		editor.remove(SHARED_KEY_SETTING_USER_TOKEN);
		editor.commit();
	}

	/**
	 * 设置用户账户
	 * 
	 * @param UserArea
	 */
	public void setSettingUserAccount(String account) {
		editor.putString(SHARED_KEY_SETTING_USER_ACCOUNT, account);
		editor.commit();
	}

	/**
	 * 获取用户账户
	 */
	public String getSettingUserAccount() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_ACCOUNT, "");
	}

	/**
	 * 设置用户密码
	 * 
	 * @param UserZaina
	 */
	public void setSettingUserPassWord(String passWord) {
		editor.putString(SHARED_KEY_SETTING_USER_PASSWORD, passWord);
		editor.commit();
	}

	/**
	 * 获取用户密码
	 */
	public String getSettingUserPassWord() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_PASSWORD, "");
	}

	/**
	 * 设置用户事务ID
	 * 
	 * @param token
	 */
	public void setSettingUserTransId(String transId) {
		editor.putString(SHARED_KEY_SETTING_USER_TRANSID, transId);
		editor.commit();
	}

	/**
	 * 获取用户事务ID
	 */
	public String getSettingUserTransId() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_TRANSID, "");
	}

	/**
	 * 设置用户手机唯一标识
	 * 
	 * @param token
	 */
	public void setSettingUserAppAgent(String appAgent) {
		editor.putString(SHARED_KEY_SETTING_USER_APPAGENT, appAgent);
		editor.commit();
	}

	/**
	 * 获取用户手机唯一标识
	 */
	public String getSettingUserAppAgent() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_APPAGENT, "");
	}

	/**
	 * 设置用户手机唯一标识
	 * 
	 * @param token
	 */
	public void setSettingUserOSVer(String OSVer) {
		editor.putString(SHARED_KEY_SETTING_USER_OSVER, OSVer);
		editor.commit();
	}

	/**
	 * 获取用户手机唯一标识
	 */
	public String getSettingUserOSVer() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_OSVER, "");
	}

	/**
	 * 设置用户工作地址
	 * 
	 * @param companyAddr
	 */
	public void setSettingUserCompanyAddr(String companyAddr) {
		editor.putString(SHARED_KEY_SETTING_USER_COMPANYADDR, companyAddr);
		editor.commit();
	}

	/**
	 * 获取用户工作地址
	 */
	public String getSettingUserCompanyAddr() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_COMPANYADDR, "");
	}

	/**
	 * 设置用户工作地址经度
	 * 
	 * @param companyLongitude
	 */
	public void setSettingUserCompanyLongitude(String companyLongitude) {
		editor.putString(SHARED_KEY_SETTING_USER_COMPANYLONGITUDE, companyLongitude);
		editor.commit();
	}

	/**
	 * 获取用户工作地址经度
	 */
	public String getSettingUserCompanyLongitude() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_COMPANYLONGITUDE, "");
	}

	/**
	 * 设置用户工作地纬度
	 * 
	 * @param companyLatitude
	 */
	public void setSettingUserCompanyLatitude(String companyLatitude) {
		editor.putString(SHARED_KEY_SETTING_USER_COMPANYLATITUDE, companyLatitude);
		editor.commit();
	}

	/**
	 * 获取用户工作地址维度
	 */
	public String getSettingUserCompanyLatitude() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_COMPANYLATITUDE, "");
	}

	/**
	 * 设置用户家庭住址
	 * 
	 * @param homeAddr
	 */
	public void setSettingUserHomeAddr(String homeAddr) {
		editor.putString(SHARED_KEY_SETTING_USER_HOMEADDR, homeAddr);
		editor.commit();
	}

	/**
	 * 获取用户家庭住址
	 */
	public String getSettingUserHomeAddr() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_HOMEADDR, "");
	}

	/**
	 * 设置用户家庭住址地址经度
	 * 
	 * @param homeLongitude
	 */
	public void setSettingUserHomeLongitude(String homeLongitude) {
		editor.putString(SHARED_KEY_SETTING_USER_HOMELONGITUDE, homeLongitude);
		editor.commit();
	}

	/**
	 * 获取用户家庭住址地址经度
	 */
	public String getSettingUserHomeLongitude() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_HOMELONGITUDE, "");
	}

	/**
	 * 设置用户家庭住址地纬度
	 * 
	 * @param homeLatitude
	 */
	public void setSettingUserHomeLatitude(String homeLatitude) {
		editor.putString(SHARED_KEY_SETTING_USER_HOMELATITUDE, homeLatitude);
		editor.commit();
	}

	/**
	 * 获取用户家庭住址地址维度
	 */
	public String getSettingUserHomeLatitude() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_HOMELATITUDE, "");
	}

	/**
	 * 设置用户支付宝账号
	 * 
	 * @param alipayAcountName
	 */
	public void setSettingUserAlipayAcountName(String alipayAcountName) {
		editor.putString(SHARED_KEY_SETTING_USER_ALIPAYACOUNTNAME, alipayAcountName);
		editor.commit();
	}

	/**
	 * 获取用户支付宝账号
	 */
	public String getSettingUserAlipayAcountName() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_ALIPAYACOUNTNAME, "");
	}

	/**
	 * 设置用户支付宝账号ID
	 * 
	 * @param alipayAcountId
	 */
	public void setSettingUserAlipayAcountId(String alipayAcountId) {
		editor.putString(SHARED_KEY_SETTING_USER_ALIPAYACOUNTID, alipayAcountId);
		editor.commit();
	}

	/**
	 * 获取用户支付宝账号ID
	 */
	public String getSettingUserAlipayAcountId() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_ALIPAYACOUNTID, "");
	}

	/**
	 *******************
	 * 设置未设置看房时间的计划数量
	 * 
	 * @param unPlanNum
	 *******************
	 */
	public void setSettingUserUnappointplancount(String unPlanNum) {
		editor.putString(SHARED_KEY_SETTING_USER_UNAPPOINTPLANCOUNT, unPlanNum);
		editor.commit();
	}
	public void clearUserUnappointplancount(){
		editor.remove(SHARED_KEY_SETTING_USER_UNAPPOINTPLANCOUNT);
		editor.commit();
	}
	public void addUnappointplancount() {
		String num = getSettingUserUnappointplancount();
		if (ValidatorUtil.isValidString(num)) {
			try {
				int unSettingNum = Integer.parseInt(num);
				num = String.valueOf(++unSettingNum);
			} catch (Exception e) {
				num = "1";
			}
		} else
			num = "1";
		setSettingUserUnappointplancount(num);
	}

	public void minusUnappointplancount() {
		String num = getSettingUserUnappointplancount();
		if (ValidatorUtil.isValidString(num)) {
			try {
				int unSettingNum = Integer.parseInt(num);
				unSettingNum -= 1;
				if (unSettingNum > 0)
					num = String.valueOf(unSettingNum);
				else
					num = "";
			} catch (Exception e) {
				num = "";
			}
		} else
			num = "";
		setSettingUserUnappointplancount(num);
	}

	/**
	 *******************
	 * 获取未设置看房时间的计划数量
	 * 
	 * @return
	 *******************
	 */
	public String getSettingUserUnappointplancount() {
		return mSharedPreferences.getString(SHARED_KEY_SETTING_USER_UNAPPOINTPLANCOUNT, "");
	}

	/**
	 *
	 * @param
	 */
	public void setIsFirstJihuaIsData(String isFirst) {
		editor.putString(IS_FIRST_JIHUA_IS_DATA, isFirst);
		editor.commit();
	}

	/**
	 * 第一次显示看房计划页
	 */
	public String getIsFirstJihuaIsData() {
		return mSharedPreferences.getString(IS_FIRST_JIHUA_IS_DATA, "0");
	}

	/**
	 *
	 * @param
	 */
	public void setIsFirstLouPanIsData(String isFirst) {
		editor.putString(IS_FIRST_LOUPAN_IS_DATA, isFirst);
		editor.commit();
	}

	/**
	 * 
	 */
	public String getIsFirstLouPanIsData() {
		return mSharedPreferences.getString(IS_FIRST_LOUPAN_IS_DATA, "0");
	}

	/**
	 *
	 * @param
	 */
	public void setIsFirstZhaoFangLouPanIsData(String isFirst) {
		editor.putString(IS_FIRST_ZHAOFANG_LOUPAN_IS_DATA, isFirst);
		editor.commit();
	}

	/**
	 * 
	 */
	public String getIsFirstZhaoFangLouPanIsData() {
		return mSharedPreferences.getString(IS_FIRST_ZHAOFANG_LOUPAN_IS_DATA, "0");
	}
	/**
	 *开关是否打开
	 * @param
	 */
	public void setIsFirstShowRedBag(boolean isFirst) {
		editor.putBoolean(IS_FIRST_SHOW_RED_BAG, isFirst);
		editor.commit();
	}

	/**
	 * 
	 */
	public boolean getIsFirstShowRedBag() {
		return mSharedPreferences.getBoolean(IS_FIRST_SHOW_RED_BAG, false);
	}
	
	
	/**
	 *是否第一次展示过了
	 * @param
	 */
	public void setIsNotFirstShowed(boolean isFirst) {
		editor.putBoolean(IS_FIRST_NOT_SHOW_RED_BAG, isFirst);
		editor.commit();
	}

	/**
	 * 
	 */
	public boolean getIsNotFirstShowed() {
		return mSharedPreferences.getBoolean(IS_FIRST_NOT_SHOW_RED_BAG, false);
	}
}
