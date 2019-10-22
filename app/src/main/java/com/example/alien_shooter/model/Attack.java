package com.example.alien_shooter.model;

import android.graphics.Point;

public class Attack {
    protected Ship source;

    protected int positionX, positionY, velocity;
    protected int id, damage;

    public Attack(Ship source, int positionX, int positionY, int velocity, int id, int damage) {
        this.source = source;
        this.positionX = positionX;
        this.positionY = positionY;
        this.velocity = velocity;
        this.damage = damage;
    }

    public Ship getSource() {
        return this.source;
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

    public int getId() {
        return this.id;
    }

    public int getDamage() {
        return this.damage;
    }

    public void move() {
        this.positionY += this.velocity;
    }
}
