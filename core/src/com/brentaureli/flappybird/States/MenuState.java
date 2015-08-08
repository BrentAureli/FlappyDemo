package com.brentaureli.flappybird.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brentaureli.flappybird.FlappyBird;

/**
 * Created by Brent on 6/25/2015.
 */
public class MenuState extends State{
    Texture background;
    Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        sb.draw(playBtn, (FlappyBird.WIDTH / 2) - (playBtn.getWidth() / 2), FlappyBird.HEIGHT / 2);
        sb.end();

    }
}
