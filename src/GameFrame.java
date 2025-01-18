import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    ImageIcon logo = new ImageIcon(getClass().getResource("/targets/Target1.png"));
    JPanel topPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel downPanel = new JPanel();
    JPanel infoPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    GameFrame(){
        setTitle("Aim Trainer");
        setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 900 / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 700 / 2, 900, 700);
        setIconImage(logo.getImage());
        setResizable(false);
       setLayout(new BorderLayout());
        setVisible(true);
        centerPanel.setLayout(null);
        centerPanel.setBackground(Utils.backgroundColor);
        topPanel.setBackground(new Color(43,148,155));
        topPanel.setPreferredSize(new Dimension(900,150));
        JLabel header1 = new JLabel(Utils.selectedGame);
        JLabel header2 = new JLabel(String.valueOf(Utils.selectedDifficulty));
        header1.setFont(new Font("Arial",Font.PLAIN,26));
        header1.setPreferredSize(new Dimension(900,40));
        header1.setHorizontalAlignment(JLabel.CENTER);
        header2.setFont(new Font("Arial",Font.PLAIN,18));
        topPanel.add(header1);
        topPanel.add(header2);
        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER,300,10));
        infoPanel.setPreferredSize(new Dimension(900,50));
        JLabel timerLabel = new JLabel("Timer");
        JLabel livesLabel = new JLabel("Lives: " + (int)Utils.settings[0].getValue());
        timerLabel.setFont(new Font("Arial",Font.PLAIN,19));
        livesLabel.setFont(new Font("Arial",Font.PLAIN,19));
        infoPanel.add(timerLabel);
        infoPanel.add(livesLabel);
        topPanel.add(infoPanel);
        add(topPanel,BorderLayout.NORTH);
        leftPanel.setPreferredSize(new Dimension(30,150));
        rightPanel.setPreferredSize(new Dimension(30,150));
        downPanel.setPreferredSize(new Dimension(900,50));
        leftPanel.setBackground(new Color(43,148,155));
        rightPanel.setBackground(new Color(43,148,155));
        downPanel.setBackground(new Color(43,148,155));
        add(leftPanel,BorderLayout.WEST);
        add(rightPanel,BorderLayout.EAST);
        add(downPanel,BorderLayout.SOUTH);
        add(centerPanel,BorderLayout.CENTER);


        if (Utils.selectedGame== GameTypes.CLASSIC.getName()){
            ClassicGame classicGame = new ClassicGame();
            centerPanel.add(classicGame);
            System.out.println(topPanel.getSize());

        }
    }

}
