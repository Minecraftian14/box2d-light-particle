package com.alyrow.gdx.particle.texture;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

/**
 * @author alyrow
 * This class load (TEXTURE or HALO mode) random texture for differents particles.
 */

public class RandomParticleTexture extends ParticleTexture {
    /**
     * Libgdx textures array
     * @see Texture
     * @see Array
     */
    private Array<Texture> textures = new Array<>();

    /**
     * Default constructor
     */
    public RandomParticleTexture() {}

    /**
     * Add texture to the list
     * @param texture {@link Texture}
     */
    public void addTexture(Texture texture) {
        textures.add(texture);
    }

    /**
     * Add texture to the list
     * @param path Path of the texture
     */
    public void addTexture(String path) {
        textures.add(new Texture(path));
    }

    /**
     * Remove texture from the list
     * @param texture {@link Texture}
     */
    public void removeTexture(Texture texture) {
        textures.removeValue(texture, true);
    }

    /**
     * Intern usage only
     * @return Return random texture
     */
    @Override
    public Texture getTexture() {
        Texture t = textures.random();
        if (t instanceof AnimatedTexture) return new AnimatedTexture(((AnimatedTexture) t).textures, ((AnimatedTexture) t).fps);
        return t;
    }

    @Override
    public void dispose() {
        textures.forEach(Texture::dispose);
    }

}
