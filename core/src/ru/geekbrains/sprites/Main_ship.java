package ru.geekbrains.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;

public class Main_ship extends Sprite {
    public static float speed = 0;
    private Vector2 touch = new Vector2(0,0);
    private Rect worldBounds;
    private boolean preskey = false;
    public Main_ship(TextureAtlas atlas) throws GameException {
        super(new TextureRegion((atlas.findRegion("main_ship")),0,0,390/2,287));
    }

    @Override
    public void update(float delta) {
        if(touch.x<pos.x && getLeft()>worldBounds.getLeft()){
            speed=-0.01f;
        }
        else if(touch.x>pos.x && getRight()<worldBounds.getRight()){
            speed=0.01f;
        }
        else {
            speed=0;
        }
        pos.add(speed,0);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.2f);
        pos.set(0,worldBounds.getBottom() + 0.15f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (preskey==false){
        this.touch = touch;
        preskey = true;}
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("trololo");
        if(preskey == true){
        this.touch.set(pos);
        preskey = false;}
        return false;
    }
    public boolean keyDown(int keycode) {
        if (preskey == false){
            preskey = true;
        if(keycode==21||keycode==29){
            this.touch.set(-2f,0);
        }
        if(keycode == 22||keycode==32){
            this.touch.set(2f,0);
        }}
        return false;
    }
    public boolean keyUp(int keycode) {
        if (preskey == true){
            preskey = false;
            this.touch.set(pos);
        }
        return false;
    }
}
