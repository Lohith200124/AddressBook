package com.example.addressbook.Activity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.addressbook.R;

public class CoustomView extends View{
    private  static  final  int SQUARESIZE = 300;
    Paint paint;
    Rect rect;
    private  int mSquareColor;
    private  int mSquaresize;
    public CoustomView(Context context) {
        super(context);
    }

    public CoustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);init(attrs);
    }

    public CoustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);init(attrs);
    }

    public CoustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public void init(AttributeSet attr){
       rect = new Rect();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        if(attr == null)
            return;
        TypedArray ta = getContext().obtainStyledAttributes(attr, R.styleable.CoustomView);
        mSquareColor = ta.getColor(R.styleable.CoustomView_square_color,Color.BLUE);
        mSquaresize = ta.getDimensionPixelSize(R.styleable.CoustomView_square_size,SQUARESIZE);
        paint.setColor(  mSquareColor);
        ta.recycle();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        rect.top =10;
        rect.left = 10;
        rect.bottom=30+SQUARESIZE;
        rect.right =30+SQUARESIZE;

        canvas.drawRect(rect,paint);
        /*canvas.translate(100,200);
        canvas.drawOval(10,10,50+SQUARESIZE,30+SQUARESIZE,new Paint(Color.GREEN));*/
        // canvas.drawColor(Color.RED);
    }
   /* TextView textView;
    public CoustomView(Context context) {
        super(context);
        init(null);
    }

    public CoustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CoustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CoustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public void init(@Nullable AttributeSet attr){

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect rect = new Rect(10,10,30,30);
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawRect(rect,paint);
        canvas.drawColor(Color.RED);
    }*/
}
