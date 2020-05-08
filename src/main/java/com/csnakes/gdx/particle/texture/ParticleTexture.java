package com.csnakes.gdx.particle.texture;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ParticleTexture {
    private Texture texture;

    public ParticleTexture(String path) {
        texture = new Texture(Gdx.files.internal(path));
    }

    public Texture getTexture() {
        return texture;
    }
}
