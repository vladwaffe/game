package com.example.ekz_graf;

import javafx.scene.effect.Light;

import java.awt.*;

class Enemy {
    private int plase;
    private static final int width = 400;
    private static final int height = 600;
    private final float zoomFactor = 5;
    private static float distance;
    private float angle;
    public final int size = 10;
    Vector3D ulf;
    private Vector3D urf;
    private Vector3D llf;
    private Vector3D lrf;
    private Vector3D ulb;
    private Vector3D urb;
    Vector3D llb;
    private Vector3D lrb;
    private Vector3D fl;

    public Enemy(int stX, int stY, int plase) {
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
        this.plase = plase;

    }

    public void move(int dx, int dy)
    {
        fl.x += dx;
        fl.y += dy;

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
    private Polygon create_poligon(Light.Point p1, Light.Point p2, Light.Point p3){
        Polygon poligon = new Polygon();
        poligon.addPoint((int)p1.getX(), (int)p1.getY());
        poligon.addPoint((int)p2.getX(), (int)p2.getY());
        poligon.addPoint((int)p3.getX(), (int)p3.getY());
        return poligon;
    }
    public void project(Graphics g) {
        Light.Point center = fl.to2D(); //центр
        Light.Point pllf = llf.to2D(); //левый задний нижний
        Light.Point plrf = lrf.to2D(); //правый задний нижний
        Light.Point pllb= llb.to2D(); //левый передний нижний
        Light.Point plrb= lrb.to2D(); //правый передний нижний
        g.setColor(Color.red);
        g.fillPolygon(create_poligon(pllf, plrf, plrb, pllb));//down
        g.fillPolygon(create_poligon(center, plrb, plrf));//right
        g.fillPolygon(create_poligon(center, pllb, pllf));//left
        g.setColor(Color.pink);
        Polygon front = new Polygon();
        g.fillPolygon(create_poligon(center, plrb, pllb));//front

    }
    public void further(int zoomFactor) {
        fl.z -= zoomFactor;
        ulf.z -= zoomFactor;
        urf.z -= zoomFactor;
        llf.z -= zoomFactor;
        lrf.z -= zoomFactor;
        ulb.z -= zoomFactor;
        urb.z -= zoomFactor;
        llb.z -= zoomFactor;
        lrb.z -= zoomFactor;

    }
    public void closer() {
        fl.z += zoomFactor;
        ulf.z += zoomFactor;
        urf.z += zoomFactor;
        llf.z += zoomFactor;
        lrf.z += zoomFactor;
        ulb.z += zoomFactor;
        urb.z += zoomFactor;
        llb.z += zoomFactor;
        lrb.z += zoomFactor;

    }
    public int getPlase() {
        return plase;
    }
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
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
}
