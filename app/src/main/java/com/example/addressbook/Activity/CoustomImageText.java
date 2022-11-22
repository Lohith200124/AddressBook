package com.example.addressbook.Activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.icu.util.Measure;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.addressbook.R;

public class CoustomImageText extends androidx.appcompat.widget.AppCompatImageView {


Paint paint,borderPaint;
RectF bounds;

    public CoustomImageText(@NonNull Context context) {
        super(context);
    }
/*
    public CoustomImageText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CoustomImageText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void init(AttributeSet attr){

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //borderPaint.setColor(borderColor);
        paint.setStyle(Paint.Style.FILL);
        if(attr == null)
            return;
        TypedArray ta = getContext().obtainStyledAttributes(attr, R.styleable.CoustomView);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        bounds.set(0,0,getMeasuredWidth(),getMeasuredHeight());
    }
   */
/* @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Try for a width based on our minimum
        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        // Whatever the width ends up being, ask for a height that would let the pie
        // get as big as it can
        int minh = MeasureSpec.getSize(w) - (int)mTextWidth + getPaddingBottom() + getPaddingTop();
        int h = resolveSizeAndState(minh, heightMeasureSpec, 0);

        setMeasuredDimension(w, h);
    }*//*

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawOval(bounds,paint);
    }
*/

}
