public class Settings {
    private String name;
    private double value;
    private double min;
    private double max;
    private boolean total;

    public Settings(String name, double value, double min, double max, boolean total) {
        this.name = name;
        this.value = value;
        this.min = min;
        this.max = max;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public boolean isTotal() {
        return total;
    }
}

