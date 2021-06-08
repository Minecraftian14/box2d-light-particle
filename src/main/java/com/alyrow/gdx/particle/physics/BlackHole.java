package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.utils.PhysicForces;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;


/**
 * A force which attracts particles. It's magnitude is directly proportional
 * to the mass of the particle and inversely proportional to square of the
 * distance between them.
 * <br /><br />
 * Note, that the force magnitude is clamped (limited) so that, particles
 * very close to center are not thrown off the screen.
 * <br /><br />
 * One can also make the force destruct all the particles getting near to
 * the center by a threshold.
 * <br /><br />
 * min(\frac{magnitude \cdot mass}{distance^{2}}, limit) \cdot  \widehat{cen-pos}
 */
public class BlackHole extends PhysicForce {

    /**
     * Magnitude of the force
     */
    private final float magnitude;
    /**
     * Abscissa of center of the force.
     */
    private final float cen_x;
    /**
     * Ordinate of center of the force.
     */
    private final float cen_y;

    /**
     * The minimum distance squared inside which
     * every particle will set to be destroyed.
     */
    private float drs = 0.5f; // destruction radius squared
    /**
     * The maximum magnitude of force which can be returned.
     */
    private float limit = 500;

    public BlackHole(float x, float y, float mass, float G) {
        cen_x = x;
        cen_y = y;
        magnitude = G * mass;
    }

    public BlackHole(float x, float y, float mass) {
        this(x, y, mass, 40000);
    }

    private final Vector2 cache = new Vector2();
    private float rs;

    @Override
    public Vector2 getForce(PhysicParticle particle) {
        cache.set(cen_x - particle.x, cen_y - particle.y);
        rs = cache.len2();

        if (rs < drs) {
            particle.deleteParticle();
            return Vector2.Zero;
        }

        return cache.nor().scl(Math.min(magnitude * particle.mass / rs, limit));
    }

    public void setDestructionRadius(float destructionRadius) {
        this.drs = destructionRadius * destructionRadius;
    }

    public void disableDestructionRadius() {
        drs = 0;
    }

    public void setLimit(float limit) {
        this.limit = limit;
    }

    @Override
    public void write(Json json) {
        json.writeValue("x", cen_x);
        json.writeValue("y", cen_y);
        json.writeValue("effect", magnitude);
        json.writeValue("drs", drs);
        json.writeValue("limit", limit);
    }

    @Override
    public void read(Json json, JsonValue jsonData) {
        super.read(json, jsonData);
    }
}
