package com.example.alien_shooter.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import com.example.tubes_2.R;
import com.example.alien_shooter.model.Ship;
import com.example.alien_shooter.presenter.GameStatus;

public class DrawerThread extends AsyncTask<Integer, Integer, Void> {
    GameFragment game;
    GameView gameView;

    ImageView playerView, enemyView;

    Bitmap playerBitmap, enemyBitmap;

    Context context;

    long previousTime, fps;

    public DrawerThread(GameFragment game, Context ct) {
        this.previousTime = 0;
        this.fps = 60; // PC MASTER RACE, MOBILE PEASANTS!!!git
        this.game = game;
        this.context = ct;

        this.gameView = game.gameView;
        this.playerView = game.playerView;
        this.enemyView = game.enemyView;

        Resources res = game.getResources();

        this.playerBitmap = BitmapFactory.decodeResource(res, R.drawable.player_ship);
        this.enemyBitmap = BitmapFactory.decodeResource(res, R.drawable.enemy_ship);
    }



    @Override
    protected Void doInBackground(Integer... integers) {
        GameStatus status = this.game.gameStatus;
        SurfaceHolder gameHolder = this.gameView.mSurfaceHolder;

        Log.d("run: ", status.getGameState() + "");
        while (status.getGameState()) {
            Log.d("run: ", "starting");
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedTimeMs = currentTimeMillis - previousTime;
            long sleepTimeMs = (long) (1000f / fps - elapsedTimeMs);

            Canvas gameCanvas = gameHolder.lockCanvas();

            try {
                /*if (gameCanvas == null) {
                    Thread.sleep(1);

                    continue;
                } else*/
                if (status.getCountdown() > 0) {
                    Paint pt = new Paint();
                    pt.setColor(Color.CYAN);
                    pt.setTextSize(48);

                    int xPos = (this.game.getWidth() / 2);
                    int yPos = (int) ((this.game.getHeight() / 2) - ((pt.descent() + pt.ascent()) / 2));

                    //gameCanvas.drawColor(ResourcesCompat.getColor(this.game.getResources(),R.color.colorPrimaryDark,null));
                    //gameCanvas.drawText(Integer.toString(status.getCountdown()), xPos, yPos, pt);

                    //this.game.initiateCountdown(xPos,yPos);

                    // just decrement here
                    status.reduceCountdown();

                    //gameHolder.unlockCanvasAndPost(gameCanvas);

                    Thread.sleep(1000);

                    continue;
                } else if (sleepTimeMs > 0) {
                    Thread.sleep(sleepTimeMs);
                }

                synchronized (gameHolder) {
                    /*
                    gameCanvas.drawBitmap(this.playerBitmap, this.game.player.getPositionX(), this.game.player.getPositionY(), null);
                    gameCanvas.drawBitmap(this.enemyBitmap, this.game.enemy.getPositionX(), this.game.enemy.getPositionY(), null);
                    */
                }

                gameHolder.unlockCanvasAndPost(gameCanvas);
                this.previousTime = currentTimeMillis;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private double getPercentageWidth(Ship ship, SurfaceView view) {
        return (double)ship.getHealth() / (double)view.getWidth();
    }

    private void determineGameStatus() {

    }
}
