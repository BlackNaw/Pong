package com.mygdx.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.scripts.IScript;

public class Pala implements IScript {
	Entity entity;
	TransformComponent tc;
	PhysicsBodyComponent pc;
	DimensionsComponent dc;
	Vector2 position;
	boolean ponerNombre = false;
	boolean subir = false, bajar = false;	

	@Override
	public void init(Entity entity) {
		this.entity = entity;
		tc = entity.getComponent(TransformComponent.class);
		pc = entity.getComponent(PhysicsBodyComponent.class);
		dc = entity.getComponent(DimensionsComponent.class);
	}

	@Override
	public void act(float delta) {
		if (!ponerNombre) {
			pc.body.setUserData(this);
			ponerNombre = true;
		}
		position = pc.body.getPosition();
		pc.body.setTransform(position, pc.body.getAngle());
		if (subir) {
			subir();
		}
		if (bajar) {
			bajar();
		}
	}

	@Override
	public void dispose() {
	}

	private boolean comprobarLimites() {
		position = pc.body.getPosition();

		if (position.y < 0) {
			position.y += 0.2;
			pc.body.setTransform(position, pc.body.getAngle());
			return false;
		}
		
		if (position.y + (dc.height / Constantes.PIXEL_TO_METER) > Gdx.graphics.getHeight() / Constantes.PIXEL_TO_METER) {
			position.y -= 0.2;
			pc.body.setTransform(position, pc.body.getAngle());
			return false;
		}

		return true;
	}

	public void subir() {
		if (comprobarLimites()) {
			position.y += 0.2f;
			pc.body.setTransform(position, pc.body.getAngle());
		}
		
	}

	public void bajar() {
		if (comprobarLimites()) {
			position.y -= 0.2f;
			pc.body.setTransform(position, pc.body.getAngle());
		}		
	}

}
