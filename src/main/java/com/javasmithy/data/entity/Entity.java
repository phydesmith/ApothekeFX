package com.javasmithy.data.entity;

import javafx.scene.image.Image;

/**
 * Base entity class for game objects but also stait GUI objects
 */
public class Entity {
    /**
     * the filepath for the image
     */
    private String spritePath;
    /**
     * an image object that is loaded from the spritepath
     */
    private Image sprite;
    /**
     * a description or name of the object
     */
    private String description;

    public Entity(String spritePath, String description) {
        this.spritePath = spritePath;
        this.sprite = new Image(spritePath);
        this.description = description;
    }

    public String getSpritePath() {
        return spritePath;
    }

    public void setSpritePath(String spritePath) {
        this.spritePath = spritePath;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
