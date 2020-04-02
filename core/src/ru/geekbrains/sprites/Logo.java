package ru.geekbrains.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;

public class Logo extends Sprite {
    private static final float V_LEN = 0.01f;
    private Vector2 touch;
    private Vector2 tmp;
    private Vector2 v;


    public Logo(Texture texture) throws GameException {
        super(new TextureRegion(texture));
        touch = new Vector2();
        v = new Vector2();
        tmp = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.1f);
        pos.set(worldBounds.pos);
    }
        @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch.x,touch.y);
        v.set(touch.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }

    @Override
    public void update(float delta) {
        tmp.set(touch);
        float remainingDistance = (tmp.sub(pos)).len();
        if (remainingDistance > V_LEN) {
            this.pos.add(v);
        }else {
            v.setZero();
            pos.set(this.touch);
        }

    }
}
