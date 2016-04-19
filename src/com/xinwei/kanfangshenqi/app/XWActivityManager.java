package com.xinwei.kanfangshenqi.app;

import java.util.Stack;

import android.app.Activity;

public class XWActivityManager {
	public static XWActivityManager instance;

	private Stack<Activity> activityStack;// activity栈

	private XWActivityManager() {
	}

	public static XWActivityManager getInstance() {
		if (instance == null) {
			instance = new XWActivityManager();
		}
		return instance;
	}

	// 把一个activity压入栈中
	public void pushOneActivity(Activity actvity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(actvity);
	}

	// 获取栈顶的activity，先进后出原则
	public Activity getLastActivity() {
		return activityStack.lastElement();
	}

	// 移除一个activity
	public void popOneActivity(Activity activity) {
		if (activityStack != null && activityStack.size() > 0) {
			if (activity != null) {
//				activity.finish();
				activityStack.remove(activity);
				activity = null;
			}

		}
	}

	// 退出所有activity
	public void finishAllActivity() {
		if (activityStack != null) {
			while (activityStack.size() > 0) {
				Activity activity = getLastActivity();
				if (activity == null)
					break;
				popOneActivity(activity);
			}
		}
	}

	public void popAllActivityExceptOne(Object obj) {
		if (activityStack != null) {
			for (int i = 0, isize = activityStack.size(); i < isize; i++) {
				Activity act = activityStack.get(i);
				if (act == null) {
					continue;
				}
				if (act.toString().equals(obj.toString()))
					continue;
				act.finish();
			}
		}
	}
}
