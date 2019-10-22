package com.example.alien_shooter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.alien_shooter.fragments.GameFragment;
import com.example.alien_shooter.fragments.MenuFragment;
import com.example.alien_shooter.presenter.Difficulty;
import com.example.tubes_2.R;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    GameFragment gameFragment;
    MenuFragment menuFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.fragmentManager = this.getSupportFragmentManager();
        this.gameFragment = GameFragment.newInstance(new Difficulty());
        this.menuFragment = MenuFragment.newInstance();
        this.menuFragment.setActivity(this);
        this.changePage("main");
    }

    public void changePage(String page){
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        switch(page) {
            case "main":
                if(this.menuFragment.isAdded()) ft.show(menuFragment);
                else{
                    ft.add(R.id.fragment_container, this.menuFragment).addToBackStack(null);
                    ft.show(menuFragment);
                }
                if(this.gameFragment.isAdded()) ft.hide(gameFragment);
                break;
            case "game":
                if(this.gameFragment.isAdded()) ft.show(gameFragment);
                else{
                    ft.add(R.id.fragment_container, this.gameFragment).addToBackStack(null);
                    ft.show(this.gameFragment);
                }
                if(this.menuFragment.isAdded()) ft.hide(menuFragment);
                break;
            default:
                break;
        }
        ft.commit();
    }
}
