package com.game.rogm.level;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.awt.*;

public class Platform {
    private float x, y, width, height;

    public Platform(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(ShapeRenderer renderer) {
        renderer.setColor(0.4f, 0.4f, 0.4f, 1);
        renderer.rect(x, y, width, height);
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public float getWidth() {
        return width;
    }
    public float getHeight() {
        return height;
    }
}
