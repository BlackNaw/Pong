package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListenerAdapter implements ContactListener {
	
	Body uno, dos;
	
	@Override
	public void beginContact(Contact contact) {
		uno = contact.getFixtureA().getBody();
		dos = contact.getFixtureB().getBody();
		if (uno.getUserData() instanceof Bola) {
			if (dos.getUserData() instanceof Muro){
				((Bola)uno.getUserData()).choqueMuro();
			}
			if (dos.getUserData() instanceof Pala){
				((Bola)uno.getUserData()).choquePala();;
			}
		}
		if (dos.getUserData() instanceof Bola) {
			if (uno.getUserData() instanceof Muro){
				((Bola)dos.getUserData()).choqueMuro();
			}
			if (uno.getUserData() instanceof Pala){
				((Bola)dos.getUserData()).choquePala();
			}
		}

	}

	@Override
	public void endContact(Contact contact) {
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
	}

}
