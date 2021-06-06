package com.alyrow.gdx.particle.physics;

import com.alyrow.gdx.particle.tester.Tester;
import com.badlogic.gdx.Gdx;

import static org.junit.Assert.*;

public class BlackHoleBuilder {

    public static void main(String[] args) {

        float mass = 20/3f;

        new Tester()
                .forForce(() -> new BlackHole(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, mass))
                .forForce(() ->
                        new BlackHoleBuilder_Prototype1(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, mass)
                                .addDestructor()
                                .addMagnitudeClamp()
                                .build()
                )
                .forForce(() ->
                        new BlackHoleBuilder_Prototype2(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f, mass)
                                .addDestructor()
                                .addMagnitudeClamp()
                                .build()
                )
                .Test();

    }

}