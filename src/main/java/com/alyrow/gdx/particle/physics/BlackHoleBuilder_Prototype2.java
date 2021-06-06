package com.alyrow.gdx.particle.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

public class BlackHoleBuilder_Prototype2 {

    private PhysicRoot root;

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
    private float destructionRadius; // destruction radius squared
    /**
     * The maximum magnitude of force which can be returned.
     */
    private float magnitudeClamp;


    public BlackHoleBuilder_Prototype2(float cen_x, float cen_y, float magnitude) {
        this.cen_x = cen_x;
        this.cen_y = cen_y;
        this.magnitude = magnitude;
        root = (radiusSqr, particle) -> magnitude * particle.mass / radiusSqr;
    }

    public BlackHoleBuilder_Prototype2 addDestructor() {
        return addDestructor(0.5f);
    }

    public BlackHoleBuilder_Prototype2 addMagnitudeClamp() {
        return addMagnitudeClamp(500);
    }

    public BlackHoleBuilder_Prototype2 addDestructor(final float radius) {
        destructionRadius = radius;

        final PhysicRoot oldRoot = root;

        root = (radiusSqr, particle) -> {
            if (radiusSqr < radius) {
                particle.deleteParticle();
                return 0;
            }
            return oldRoot.getMagnitude(radiusSqr, particle);
        };

        return this;
    }

    public BlackHoleBuilder_Prototype2 addMagnitudeClamp(final float clamp) {
        magnitudeClamp = clamp;

        final PhysicRoot oldRoot = root;

        root = (radiusSqr, particle) -> Math.min(oldRoot.getMagnitude(radiusSqr, particle), clamp);

        return this;
    }

    public PhysicForce build() {

        final Vector2 cache = new Vector2(0, 0);

        return new PhysicForce() {
            @Override
            public Vector2 getForce(PhysicParticle particle) {
                cache.set(cen_x - particle.x, cen_y - particle.y);
                float radiusSqr = cache.len2();
                return cache.nor().scl(root.getMagnitude(radiusSqr, particle));
            }

            @Override
            public void write(Json json) {
                super.write(json);
            }

            @Override
            public void read(Json json, JsonValue jsonData) {
                json.writeValue("Builder", BlackHoleBuilder_Prototype2.class.getName());
                json.writeValue("cen_x", cen_x);
                json.writeValue("cen_y", cen_y);
                json.writeValue("magnitude", magnitude);
                json.writeValue("destructionRadius", destructionRadius);
                json.writeValue("magnitudeClamp", magnitudeClamp);
            }
        };
    }

    public interface PhysicRoot {
        float getMagnitude(float radiusSqr, PhysicParticle particle);
    }

}
