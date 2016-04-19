package com.xinwei.kanfangshenqi.common;


public class Const {
	/**
	 * default encoding
	 */
	public static final String DEFAULT_CHARSET = "utf-8";
	public static final String MAN = "1";
	public static final String WOMAN = "0";
	public static final double SCALE = 0.8;
	public static final String USER_INFO = "user_info";
	public static final String IS_FIRST_USE = "is_first_use";
	public static final String IS_FIRST_USE_INVENTORY = "is_first_use_inventory";
	public static final String IS_FIRST_USE_MY_FRAGMENT = "is_first_use_my_fragment";
	public static final String ACTION_UPLOAD_ERROR = "com.xinwei.kanfangshenqi.CRASH_ERROR";
	/**
	 * app server address
	 */
//	public static final String SERVER_URL = "http://192.168.0.137:8082/kfsqApp";
//	public static final String SERVER_URL = "http://192.168.0.89:8080/KFSQ";
	public static final String SERVER_URL = "http://testapp.kfsq.cn/kfsqApp";
	//生产环境
//	public static final String SERVER_URL = "http://app.kfsq.cn/kfsqApp";
	public static final String PROTOCOL_URL="http://www.kfsq.cn/app/protocol.html";
	/**
	 * 登录
	 */
	public static final String URL_LOGIN = SERVER_URL+"/app/v1/login";
	public static final String URL_IS_SHOW_RED_BAG = SERVER_URL+"/app/switch/cityId";
	public static final String PARAM_ACCOUNT = "account";
	public static final String PARAM_PWD = "password";
	
	public static final String PARAM_TRANSID = "transId";
	public static final String PARAM_APPAGENT = "appAgent";
	public static final String PARAM_OSVER = "OSVer";
	public static final String PARAM_TOKEN = "token";
	
	//登录失效标示
	public static final String FLAG_UNLOGIN = "un_login";
	/**
	 * user token
	 */
	public static final String USERTOKEN = "usertoken";
	/**
	 * tempToken
	 */
	public static final String TEMPTOKEN = "TEMPTOKEN";
	/**
	 * 百科
	 */
	public static final String URL_ENCYCLOPEDIACLASSES = SERVER_URL+"/app/v1/encyclopediaClasses";
	public static final String PARAM_PARENTID = "parentId";
	/**
	 * 百科详情
	 */
	public static final String URL_ENCYCLOPEDIA_INFO = SERVER_URL+"/app/v1/encyclopediaInfo/";
	/**
	 * 传递到WebActivity的Bundle参数key
	 */
	public static final String WEB_URL_KEY = "web_url_key";
	public static final String WEB_TITLE_KEY = "web_title_key";
	public static final String WEB_LEFT_TITLE_KEY = "web_left_title_key";
	
