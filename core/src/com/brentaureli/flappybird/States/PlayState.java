package com.brentaureli.flappybird.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.brentaureli.flappybird.Sprites.Bird;
import com.brentaureli.flappybird.Sprites.Tube;

/**
 * Created by Brent on 6/25/2015.
 */
public class PlayState extends State {
    private static final int GROUND_Y_OFFSET = -30;
    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT = 4;

    private Bird bird;
    private Texture background;
    private Texture ground;
    private Texture gameoverImg;
    private Vector2 groundPos1;
    private Vector2 groundPos2;
    private Array<Tube> tubes;
    private ShapeRenderer sr;

    private boolean gameover;

    public PlayState(GameStateManager gsm){
        super(gsm);
        bird = new Bird(40, 200);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        gameoverImg = new Texture("gameover.png");

        tubes = new Array<Tube>();
        for(int i = 1; i <= TUBE_COUNT; i++)
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        gameover = false;
    }
    @Override
    public void handleInput() {
        if(Gdx.input.isTouched()) {
            if(gameover)
                gsm.set(new PlayState(gsm));
            else
                bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        cam.position.set(bird.getX() + 80, cam.viewportHeight / 2, 0);
        for(Tube tube : tubes){
            if(cam.position.x - cam.viewportWidth / 2 > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x +((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }

            if(tube.collides(bird.getBounds())){
                bird.colliding = true;
                gameover = true;
            }
        }
        if(bird.getY() <= ground.getHeight() + GROUND_Y_OFFSET){
            gameover = true;
            bird.colliding = true;
        }
        cam.update();
    }

    public void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2, 0);
        if(cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() * 2, 0);

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, cam.position.x - (cam.viewportWidth / 2), 0);
        //System.out.println("X: " + groundPos1.x + " Y: " + groundPos1.y);
        for(Tube tube : tubes){
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.draw(bird.getTexture(), bird.getX(), bird.getY());
        if(gameover)
            sb.draw(gameoverImg, cam.position.x - gameoverImg.getWidth() / 2, cam.position.y);
        sb.end();
    }
}
