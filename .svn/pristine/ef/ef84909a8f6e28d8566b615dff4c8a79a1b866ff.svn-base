package com.xinwei.kanfangshenqi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.xinwei.kanfangshenqi.R;

public class DrawRadioButton extends RadioButton {

    private int mDrawableWidth;// xml文件中设置的大小
    private int mDrawableHeight;// xml文件中设置的大小

    public DrawRadioButton(Context context) {
        this(context, null, 0);
    }

    public DrawRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom = null;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.DrawRadioButton);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if(attr== R.styleable.DrawRadioButton_drawableWidth){
            	mDrawableWidth = a.getDimensionPixelSize(R.styleable.DrawRadioButton_drawableWidth, 30);
            }else if(attr== R.styleable.DrawRadioButton_drawableHeight){
            	mDrawableHeight = a.getDimensionPixelSize(R.styleable.DrawRadioButton_drawableHeight, 30);
            }else if(attr== R.styleable.DrawRadioButton_drawableTop){
            	drawableTop = a.getDrawable(attr);
            }else if(attr== R.styleable.DrawRadioButton_drawableBottom){
            	drawableRight = a.getDrawable(attr);
            }else if(attr== R.styleable.DrawRadioButton_drawableRight){
            	drawableBottom = a.getDrawable(attr);
            }else if(attr== R.styleable.DrawRadioButton_drawableLeft){
            	 drawableLeft = a.getDrawable(attr);
            }
        }
        a.recycle();
         
        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
 
    }
 
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left,
            Drawable top, Drawable right, Drawable bottom) {
 
        if (left != null) {
            left.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        if (right != null) {
            right.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        if (top != null) {
            top.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableWidth, mDrawableHeight);
        }
        setCompoundDrawables(left, top, right, bottom);
    }
 
}