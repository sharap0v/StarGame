package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprites.Background;
import ru.geekbrains.sprites.Logo;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Texture lg;
    private Background background;
    private Logo logo;
    private Vector2 pos;

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        lg = new Texture("badlogic.jpg");
        try {
            background = new Background(bg);
            logo = new Logo(lg);
        } catch (GameException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        pos = new Vector2();
    }

    @Override
    public void render(float delta) {
       update(delta);
       draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bg.dispose();
        lg.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
      background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        pos.set(touch);
        logo.touchDown(touch,pointer,button);
        return false;
    }

    private void update(float delta) {
        logo.update(delta);
    }

    private void draw() {
        Gdx.gl.glClearColor(0.5f, 0.7f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        logo.draw(batch);
        //batch.draw(lg,pos.x,pos.y);
        batch.end();
    }

}
