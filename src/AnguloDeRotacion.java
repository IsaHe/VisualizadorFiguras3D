import javax.swing.*;
import java.awt.*;

public class AnguloDeRotacion extends JSlider {
    AnguloDeRotacion() {
        this.setBackground(Color.BLACK);
        this.setPaintTicks(true);
        this.setMinorTickSpacing(5);
        this.setPaintTrack(true);
        this.setMajorTickSpacing(10);
        this.setPaintLabels(true);
        this.setFont(new Font("MV Boli", Font.PLAIN, 10));
        this.setOrientation(SwingConstants.VERTICAL);
        this.setMaximum(90);
        this.setMinimum(-90);
        this.setValue(0);
    }

}
