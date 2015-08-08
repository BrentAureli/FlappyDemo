package com.brentaureli.flappybird.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Brent on 6/25/2015.
 */
public class Tube {
    public static final int TUBE_WIDTH = 52; //width of tube texture(used for spacing tubes on playstate)
    private static final int TUBE_GAP = 100; //opening between tubes
    private static final int LOWEST_OPENING = 120; //lowest position the top of the bottom tube can be, must be above 90 to be above ground level
    private static final int FLUCTUATION = 130; //may adjust to keep top tube in view

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBottomTube;
    private Rectangle boundsTop, boundsBottom;
    private Random  rand;

    public Tube(float x){
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        rand = new Random();
        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING + TUBE_GAP);
        posBottomTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }


    public Texture getTopTube() {
        return topTube;
    }

    public void setTopTube(Texture topTube) {
        this.topTube = topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public void setBottomTube(Texture bottomTube) {
        this.bottomTube = bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public void setPosTopTube(Vector2 posTopTube) {
        this.posTopTube = posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }

    public void setPosBottomTube(Vector2 posBottomTube) {
        this.posBottomTube = posBottomTube;
    }
    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING + TUBE_GAP);
        posBottomTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(x, posTopTube.y);
        boundsBottom.setPosition(x, posBottomTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsBottom) || player.overlaps(boundsTop);
    }

    public Rectangle getBoundsBottom(){
        return boundsBottom;
    }

    public Rectangle getBoundsTop(){
        return boundsTop;
    }
}
