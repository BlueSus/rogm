package com.game.rogm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends ApplicationAdapter {
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private float playerX, playerY;
    private float moveSpeed = 200f; // pixels per second

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        shapeRenderer = new ShapeRenderer();

        // Start player in center
        playerX = 400;
        playerY = 300;
    }

    @Override
    public void render() {
        // Clear screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        handleInput(Gdx.graphics.getDeltaTime());

        // Draw player
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 1, 0, 1); // green
        shapeRenderer.rect(playerX, playerY, 32, 32);
        shapeRenderer.end();
    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            playerY += moveSpeed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            playerY -= moveSpeed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            playerX -= moveSpeed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            playerX += moveSpeed * delta;
        }
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
