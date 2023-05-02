package com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
// import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{
    double temp;
    Timer timer;
    Grid flowGrid;
    Vector<Particle> particles = new Vector<Particle>();
    public GamePanel(){
        timer = new Timer(15, this);
        timer.start();
        flowGrid = new Grid();
        for (int i=0; i<100; i++){
            particles.addElement(new Particle(250 + Math.random()*250, 250 + Math.random()*250));
        }
        this.setPreferredSize(new Dimension(500, 500));
        this.setBackground(new Color(15, 15, 15));
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        flowGrid.render(g2);
        for (int i=0; i<particles.size(); i++){
            particles.get(i).render(g2);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        flowGrid.noiseUpdate();
        flowGrid.time += 0.005;
        for (int i=0; i<particles.size(); i++){
            particles.get(i).update();
            int r = (int)((particles.get(i).position[0] - 10) / flowGrid.cellSeperation);
            int c = (int)((particles.get(i).position[1] - 10) / flowGrid.cellSeperation);
            try {
                particles.get(i).addForce(new double[]{Math.cos(flowGrid.cell[r][c]), Math.sin(flowGrid.cell[r][c])});
            } catch (Exception e1) {
                System.out.println("error : " + e);
            }

            // constrain point inside the boundary

            if (particles.get(i).position[0] <= 20d){
                particles.get(i).position[0] = 470d;
                particles.get(i).previousPosition[0] = particles.get(i).position[0] - particles.get(i).velocity[0];
            } else if( particles.get(i).position[0] >= 480d ){
                particles.get(i).position[0] = 30d;
                particles.get(i).previousPosition[0] = particles.get(i).position[0] - particles.get(i).velocity[0];
            }
            if (particles.get(i).position[1] <= 20d){
                particles.get(i).position[1] = 470d;
                particles.get(i).previousPosition[1] = particles.get(i).position[1] - particles.get(i).velocity[1];
            } else if( particles.get(i).position[1] >= 480d ){
                particles.get(i).position[1] = 30d;
                particles.get(i).previousPosition[1] = particles.get(i).position[1] - particles.get(i).velocity[1];
            }
        }
        repaint();
    }
}
