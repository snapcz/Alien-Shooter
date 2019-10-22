package com.example.alien_shooter.fragments;

import com.example.alien_shooter.presenter.GameStatus;

public class MovementThread implements Runnable {
    GameStatus status;

    public MovementThread(GameStatus status) {
        this.status = status;
    }

    @Override
    public void run() {
        while (this.status.getGameState()) {
            try {
                Thread.sleep(this.status.getDifficulty().getEnemyMoveTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
