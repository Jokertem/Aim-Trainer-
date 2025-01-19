import javax.swing.*;
import java.awt.*;

public class Utils {
    public static String selectedGame = GameTypes.CLASSIC.getName();
    public static Difficulty selectedDifficulty = Difficulty.MEDIUM;
    public static Settings[] settings = new Settings[]{new Settings("Lives", 3, 1, 6, true),
            new Settings("Target Delay", 3.0, 1.0, 5.5, false)};
    public static ImageIcon targetIcon = new ImageIcon(Utils.class.getResource("targets/Target1.png"));
    public static ImageIcon crosshairIcon = new ImageIcon(Utils.class.getResource("crosshairs/CrossHair1.png"));
    public static Color backgroundColor = new Color(214, 212, 122);
    public static int targets = 0;
}
