package com.example.alien_shooter.model;

import android.graphics.Point;

public class Ship {
    protected int health;

    protected int width, height;

    protected int positionX, positionY;

    public Ship(int health, int width, int height) {
        this.health = health;
        this.width = width;
        this.height = height;
    }

    public int getHealth() {
        return this.health;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Point getPosition() {
        return new Point(this.positionX, this.positionY);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }
}