	/**
	 * 精彩活动
	 */
	public static final String URL_EXERCISES = SERVER_URL+"/app/v1/exercises/curPage/";
	/**
	 * 个人成就
	 */
	public static final String URL_ACHIEVEMENT =  SERVER_URL+"/app/v1/achievement";
	/**
	 * 现金红包
	 */
	public static final String URL_ENVELOPERECEIVE =  SERVER_URL+"/app/v1/envelopeReceive/curPage/";
	/**
	 * 提现记录
	 */
	public static final String URL_CASH_RECORD =  SERVER_URL+"/app/v1/cashApplications/curPage/";
	/**
	 * 收入记录
	 */
	public static final String URL_INCOME_RECORD =  SERVER_URL+"/app/v1/envelopeReceives/curPage/";
	/**
	 * 提现
	 */
	public static final String URL_CASH = SERVER_URL+"/app/v1/cash";
	public static final String PARAM_PRICE = "price";
	public static final String PARAM_AMMOUNT = "ammount";
	public static final String PARAM_ISDEFAULT = "isDefault";
	public static final String PARAM_ACCOUNTTYPE = "accountType";
	public static final String PARAM_ACCOUNTNAME = "accountName";
	public static final String VALUE_BANK_CARD = "1";//银行卡
	public static final String VALUE_ZHIFUBAO = "0";//支付宝
	public static final String YES = "1";
	public static final String NO = "0";
	/**
	 * 交易详情
	 */
	public static final String URL_TRADE_DETAIL = SERVER_URL+"/app/v1/cashDetail/";
	//重新申请提现
	public static final String URL_RESUBMIT_CASH = SERVER_URL+"/app/v1/reapplyCash/";
	/**
	 * 发表有礼
	 */
	public static final String URL_UNPUBLISH = SERVER_URL+"/app/v1/plan/status/curPage/";
	public static final String URL_PUBLISHED = SERVER_URL+"/app/v1/buildingComment/curPage/";
	/**
	 * 看房工具
	 */
	public static final String URL_LOAN_CALCULATOR = SERVER_URL+"/app/v1/dk";
	public static final String URL_ACCUMULATION_FUND_CALCULATOR = SERVER_URL+"/app/v1/gjj";
	public static final String URL_BUY_HOUSE_ABILITY = SERVER_URL+"/app/v1/pg";
	public static final String URL_ADVANCE_REPAYMENT_CALCULATOR = SERVER_URL+"/app/v1/hk";
	public static final String URL_TAXATION_CALCULATOR = SERVER_URL+"/app/v1/sf";
	public static final String URL_ACCUMULATION_FUND_LOAN_INTEREST = SERVER_URL+"/app/v1/lilv";
	/**
	 * 通知
	 */
	public static final String URL_NOTIFY = SERVER_URL+"/app/v1/memberPushInfo/curPage/";
	public static final String URL_NOTIFY_DETAIL_INFO = SERVER_URL+"/app/v1/pushInfo/";
	/**
	 * 发现
	 */
	public static final String URL_DISCOVERY = SERVER_URL+"/app/v1/discovery/lastTime/";
	/**
	 * 看房圈
	 */
	public static final String URL_SEE_HOUSE_CIRCLE = SERVER_URL+"/app/v1/houseCircle/";
	public static final String PARAM_LASTTIME = "lastTime";//精确到秒
	public static final String PARAM_TYPE = "type";//0:问问；1：关注；2提醒
	public static final String PARAM_CURPAGE = "CurPage";
	public static final String ASK_TYPE = "0";
	public static final String INTEREST_TYPE = "1";
	public static final String REMIND_TYPE = "2";
	public static final String REMIND_REQUEST_TIME = "remind_request_time";
	/**
	 * 楼盘详情页
	 */
	public static final String PLANS_INFOS_BUILDING_ID = "plans_infos_building_id";
	/**
	 * 取消关注
	 * 
	 */
	public static final String URL_CANCEL_INTEREST = SERVER_URL+"/app/v1/follow/d/";
	
	
	/**
	 * 多条件分页查询楼盘
	 */
	public static final String URL_NAME_OF_HOUSES =SERVER_URL+"/app/v1/buildings" ;
	public static final String URL_NAME_OF_QUYU_V2 =SERVER_URL+"/app/v2/building/city";
	public static final String CITY_NUMBER ="24";
	public static final String PARAM_LONGITUDESPAN = "longitudeSpan";//经度跨度
	public static final String PARAM_LATITUDESPAN = "latitudeSpan";//纬度跨度
	public static final String PARAM_LONGITUDE = "longitude";//经度
	public static final String PARAM_LATITUDE = "latitude";//纬度
	/**
	 * 根据杂谈编号查询杂谈(问问)详情
	 */
	public static final String URL_CHATCOMMENT_DETAIL = SERVER_URL+"/app/v1/chatComment/";//{commentId}/curPage/{curPage}
	public static final String URL_BUILDING_COMMENT_DETAIL = SERVER_URL+"/app/v1/buildingComment/";//{commentId}/curPage/{curPage}
	public static final String PARAM_cURPAGE = "curPage";
	public static final String PARAM_COMMENTID = "commentId";
	/**
	 * 关注详情
	 */
	public static final String URL_INTEREST_DETAIL = SERVER_URL+"/app/v1/buildingComment/";//{buildingId}/curPage/{curPage}
	public static final String PARAM_BUILDING_ID = "buildingId";
	/**
	 * 添加看房圈评论
	 */
	public static final String URL_CHATCOMMENT= SERVER_URL+"/app/v1/chatComment";
	public static final String URL_BUILDING_COMMENT= SERVER_URL+"/app/v1/buildingComment";
	public static final String PARAM_PARENT_COMMENTID = "parentCommentId";//上级评论编号
	public static final String PARAM_REPLYCOMMENTID = "replyCommentId";//二级评论编号
	public static final String PARAM_CONTENT = "content";//评论内容
	public static final String PARAM_COMMENT_PATHS = "commentPaths";//评论图片内容
	/**
	 * 上传文件
	 */
	public static final String URL_UPLOAD = SERVER_URL+"/app/v1/upload/";
	public static final String TYPE_HEADPORTRAIT = "headPortrait";
	public static final String TYPE_COMMENTIMG = "commentImg";

