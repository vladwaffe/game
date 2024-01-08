package com.example.ekz_graf;

import javafx.scene.effect.Light;

import java.awt.*;

class Cube {
    private static int width = 400;
    private static int height = 600;

    private static float distance;
    private float angle;
    public int size = 50;
    Vector3D ulf, urf, llf, lrf; // upper left front, upper right front, usw..
    Vector3D ulb, urb, llb, lrb; // upper left back ,...
    Vector3D fl, fr, bl, br;





    public Cube(int stX, int stY) {
        angle = (float) Math.toRadians(40);
        distance = (width / 2) / (float) (Math.tan(angle / 2));
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

    public void project(Graphics g) {

        Light.Point center = fl.to2D(); //центр

        Light.Point pulf = ulf.to2D(); //левый задний верхний
        Light.Point purf = urf.to2D(); //правый задний верхний
        Light.Point pllf = llf.to2D(); //левый задний нижний
        Light.Point plrf = lrf.to2D(); //правый задний нижний
        Light.Point pulb= ulb.to2D(); //левый верхний передний
        Light.Point purb= urb.to2D(); //правый верхний передний
        Light.Point pllb= llb.to2D(); //левый передний нижний
        Light.Point plrb= lrb.to2D(); //правый передний нижний

        g.setColor(Color.gray);
        Polygon right = new Polygon();
        right.addPoint((int)purf.getX(), (int)purf.getY());
        right.addPoint((int)purb.getX(), (int)purb.getY());
        right.addPoint((int)plrb.getX(), (int)plrb.getY());
        right.addPoint((int)plrf.getX(), (int)plrf.getY());
        g.fillPolygon(right);
        g.setColor(Color.gray);
        Polygon left = new Polygon();
        left.addPoint((int)pulf.getX(), (int)pulf.getY());
        left.addPoint((int)pulb.getX(), (int)pulb.getY());
        left.addPoint((int)pllb.getX(), (int)pllb.getY());
        left.addPoint((int)pllf.getX(), (int)pllf.getY());
        g.fillPolygon(left);
        g.setColor(Color.WHITE);
        Polygon front = new Polygon();
        front.addPoint((int) pulb.getX(), (int) pulb.getY());
        front.addPoint((int) purb.getX(), (int) purb.getY());
        front.addPoint((int) plrb.getX(), (int) plrb.getY());
        front.addPoint((int) pllb.getX(), (int) pllb.getY());
        g.fillPolygon(front);
        g.setColor(Color.LIGHT_GRAY);
        Polygon up = new Polygon();
        up.addPoint((int) pulf.getX(), (int) pulf.getY());
        up.addPoint((int) pulb.getX(), (int) pulb.getY());
        up.addPoint((int) purb.getX(), (int) purb.getY());
        up.addPoint((int) purf.getX(), (int) purf.getY());
        g.fillPolygon(up);
    }

    float zoomFactor = 5;
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

    public static int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public void motion(Graphics g){
        int startX = 200;
        int startY = 0;
        int endX = 0;
        int endY = 600;
        int x, y = 0;
        g.fillOval(startX-1, startY-1, 2, 2);
        g.drawLine(200, 0, 0, 600);
        while(y<600){

        }


    }
}
