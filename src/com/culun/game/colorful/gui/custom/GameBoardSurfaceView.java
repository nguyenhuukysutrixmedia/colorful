package com.culun.game.colorful.gui.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.culun.game.colorful.R;
import com.culun.game.colorful.constant.MyConstants;
import com.culun.game.colorful.controller.GameWorld;
import com.culun.game.colorful.gameobjects.MyBox;

public class GameBoardSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	/**
	 * Constants
	 */
	private final int MARGIN_TOP_TEXT = 30;

	private final int TIME_COLOR = Color.CYAN;
	private int BACKGROUND_COLOR = Color.WHITE;

	/**
	 * values load from resource
	 */
	private float TEXT_SIZE_NORMAL = 48f;
	private float BOX_RADIUS = 8f;
	private Typeface TYPE_FACE;

	private Context mContext;

	private GameWorld mGameWorld;
	private int midPointY;
	private int gameWidth;
	private int gameHeight;
	private int startBoardHeight;

	private SurfaceHolder mHolder;
	private Paint mPaint;
	private Paint mPaintText;
	private Canvas mCanvas;
	private GameBoardInputHandler mInputHandler;

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

	private void loadResourceValues() {
		BOX_RADIUS = (float) getResources().getDimension(R.dimen.box_round);
		TEXT_SIZE_NORMAL = (float) getResources().getDimension(R.dimen.text_size_normal);
		TYPE_FACE = Typeface.createFromAsset(getContext().getAssets(), MyConstants.FONT_PATH);
	}

	private void initGameBoardSurfaceView() {

		loadResourceValues();

		mContext = getContext();
		mHolder = getHolder();
		mHolder.addCallback(this);
		createPaint();
	}

	private void createPaint() {
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);

		mPaint.setTextSize(TEXT_SIZE_NORMAL);
		mPaint.setTextAlign(Align.LEFT);
		mPaint.setTypeface(TYPE_FACE);
	}

	private void initGameWorld() {
		if (mGameWorld == null) {
			gameWidth = getWidth();
			gameHeight = getHeight();

			// startBoardHeight = (getHeight() - gameWidth) / 2;

			mGameWorld = new GameWorld(getContext(), this, gameWidth, gameHeight);
			mInputHandler = new GameBoardInputHandler(mGameWorld, 1.0f, 1.0f);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		mInputHandler.onTouchEvent(event);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// mCanvas = mHolder.lockCanvas();
			// mCanvas.drawColor(Color.BLUE);
			// // canvas.drawCircle(event.getX(), event.getY(), 50, mPaint);
			// float x = event.getX();
			// float y = event.getY();
			//
			// mCanvas.drawRoundRect(new RectF(x - 50, y - 50, x + 50, y + 50), 10, 10, mPaint);
			// mHolder.unlockCanvasAndPost(mCanvas);
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

	private void drawMenuUI() {

	}

	private void drawTime() {

		// float width = (float)myWorld.getTimeOut()/ MyConstants.GAME_TIME * (gameWidth-2);
		// canvas.rect(1, 1, width, 12);
	}

	private void drawScore() {

		mPaint.setColor(mGameWorld.getBoxColor());
		mCanvas.drawText("" + mGameWorld.getScore(), TEXT_SIZE_NORMAL, TEXT_SIZE_NORMAL, mPaint);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void drawBox() {

		int size = mGameWorld.getListBoxs().size();

		for (int i = 0; i < mGameWorld.getListBoxs().size(); i++) {
			MyBox myBox = mGameWorld.getListBoxs().get(i);

			if (myBox.isTarget()) {
				mPaint.setColor(mGameWorld.getTargetBoxColor());
			} else {
				mPaint.setColor(mGameWorld.getBoxColor());
			}

			if (Build.VERSION.SDK_INT >= 21) {
				mCanvas.drawRoundRect(myBox.getLeft(), myBox.getTop(), myBox.getRight(), myBox.getBottom(), BOX_RADIUS,
						BOX_RADIUS, mPaint);
			} else {
				mCanvas.drawRect(myBox.getLeft(), myBox.getTop(), myBox.getRight(), myBox.getBottom(), mPaint);
			}
		}

	}

	private void drawBoardBackground() {

		mCanvas.drawColor(BACKGROUND_COLOR);
		// mPaint.setColor(BACKGROUND_COLOR);
		// mCanvas.drawRect(new RectF(0, 0, gameWidth, gameHeight), mPaint);
	}

	public void doDraw() {

		mHolder = getHolder();
		mCanvas = mHolder.lockCanvas();

		initGameWorld();

		drawBoardBackground();

		if (mGameWorld.isRunning()) {
			drawBox();
			drawScore();
			drawTime();
		} else if (mGameWorld.isReady()) {
			drawBox();
			drawScore();
		} else if (mGameWorld.isMenu()) {
			drawMenuUI();
		} else if (mGameWorld.isGameOver()) {
			drawBox();
			drawScore();
		} else if (mGameWorld.isHighScore()) {
			drawBox();
			drawScore();
		}

		mHolder.unlockCanvasAndPost(mCanvas);
	}
}