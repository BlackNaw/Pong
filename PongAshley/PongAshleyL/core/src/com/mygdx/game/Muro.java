package com.mygdx.game;

import com.badlogic.ashley.core.Entity;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.physics.PhysicsBodyComponent;
import com.uwsoft.editor.renderer.scripts.IScript;

public class Muro implements IScript {
	Entity entity;
	TransformComponent tc;
	PhysicsBodyComponent pc;
	boolean ponerNombre = false; 

	@Override
	public void init(Entity entity) {
		this.entity = entity;
		tc = entity.getComponent(TransformComponent.class);
		pc = entity.getComponent(PhysicsBodyComponent.class);
	}

	@Override
	public void act(float delta) {		
		if (!ponerNombre) {
			pc.body.setUserData(this);
			ponerNombre = true;
		}
	}

	@Override
	public void dispose() {
	}

}
