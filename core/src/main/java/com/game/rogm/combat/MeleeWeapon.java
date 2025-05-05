package com.game.rogm.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MeleeWeapon extends Weapon {
    private boolean isAttacking = false;
    private float attackDuration = 0.2f;
    private float attackTimer = 0;

    private float hitboxX, hitboxY, hitboxW = 30, hitboxH = 40;

    @Override
    public void update(float delta) {
        if (cooldown > 0) cooldown -= delta;
        if (isAttacking) {
            attackTimer -= delta;
            if (attackTimer <= 0) {
                isAttacking = false;
            }
        }
    }
    @Override
    public void attack(float playerX, float playerY, boolean facingRight) {
        if (cooldown <= 0) {
            isAttacking = true;
            attackTimer = attackDuration;
            cooldown = 0.3f;

            if (facingRight) {
                hitboxX = playerX + 32;
            } else {
                hitboxX = playerX - hitboxW;
            }
            hitboxY = playerY;
        }
    }
    @Override
    public void render(ShapeRenderer renderer) {
        if (isAttacking) {
            renderer.setColor(1, 0, 0, 1); // red
            renderer.rect(hitboxX, hitboxY, hitboxW, hitboxH);
        }
    }

    public boolean isAttacking() {
        return isAttacking;
    }
    public float getHitboxX() {
        return hitboxX;
    }
    public float getHitboxY() {
        return hitboxY;
    }
    public float getHitboxW() {
        return hitboxW;
    }
    public float getHitboxH() {
        return hitboxH;
    }
}
