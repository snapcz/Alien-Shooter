package com.example.alien_shooter.presenter;

import android.os.Parcel;
import android.os.Parcelable;

public class Difficulty implements Parcelable {
    // Apakah player bisa charge attack?
    private int chargeEnabled;

    // darah kapal
    private int playerHealth;
    private int enemyHealth;

    // peluang musuh bisa nembak attack X
    private double chanceSmallAttack;
    private double chanceChargeAttack;

    // kecepatan attack
    private int smallAttackSpeed;
    private int chargeAttackSpeed;

    // waktu attack musuh
    private int enemyAttackTime;

    // waktu gerak musuh
    private int enemyMoveTime;

    protected Difficulty(Parcel in) {
        this.chargeEnabled = in.readInt();
        this.playerHealth = in.readInt();
        this.enemyHealth = in.readInt();
        this.chanceSmallAttack = in.readDouble();
        this.chanceChargeAttack = in.readDouble();
        this.smallAttackSpeed = in.readInt();
        this.chargeAttackSpeed = in.readInt();
        this.enemyAttackTime = in.readInt();
        this.enemyMoveTime = in.readInt();
    }

    public Difficulty() {

    }

    public double getChanceChargeAttack() {
        return chanceChargeAttack;
    }

    public double getChanceSmallAttack() {
        return chanceSmallAttack;
    }

    public int getChargeAttackSpeed() {
        return chargeAttackSpeed;
    }

    public int getEnemyAttackTime() {
        return enemyAttackTime;
    }

    public int getEnemyHealth() {
        return enemyHealth;
    }

    public int getEnemyMoveTime() {
        return enemyMoveTime;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public int getSmallAttackSpeed() {
        return smallAttackSpeed;
    }

    public int getChargeEnabled() {
        return chargeEnabled;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.chargeEnabled);
        parcel.writeInt(this.playerHealth);
        parcel.writeInt(this.enemyHealth);
        parcel.writeDouble(this.chanceSmallAttack);
        parcel.writeDouble(this.chanceChargeAttack);
        parcel.writeInt(this.smallAttackSpeed);
        parcel.writeInt(this.chargeAttackSpeed);
        parcel.writeInt(this.enemyAttackTime);
        parcel.writeInt(this.enemyMoveTime);
    }

    public static final Parcelable.Creator<Difficulty> CREATOR = new Parcelable.Creator<Difficulty>() {
        @Override
        public Difficulty createFromParcel(Parcel source) {
            return new Difficulty(source);
        }

        @Override
        public Difficulty[] newArray(int size) {
            return new Difficulty[size];
        }
    };
}
