import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {
    ImageIcon logo = new ImageIcon(getClass().getResource("/targets/Target1.png"));
    JPanel topPanel = new JPanel();
    JPanel infoPanel = new JPanel();
    MyFrame frame;

    GameFrame(MyFrame frame) {
        this.frame = frame;
        setTitle("Aim Trainer");
        setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 900 / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 700 / 2, 900, 700);
        setIconImage(logo.getImage());
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(new Color(43, 148, 155));
        setVisible(true);


        topPanel.setPreferredSize(new Dimension(900, 150));
        JLabel header1 = new JLabel(Utils.selectedGame);
        JLabel header2 = new JLabel(String.valueOf(Utils.selectedDifficulty));
        header1.setFont(new Font("Arial", Font.PLAIN, 26));
        header1.setPreferredSize(new Dimension(900, 40));
        header1.setHorizontalAlignment(JLabel.CENTER);
        header2.setFont(new Font("Arial", Font.PLAIN, 18));
        topPanel.add(header1);
        topPanel.add(header2);
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
        infoPanel.setPreferredSize(new Dimension(900, 50));

        JLabel timerLabel = new JLabel("Timer");
        JLabel livesLabel = new JLabel("Lives: " + (int) Utils.settings[0].getValue());
        JLabel targetLabel = new JLabel("Target: " + Utils.targets);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 19));
        livesLabel.setFont(new Font("Arial", Font.PLAIN, 19));
        targetLabel.setFont(new Font("Arial", Font.PLAIN, 19));


        topPanel.setBounds(0, 0, 900, 110);
        add(topPanel);
        infoPanel.add(targetLabel);
        infoPanel.add(timerLabel);
        infoPanel.add(livesLabel);
        topPanel.add(infoPanel);


        if (Utils.selectedGame == GameTypes.CLASSIC.getName()) {
            ClassicGame classicGame = new ClassicGame(livesLabel, targetLabel);
            classicGame.setBounds(this.getBounds().width / 2 - 800 / 2, 130, 800, 500);
            add(classicGame);

        } else if (Utils.selectedGame == GameTypes.DOUBLESHOT.getName()) {
            DoubleShoot doubleShoot = new DoubleShoot(livesLabel, targetLabel);
            doubleShoot.setBounds(this.getBounds().width / 2 - 800 / 2, 130, 800, 500);
            add(doubleShoot);
        } else if (Utils.selectedGame == GameTypes.FOLLOW.getName()) {
            Follow follow = new Follow(timerLabel, targetLabel);
            follow.setBounds(this.getBounds().width / 2 - 800 / 2, 130, 800, 500);
            add(follow);
        }

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setState(Frame.NORMAL);
            }
        });
    }

}
