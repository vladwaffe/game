package com.example.ekz_graf;
import java.awt.*;


public class Stars {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Stars(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.drawLine(x-6, y, x-2, y-2);
        g.drawLine(x-2, y-2, x, y-6);
        g.drawLine(x, y-6, x+2, y-2);
        g.drawLine(x+2, y-2, x+6, y);
        g.drawLine(x+6, y, x+2, y+2);
        g.drawLine(x+2, y+2, x, y+6);
        g.drawLine(x-2, y+2, x-6, y);
    }
    public void motion(int dy){
        y = y + dy;
    }
}
