package com.noodles.game.states;

import com.noodles.game.utils.KeyHandler;
import com.noodles.game.utils.MouseHandler;

import java.awt.Graphics2D;

public abstract class GameState { // abstract class - cant be instantiated, only be inherited from another class

    private GameStateManager gsm;

    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void render(Graphics2D g);




}
