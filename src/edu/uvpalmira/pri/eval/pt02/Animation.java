package edu.uvpalmira.pri.eval.pt02;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Animation extends JComponent implements Runnable {

    private Thread thread = null;

    private final BufferedImage tree;

    private static final int[] xs = {287, 267, 248, 230, 213, 198, 98, 106, 126, 146, 166, 185, 209, 226, 244, 261, 277, 292, 305, 324, 341, 345, 78, 78, 96, 116, 135, 151, 166, 185, 205, 225, 244, 264, 283, 302, 321, 339, 356, 372, 385, 395, 408, 422, 426, 426, 419, 407, 396, 384, 368, 350, 331, 312, 292, 272, 252, 236, 215, 150, 131, 111, 93, 77, 61, 48, 37, 38, 55, 65, 74, 94};
    private static final int[] ys = {111, 110, 118, 128, 140, 154, 300, 288, 288, 286, 283, 279, 272, 264, 256, 246, 234, 221, 207, 195, 203, 224, 417, 406, 406, 406, 406, 405, 405, 402, 399, 395, 391, 386, 381, 375, 368, 358, 348, 336, 322, 308, 303, 315, 336, 356, 375, 391, 400, 412, 425, 436, 445, 453, 459, 465, 470, 473, 475, 482, 477, 471, 462, 450, 437, 422, 418, 431, 462, 476, 486, 498};
    private Color[] colors = {Color.red, Color.green, Color.blue};
    private int changeColor = 0;
    private int circuito = 3;

    public Animation() throws IOException {
        tree = ImageIO.read(getClass().getResourceAsStream("/image/tree.png"));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tree, 0, 0, this);

        switch (circuito) {
            case 0:
                for (int p = 0; p < xs.length-2; p += 2) {
                    g.setColor(colors[changeColor]);
                    g.fillOval(xs[p], ys[p], 15, 15);
                }
                break;
            case 1:
                for (int p = 1; p < xs.length-1; p += 2) {
                    g.setColor(colors[changeColor]);
                    g.fillOval(xs[p], ys[p], 15, 15);
                }
                break;
            case 2:
                for (int p = 2; p < xs.length; p += 2) {
                    g.setColor(colors[changeColor]);
                    g.fillOval(xs[p], ys[p], 15, 15);
                }
                break;
            case 3:
                break;
        }

    }

    @Override
    public void run() {
        while (this.thread != null) {
            if (changeColor == 2) {
                changeColor = 0;
            }else{
            changeColor++;
            }
            circuito=changeColor;
            repaint();
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
        this.circuito=3;
        
        repaint();
    }

    public void restart() {
        // Iniciar variables
        this.init();
    }

}
