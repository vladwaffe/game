package com.example.ekz_graf;

import javafx.scene.effect.Light;

class Vector3D {
    private float rotX, rotY, rotZ = 0f;
    public float x, y, z;
    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
    public Light.Point to2D() {
        Vector3D v = new Vector3D(x, y, z);
        rotateVector(v, rotY, -rotX, rotZ);
        Light.Point p;
        /* 3d -> 2d */
        float Z = Cube.getDistance() + v.z;
        p = new Light.Point((Cube.getDistance() * v.x / Z), (Cube.getDistance() * v.y / Z), z, null);
        p.setX(p.getX()+ (double) Cube.getWidth() /2);
        p.setY(p.getY()+ (double) Cube.getHeight() /2);
        return p;
    }
    public void rotateVector(Vector3D p, float thetaX, float thetaY, float thetaZ) {
        float aX, aY, aZ; // temp point
        float camX = 0;
        float camY = 0;
        float camZ = 0;
        aX = p.x;
        aY = p.y;
        aZ = p.z;
        // 3D -> 2D transformation matrix calculation with rotation
        // and camera coordinate parameters
        aY = p.y;
        aZ = p.z;
        // Rotation um x-Achse
        //p[i][x] = px;
        p.y = (float) ((aY-camY)*Math.cos(thetaX)-(aZ-camZ)*Math.sin(thetaX));
        p.z = (float) ((aY-camY)*Math.sin(thetaX)+(aZ-camZ)*Math.cos(thetaX));
        aX = p.x;
        aZ = p.z;
        // Rotation um y-Achse
        p.x = (float) ((aX-camX)*Math.cos(thetaY)+(aZ-camZ)*Math.sin(thetaY));
        //p[i][y]= py;
        p.z = (float) (-(aX-camX)*Math.sin(thetaY)+(aZ-camZ)*Math.cos(thetaY));
        aY = p.y;
        aX = p.x;
        // Rotation um z-Achse
        p.x = (float) ((aX-camX)*Math.cos(thetaZ)-(aY-camY)*Math.sin(thetaZ));
        p.y = (float) ((aY-camY)*Math.cos(thetaZ)+(aX-camX)*Math.sin(thetaZ));
    }
}
