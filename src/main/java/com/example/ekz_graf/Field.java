package com.example.ekz_graf;

import java.awt.*;

public class Field{
    public Field(){}
    public void draw(Graphics g){
        g.setColor(Color.GREEN);
        Polygon front = new Polygon();
        front.addPoint(0, 560);
        front.addPoint(0, 538);
        front.addPoint(400, 538);
        front.addPoint(400, 560);
        g.fillPolygon(front);
        Polygon up = new Polygon();
        up.addPoint(0, 538);
        up.addPoint(195, 145);
        up.addPoint(205, 145);
        up.addPoint(400, 538);
        g.fillPolygon(up);
    }


}
