public enum GameTypes {
    CLASSIC("Classic"), DOUBLESHOT("Double Shot"), FOLLOW("Follow");
    private String name;

    GameTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
