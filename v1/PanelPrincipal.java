package v1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelPrincipal extends JPanel {
    int distanciaFocal;
    int size;
    Linea[] triangulo;
    double alpha = Math.PI/180;

    PanelPrincipal(int distanciaFocal, int size) {

        this.distanciaFocal = distanciaFocal;
        this.setPreferredSize(new Dimension(500, 500));
        this.size = size;
        this.setBackground(Color.black);

        triangulo = Linea.crearTriangulo(63, 100);

        new Timer(50, (ActionEvent e)-> {

            xRotate(alpha,triangulo);
            repaint();

        }).start();

    }

    public void xRotate(double alpha, Linea[] objeto3D) {

        double senAlpha = Math.sin(alpha);
        double cosAlpha = Math.cos(alpha);

        for (Linea linea : objeto3D) {

            rotarPunto(linea.getPunto1(), linea, cosAlpha, senAlpha, 1, 0, 2);
            rotarPunto(linea.getPunto2(), linea, cosAlpha, senAlpha, 2, 0, 2);

        }

    }

    public void yRotate(double alpha, Linea[] objeto3D) {

        double senAlpha = Math.sin(alpha);
        double cosAlpha = Math.cos(alpha);

        for (Linea linea : objeto3D) {

            rotarPunto(linea.getPunto1(), linea, cosAlpha, senAlpha, 1, 1, 2);
            rotarPunto(linea.getPunto2(), linea, cosAlpha, senAlpha, 2, 1, 2);

        }

    }

    public void rotarPunto(int[] punto, Linea linea, double cosAlpha, double senAlpha, int puntoQueGuardar, int dondeGuardar1, int dondeGuardar2) {

        double cordenada1 = punto[dondeGuardar1];
        double cordenada2 = punto[dondeGuardar2];

        punto[dondeGuardar1] = (int) Math.round (cordenada1 * cosAlpha - cordenada2 * senAlpha);
        punto[dondeGuardar2] = (int) Math.round (cordenada2 * cosAlpha + cordenada1 * senAlpha);

        if (puntoQueGuardar == 1) {
        linea.setPunto1(punto);
        } else {
            linea.setPunto2(punto);
        }

    }

    public void dibujarCubo(Graphics2D g2D) {

        g2D.translate(getWidth()/2, getHeight()/2);

        for (Linea linea : triangulo) {
            linea.proyeccionEnPlano(distanciaFocal);
            g2D.drawLine(linea.getLinea()[0][0], linea.getLinea()[0][1], linea.getLinea()[1][0], linea.getLinea()[1][1]);
        }

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dibujarCubo(g2d);

    }
}
