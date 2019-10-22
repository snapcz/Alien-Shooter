package com.example.alien_shooter.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tubes_2.R;
import com.example.alien_shooter.presenter.GameStatus;
import com.example.alien_shooter.presenter.Difficulty;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    DrawerThread drawerThread;

    GameView gameView;

    ImageView playerView;
    ImageView enemyView;

    GameStatus gameStatus;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);


        // this.difficulty = this.getArguments().getParcelable("difficulty");

        this.gameStatus = new GameStatus(this, null);

        this.playerView = view.findViewById(R.id.playerView);
        this.enemyView = view.findViewById(R.id.enemyView);


        LinearLayout gameWrapper = view.findViewById(R.id.gameView);
        this.gameView = new GameView(this.getContext(), this);
        gameWrapper.addView(this.gameView);
        return view;
    }

    public void initializeGame() {
        this.gameStatus.initializeGame();
    }


    public void startGame(){
        this.drawerThread = new DrawerThread(this,getContext());
        this.drawerThread.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,0);
    }

    public void endGame() {
        this.gameStatus.endGame();
    }

    public static GameFragment newInstance(Difficulty difficulty) {
        GameFragment fragment = new GameFragment();

        Bundle args = new Bundle();
        args.putParcelable("difficulty", difficulty);
        fragment.setArguments(args);

        return fragment;
    }

    public GameView getGameView() {
        return gameView;
    }

    public int getWidth(){return this.gameView.width;}

    public int getHeight() {return this.gameView.height;}

    public int getPlayerPosX(){
        return this.gameStatus.player.getPositionX();
    }

    public int getPlayerPosY(){
        return this.gameStatus.player.getPositionY();
    }

    public int getEnemyY() {
        return this.gameStatus.enemy.getPositionX();
    }

    public int getEnemyX() {
        return this.gameStatus.enemy.getPositionY();
    }
}
