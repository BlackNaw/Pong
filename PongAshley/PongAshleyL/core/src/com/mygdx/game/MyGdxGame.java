package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.utils.ItemWrapper;

public class MyGdxGame extends ApplicationAdapter {
	Teclado teclado;
	SceneLoader sl;
	Viewport viewport;
	Box2DDebugRenderer debugRender;
	OrthographicCamera camera;
	ContactListenerAdapter contact;
	ItemWrapper bola, palaIzquierdaChild, palaDerechaChild, limiteSuperior, limiteInferior;
	Pala palaDerecha, palaIzquierda;

	@Override
	public void create() {
		palaDerecha = new Pala();
		palaIzquierda = new Pala();
		contact = new ContactListenerAdapter();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		viewport = new ScreenViewport(camera);
		// Creamos el escenario
		sl = new SceneLoader();
		debugRender = new Box2DDebugRenderer();
		// ZONA DE CARGA
		sl.loadScene("MainScene", viewport);
		ItemWrapper root = new ItemWrapper(sl.getRoot());
		// Aqui obtenemos entity llamado base en overlap y le damos el
		// comportamiento de player
		bola = root.getChild("bola");
		bola.addScript(new Bola());
		palaIzquierdaChild = root.getChild("palaIzquierda");
		palaIzquierdaChild.addScript(palaIzquierda);
		palaDerechaChild = root.getChild("palaDerecha");
		palaDerechaChild.addScript(palaDerecha);
		limiteInferior = root.getChild("limiteInferior");
		limiteInferior.addScript(new Muro());
		limiteSuperior = root.getChild("limiteSuperior");
		limiteSuperior.addScript(new Muro());
		teclado = new Teclado( palaIzquierda, palaDerecha);
		Gdx.input.setInputProcessor(teclado);
		sl.world.setContactListener(contact);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		debugRender.render(sl.world, camera.combined.scale(20, 20, 1));
		// Actualizamos el motor de ashly
		sl.getEngine().update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void dispose() {
		debugRender.dispose();
	}
}
