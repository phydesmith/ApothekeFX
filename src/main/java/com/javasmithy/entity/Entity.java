package com.javasmithy.entity;

import javafx.scene.image.Image;

public class Entity {
    private String spritePath;
    private Image sprite;
    private String name;

    public Entity(String spritePath, String name) {
        this.spritePath = spritePath;
        this.sprite = new Image(spritePath);
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
