package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class BlackHoleBuilder_Prototype1 {

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


    private boolean isDestructor = false;
    /**
     * The minimum distance squared inside which
     * every particle will set to be destroyed.
     */
    private float destructionRadius; // destruction radius squared
    private boolean isMagnitudeClamp = false;
    /**
     * The maximum magnitude of force which can be returned.
     */
    private float magnitudeClamp;


    public BlackHoleBuilder_Prototype1(float cen_x, float cen_y, float magnitude) {
        this.cen_x = cen_x;
        this.cen_y = cen_y;
        this.magnitude = magnitude;
    }

    public BlackHoleBuilder_Prototype1 addDestructor() {
        return addDestructor(0.5f);
    }

    public BlackHoleBuilder_Prototype1 addMagnitudeClamp() {
        return addMagnitudeClamp(500);
    }

    public BlackHoleBuilder_Prototype1 addDestructor(float radius) {
        isDestructor = true;
        destructionRadius = radius;
        return this;
    }

    public BlackHoleBuilder_Prototype1 addMagnitudeClamp(float clamp) {
        isMagnitudeClamp = true;
        magnitudeClamp = clamp;
        return this;
    }

    public PhysicForce build() {

        PhysicRoot root;

        final Vector2 cache = new Vector2(0, 0);

        if (isDestructor) {
            if (isMagnitudeClamp) {
                root = particle -> {
                    cache.set(cen_x - particle.x, cen_y - particle.y);
                    float rs = cache.len2();

                    if (rs < destructionRadius) {
                        particle.deleteParticle();
                        return Vector2.Zero;
                    }

                    return cache.nor().scl(Math.min(magnitude * particle.mass / rs, magnitudeClamp));
                };

            } else /* not isMagnitudeClamp */ {
                root = particle -> {
                    cache.set(cen_x - particle.x, cen_y - particle.y);
                    float rs = cache.len2();

                    if (rs < destructionRadius) {
                        particle.deleteParticle();
                        return Vector2.Zero;
                    }

                    return cache.nor().scl(magnitude * particle.mass / rs);
                };
            }

        } else /* not isDestrictor */ {
            if (isMagnitudeClamp) {
                root = particle -> {
                    cache.set(cen_x - particle.x, cen_y - particle.y);
                    float rs = cache.len2();

                    return cache.nor().scl(Math.min(magnitude * particle.mass / rs, magnitudeClamp));
                };

            } else /* not isMagnitudeClamp */ {
                root = particle -> {
                    cache.set(cen_x - particle.x, cen_y - particle.y);
                    float rs = cache.len2();

                    return cache.nor().scl(magnitude * particle.mass / rs);
                };
            }
        }

        return new PhysicForce() {
            @Override
            public Vector2 getForce(PhysicParticle particle) {
                return root.getForce(particle);
            }

            @Override
            public void write(Json json) {
                super.write(json);
            }

            @Override
            public void read(Json json, JsonValue jsonData) {
                json.writeValue("Builder", BlackHoleBuilder_Prototype1.class.getName());
                json.writeValue("cen_x", cen_x);
                json.writeValue("cen_y", cen_y);
                json.writeValue("magnitude", magnitude);
                json.writeValue("isDestructor", isDestructor);
                json.writeValue("destructionRadius", destructionRadius);
                json.writeValue("isMagnitudeClamp", isMagnitudeClamp);
                json.writeValue("magnitudeClamp", magnitudeClamp);
            }
        };
    }

    public interface PhysicRoot {
        Vector2 getForce(PhysicParticle particle);
    }

}
