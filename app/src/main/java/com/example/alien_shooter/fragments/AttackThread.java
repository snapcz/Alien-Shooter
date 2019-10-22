package com.example.alien_shooter.fragments;

import com.example.alien_shooter.presenter.GameStatus;

public class AttackThread implements Runnable {
    GameStatus status;

    public AttackThread(GameStatus status) {
        this.status = status;
    }

    @Override
    public void run() {
        while (this.status.getGameState()) {
            /**
             * Kocok random
             * tambahin ke list, done!
             */
            try {
                Thread.sleep(this.status.getDifficulty().getEnemyAttackTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
