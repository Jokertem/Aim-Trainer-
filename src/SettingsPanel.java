import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;


public class SettingsPanel extends JPanel {
    JPanel gamePanel = new JPanel();
    JPanel difficultyPanel = new JPanel();
    JPanel gameSettingsPanel = new JPanel();
    MyFrame frame;



    SettingsPanel(MyFrame frame) {
        this.frame = frame;
        gamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        difficultyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        difficultyPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        difficultyPanel.setPreferredSize(new Dimension(750, 60));
        gameSettingsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        gameSettingsPanel.setPreferredSize(new Dimension(750,90));

        render();

    }

    private void render() {
        removeAll();
        gamePanel.removeAll();
        difficultyPanel.removeAll();
        gameSettingsPanel.removeAll();
        for (GameTypes gameTypes : GameTypes.values()) {
            JLabel label = new JLabel(gameTypes.getName());
            label.setPreferredSize(new Dimension(200, 80));
            label.setBorder(BorderFactory.createLineBorder( new Color(206,2,0),2));
            if (label.getText() == Utils.selectedGame) label.setBorder(BorderFactory.createLineBorder(new Color(54,237,16),5));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(new Font("Arial", Font.PLAIN, 24));
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    selectGame(label);
                }
            });
            gamePanel.add(label);
        }
        for (Difficulty difficulty : Difficulty.values()) {
            JButton button = new JButton(difficulty.name());
            if (button.getText() == Utils.selectedDifficulty.toString()) button.setBackground(new Color(54,237,16));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectDifficulty(button);
                }
            });
            difficultyPanel.add(button);
        }
        for (Settings settings : Utils.settings) {
//            if (Utils.selectedGame ==GameTypes.CLASSIC.getName()){
//                if (settings.getName()=="Lives") continue;
//            }
            JPanel settingsPanel = new JPanel();
            JLabel label = new JLabel(settings.getName());
            JButton buttonDec = new JButton("-");
            JLabel label2 = new JLabel(String.valueOf(settings.getValue()));
            if (settings.isTotal()) label2.setText(String.valueOf((int) settings.getValue()));
            JButton buttonInc = new JButton("+");
            settingsPanel.add(label);
            settingsPanel.add(buttonDec);
            settingsPanel.add(label2);
            settingsPanel.add(buttonInc);
            buttonDec.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeSettingsDown(settings);
                }
            });
            buttonInc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    changeSettingsUp(settings);
                }
            });
            gameSettingsPanel.add(settingsPanel);
        }

        add(gamePanel);
        add(difficultyPanel);
        add(gameSettingsPanel);
        JButton startButton = new JButton("Start");
        startButton.setBackground(new Color(54,237,16));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startingGame();
            }
        });
        add(startButton);
        revalidate();

    }

    private void selectGame(JLabel label) {
        Utils.selectedGame = label.getText();
        render();
    }

    private void selectDifficulty(JButton button) {
        Utils.selectedDifficulty = Difficulty.valueOf(button.getText());
        if (button.getText()==Difficulty.EASY.toString()){
            Utils.settings[0].setValue(6);
            Utils.settings[1].setValue(1);
        }
        if (button.getText()==Difficulty.MEDIUM.toString()){
            Utils.settings[0].setValue(4);
            Utils.settings[1].setValue(3);
        }
        if (button.getText()==Difficulty.HARD.toString()){
            Utils.settings[0].setValue(2);
            Utils.settings[1].setValue(5);
        }
        render();
    }

    private void changeSettingsDown(Settings settings) {
        Utils.selectedDifficulty = Difficulty.CUSTOM;
        if (settings.getValue() <= settings.getMin()) return;
        if (settings.isTotal()) settings.setValue(settings.getValue() - 1);
        else {
            BigDecimal decimal = new BigDecimal(String.valueOf(settings.getValue()));

            settings.setValue(decimal.subtract(new BigDecimal("0.10")).doubleValue());
        }
        render();
    }

    private void changeSettingsUp(Settings settings) {
        Utils.selectedDifficulty = Difficulty.CUSTOM;
        if (settings.getValue() >= settings.getMax()) return;
        if (settings.isTotal()) settings.setValue(settings.getValue() + 1);
        else  {
            BigDecimal decimal = new BigDecimal(String.valueOf(settings.getValue()));

            settings.setValue(decimal.add(new BigDecimal("0.10")).doubleValue());
        }
        render();
    }
    private void startingGame(){
        frame.setState(Frame.ICONIFIED);
        GameFrame gameFrame = new GameFrame();


    }


}
