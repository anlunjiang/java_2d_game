package com.noodles.game.states;

import com.noodles.game.graphics.Font;
import com.noodles.game.graphics.Sprite;
import com.noodles.game.utils.KeyHandler;
import com.noodles.game.utils.MouseHandler;
import com.noodles.game.utils.Vector2f;

import java.awt.Graphics2D;

public class PlayState extends GameState{

    private Font font;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        font = new Font("font/ZeldaFont.png", 16, 16);
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

    }

    public void render(Graphics2D g) {
//        g.setColor(Color.RED);
//        g.fillRect(100, 100, 64, 64);
        Sprite.drawArray(g, font, "aa your mooom", new Vector2f(100, 100), 32, 32, 32, 0 );
    }

}
