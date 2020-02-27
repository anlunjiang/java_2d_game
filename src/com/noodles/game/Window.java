package com.noodles.game;

import javax.swing.JFrame;
// JFrame makes windows in Java

public class Window extends JFrame {

    public Window() {
        setTitle("MyFirstJavaWindow");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allows to exit the window
        setContentPane(new GamePanel(1280, 720));
        pack(); // compacts window
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
