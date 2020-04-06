package ru.geekbrains.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.exception.GameException;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.EnemyPool;

public class EnemyRespawn extends Sprite {

    private static final float SHIP_HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.98f;

    private Rect worldBounds;
    private EnemyPool enemyPool;
    private TextureRegion enemyRegion;
    private Vector2 bulletV;

    private final Vector2 v;

    private float animateTimer;
    private float animateInterval = 10f;

    public EnemyRespawn(TextureAtlas atlas, EnemyPool enemyPool) throws GameException {
        super(atlas.findRegion("bulletMainShip"));
        this.enemyPool = enemyPool;
        enemyRegion = atlas.findRegion("enemy0");
        bulletV = new Vector2(0, -0.1f);
        v = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(SHIP_HEIGHT);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        animateTimer+=delta;
        if (animateTimer >= animateInterval) {
            animateTimer = 0;
            resp();
        }
    }

    public void resp() {
        Enemy enemy = enemyPool.obtain();
        enemy.set(this, enemyRegion, pos, bulletV, 0.3f, worldBounds, 1);
    }

}
