package com.example.administrator.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class DrawView extends View {
    public DrawView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
//        paint.setShadowLayer(2, 3, 3, Color.rgb(90, 90, 90));
//        canvas.drawRect(40, 40, 200, 100, paint);//前四个参数分别是左上点的坐标和右下点的坐标
        paint.setAntiAlias(true);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
        int left = 50, top = 20, right = 200, bottom = 100;
        RectF rectF = new RectF(left, top, right,  bottom);
        canvas.drawArc(rectF, 0, 60, true, paint);

        paint.setColor(Color.RED);
        top = bottom + 10;
        bottom = top + 50;
        RectF rectF1 = new RectF(left, top, right, bottom);
        canvas.drawArc(rectF1, 0, 90, false, paint);

        paint.setColor(Color.BLACK);
        top = bottom + 10;
        bottom = top + 50;
        canvas.drawCircle(top, bottom, 15, paint);
        top = bottom + 10;
        bottom = top + 50;
        paint.setColor(Color.BLUE);
        canvas.drawLine(left, top, right, bottom, paint);

        top = bottom + 10;
        bottom = top + 100;
        paint.setColor(Color.LTGRAY);
        canvas.drawLines(new float[]{left, top, left,top+50,left,top+100,left+50,top,left+100,top,left+50,top+50,left+100,top+100,left+50,top+100}, paint);

        top = bottom + 10;
        bottom = top + 50;
        paint.setColor(Color.GRAY);
        RectF rectF2 = new RectF(left, top, right, bottom);
        canvas.drawOval(rectF2, paint);

        top = bottom + 10;
        bottom = top + 20;
        paint.setColor(Color.GREEN);
        canvas.drawPoint(left,top, paint);

        top = bottom + 10;
        bottom = top + 100;
        left += 200;
        paint.setColor(Color.BLACK);
        canvas.drawPoints(new float[]{left, top, left,top+50,left,top+100,left+50,top,left+100,top,left+50,top+50,left+100,top+100,left+50,top+100}, paint);

        top = bottom + 10;
        bottom = top + 50;
        left -= 200;
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(left, top, right, bottom, paint);

        top = bottom + 10;
        bottom = top + 50;
        paint.setColor(Color.LTGRAY);
        RectF rectF3 = new RectF(left, top, right, bottom);
        canvas.drawRoundRect(rectF3, 6, 6, paint);

        super.onDraw(canvas);
    }
}
