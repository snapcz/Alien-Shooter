package com.example.alien_shooter.presenter;

import android.graphics.drawable.Drawable;

import com.example.tubes_2.R;
import com.example.alien_shooter.fragments.GameFragment;
import com.example.alien_shooter.model.Attack;
import com.example.alien_shooter.model.Ship;

import java.util.ArrayList;
import java.util.List;

public class GameStatus {
    GameFragment fragment;
    Difficulty difficulty;

    boolean gameState;

    int countdown;

    public Ship player, enemy;

    List<Attack> attacks;

    public GameStatus(GameFragment fragment, Difficulty difficulty) {
        this.fragment = fragment;
        this.difficulty = difficulty;

        this.attacks = new ArrayList<>();
    }

    public void initializeGame() {
        this.gameState = true;

        Drawable playerShip = fragment.getResources().getDrawable(R.drawable.player_ship);
        int playerWidth = playerShip.getIntrinsicWidth();
        int playerHeight = playerShip.getIntrinsicHeight();

        Drawable enemyShip = fragment.getResources().getDrawable(R.drawable.enemy_ship);
        int enemyWidth = enemyShip.getIntrinsicWidth();
        int enemyHeight = enemyShip.getIntrinsicHeight();

        this.player = new Ship(100, playerWidth, playerHeight);
        this.enemy = new Ship(100, enemyWidth, enemyHeight);

        this.player.setPosition(fragment.getGameView().getWidth() - (playerWidth / 2), fragment.getGameView().getHeight() - playerHeight);
        this.enemy.setPosition(fragment.getGameView().getWidth() - (enemyWidth / 2), 0);

        this.attacks.clear();
        this.countdown = 3;
    }

    public void endGame() {
        this.gameState = false;
    }

    public void reduceCountdown() {
        this.countdown = this.countdown - 1;
    }

    public boolean getGameState() {
        return this.gameState;
    }

    public int getCountdown() {
        return countdown;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }
}
