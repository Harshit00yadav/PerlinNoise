package com;

import java.awt.Color;
import java.awt.Graphics2D;

public class Particle {
    double[] position;
    double[] previousPosition;
    double[] velocity;
    double airFriction = 1.2d;

    public Particle(double x, double y){
        position = new double[]{x, y};
        previousPosition = new double[]{x, y};
        velocity = new double[2];
    }
    public void update(){
        velocity[0] = (position[0] - previousPosition[0]) / airFriction;
        velocity[1] = (position[1] - previousPosition[1]) / airFriction;
        previousPosition[0] = position[0];
        previousPosition[1] = position[1]; 
        position[0] += velocity[0];
        position[1] += velocity[1];
    }
    public void addForce(double[] force){
        position[0] += force[0] / airFriction;
        position[1] += force[1] / airFriction;
    }
    public void render(Graphics2D g2){
        g2.setPaint(new Color(255, 255, 255));
        g2.fillOval((int)position[0], (int)position[1], 5, 5);
    }
}
