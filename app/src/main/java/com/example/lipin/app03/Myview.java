package com.example.lipin.app03;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 自訂View
 */

public class Myview extends View {
    private Bitmap ballBmp;
//    private  Context context;//彈性大寫法
    private  MainActivity activity;
    private Resources resources;
    private Paint paint;
    private  int viewW,viewH;
    private  float ballw,ballh,ballx,bally,dx,dy;
    private boolean isinit;
    private Timer timer;
    public Myview(Context context) {
        super(context);
//        setBackgroundColor(Color.GREEN);
        setBackgroundResource(R.drawable.bg);//如果圖太大它會自動縮放到螢幕內
        activity = new MainActivity();
        paint = new Paint();
        paint.setAlpha(127);//讓該物件變透明;
    //已經使用instanceof比對過 context =MainActivity;
        //目的 需要有Resource 物件,只有MainActivity內可以getResource
            //所以將MainActivity的context拿來這裡用讓Myview is a MainActivity
                //目前寫的方法很死,現在這個view只能活在MainActivity這裡面,不能拿給其他context做使用
//            this.context =context;//彈性大的寫法
        activity = (MainActivity)context;
        resources = activity.getResources();
        timer = new Timer();
        //這樣終於拿到球了
        ballBmp = BitmapFactory.decodeResource(resources,R.drawable.pokeball);
        timer.schedule(new BallTack(),1000,30);//球的移動畫面更新
        timer.schedule(new RefreshView(),0,17);//view的畫面更新
        //                                            60FPS  1000/60 =16.6
    }
    //目的讓他只抓一次寬高即可
    private void init(){
        isinit = true;
        viewW = getWidth();
        viewH = getHeight();

        //球的寬式螢幕的1/8,縮小球的大小
        ballw = viewW/15f; ballh =ballw;//因為球是正方形,所以就不多去判斷高

        //專門轉換影像的工具
        Matrix matrix = new Matrix();
        // matrix.postScale需要拿的是比例 百分率→部分量÷全部量＝百分率
        matrix.postScale(ballw/ballBmp.getWidth(),ballh/ballBmp.getHeight());
        ballBmp = Bitmap.createBitmap(ballBmp,0,0,
                ballBmp.getWidth(),ballBmp.getHeight(),matrix,false);
//        matrix.reset();
        ballx = bally =100;//給予初始球的位置
        dx =dy = 20;//ball的移動距離
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX(),ey = event.getY();
        if (ex>=1567&&ex<=2000&&ey>=650&&ey<=1000){//右下角給予按鈕寫法
            if (event.getAction() ==MotionEvent.ACTION_DOWN){

            }else if (event.getAction() ==MotionEvent.ACTION_MOVE){

            }else if(event.getAction() == MotionEvent.ACTION_UP){

            }
        }
//        return gd.onTouchEvent(event);//手勢偵測
        return true;
    }

    private class MyGDListenner extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.v("brad",velocityX+"x"+velocityY);
            if (Math.abs(velocityX)>Math.abs(velocityY)){
                //左右
                //0太敏感,使用100
                if (velocityX>100){
                    //右
                }
                if (velocityX<100){
                    //左
                }
            }
            if (Math.abs(velocityX)<Math.abs(velocityY)){
                //上下
                if (velocityY>100){
                    //下
                }
                if (velocityY<100){
                    //上
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    //保持原則,畫圖的時候再去畫,如果要寫邏輯運算的話在其他地方寫
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isinit)init();//假如isinit =fales就去執行init()初始化
        //使用canvas不會縮放
//        canvas.drawBitmap(getter_setter.getbgimage(),0,0,null);//背景

        //目的需要有Resource 物件,所以現在拿到了
        //自己稍微改寫方法,目前getter_setter.getBitmap() = ballBmp;

        canvas.drawBitmap(ballBmp,ballx,bally,paint);//球


    }
    private  class RefreshView extends TimerTask{
        @Override
        public void run() {
            postInvalidate();
        }
    }
    //時間到要做的事
    private  class BallTack extends TimerTask{
        @Override
        public void run() {//碰到牆壁回彈
            if (ballx<0||ballx+ballw>viewW){
               dx*=-1;//將位置變成負的就回彈了
            }
            if (bally<0||bally+ballh>viewH){
                dy*=-1;
            }
            ballx+= dx;
            bally+= dy;
            postInvalidate();

        }
    }
}

