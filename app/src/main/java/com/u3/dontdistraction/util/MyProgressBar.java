package com.u3.dontdistraction.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by ${U3} on 2016/3/17.
 */
public class MyProgressBar extends View {
    float progress = 360;
    float arcStar = 270;
    float arcEnd = 360;
    double rotateStep = 0.2;
    Bitmap bitmap;
    int totalTime;
    Bitmap image;
    Drawable drawable;
    int boundWidth = 5;
    private int progressWidth = 30;
    private boolean isRotate = false;
    private int progressColor = Color.GREEN;
    private int progressBackColor = Color.GREEN;
    private float rotateDegree = 0;


    public MyProgressBar(Context context) {
        super(context);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private float radiu;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public void setRadiu(float radiu) {
        this.radiu = radiu;
        invalidate();
    }

    public void start(long time) {
        bitmap = null;

        time *= 60000;
        final float step = (float) 360 / (time / 30);
        CountDownTimer mTimer = new CountDownTimer(time, 30) {
            public void onTick(long millisUntilFinished) {
                progress -= step;
                rotateDegree -= rotateStep;
                invalidate();
            }

            @Override
            public void onFinish() {
                end(step);
            }

        };
        mTimer.start();
    }

    private void end(float step) {
        progress -= step;
        invalidate();
        progress = 0;
        rotateDegree = 0;
        invalidate();
    }

    public void setBoundWidth(int width) {
        boundWidth = width;
    }

    public void setProgressWidth(int width) {
        progressWidth = width;
    }

    public void setProgressColor(int color) {
        progressColor = color;
    }

    public void setProgressBackColor(int color) {
        progressBackColor = color;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        invalidate();
    }
    public void setIsRote(boolean rotate)
    {
        this.isRotate = rotate;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();

        //get the view's width and height and decide the radiu
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        radiu = Math.min(width, height) / 2 - boundWidth - progressWidth;

        //setup the paint
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(boundWidth);
        paint.setColor(Color.BLACK);

        //draw the inner circle
        int centerX = paddingLeft + getWidth()/2;
        int centerY = paddingTop + getHeight() / 2;
        canvas.drawCircle(centerX,centerY, radiu, paint);
        

        float  totalRadiu = radiu +boundWidth  +progressWidth/2;

        //draw the circlr pic
        if (drawable != null&&bitmap == null) {
            image = ((BitmapDrawable) drawable).getBitmap();

            bitmap = Bitmap.createBitmap((int) (2 * totalRadiu), (int) (2 * totalRadiu), Bitmap.Config.ARGB_8888);
            Canvas bitmapCanvas = new Canvas(bitmap);

            Paint bitmapPaint = new Paint();
            bitmapPaint.setAntiAlias(true);

            bitmapCanvas.drawCircle(totalRadiu, totalRadiu, radiu, bitmapPaint);

            bitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            bitmapCanvas.drawBitmap(image,null,new RectF(0,0,2*totalRadiu,2*totalRadiu) , bitmapPaint);


        }
        Rect rect = new Rect((int)(centerX -totalRadiu),(int)(centerY-totalRadiu),(int)(centerX+totalRadiu),(int)(centerY+ totalRadiu));
        canvas.save();
        if(isRotate)
        canvas.rotate(rotateDegree,centerX,centerY);
        canvas.drawBitmap(bitmap,null ,rect, paint);

        canvas.restore();
        //set paint for arc
        paint.setStrokeWidth(progressWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);

        //prepare for draw arc
        RectF oval = new RectF();
        oval.left = centerX -totalRadiu ;
        oval.top =centerY- totalRadiu ;
        oval.right =  centerX + totalRadiu;
        oval.bottom = centerY+ totalRadiu;
        paint.setColor(progressBackColor);

        //draw background arc
        canvas.drawArc(oval, arcStar, arcEnd, false, paint);

        //draw progress arc
        paint.setColor(progressColor);
        canvas.drawArc(oval, arcStar, progress, false, paint);
    }

}
