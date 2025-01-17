public class Utils {
    public static String selectedGame = GameTypes.CLASSIC.getName();
    public static Difficulty selectedDifficulty = Difficulty.MEDIUM;
    public static Settings[] settings = new Settings[]{new Settings("Lives", 3, 1, 6, true),
            new Settings("Targets per Seconds", 3.5, 0.5, 8, false)};

}
