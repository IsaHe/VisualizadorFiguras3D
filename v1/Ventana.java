package v1;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    PanelPrincipal panelPrincipal;
    final int size;

    Ventana(int distanciaFocal, int size) {

        this.size = size;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2));

        panelPrincipal = new PanelPrincipal(distanciaFocal, size);

        this.add(panelPrincipal);
        this.pack();
        this.setVisible(true);
    }
}
