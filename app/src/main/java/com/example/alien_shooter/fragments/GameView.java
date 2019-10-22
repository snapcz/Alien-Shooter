package com.example.alien_shooter.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.res.ResourcesCompat;

import com.example.tubes_2.R;
import com.example.alien_shooter.presenter.GameStatus;

public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    GameFragment gameFragment;
    SurfaceHolder mSurfaceHolder;
    int width,height;
    long previousTime,fps;
    Bitmap playerBitmap, enemyBitmap;
    Thread drawThread;

    public GameView(Context ctx) {
        super(ctx);

        this.setAlpha(0);
        this.setZOrderOnTop(true);

        this.getHolder().addCallback(this);
    }

    public GameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        this.setAlpha(0);
        this.setZOrderOnTop(true);

        getHolder().addCallback(this);
    }

    public GameView(Context ctx, GameFragment fragment) {
        super(ctx);
        this.gameFragment = fragment;

        this.setZOrderMediaOverlay(true);
        this.getHolder().addCallback(this);

        this.mSurfaceHolder = this.getHolder();
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d("surfaceCreated: ","created");
        this.gameFragment.initializeGame();
        this.fps=60;
        this.previousTime =0;

        Canvas surfaceCanvas = this.mSurfaceHolder.lockCanvas();
        this.width = surfaceCanvas.getWidth();
        this.height = surfaceCanvas.getHeight();

        Paint pt = new Paint();
        pt.setColor(Color.WHITE);
        surfaceCanvas.drawColor(pt.getColor());

        this.mSurfaceHolder.unlockCanvasAndPost(surfaceCanvas);
        this.playerBitmap = BitmapFactory.decodeResource(this.gameFragment.getResources(),R.drawable.player_ship);
        this.enemyBitmap = BitmapFactory.decodeResource(this.gameFragment.getResources(),R.drawable.enemy_ship);

        this.startDraw();
    }

    private void startDraw() {
        if(this.mSurfaceHolder.getSurface().isValid() && drawThread == null){
            drawThread = new Thread(this);
            drawThread.start();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        // do nothing atm
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW, PorterDuff.Mode.CLEAR);
        super.onDraw(canvas);
    }

    @Override
    public void run() {
        GameStatus status = gameFragment.gameStatus;

        Log.d("run: ", status.getGameState() + "");
        while (status.getGameState()) {
            Log.d("run: ", "starting");
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedTimeMs = currentTimeMillis - previousTime;
            long sleepTimeMs = (long) (1000f / fps - elapsedTimeMs);

            Canvas gameCanvas = this.mSurfaceHolder.lockCanvas();
            gameCanvas.drawColor(ResourcesCompat.getColor(this.gameFragment.getResources(), R.color.colorPrimaryDark,null));

            try {
                if (gameCanvas == null) {
                    Thread.sleep(1);

                    continue;
                } else if (status.getCountdown() > 0) {
                    Paint pt = new Paint();
                    pt.setColor(Color.CYAN);
                    pt.setTextSize(48);

                    int xPos = (this.width / 2);
                    int yPos = (int) ((this.height / 2) - ((pt.descent() + pt.ascent()) / 2));


                    gameCanvas.drawText(Integer.toString(status.getCountdown()), xPos, yPos, pt);

                    // just decrement here
                    status.reduceCountdown();

                    this.mSurfaceHolder.unlockCanvasAndPost(gameCanvas);

                    Thread.sleep(1000);

                    continue;
                } else if (sleepTimeMs > 0) {
                    Thread.sleep(sleepTimeMs);
                }

                synchronized (mSurfaceHolder) {

                    gameCanvas.drawBitmap(this.playerBitmap, this.gameFragment.getPlayerPosX(), this.gameFragment.getPlayerPosY(), null);
                    gameCanvas.drawBitmap(this.enemyBitmap, this.gameFragment.getEnemyX(), this.gameFragment.getEnemyY(), null);

                }

                this.mSurfaceHolder.unlockCanvasAndPost(gameCanvas);
                this.previousTime = currentTimeMillis;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
