package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class Teclado extends InputAdapter {
	Pala palaIzquierda, palaDerecha;

	public Teclado(Pala palaIzquierda, Pala palaDerecha) {
		this.palaIzquierda = palaIzquierda;
		this.palaDerecha = palaDerecha;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.UP:
			palaDerecha.subir = true;
			break;
		case Keys.DOWN:
			palaDerecha.bajar = true;
			break;
		case Keys.W:
			palaIzquierda.subir = true;
			break;
		case Keys.S:
			palaIzquierda.bajar = true;
			break;

		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.UP:
			palaDerecha.subir = false;
			break;
		case Keys.DOWN:
			palaDerecha.bajar = false;
			break;
		case Keys.W:
			palaIzquierda.subir = false;
			break;
		case Keys.S:
			palaIzquierda.bajar = false;
			break;

		}
		return false;
	}
}
