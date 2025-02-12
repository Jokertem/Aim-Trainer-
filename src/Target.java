public class Target {
    private int x;
    private int y;
    private int size;
    private boolean rise;

    public Target(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.rise = true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isRise() {
        return rise;
    }

    public void setRise(boolean rise) {
        this.rise = rise;
    }
}
