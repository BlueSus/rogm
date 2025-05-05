package com.game.rogm.combat;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Weapon {
    protected float cooldown = 0;

    public abstract void update(float delta);
    public abstract void attack(float x, float y, boolean facingRight);
    public abstract void render(ShapeRenderer renderer);
}
