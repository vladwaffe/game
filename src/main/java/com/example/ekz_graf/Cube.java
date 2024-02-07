package com.example.ekz_graf;

import javafx.scene.effect.Light;

import java.awt.*;

class Cube {
    private static final int width = 400; //это что - константа? тогда private static final
    private static final int height = 600;
    public final int size = 50;
    private final float zoomFactor = 5;
    private static float distance;
    private float angle;

    Vector3D ulf, urf, llf, lrf; // upper left front, upper right front, usw..
    Vector3D ulb, urb, llb, lrb; // upper left back ,...
    Vector3D fl, fr, bl, br;

//Лишние пробелы




    public Cube(int stX, int stY) {
        angle = (float) Math.toRadians(40);
        distance = ((float) width / 2) / (float) (Math.tan(angle / 2));
        int size_2 = size/2;
        int startx = stX - size_2;
        int starty = stY - size_2;

        ulf = new Vector3D(startx, starty, 0);
        urf = new Vector3D(startx + size, starty, 0);
        llf = new Vector3D(startx, starty+size, 0);
        lrf = new Vector3D(startx + size, starty + size, 0);
        ulb = new Vector3D(startx, starty, -size);
        urb = new Vector3D(startx + size, starty, -size);
        llb = new Vector3D(startx, starty+size, -size);
        lrb = new Vector3D(startx + size, starty + size, -size);


        fl = new Vector3D(startx+(size/2), starty, -(size/2));

    }

    public void move(int dx, int dy)
    {
        ulf.x += dx;
        urf.x += dx;
        llf.x += dx;
        lrf.x += dx;
        ulb.x += dx;
        urb.x += dx;
        llb.x += dx;
        lrb.x += dx;

        ulf.y += dy;
        urf.y += dy;
        llf.y += dy;
        lrf.y += dy;
        ulb.y += dy;
        urb.y += dy;
        llb.y += dy;
        lrb.y += dy;

    }
    private Polygon create_poligon(Light.Point p1, Light.Point p2, Light.Point p3, Light.Point p4){
        Polygon poligon = new Polygon();
        poligon.addPoint((int)p1.getX(), (int)p1.getY());
        poligon.addPoint((int)p2.getX(), (int)p2.getY());
        poligon.addPoint((int)p3.getX(), (int)p3.getY());
        poligon.addPoint((int)p4.getX(), (int)p4.getY());
        return poligon;
    }

    public void project(Graphics g) {
        Light.Point pulf = ulf.to2D(); //левый задний верхний
        Light.Point purf = urf.to2D(); //правый задний верхний
        Light.Point pllf = llf.to2D(); //левый задний нижний
        Light.Point plrf = lrf.to2D(); //правый задний нижний
        Light.Point pulb= ulb.to2D(); //левый верхний передний
        Light.Point purb= urb.to2D(); //правый верхний передний
        Light.Point pllb= llb.to2D(); //левый передний нижний
        Light.Point plrb= lrb.to2D(); //правый передний нижний
        g.setColor(Color.gray);
        g.fillPolygon(create_poligon(purf, purb, plrb, plrf)); //right
        g.setColor(Color.gray);
        g.fillPolygon(create_poligon(pulf, pulb, pllb, pllf)); //left
        g.setColor(Color.WHITE);
        g.fillPolygon(create_poligon(pulb, purb, plrb, pllb)); //front
        g.setColor(Color.LIGHT_GRAY);
        g.fillPolygon(create_poligon(pulf, pulb, purb, purf)); //up
    }
    public void further(int zoomFactor) {
        ulf.z -= zoomFactor;
        urf.z -= zoomFactor;
        llf.z -= zoomFactor;
        lrf.z -= zoomFactor;
        ulb.z -= zoomFactor;
        urb.z -= zoomFactor;
        llb.z -= zoomFactor;
        lrb.z -= zoomFactor;

    }
    public void closer(int zoomFactor) {
        ulf.z += zoomFactor;
        urf.z += zoomFactor;
        llf.z += zoomFactor;
        lrf.z += zoomFactor;
        ulb.z += zoomFactor;
        urb.z += zoomFactor;
        llb.z += zoomFactor;
        lrb.z += zoomFactor;

    }
    public static int getWidth(){
        return width;
    }
    public static int getHeight(){
        return height;
    }
    public static float getDistance() {
        return distance;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }
    public float getAngle() {
        return angle;
    }
    public void setAngle(float angle) {
        this.angle = angle;
    }
   /* public void motion(Graphics g){
        //что за мэджик намберс? в константы!!!
        int startX = 200;
        int startY = 0;
        int endX = 0;
        int endY = 600;
        int x, y = 0;
        g.fillOval(startX-1, startY-1, 2, 2);
        g.drawLine(200, 0, 0, 600);
        while(y<600){
        }
    }*/
}
