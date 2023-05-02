package com;

import java.awt.Color;
import java.awt.Graphics2D;

public class Grid {
    double[][] cell;
    int cellSeperation;
    double time;
    public Grid(){
        cell = new double[24][24];
        cellSeperation = 20;
    }
    public void noiseUpdate(){
        for (int r=0; r<cell.length; r++){
            for (int c=0; c<cell[r].length; c++){
                cell[r][c] = (PerlinNoise.noise(r*0.1, c*0.1, time) + 1d) * 2 * Math.PI;
            }
        }
    }
    public void render(Graphics2D g2){
        g2.setPaint(new Color(55, 55, 55));
        for (int r=0; r<cell.length; r++){
            for (int c=0; c<cell[r].length; c++){
                g2.drawLine(10 + r*cellSeperation, 10 + c*cellSeperation, (int)(10 + r*cellSeperation + 10*Math.cos(cell[r][c])), (int)(10 + c*cellSeperation + 10*Math.sin(cell[r][c])));
            }
        }
    }
}
