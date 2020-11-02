package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;

public class BlackHole extends PhysicForce {

    private float effect;
    private Vector2 center;

    public BlackHole(float x, float y, float mass, float G) {
        center = new Vector2(x, y);
        effect = G * mass;
    }

    public BlackHole(float x, float y, float mass) {
        this(x, y, mass, 40000);
    }

    private Vector2 cache = Vector2.Zero;
    private float rs;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache.x = center.x - particle.x;
        cache.y = center.y - particle.y;
        rs = cache.len2();
        return cache.nor().scl(effect * particle.mass / rs);
    }
}
