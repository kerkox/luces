package edu.uvpalmira.pri.eval.pt01;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;

public class Animation extends JComponent implements Runnable {

    private Thread thread = null;
    private int ancho = 450;
    private int alto = 450;

    private int distancia = 35;
    private int luz = 35;
    private int opcionColor = 0;
    private Color[] colores = {Color.blue, Color.GREEN, Color.yellow, Color.red, Color.ORANGE, Color.cyan};

    public Animation() {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cx = ancho / 2;
        int cy = alto / 2;
        boolean relleno = false;
        for (int f = distancia; f < 200; f += 10) {
            if (opcionColor > 5) {
                opcionColor = 0;
            }
            if (luz == f) {
                relleno = true;
            }
            for (int angulo = 0; angulo <= 360; angulo += 20) {

                int x = (int) (f * Math.cos(Math.toRadians(angulo)));
                int y = (int) (f * Math.sin(Math.toRadians(angulo)));
                int coorx = cx + x;
                int coory = cy - y;
                int diametro = 10;
                g.setColor(Color.BLACK);
                g.drawOval(coorx - (diametro / 2), coory - (diametro / 2), diametro, diametro);
                
                if (relleno) {
                    g.setColor(colores[opcionColor]);
                    g.fillOval(coorx - (diametro / 2), coory - (diametro / 2), diametro, diametro);
                    
                }

               
            }
            relleno=false;
             opcionColor++;

        }

    }

    @Override
    public void run() {
        while (this.thread != null) {
            repaint();
            if (luz > 200) {
                luz = 35;
            }
            luz += 10;
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void init() {
        if (this.thread == null) {
            this.thread = new Thread(this);
            this.thread.start();
        }
    }

    public void pause() {
        this.thread = null;
    }

    public void restart() {
        // Iniciar variables
        this.init();
    }

}
