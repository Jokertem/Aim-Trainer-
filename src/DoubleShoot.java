import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class DoubleShoot extends JComponent {
    JLabel livesLabel;
    JLabel targetLabel;
    Random random = new Random();
    ArrayList<Target> targets = new ArrayList<>();
    double livesValue = Utils.settings[0].getValue();
    int targetValue = 0;
    double duration = 0;
    boolean start = false;
    Timer spawn;
    Timer game;
    int click = 0;

    DoubleShoot(JLabel lives, JLabel target) {
        this.livesLabel = lives;
        this.targetLabel = target;
        spawn = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (start) {

                    BigDecimal decimal = new BigDecimal(String.valueOf(duration));
                    if (duration > 0) {
                        duration = decimal.subtract(new BigDecimal("0.1")).doubleValue();

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

                if (duration <= 0 && targets.size() > 0) {
                    targets.clear();
                    livesValue--;
                    livesLabel.setText("Lives: " + (int) livesValue);
                    if (targets.size() <= 0 && livesValue > 0) {
                        for (int i = 0; i < 2; i++) {
                            int randomX = random.nextInt(getBounds().width);
                            if (randomX + 64 > getBounds().width) randomX -= 64;
                            int randomY = random.nextInt(getBounds().height);
                            if (randomY + 64 > getBounds().height) randomY -= 64;
                            Target target = new Target(randomX, randomY, 64);
                            targets.add(target);
                            duration = Utils.settings[2].getValue();
                        }


                    }
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
                    System.out.println(click);
                    return;
                }
                if (!start && click <= 0) {
                    Utils.targets = 0;
                    targetLabel.setText("Target: " + Utils.targets);
                    livesValue = Utils.settings[0].getValue();
                    livesLabel.setText("Lives: " + (int) livesValue);
                    duration = Utils.settings[1].getValue();
                    start = true;
                    if (targets.size() <= 0) {
                        for (int i = 0; i < 2; i++) {
                            int randomX = random.nextInt(getBounds().width);
                            if (randomX + 64 > getBounds().width) randomX -= 64;
                            int randomY = random.nextInt(getBounds().height);
                            if (randomY + 64 > getBounds().height) randomY -= 64;
                            Target target = new Target(randomX, randomY, 64);
                            targets.add(target);
                            duration = Utils.settings[2].getValue();
                        }


                    }
                }

                for (int i = 0; i < targets.size(); i++) {
                    Target target = targets.get(i);
                    if (targets.size() == 2 && target.getX() < e.getX() && target.getX() + target.getSize() > e.getX() &&
                            target.getY() < e.getY() && target.getY() + target.getSize() > e.getY()) {
                        targets.remove(target);
                    } else if (targets.size() == 1 && target.getX() < e.getX() && target.getX() + target.getSize() > e.getX() &&
                            target.getY() < e.getY() && target.getY() + target.getSize() > e.getY()) {
                        targets.remove(target);
                        Utils.targets++;
                        targetLabel.setText("Target: " + Utils.targets);
                        if (targets.isEmpty()) {
                            for (int j = 0; j < 2; j++) {
                                int randomX = random.nextInt(getBounds().width);
                                if (randomX + 64 > getBounds().width) randomX -= 64;
                                int randomY = random.nextInt(getBounds().height);
                                if (randomY + 64 > getBounds().height) randomY -= 64;
                                Target _target = new Target(randomX, randomY, 64);
                                targets.add(_target);
                                duration = Utils.settings[2].getValue();

                            }

                        }
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
