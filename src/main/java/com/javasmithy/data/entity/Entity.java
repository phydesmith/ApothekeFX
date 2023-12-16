package com.javasmithy.data.entity;

import javafx.scene.image.Image;

public class Entity {
    private String spritePath;
    private Image sprite;
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
