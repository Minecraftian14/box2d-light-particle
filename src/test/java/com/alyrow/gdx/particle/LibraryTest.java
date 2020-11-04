/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.alyrow.gdx.particle;

import box2dLight.RayHandler;
import com.alyrow.gdx.particle.modifiers.RandomPositionShape;
import com.alyrow.gdx.particle.physics.PhysicManager;
import com.alyrow.gdx.particle.rules.ParticleEmissionDuration;
import com.alyrow.gdx.particle.rules.ParticleEmissionLightRandom;
import com.alyrow.gdx.particle.rules.ParticleEmissionNumber;
import com.alyrow.gdx.particle.rules.ParticleLife;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class LibraryTest extends Game {

    private World world;
    private RayHandler rayHandler;
    private Box2DDebugRenderer debugRenderer;
    private ParticleSystem system;
    private OrthographicCamera camera;
    private ParticleEmissionLightRandom emissionLight;

    @Override
    public void create() {
        Box2D.init();
        world = new World(new Vector2(10, 0), true);
        rayHandler = new RayHandler(world);
        rayHandler.setBlurNum(3);
        rayHandler.setAmbientLight(new Color(0.1f, 0.1f, 0.1f, 0f));
        rayHandler.setShadows(true);
        debugRenderer = new Box2DDebugRenderer();

        ParticleRules rules = new ParticleRules();
        ParticleEmissionNumber ps = new ParticleEmissionNumber(ParticleEmissionNumber.INNER_SCREEN, 1000);
        ps.setDelay(1f);
        rules.setNumber(ps); //One particle emitted per seconds
        rules.setLife(new ParticleLife(5, true)); //Particles life : 5s. Life will decrease when particles are outside the screen.
        rules.setDuration(new ParticleEmissionDuration(true)); //Infinite duration for the emission
        emissionLight = new ParticleEmissionLightRandom(rayHandler, 16, /*new Color(0.37647f, 1, 1, 1)*/Color.BLACK, 10.5f, 50);
        rules.setLight(emissionLight); //Add random light distance between 35 and 45.

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2.0f, camera.viewportHeight / 2.0f, 1.0f);
        camera.update();
        system = new ParticleSystem(ParticleType.HALO, null, camera);
        system.setRules(rules);
        system.setParticlesPosition(-2, 0);
//        system.disableBlending();

        system.getModifierManager().addModifier(
//                new RandomPositionRectangle(camera.viewportWidth/2, camera.viewportHeight)
                new RandomPositionShape(new Rectangle(0, 0, 200, 200), new Ellipse2D.Float(300, 300, 200, 200))
//                new RandomMassModifier(10,30),
//                new RandomChargeModifier(),
//                new MassProportionalLightRadius(2f)
        );

        PhysicManager physicManager = new PhysicManager();
        //Here I have divided by 16 because my tile map ratio is 1/16
//        physicManager.addForce(new BrownianForce(100, 100, 10000, 0.6D));
//        physicManager.addForce(new RandomLinearForce(5, 30, 0, 0));
//        physicManager.addForce(new RandomRadialForce(-10, 10));
//        physicManager.addForce(new BlackHole(camera.viewportWidth/2f, camera.viewportHeight/2f, 100));
//        physicManager.addForce(new BlackHole(0, 0, 100));
//        physicManager.addForce(new WhiteHole(camera.viewportWidth/2f, camera.viewportHeight/2f, 60));
//        physicManager.addForce(new BlackLine(new Line(0, camera.viewportHeight/2f), 60));
//        physicManager.addForce(new BlackLine(new Line(0, -1), 60));
//        physicManager.addForce(new WhiteLine(new Line(0, camera.viewportHeight/2f), 60));
//        physicManager.addForce(new Revolution(camera.viewportWidth/2f, camera.viewportHeight/2f, 20));
//        physicManager.addForce(new Fan(camera.viewportWidth/2f, camera.viewportHeight/2f, 20));
//        Whirlpool w = new Whirlpool(camera.viewportWidth/2f, camera.viewportHeight/2f, 50, 50);
//        w.setDestructionRadius(0);
//        physicManager.addForce(w);
//        PointAttract p = new PointAttract(camera.viewportWidth/2f, camera.viewportHeight/2f, 60);
//        p.setDestructionRadius(4f);
//        physicManager.addForce(p);
//        physicManager.addForce(new Drain(camera.viewportWidth/2f, camera.viewportHeight/2f, 10, 10));
//        physicManager.addForce(new ColorPoint(new Color(1,0,0,1), 0, 0, 500));
//        physicManager.addForce(new ColorPoint(new Color(0,1,0,1), 0, camera.viewportHeight, 500));
//        physicManager.addForce(new ColorPoint(new Color(0.65f,0,0.74f,1), camera.viewportWidth, camera.viewportHeight, 500));
//        physicManager.addForce(new ColorPoint(new Color(0,0,1,1), camera.viewportWidth, 0, 500));
//        physicManager.addForce(new SwitchableForce(
//                new BlackHole(camera.viewportWidth/2f, camera.viewportHeight/2f, 60),
//                new WhiteHole(camera.viewportWidth/2f, camera.viewportHeight/2f, 60)
//        ));
//        physicManager.addForce(new SwitchableForce(
//                new Revolution(camera.viewportWidth/2f, camera.viewportHeight/2f, 30),
//                new Revolution(camera.viewportWidth/2f, camera.viewportHeight/2f, -30),
//                new Revolution(camera.viewportWidth/2f, camera.viewportHeight/2f, 60),
//                new Revolution(camera.viewportWidth/2f, camera.viewportHeight/2f, -60)
//        ));
//        physicManager.addForce(new SwitchableForce(
//                new TimeMatcherSwitch(200, 400),
//                new Revolution(camera.viewportWidth/2f, camera.viewportHeight/2f, 30),
//                new Revolution(camera.viewportWidth/2f, camera.viewportHeight/2f, -30)
//        ));
//        physicManager.addForce(new LinearTransitionForce(
//                new Revolution(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 30),
//                new Revolution(camera.viewportWidth / 2f, camera.viewportHeight / 2f, -30),
//                0.0001f // also try 0.001f
//        ));
//        physicManager.addForce(new PointElectrostaticForce(camera.viewportWidth / 3f, camera.viewportHeight / 2f, 1000));
//        physicManager.addForce(new PointElectrostaticForce(2 * camera.viewportWidth / 3f, camera.viewportHeight / 2f, -1000));

        system.setPhysicManager(physicManager);
    }

    @Override
    public void render() {
        camera.update();
        world.step(1f / 60f, 6, 2);
        system.setParticlesPosition(Math.round(Math.random() * Gdx.graphics.getWidth()), Math.round(Math.random() * Gdx.graphics.getHeight())); //Random position
        emissionLight.color = new Color((float) Math.random() * 0, (float) Math.random() * 0, /*(float) Math.random()*/1, 1);
        system.render();
        rayHandler.setCombinedMatrix(camera.combined);
        rayHandler.updateAndRender();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("NebulaDemo");
        configuration.setWindowedMode(1024, 768);
        new Lwjgl3Application(new LibraryTest(), configuration);
    }
}
