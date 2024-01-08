package com.example.ekz_graf;

import java.awt.*;

public class Field{
    public Field(Graphics g){
        g.setColor(Color.PINK);
        Polygon front = new Polygon();
        front.addPoint(0, 560);
        front.addPoint(0, 540);
        front.addPoint(400, 540);
        front.addPoint(400, 560);
        g.fillPolygon(front);



    }
}
