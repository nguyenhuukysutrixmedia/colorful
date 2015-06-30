package com.culun.game.colorful.controller;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;

import com.culun.game.colorful.R;
import com.culun.game.colorful.constant.MyConstants;
import com.culun.game.colorful.gameobjects.MyBox;
import com.culun.game.colorful.gui.custom.GameBoardSurfaceView;

public class GameWorld {

	private final int[] ALPHA_RANGES = new int[]{170, 180, 190, 200, 210, 220, 230, 235, 240, 245};
	private final int MAX_ALPHA = ALPHA_RANGES[ALPHA_RANGES.length - 1];
	private final int[] SCORE_RANGES = new int[]{1, 3, 6, 11, 19, 32, 40, 50, 60, 70};
	private final float MAX_SIZE = SCORE_RANGES.length;

	private float MARGIN_WALL;
	private float MARGIN_BOXS;

	private int alpha = ALPHA_RANGES[0];
	private int timeOut = MyConstants.GAME_TIME;

	private int size = 2;
	private int boxColor;
	private int targetBoxColor;
	private ArrayList<MyBox> listBoxs;

	private GameBoardSurfaceView gameBoardSurfaceView;

	// private Rectangle ground;
	private int score = 0;
	private float runTime = 0;
	private int midPointY;
	private GameState currentState;
	private float gameWidth;
	private float gameHeight;
	private Random randrom;

	private Context mContext;

	public GameWorld(Context context, GameBoardSurfaceView gameBoardSurfaceView, float gameWidth, float gameHeight) {
		this.gameBoardSurfaceView = gameBoardSurfaceView;
		mContext = context;
		randrom = new Random(System.currentTimeMillis());
		loadDimenValues();

		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		currentState = GameState.READY;
		generateListBoxs();
	}

	private void loadDimenValues() {
		MARGIN_BOXS = (float) mContext.getResources().getDimensionPixelSize(R.dimen.box_margin_box);
		MARGIN_WALL = (float) mContext.getResources().getDimensionPixelSize(R.dimen.box_margin_wall);
	}

	private void randomColor() {

		int r = randrom.nextInt(256);
		int g = randrom.nextInt(256);
		int b = randrom.nextInt(256);

		double darkness = 1 - (0.299 * r + 0.587 * g + 0.114 * b) / 255;
		while (darkness < 0.5) {
			// It's a light color
			r = randrom.nextInt(256);
			g = randrom.nextInt(256);
			b = randrom.nextInt(256);
			darkness = 1 - (0.299 * r + 0.587 * g + 0.114 * b) / 255;
		}

		boxColor = Color.argb(255, r, g, b);

		targetBoxColor = Color.argb(getAlpha(), r, g, b);
	}

	private int getAlpha() {

		if (size <= ALPHA_RANGES.length) {
			alpha = ALPHA_RANGES[size - 1];
		}
		return alpha;
	}

	private void generateListBoxs() {

		listBoxs = new ArrayList<>();
		int boxSize = (int) (gameWidth - (MARGIN_WALL * 2) - MARGIN_BOXS * (size - 1)) / size;
		randomColor();

		int n = size * size;
		int targetIndex = randrom.nextInt(n);
		for (int i = 0; i < n; i++) {

			int row = i / size;
			float left = MARGIN_WALL + (i % size) * (MARGIN_BOXS + boxSize);
			float top = (gameHeight + gameWidth) / 2 - MARGIN_WALL - boxSize * (size - row) - MARGIN_BOXS
					* (size - row - 1);

			boolean isTarget = (i == targetIndex);
			MyBox myBox = new MyBox(left, top, left + boxSize, top + boxSize, boxSize, isTarget);
			listBoxs.add(myBox);
		}
	}

	public void update(float delta) {
		runTime += delta;

		switch (currentState) {
			case READY :
			case MENU :
				updateReady(delta);
				break;

			case RUNNING :
				updateRunning(delta);
				break;
			default :
				break;
		}

	}

	private void updateReady(float delta) {

	}

	public void updateRunning(float delta) {

		timeOut -= runTime;
		if (delta > .15f) {
			delta = .15f;
		}

	}

	public void updateScore() {

	}

	public int getMidPointY() {
		return midPointY;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
		if (size < MAX_SIZE && score >= SCORE_RANGES[size - 2])
			size++;
		generateListBoxs();
		gameBoardSurfaceView.doDraw();
	}

	public void start() {

		currentState = GameState.RUNNING;
	}

	public void ready() {
		timeOut = MyConstants.GAME_TIME;
		score = 0;
		currentState = GameState.READY;
	}

	public void restart() {
		currentState = GameState.READY;
		ready();
	}

	public boolean isReady() {
		return currentState == GameState.READY;
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}

	public int getBoxColor() {
		return boxColor;
	}

	public int getTargetBoxColor() {
		return targetBoxColor;
	}

	public ArrayList<MyBox> getListBoxs() {
		return listBoxs;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
	}

}
