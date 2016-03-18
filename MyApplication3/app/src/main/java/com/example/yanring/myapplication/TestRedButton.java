package com.example.yanring.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import org.w3c.dom.Attr;

/**
 * Created by Yanring on 2016/2/3.
 * 写需求
 *
 *
 */
public class TestRedButton extends View implements View.OnClickListener{

    private Paint mPaint;
    private int mNumber=20;
    private Rect mRect;
    private  int mBackgroundColor;
    private  int mTextSize;
    public TestRedButton(Context context) {
        this(context,null);
    }



    public TestRedButton(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TestRedButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context,AttributeSet attrs) {//init the view
        mPaint = new Paint();
        mRect = new Rect();
        this.setOnClickListener(this);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.TestRedButton);//第一步获取自定义属性的所有属性

        //获取backgroundColor,TextSize

        mBackgroundColor=typedArray.getColor(R.styleable.TestRedButton_backgroundColor,Color.RED);//默认为红色
        mTextSize = (int) typedArray.getDimension(R.styleable.TestRedButton_TextSize,30);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(mBackgroundColor);
        canvas.drawCircle(getWidth()/2,getWidth()/2,getWidth()/2,mPaint);
        //白色的数字
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(mTextSize);


        String text = String.valueOf(mNumber);
        mPaint.getTextBounds(text,0,text.length(),mRect);
        //mRect获得text的边距



        int textWidth = mRect.width();
        int textHeight = mRect.height();
        canvas.drawText(text,getWidth()/2-textWidth/2,getHeight()/2+textHeight/2,mPaint);
    }

    @Override
    public void onClick(View view) {
        mNumber--;
        invalidate();
    }
}
