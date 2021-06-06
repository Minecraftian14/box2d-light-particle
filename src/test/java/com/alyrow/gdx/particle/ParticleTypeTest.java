package com.alyrow.gdx.particle;

import com.alyrow.gdx.particle.modifiers.Modifier;
import com.alyrow.gdx.particle.tester.Tester;
import com.alyrow.gdx.particle.texture.ParticleTexture;

public class ParticleTypeTest {

    public static void main(String[] args) {

//        new Tester().setParticleType(ParticleType.DISCORD).Test();

        new Tester()
                .setParticleType(ParticleType.GRILL)
                .forModifier(() -> new Modifier() {
                    @Override
                    public void modify() {
                        setX(((int) (300 * Math.random())) * 2);
                        setY(((int) (300 * Math.random())) * 2);
                    }
                })
                .Test();

    }

}