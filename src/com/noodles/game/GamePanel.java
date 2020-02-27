package com.noodles.game;

import javax.swing.JPanel;
import java.awt.Dimension;

public class GamePanel extends JPanel{

    public static int width;
    public static int height;

    public GamePanel(int width, int height) {

        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus(); // allows jpanel to have input when jframe is made
    }

}
