package com.game.rogm.characters;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.game.rogm.Main;
import com.game.rogm.level.Platform;
import com.game.rogm.combat.MeleeWeapon;

import java.util.List;

public class PlayerCharacter extends Character {

    private final float gravity = -800f;
    private final float jumpVelocity = 450f;
    private MeleeWeapon weapon = new MeleeWeapon();
    private boolean facingRight = true;

    public PlayerCharacter(float x, float y) {
        super(x,y);
    }

    @Override
    public void update(float delta) {
        // Handle input
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocityX = -speed;
            facingRight = false;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocityX = speed;
            facingRight = true;
        } else {
            velocityX = 0;
        }

        // Gravity
        velocityY += gravity * delta;

        // Predict position
        float nextX = x + velocityX * delta;
        float nextY = y + velocityY * delta;

        // Reset grounded
        grounded = false;

        // Predict collisions and snap to platform if needed
        for (Platform p : Main.getPlatforms()) {
            boolean overlapsX = nextX + width > p.getX() && nextX < p.getX() + p.getWidth();
            boolean falling = velocityY <= 0;
            boolean abovePlatform = y >= p.getY() + p.getHeight();
            boolean willLand = nextY <= p.getY() + p.getHeight();

            if (overlapsX && falling && abovePlatform && willLand) {
                velocityY = 0;
                nextY = p.getY() + p.getHeight();
                grounded = true;
                break;
            }
        }

        // check for jump AFTER grounded is confirmed
        if (grounded && (Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))) {
            velocityY = jumpVelocity;
            grounded = false;
        }

        // Apply final position
        x = nextX;
        y = nextY;

        // J to attack
        if (Gdx.input.isKeyPressed(Input.Keys.J)) {
            weapon.attack(x, y, facingRight);
        }

        // update melee
        weapon.update(delta);
    }
    // checking collisions
    public void checkCollision(List<Platform> platforms) {
        grounded = false;

        float nextX = x + velocityX * Gdx.graphics.getDeltaTime();
        float nextY = y + velocityY * Gdx.graphics.getDeltaTime();

        for (Platform p : platforms) {
            boolean overlapsX = nextX + width > p.getX() && nextX < p.getX() + p.getWidth();
            boolean landing = y >= p.getY() + p.getHeight(); // above the platform
            boolean fallingIntoPlatform = nextY <= p.getY() + p.getHeight() && y > p.getY() + p.getHeight();

            if (overlapsX && fallingIntoPlatform && landing) {
                // Land on the platform
                y = p.getY() + p.getHeight();
                velocityY = 0;
                grounded = true;
                return;
            }
        }
    }


    @Override
    public void render(ShapeRenderer renderer) {
        renderer.setColor(0, 1, 0, 1); // green
        renderer.rect(x, y, width, height);
        // render hitbox
        weapon.render(renderer);
    }
}
