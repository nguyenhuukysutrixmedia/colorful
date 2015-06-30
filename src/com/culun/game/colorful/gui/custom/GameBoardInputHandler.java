package com.culun.game.colorful.gui.custom;

import java.util.ArrayList;

import android.view.MotionEvent;

import com.culun.game.colorful.controller.GameWorld;
import com.culun.game.colorful.gameobjects.MyBox;

public class GameBoardInputHandler {

	private GameWorld mGameWorld;

	private float scaleFactorX;
	private float scaleFactorY;

	public GameBoardInputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY) {
		this.mGameWorld = myWorld;

		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
	}

	public boolean onTouchEvent(MotionEvent event) {

		float screenX = event.getX();
		float screenY = event.getY();
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			System.out.println("touchDown: " + screenX + "-" + screenY);

			ArrayList<MyBox> listBoxs = mGameWorld.getListBoxs();
			for (int i = 0; i < listBoxs.size(); i++) {
				MyBox myBox = listBoxs.get(i);
				if (screenX >= myBox.getLeft() && screenX <= (myBox.getRight()) && screenY >= myBox.getTop()
						&& screenY <= (myBox.getBottom())) {

					myBox.onClick();
					if (mGameWorld.isReady()) {
						mGameWorld.start();
					}
					if (mGameWorld.isRunning()) {
						if (myBox.isTarget()) {
							mGameWorld.addScore(1);
						}
					}

				}
			}

			break;
		}

		return true;
	}

	private float scaleX(float screenX) {
		return (float) (screenX / scaleFactorX);
	}

	private float scaleY(float screenY) {
		return (float) (screenY / scaleFactorY);
	}
}
