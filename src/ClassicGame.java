import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class ClassicGame extends JComponent {
    JLabel livesLabel;
    JLabel targetLabel;
    Random random = new Random();
    ArrayList<Target> targets = new ArrayList<>();
    double livesValue = Utils.settings[0].getValue();
    int targetValue = 0;
    double delay = Utils.settings[1].getValue();
    boolean start = false;
    Timer spawn;
    Timer game;
    int click = 0;

    ClassicGame(JLabel lives, JLabel target) {
        this.livesLabel = lives;
        this.targetLabel = target;
        spawn = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (start) {
                    BigDecimal decimal = new BigDecimal(String.valueOf(delay));
                    delay = decimal.subtract(new BigDecimal("0.1")).doubleValue();
                    if (delay <= 0) {
                        int randomX = random.nextInt(getBounds().width);
                        if (randomX + 64 > getBounds().width) randomX -= 64;
                        int randomY = random.nextInt(getBounds().height);
                        if (randomY + 64 > getBounds().height) randomY -= 64;
                        Target target = new Target(randomX, randomY, 1);
                        targets.add(target);
                        delay = Utils.settings[1].getValue();
                    }

                }

                repaint();
            }

        });
        game = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (livesValue <= 0 && start) {
                    targets.clear();
                    start = false;
                    click = 1;
                }

                for (int i = 0; i < targets.size(); i++) {
                    Target target = targets.get(i);
                    if (target.getSize() <= 1 && !target.isRise()) {
                        targets.remove(i);
                        livesValue--;
                        livesLabel.setText("Lives: " + (int) livesValue);
                    }
                    if (target.getSize() > 64) target.setRise(false);
                    if (target.isRise()) target.setSize(target.getSize() + 1);
                    else target.setSize(target.getSize() - 1);

                }

                repaint();
            }

        });
        spawn.start();
        game.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseDragged(e);
                if (click > 0) {
                    click--;
                    return;
                }
                if (!start && click <= 0) {
                    Utils.targets = 0;
                    targetLabel.setText("Target: " + Utils.targets);
                    livesValue = Utils.settings[0].getValue();
                    livesLabel.setText("Lives: " + (int) livesValue);
                    delay = Utils.settings[1].getValue();
                    start = true;
                }

                for (int i = 0; i < targets.size(); i++) {
                    Target target = targets.get(i);
                    if (target.getX() < e.getX() && target.getX() + target.getSize() > e.getX() &&
                            target.getY() < e.getY() && target.getY() + target.getSize() > e.getY()) {
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
        graphics2D.setPaint(Utils.backgroundColor);
        graphics2D.fill(new Rectangle(0, 0, this.getBounds().width, this.getBounds().height));
        if (!start) {
            Font font = new Font("Arial", Font.PLAIN, 25);
            graphics2D.setFont(font);
            String text = "Click to start";
            graphics2D.setPaint(new Color(0, 0, 0));
            FontMetrics metrics = g.getFontMetrics();
            int textWidth = metrics.stringWidth(text);
            graphics2D.drawString(text, this.getBounds().width / 2 - textWidth / 2, this.getBounds().height / 2);


        }
        for (Target target : targets) {
            graphics2D.drawImage(Utils.targetIcon.getImage(), target.getX(), target.getY(), target.getSize(), target.getSize(), null);
        }
    }
}
