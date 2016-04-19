package com.xinwei.kanfangshenqi.adapter;

import java.util.ArrayList;
import java.util.List;

import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.activity.AskDetailNewActivity;
import com.xinwei.kanfangshenqi.model.Ask;
import com.xinwei.kanfangshenqi.model.ReplyComment;
import com.xinwei.kanfangshenqi.util.ImageLoaderUtil;
import com.xinwei.kanfangshenqi.util.TextViewWriterUtil;
import com.xinwei.kanfangshenqi.util.ValidatorUtil;
import com.xinwei.kanfangshenqi.view.CircleImageViewWithBorder;
import com.xinwei.kanfangshenqi.view.GridViewInScroll;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CommentNewAdp extends BaseAbsAdp {
	public List<ReplyComment> replyList = new ArrayList<ReplyComment>();
	private Context context;
	private Ask askResponse;
	public CommentNewAdp(Context context, Ask askResponse) {
		this.context = context;
		this.replyList.add(null);
		if(askResponse.getReplayComments()!=null){
			for(int i = 0;i<askResponse.getReplayComments().size();i++){
				this.replyList.add(askResponse.getReplayComments().get(i));
			}
		}
		this.askResponse = askResponse;
	}

	@Override
	public List<?> onGetListData() {
		return replyList;
	}

	private final int TYPE_1 = 0;
	private final int TYPE_2 = 1;
	@Override 
    public int getViewTypeCount() { 
		return 2;
	}
	@Override
	public int getItemViewType(int position) {
		if(position == 0){
			return TYPE_1;
		}else{
			return TYPE_2;
		}
	}
	ViewHolder viewHolder;
	ViewHolder viewHolder2;
	@Override
	public View onGetView(final int position, View view) {
		switch(getItemViewType(position)){
		case TYPE_1:
			view = loadFirstView(view);
			break;
		case TYPE_2:
			view = loadOtherView(position,view);
			break;
		}
		return view;
	}
	public ImgAdp imgAdp;
	private View loadFirstView(View view){
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.adp_ask_detail_first_item,
					null);
			viewHolder = new ViewHolder();
			viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
			viewHolder.txtContent = (TextView) view.findViewById(R.id.txtContent);
			viewHolder.txtTime = (TextView) view.findViewById(R.id.txtTime);
			viewHolder.txtNum = (TextView) view.findViewById(R.id.txtNum);
			viewHolder.imgArrow = (ImageView) view.findViewById(R.id.imgArrow);
			viewHolder.lltCommentParent = (LinearLayout) view.findViewById(R.id.lltCommentParent);
			viewHolder.imgHeadFirst = (CircleImageViewWithBorder) view.findViewById(R.id.imgHead);
			viewHolder.gridViewImg = (GridViewInScroll) view.findViewById(R.id.gridViewImg);
			view.setTag(viewHolder);
		} else{
			viewHolder = (ViewHolder) view.getTag();
		}
		TextViewWriterUtil.writeValue(viewHolder.txtName, askResponse.getNickName());

		if(ValidatorUtil.isValidString(askResponse.getContent())){
			TextViewWriterUtil.writeValue(viewHolder.txtContent,askResponse.getContent());
			viewHolder.txtContent.setVisibility(View.VISIBLE);
		}else
			viewHolder.txtContent.setVisibility(View.GONE);
//		TextViewWriterUtil.writeValue(viewHolder.txtContent, askResponse.getContent());
		TextViewWriterUtil.writeValue(viewHolder.txtTime, askResponse.getCreateTime());
		TextViewWriterUtil.writeValue(viewHolder.txtNum, String.valueOf(askResponse.getReplayDataCount()));
		ImageLoaderUtil.getInstance().bindHeadImg(viewHolder.imgHeadFirst, askResponse.getHeadPortrait());
		if(ValidatorUtil.isValidList(askResponse.getReplayComments())){
			viewHolder.imgArrow.setVisibility(View.VISIBLE);
		}else{
			viewHolder.imgArrow.setVisibility(View.GONE);
		}
		viewHolder.lltCommentParent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					AskDetailNewActivity activity = (AskDetailNewActivity)context;
					activity.setReply2Owner();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		imgAdp = new ImgAdp(context, askResponse.getCommentImgs());
		viewHolder.gridViewImg.setAdapter(imgAdp);
		viewHolder.gridViewImg.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				try {
					AskDetailNewActivity activity = (AskDetailNewActivity)context;
					activity.showPop(position);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return view;
	}
	private View loadOtherView(final int position, View view){
		if (view == null) {
			view = LayoutInflater.from(context).inflate(R.layout.adp_comment,
					null);
			viewHolder2 = new ViewHolder();
			viewHolder2.imgHead = (CircleImageViewWithBorder) view
					.findViewById(R.id.imgHead);
			viewHolder2.txtDesc = (TextView) view.findViewById(R.id.txtDesc);
			view.setTag(viewHolder2);
		} else
			viewHolder2 = (ViewHolder) view.getTag();
		ReplyComment replayComment = replyList.get(position);
		if (replayComment != null) {
			ImageLoaderUtil.getInstance().bindHeadImg(viewHolder2.imgHead, replayComment.getReplyMemberHeadPortrait());
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
				if(viewHolder2.txtDesc!=null)
					viewHolder2.txtDesc.setText(style);
//				TextViewWriterUtil.writeValue(viewHolder.txtDesc, content);
			}else{
				String content = name + "："
						+ desc;
				SpannableStringBuilder style = new SpannableStringBuilder(content);
				style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.txt_blue)), 0, (name + "：").length(),
						Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
				if(viewHolder2.txtDesc!=null)
					viewHolder2.txtDesc.setText(style);
//				TextViewWriterUtil.writeValue(viewHolder.txtDesc, name + "："
//						+ desc);
			}
		}
		return view;
	}
	class ViewHolder {
		//Holder
		CircleImageViewWithBorder imgHead;
		TextView txtDesc;
		
		
		//Holder2
		private TextView txtName;
		private TextView txtContent;
		private TextView txtTime;
		private TextView txtNum;
		private LinearLayout lltCommentParent;
		private CircleImageViewWithBorder imgHeadFirst;
		private ImageView imgArrow;
		private RelativeLayout rltComment;
		private GridViewInScroll gridViewImg;
	}
	
}
