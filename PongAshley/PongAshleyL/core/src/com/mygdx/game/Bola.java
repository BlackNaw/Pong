package com.mygdx.game;

import java.util.Random;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.scripts.IScript;

public class Bola implements IScript {

	Entity entity;
	TransformComponent tc;
	PhysicsBodyComponent pc;
	float avanceY = 0.2f;
	float avanceX = 0.2f;
	boolean ponerNombre = false;
	Vector2 position;
	@Override
	public void init(Entity entity) {
		this.entity = entity;
		tc = entity.getComponent(TransformComponent.class);
		pc = entity.getComponent(PhysicsBodyComponent.class);
		avanceX *= sorteoDireccion();
		avanceY *= sorteoDireccion();
	}

	@Override
	public void act(float delta) {
		position = pc.body.getPosition();
		position.y += avanceY;
		position.x += avanceX;
		pc.body.setTransform(position, pc.body.getAngle());
		if (!ponerNombre) {
			pc.body.setUserData(this);
			pc.body.setBullet(true);
			ponerNombre = true;
		}
		fueraLimites();
	}

	public int sorteoDireccion(){
		  int valor;
		  Random random=new Random();
		  valor=random.nextInt(2);
		  return valor==0?-1:1;
		}

	public void fueraLimites() {
		position = pc.body.getPosition();
		if (position.x > (Gdx.graphics.getWidth() / Constantes.PIXEL_TO_METER) || position.x < 0) {
			position.x = (Gdx.graphics.getWidth() / 2) / Constantes.PIXEL_TO_METER;
			position.y = (Gdx.graphics.getHeight() / 2) / Constantes.PIXEL_TO_METER;
			avanceX = 0.2f;
			avanceX *= sorteoDireccion();
			avanceY *= sorteoDireccion();
		}
		pc.body.setTransform(position, pc.body.getAngle());
	}

	@Override
	public void dispose() {
	}

	public void choqueMuro() {
		avanceY *= -1;
	}

	public void choquePala() {
		avanceX += (Math.abs(avanceX) / avanceX) * 0.05f;
		avanceX *= -1;
	}

}
