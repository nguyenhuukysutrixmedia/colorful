package com.culun.game.colorful.gui.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameBoardSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder mHolder;
	private Paint mPaint;

	public GameBoardSurfaceView(Context context) {
		super(context);
		initGameBoardSurfaceView();
	}

	public GameBoardSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameBoardSurfaceView();
	}

	public GameBoardSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameBoardSurfaceView();
	}

	private void initGameBoardSurfaceView() {
		mHolder = getHolder();
		mHolder.addCallback(this);
		mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.FILL);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}

	public void doDraw() {
		mHolder = getHolder();
		Canvas canvas = mHolder.lockCanvas();
		// canvas.drawColor(Color.BLUE);
		// canvas.drawRect(new RectF(0, 110, 100, 290), mPaint);

		mHolder.unlockCanvasAndPost(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {

			case MotionEvent.ACTION_DOWN :
				Canvas canvas = mHolder.lockCanvas();
				canvas.drawColor(Color.BLUE);
				// canvas.drawCircle(event.getX(), event.getY(), 50, mPaint);
				float x = event.getX();
				float y = event.getY();

				canvas.drawRoundRect(new RectF(x - 50, y - 50, x + 50, y + 50), 10, 10, mPaint);
				mHolder.unlockCanvasAndPost(canvas);
				break;
		}

		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		doDraw();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}
}