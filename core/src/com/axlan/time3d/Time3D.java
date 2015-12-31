package com.axlan.time3d;

import java.util.Vector;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;

public class Time3D extends Game {
	@Override 
	public void create () {
		setScreen(new Test2D(this,new Vector<Vector2>()));
	}
}
