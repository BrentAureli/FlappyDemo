package com.brentaureli.flappybird.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Brent on 6/26/2015.
 */
public class Animation {
    Array<TextureRegion> frames;
    float maxFrameTime;
    float currentFrameTime;
    int frameCount;
    int frame;


    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        TextureRegion temp;
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());
            frames.add(temp);
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
            frame = 0;

    }

    public void flip(){
        for(TextureRegion region : frames)
            region.flip(true, false);
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
