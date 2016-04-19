package com.xinwei.kanfangshenqi.calendar.doim;

import java.util.List;

import com.xinwei.kanfangshenqi.view.CalendarView;
import com.xinwei.kanfangshenqi.view.CalendarView.CallBack;

import android.content.Context;

/**
 * CalendarView的辅助类
 * @author jiangzl
 *
 */
public class CalendarViewBuilder {
		private CalendarView[] calendarViews;
		private List<String> datalist;
		/**
		 * 生产多个CalendarView
		 * @param context
		 * @param count
		 * @param style
		 * @param callBack
		 * @return
		 */
		public  CalendarView[] createMassCalendarViews(Context context,int count,int style,CallBack callBack,List<String> datalist){
			calendarViews = new CalendarView[count];
			for(int i = 0; i < count;i++){
				calendarViews[i] = new CalendarView(context, style,callBack,datalist);
			}
			return calendarViews;
		}
		
		public  CalendarView[] createMassCalendarViews(Context context,int count,CallBack callBack,List<String> datalist){
			
			return createMassCalendarViews(context, count, CalendarView.MONTH_STYLE,callBack,datalist);
		}
		/**
		 * 切换CandlendarView的样式
		 * @param style
		 */
		public void swtichCalendarViewsStyle(int style){
			if(calendarViews != null)
			for(int i = 0 ;i < calendarViews.length;i++){
				calendarViews[i].switchStyle(style);
			}
		}
		/**
		 * CandlendarView回到当前日期
		 */
		
		public void backTodayCalendarViews(){
			if(calendarViews != null)
			for(int i = 0 ;i < calendarViews.length;i++){
				calendarViews[i].backToday();
			}
		}

		public List<String> getDatalist() {
			return datalist;
		}

		public void setDatalist(List<String> datalist) {
			this.datalist = datalist;
		}
		public void upData(){
			if(calendarViews!=null)
			for(int i = 0 ;i < calendarViews.length;i++){
				calendarViews[i].update();
			}
		}
		
}
