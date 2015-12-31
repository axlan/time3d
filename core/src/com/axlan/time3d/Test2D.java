package com.axlan.time3d;

import java.util.Vector;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class Test2D extends DefaultScreen implements InputProcessor {

	private Vector<Vector2> pointHistory;
	private final float w=20;
	private final float h=20;
	private ShapeRenderer shapeRenderer;
	
	
	public Test2D(Game game, Vector<Vector2> points) {
		super(game);
		pointHistory = points;
		pointHistory.add(new Vector2(0,0));
		shapeRenderer = new ShapeRenderer();
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Vector2 point = pointHistory.lastElement();
		shapeRenderer.begin(ShapeType.Filled);
	    shapeRenderer.setColor(Color.RED);
	    shapeRenderer.rect(point.x*w, point.y*h, w, h);
	    shapeRenderer.end();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		Vector2 point = pointHistory.lastElement().cpy();
		
		switch (keycode) {
		case Keys.UP:
			point.add(0,1);
			break;
		case Keys.DOWN:
			point.add(0,-1);
			break;
		case Keys.LEFT:
			point.add(-1,0);
			break;
		case Keys.RIGHT:
			point.add(1,0);
			break;
		case Keys.S:
			game.setScreen(new History3D(game,pointHistory));
			break;
		default:
			break;
		}
		pointHistory.add(point);
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
