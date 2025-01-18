import javax.swing.*;
import java.awt.*;

public class ClassicGame extends JComponent {

    ClassicGame(){
      setBounds(0,0,900-60,700-232);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(Utils.targetIcon.getImage(),0,0,64,64,null);
        graphics2D.drawImage(Utils.targetIcon.getImage(),this.getBounds().width-64,this.getBounds().height-64,64,64,null);
    }
}
