import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame() {
        setTitle("Aim Trainer");
        setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 750 / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 500 / 2, 750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(new SettingsPanel());
    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
    }
}
