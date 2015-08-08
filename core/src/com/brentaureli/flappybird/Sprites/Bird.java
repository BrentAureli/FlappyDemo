package com.brentaureli.flappybird.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Brent on 6/25/2015.
 */
public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture texture;
    private Animation birdAnimation;

    public boolean colliding;


    public Bird(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x, y, texture.getWidth()/3, texture.getHeight());
        colliding = false;
    }

    public void update(float dt){
        birdAnimation.update(dt);
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        if(!colliding)
            position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 82)
            position.y = 82;

        velocity.scl(1 / dt);
        updateBounds();
    }

    public void jump(){
        velocity.y = 250;
    }

    public void updateBounds(){
        bounds.setPosition(position.x, position.y);
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getWidth(){
        return texture.getWidth();
    }

    public float getHeight(){
        return texture.getHeight();
    }

    public TextureRegion getTexture(){
        return birdAnimation.getFrame();
    }

    public Rectangle getBounds(){
        return bounds;
    }
}
