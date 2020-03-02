package com.noodles.game.states;

import com.noodles.game.utils.KeyHandler;
import com.noodles.game.utils.MouseHandler;

import java.awt.Graphics2D;
import java.awt.Color;

public class PlayState extends GameState{

    public PlayState(GameStateManager gsm) {
        super(gsm);
    }

    public void update(){

    }

    public void input(MouseHandler mouse, KeyHandler key) {
        if(key.up.down) {
            System.out.println("'W' is being pressed");
        }
        if(mouse.getButton() != -1) {
            System.out.println(mouse.getButton());
        }
        System.out.println(mouse.getX());
        //System.out.println(mouse.getX());
        //System.out.println(mouse.getButton());

    }

    public void render(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillRect(100, 100, 64, 64);
    }

}
