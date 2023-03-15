package v2;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    private final PanelPrincipal panelPrincipal;
    private final AnguloDeRotacion xSlider;
    private final AnguloDeRotacion ySlider;

    Ventana(int distanciaFocal, int size) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2, 2));

        panelPrincipal = new PanelPrincipal(distanciaFocal, size);
        xSlider = new AnguloDeRotacion();
        xSlider.setOrientation(SwingConstants.HORIZONTAL);
        ySlider = new AnguloDeRotacion();

        JButton relleno = new JButton("Pa rellenar");
        relleno.setBackground(Color.BLACK);

        xSlider.addChangeListener((ChangeListener) -> {

            if (panelPrincipal.isTimerTick()) {

                panelPrincipal.figura = Linea.crearCubo(size);
                PanelPrincipal.setAlpha(Math.toRadians(xSlider.getValue()));
//                System.out.println("El angulo de rotacion de x esta en ->" + Math.toRadians(xSlider.getValue()));

                panelPrincipal.xRotate(panelPrincipal.figura);
                panelPrincipal.yRotate(panelPrincipal.figura, Math.toRadians(ySlider.getValue()));

                panelPrincipal.repaint();

            }

        });


        ySlider.addChangeListener((ChangeListener) -> {

            if (!panelPrincipal.isTimerTick()) {

                panelPrincipal.figura = Linea.crearCubo(size);
                PanelPrincipal.setAlpha(Math.toRadians(ySlider.getValue()));
//                System.out.println("El angulo de rotacion de x esta en ->" + Math.toRadians(ySlider.getValue()));

                panelPrincipal.yRotate(panelPrincipal.figura);
                panelPrincipal.xRotate(panelPrincipal.figura, Math.toRadians(xSlider.getValue()));

                panelPrincipal.repaint();

            }

        });

        this.add(ySlider);
        this.add(panelPrincipal);
        this.add(relleno);
        this.add(xSlider);
        this.pack();
        this.setVisible(true);
    }
}
