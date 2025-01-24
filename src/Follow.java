import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Follow extends JComponent {

    JLabel targetLabel;
    JLabel timerLabel;
    Random random = new Random();
    ArrayList<Target> targets = new ArrayList<>();
    int moveHorizontal;
    int moveVertical;
    int time = 90;
    int targetValue = 0;
    double delay = Utils.settings[1].getValue();
    boolean start = false;
    Timer spawn;
    Timer game;
    int click = 0;

    Follow(JLabel timer, JLabel target) {
        this.timerLabel = timer;
        this.targetLabel = target;
        spawn = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (start) {
                    if (time <= 1) {
                        targets.clear();
                        start = false;
                        click = 2;

                    }
                    time--;
                    timerLabel.setText(String.valueOf(time));
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

                if (!targets.isEmpty()) {
                    Target target1 = targets.getFirst();
                    if (moveHorizontal == 0) {
                        target1.setX((int) (target1.getX() - Utils.settings[3].getValue()));
                    } else if (moveHorizontal == 1) target1.setX((int) (target1.getX() + Utils.settings[3].getValue()));
                    if (moveVertical == 0) {
                        target1.setY((int) (target1.getY() - Utils.settings[3].getValue()));
                    } else if (moveVertical == 1) target1.setY((int) (target1.getY() + Utils.settings[3].getValue()));
                    repaint();


                    if (target1.getX() < 0) {
                        moveHorizontal = 1;
                    }
                    if (target1.getX() + target1.getSize() > getBounds().width) {
                        moveHorizontal = 0;
                    }
                    if (target1.getY() < 0) {
                        moveVertical = 1;
                    }
                    if (target1.getY() + target1.getSize() > getBounds().height) {
                        moveVertical = 0;
                    }
                    boolean probably = random.nextInt(50) == 0;
                    if (probably) {
                        int change = random.nextInt(2);
                        switch (change) {
                            case 0 -> {
                                if (moveHorizontal == 0) moveHorizontal = 1;
                                else if (moveHorizontal == 1) moveHorizontal = 0;
                            }
                            case 1 -> {
                                if (moveVertical == 0) moveVertical = 1;
                                else if (moveVertical == 1) moveVertical = 0;
                            }
                        }
                    }
                    ;
                }

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
                    time = 90;
                    delay = Utils.settings[1].getValue();
                    start = true;
                    int randomX = random.nextInt(getBounds().width);
                    if (randomX + 64 > getBounds().width) randomX -= 64;
                    int randomY = random.nextInt(getBounds().height);
                    if (randomY + 64 > getBounds().height) randomY -= 64;
                    Target target = new Target(randomX, randomY, 64);
                    targets.add(target);
                    moveHorizontal = random.nextInt(2);
                    moveVertical = random.nextInt(2);
                }

                for (int i = 0; i < targets.size(); i++) {
                    Target target = targets.get(i);
                    if (target.getX() < e.getX() && target.getX() + target.getSize() > e.getX() &&
                            target.getY() < e.getY() && target.getY() + target.getSize() > e.getY()) {
                        Utils.targets++;
                        targetLabel.setText("Target: " + Utils.targets);

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
