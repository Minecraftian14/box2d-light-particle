package com.csnakes.gdx.particle.physics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.*;

import java.util.HashMap;
import java.util.Map;

public class PhysicParticle {
    public Body body;
    public float x, x_start, y, y_start, r, width, height;
    public Map<String, Float> data = new HashMap<String, Float>();
    CircleShape shapeCircle;
    public World world;
    private Camera camera;

    public PhysicParticle(float x, float y, World world, Camera camera) {
        this.x = x;
        this.y = y;
        x_start = x;
        y_start = y;
        this.camera = camera;

        this.world = world;

        if (world != null) {
            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set(x/camera.viewportWidth, y/camera.viewportHeight);
            body = world.createBody(bodyDef);
        }
    }

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
        if (world != null) {
            shapeCircle = new CircleShape();
            shapeCircle.setRadius(width / 4f);
            FixtureDef fixtureDef = new FixtureDef();
            fixtureDef.density = 0.1f;
            fixtureDef.shape = shapeCircle;
            body.createFixture(shapeCircle, 0.1f);
            shapeCircle.dispose();
        }
    }
}
