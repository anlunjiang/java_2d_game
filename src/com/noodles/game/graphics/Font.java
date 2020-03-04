package com.noodles.game.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Font {

    private BufferedImage FONTSHEET = null; // sheet of sprite coords
    private BufferedImage[][] spriteArray;
    private final int TILE_SIZE = 32;
    public int w;
    public int h;
    public int wLetter;
    public int hLetter;

    // Constructor
    public Font(String file) {
        w = TILE_SIZE;
        h = TILE_SIZE;

        System.out.println("Loading: " + file + "...");

        FONTSHEET = loadFont(file);

        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadFontArray();
    }

    public Font(String file, int w, int h) {
        this.w = w;
        this.h = h;
        System.out.println("Loading: " + file + "...");
        FONTSHEET = loadFont(file);

        wLetter = FONTSHEET.getWidth() / w;
        hLetter = FONTSHEET.getHeight() / h;
        loadFontArray();
    }

    public void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setWidth(int i) {
        w = i;
        wLetter = FONTSHEET.getWidth() / w;
    }

    public void setHeight(int i) {
        h = i;
        hLetter = FONTSHEET.getWidth() / h;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    private BufferedImage loadFont(String file) {
        BufferedImage sprite = null;
        try {
            sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(file));
        } catch (Exception e) {
            System.out.println("ERROR: could not load file: " + file);
        }

        return sprite;
    }

    public void loadFontArray() {
        spriteArray = new BufferedImage[wLetter][hLetter]; // total number of sprites in spritesheet

        for (int x = 0; x < wLetter; x++) {
            for (int y = 0; y < hLetter; y++) {
                spriteArray[x][y] = getLetter(x, y);
            }
        }
    }

    public BufferedImage getfontsheet() {
        return FONTSHEET;
    }

    public BufferedImage getLetter(int x, int y) {

        return FONTSHEET.getSubimage(x * w, y * h, w, h); // w and h are the sprite tile size
        //x, y – the X, Y coordinates of the upper-left corner of the specified rectangular region
        //w, h – the width/height of the specified rectangular region
        //Returns:
        //a BufferedImage that is the subimage of this BufferedImage
    }

    public BufferedImage getFont(char letter) {
        int value = letter - 65;
        // Font letter image needs to match with ascii alphabet table

        int x = value % wLetter; // mod to get x value - below the max width
        int y = value / wLetter; // works because it's a 10 x 8 sprite array
        System.out.println("value = " + value);
        System.out.println("x, y = " + x + " " + y);

        return getLetter(x, y);
    }
}
