package com.game.rogm.characters;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Character {
    protected float x, y;
    protected float width = 32, height = 48;
    protected float velocityX = 0, velocityY = 0;
    protected float speed = 200f;
    protected boolean grounded = false;

    public Character(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void update(float delta);
    public abstract void render(ShapeRenderer renderer);

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
