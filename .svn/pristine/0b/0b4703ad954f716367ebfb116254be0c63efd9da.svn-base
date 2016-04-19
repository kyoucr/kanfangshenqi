package com.xinwei.kanfangshenqi.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.model.ReplyComment;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.Utils;
import com.xinwei.kanfangshenqi.view.CircleImageViewWithBorder;

public class CommentAdp extends BaseAbsAdp {
	private List<ReplyComment> list;
	private Context context;

	public CommentAdp(Context context, List<ReplyComment> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public List<?> onGetListData() {
		return list;
	}

	ViewHolder viewHolder;
	@Override
	public View onGetView(final int position, View view) {
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.adp_comment,
					null);
			viewHolder = new ViewHolder();
			viewHolder.imgHead = (CircleImageViewWithBorder) view
					.findViewById(R.id.imgHead);
			viewHolder.txtDesc = (TextView) view.findViewById(R.id.txtDesc);
			view.setTag(viewHolder);
		} else
			viewHolder = (ViewHolder) view.getTag();
		ReplyComment replayComment = list.get(position);
		if (replayComment != null) {
			ImageLoaderUtil.getInstance().bindHeadImg(viewHolder.imgHead, replayComment.getReplyMemberHeadPortrait());
			String name = replayComment.getReplyMemberName() != null ? replayComment
					.getReplyMemberName() : "";
			String replyToName = replayComment.getReplyParentMemberName() != null ? replayComment
					.getReplyParentMemberName() : null;
			String desc = replayComment.getReplyContent() != null ? replayComment
					.getReplyContent() : "";
			if (replyToName != null){
				String content = name
						+ context.getString(R.string.txt_reply_only)
						+ replyToName + "：" + desc;
				SpannableStringBuilder style = new SpannableStringBuilder(content);
				style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.txt_blue)), 0, name.length(),
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.txt_blue)), (name
						+ context.getString(R.string.txt_reply_only)).length(), (name
								+ context.getString(R.string.txt_reply_only)+ replyToName + "：").length(),
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				viewHolder.txtDesc.setText(style);
//				TextViewWriterUtil.writeValue(viewHolder.txtDesc, content);
			}else{
				String content = name + "："
						+ desc;
				SpannableStringBuilder style = new SpannableStringBuilder(content);
				style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.txt_blue)), 0, (name + "：").length(),
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				viewHolder.txtDesc.setText(style);
//				TextViewWriterUtil.writeValue(viewHolder.txtDesc, name + "："
//						+ desc);
			}
		}
		return view;
	}

	class ViewHolder {
		CircleImageViewWithBorder imgHead;
		TextView txtDesc;
	}
	
}
