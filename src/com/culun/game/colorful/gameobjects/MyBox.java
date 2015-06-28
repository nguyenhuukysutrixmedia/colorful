package com.culun.game.colorful.gameobjects;


public class MyBox {

	private float top, left, bottom, right;
	private float size;
	private float originalY;
	private boolean isTarget;

	public MyBox(float left, float top, float right, float bottom, float size, boolean isTarget) {
		this.size = size;
		this.top = top;
		this.left = left;
		this.right = right;
		this.bottom = bottom;
		this.isTarget = isTarget;
	}

	public void update(float delta) {

	}

	public void updateReady(float runTime) {
		// position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
	}

	public void onClick() {
		if (isTarget) {

		}
	}

	public float getSize() {
		return size;
	}

	public boolean isTarget() {
		return isTarget;
	}

	public float getTop() {
		return top;
	}

	public void setTop(float top) {
		this.top = top;
	}

	public float getLeft() {
		return left;
	}

	public void setLeft(float left) {
		this.left = left;
	}

	public float getBottom() {
		return bottom;
	}

	public void setBottom(float bottom) {
		this.bottom = bottom;
	}

	public float getRight() {
		return right;
	}

	public void setRight(float right) {
		this.right = right;
	}

	public float getOriginalY() {
		return originalY;
	}

	public void setOriginalY(float originalY) {
		this.originalY = originalY;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public void setTarget(boolean isTarget) {
		this.isTarget = isTarget;
	}

}