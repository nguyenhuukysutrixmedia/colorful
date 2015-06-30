package com.culun.game.colorful.controller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.culun.game.colorful.gameobjects.MyBox;

public class GameRenderer {

	private final int TIME_COLOR = Color.CYAN;

	private GameWorld mGameWorld;
	private Canvas mCanvas;
	private Paint mPaint;

	private int midPointY;
	private int gameWidth;
	private int gameHeight;

	// Game Objects
	private MyBox myBox;

	// Game Assets
	// private TextureRegion bg, grass, birdMid, skullUp, skullDown, bar;
	// private Animation birdAnimation;

	// Buttons

	public GameRenderer(GameWorld world, int gameWidth, int gameHeight, int midPointY) {
		mGameWorld = world;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		this.midPointY = midPointY;

		initGameObjects();
		initAssets();
		setupTweens();

	}

	private void setupTweens() {

	}

	private void initGameObjects() {

	}

	private void initAssets() {

	}

	private void drawBirdCentered(float runTime) {
		// batcher.draw(birdAnimation.getKeyFrame(runTime), 59, myBox.getY() - 15, myBox.getWidth() / 2.0f,
		// myBox.getHeight() / 2.0f, myBox.getWidth(), myBox.getHeight(), 1, 1, 0);
	}

	private void drawMenuUI() {

	}

	private void drawTime() {

		// float width = (float)myWorld.getTimeOut()/ MyConstants.GAME_TIME * (gameWidth-2);
		// canvas.rect(1, 1, width, 12);
	}

	private void drawScore() {
		int length = ("" + mGameWorld.getScore()).length();
		// AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), 68 - (3 * length), midPointY - 82);
		// AssetLoader.font.draw(batcher, "" + myWorld.getScore(), 68 - (3 * length), midPointY - 83);
	}

	private void drawBox() {

		int size = mGameWorld.getListBoxs().size();

		for (int i = 0; i < mGameWorld.getListBoxs().size(); i++) {
			MyBox myBox = mGameWorld.getListBoxs().get(i);

			if (myBox.isTarget()) {
				mPaint.setColor(mGameWorld.getTargetBoxColor());
			} else {
				mPaint.setColor(mGameWorld.getBoxColor());
			}
//			mCanvas.drawRect(myBox.getX(), myBox.getY(), myBox.getSize(), myBox.getSize(),mPaint);
			// batcher.draw(new Texture(myBox.getPixmap()), myBox.getX(), myBox.getY());
		}

	}

	public void render() {

		mCanvas.drawColor(Color.BLUE);
		mCanvas.drawRect(new RectF(0, 0, gameWidth, gameHeight), mPaint);

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
	}

}