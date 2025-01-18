import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class ClassicGame extends JComponent {
    JLabel livesLabel;
    JLabel targetLabel;
    Random random = new Random();
 ArrayList<Target> targets = new ArrayList<>();
Timer spawn;
Timer game;
    ClassicGame(JLabel lives,JLabel target){
        this.livesLabel = lives;
        this.targetLabel =target;
      setBounds(0,0,900-60,700-232);
      spawn = new Timer(600, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              for (int i = 0; i <Utils.settings[1].getValue() ; i++) {
                  int randomX = random.nextInt(getBounds().width);
                  if (randomX+64 > getBounds().width) randomX -=64;
                  int randomY = random.nextInt(getBounds().height);
                  if (randomY+64 > getBounds().height) randomY -=64;
                  Target target = new Target(randomX,randomY,1);
                 targets.add(target);

              }
              repaint();
          }

      });
      game = new Timer(25, new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {

              for (int i = 0; i <targets.size() ; i++) {
                  Target target = targets.get(i);
                  if (target.getSize() <=1 && !target.isRise()) {
                  targets.remove(i);
                  Utils.settings[0].setValue(Utils.settings[0].getValue()-1);
                      livesLabel.setText("Lives: " + (int)Utils.settings[0].getValue());
                  }
                  if (target.getSize()>64) target.setRise(false);
                  if (target.isRise()) target.setSize(target.getSize()+1);
                  else target.setSize(target.getSize()-1);

              }

repaint();
          }

      });
      spawn.start();
      game.start();

      addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
              for (int i = 0; i <targets.size() ; i++) {
                  Target target = targets.get(i);
                  if (target.getX()< e.getX() && target.getX()+target.getSize()> e.getX() &&
                          target.getY()< e.getY() && target.getY()+target.getSize()> e.getY()){
                      Utils.targets++;
                      targetLabel.setText("Target: " + Utils.targets);
                      targets.remove(target);

                  }
                      repaint();


              }

          }

      });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
        Graphics2D graphics2D = (Graphics2D) g;

        for (Target target:targets){
            graphics2D.drawImage(Utils.targetIcon.getImage(),target.getX(), target.getY(), target.getSize(),target.getSize(),null);
        }
    }
}
