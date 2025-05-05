package com.game.rogm;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.rogm.characters.PlayerCharacter;
import com.game.rogm.level.Platform;

import java.util.ArrayList;
import java.util.List;

public class Main extends ApplicationAdapter {
    private OrthographicCamera camera;
    private ShapeRenderer renderer;
    private PlayerCharacter player;
    private static List<Platform> platforms;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        renderer = new ShapeRenderer();

        player = new PlayerCharacter(100, 300);
        platforms = new ArrayList<>();
        platforms.add(new Platform(0, 100, 800, 30)); // floor
        platforms.add(new Platform(300, 200, 150, 20)); // floating
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        player.update(delta);
        player.checkCollision(platforms);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        renderer.setProjectionMatrix(camera.combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        player.render(renderer);
        for (Platform platform : platforms) {
            platform.render(renderer);
        }
        renderer.end();
    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

    public static List<Platform> getPlatforms() {
        return platforms;
    }

}
