package com.noodles.game;

import com.noodles.game.states.GameStateManager;
import com.noodles.game.utils.KeyHandler;
import com.noodles.game.utils.MouseHandler;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class GamePanel extends JPanel implements Runnable { // creates a thread

    public static int width;
    public static int height;

    private Thread thread;
    private BufferedImage img;
    private Graphics2D g;
    private boolean running = false;

    private MouseHandler mouse;
    private KeyHandler key;

    private GameStateManager gsm;

    public GamePanel(int width, int height) {
        this.width = width; // "this" replaced with the instance object name (GamePanel.width = width)
        this.height  = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus(); // allows jpanel to have input when jframe is made
    }

    public void addNotify() {
        super.addNotify(); // inputs from KBM

        if(thread == null) {
            thread = new Thread(this, "GameThread");
            thread.start();
        }
    }

    public void init() {
        running = true;
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); // will be drawn onto screen
        g = (Graphics2D) img.getGraphics(); // can draw on the buffered image

        mouse = new MouseHandler();
        key = new KeyHandler();

        gsm = new GameStateManager();
    }

    public void run() {
        init();
        // fixed time game loop
        final double GAME_HERTZ = 60.0;
        final double TBU =  1000000000 / GAME_HERTZ; // time before update nanoseconds

        final int MUBR = 5; // Max Updates before render
        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        final double TARGET_FPS = 30.0;
        final double TTBR = 1000000000 / TARGET_FPS; // Total Time Before Render

        int frameCount = 0;
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);
        int oldFrameCount = 0;

        while(running) {
            double now = System.nanoTime();
            int updateCount = 0;

            while(((now - lastUpdateTime) > TBU) && (updateCount < MUBR)) {
                update();
                input(mouse, key);
                lastUpdateTime += TBU;
                updateCount++;
            }

            if(now - lastUpdateTime > TBU) {
                lastUpdateTime = now - TBU;
            }

            input(mouse, key);
            render();
            draw();
            lastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if(thisSecond > lastSecondTime) {
                if (frameCount != oldFrameCount) {
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            while(now - lastRenderTime < TTBR && now - lastUpdateTime < TBU) {
                Thread.yield();
                try {
                    Thread.sleep(1);
                } catch(Exception e) {
                    System.out.println("ERROR: Yielding thread");
                }
                now = System.nanoTime();
            }
        }
    }

    public void update() {
        gsm.update();
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        gsm.input(mouse, key);
    }

    public void render() {
        if(g != null) {
            g.setColor(new Color(66, 134,244));
            g.fillRect(0, 0, width, height); // background
            gsm.render(g);
        }
    }

    public void draw() {
        Graphics g2 = (Graphics) this.getGraphics(); // instantiate another graphics obj
        g2.drawImage(img, 0, 0, width, height, null); // draws onto screen
        g2.dispose(); // need to remove after
    }
}
