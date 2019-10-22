package com.example.alien_shooter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.alien_shooter.MainActivity;
import com.example.tubes_2.R;

public class MenuFragment extends Fragment implements View.OnClickListener{
    Button startGame,score;
    MainActivity ma;


    public MenuFragment(){
        //empty constructor
    }

    public void setActivity(MainActivity ma){this.ma = ma;}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_menu,container,false);
        startGame = view.findViewById(R.id.btn_startGame);
        score = view.findViewById(R.id.btn_score);
        startGame.setOnClickListener(this);
        score.setOnClickListener(this);

        return view;
    }

    public static MenuFragment newInstance( ){
        MenuFragment mf = new MenuFragment();
        Bundle args = new Bundle();
        mf.setArguments(args);
        return mf;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==startGame.getId()){
            this.ma.changePage("game");
        }
        else this.ma.changePage("score");
    }
}