	public static final String PARAM_COMMENTPATHS = "commentPaths";
	public static final String URL_UPLOAD_COMMENTIMG = SERVER_URL+"/app/v2/upload/commentImg";
	//校验密码
	public static final String URL_PWD_VALIDATE = SERVER_URL+"/app/v1/member/cp";
	//更换密码
	public static final String URL_USERINFO_CHANGE = SERVER_URL+"/app/v1/member/m";
	//上传错误日志
	public static final String URL_UPLOAD_ERROR_LOG = SERVER_URL+"/app/v1/upload/errorAndroidC";//参数type=error";
	/**
	 * 楼盘详情页
	 */
	public static final String URL_BUILDING_DETAIL_INFO = SERVER_URL+"/app/v2/building/";//?buildingId={buildingId}&areaId={areaId}&priceId={priceId}&featureId={featureId}

	public static final String PARAM_AREA_ID = "areaId";
	public static final String PARAM_PRICE_ID = "priceId";
	public static final String PARAM_FEATURE_ID = "featureId";
	
	public static final String URL_ADD_PLAN = SERVER_URL+"/app/v1/plan";//添加看房计划
	public static final String URL_ADD_INTEREST = SERVER_URL+"/app/v1/follow";//添加关注
	public static final String URL_BUILDINGS_BY_CONDITION = SERVER_URL+"/app/v1/buildings";//查询楼盘
	public static final String PARAM_ISCOMPREHENSIVE = "isComprehensive";//是否是综合查询
	public static final String PARAM_PAGENUM = "pageNum";//当前页
	public static final String PARAM_ISPAGE = "isPage";//是否分页查
	
	public static final String PARAM_NICKNAME = "nickName";
	public static final String PARAM_ACCOUNTID = "accountId";
	public static final String PARAM_ACCOUNTNUM = "accountNum";
	public static final String PARAM_SEX = "sex";
	public static final String PARAM_COMPANYADDR = "companyAddr";
	public static final String PARAM_COMPANYLONGITUDE = "companyLongitude";
	public static final String PARAM_COMPANYLATITUDE = "companyLatitude";
	public static final String PARAM_HOMEADDR = "homeAddr";
	public static final String PARAM_HOMELONGITUDE = "homeLongitude";
	public static final String PARAM_HOMELATITUDE = "homeLatitude";
	/**
	 * 计划
	 */
	public static final String URL_PLANS = SERVER_URL+"/app/v1/plans";//计划列表
	public static final String URL_FEEDBACK = SERVER_URL+"/app/v1/feedback";//反馈
	public static final String PARAM_CONTENTVALUE = "contentValue";//反馈内容
	
	public static final String URL_PLAN_M = SERVER_URL+"/app/v1/plan/m/";//修改看房计划
	public static final String PARAM_ISVALID = "isValid";
	public static final String PARAM_PLANID = "planId";
	
	public static final String URL_LOGIN_SPEED = SERVER_URL+"/app/v1/login/shortcut";
	public static final String PARAM_CODE = "code";
	
	public static final String URL_SENDVC= SERVER_URL+"/app/v1/member/sendvc";//验证码
	public static final String PARAM_CODETYPE = "codeType";
	public static final String PARAM_PHONE = "phone";
	
	
}
