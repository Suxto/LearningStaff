import java.util.StringJoiner;

public class CatalogueEntry {
    private final String name;
    private long number;
    private double cost;

    public void setNumber(long number) {
        this.number = number;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public CatalogueEntry(String nm, long num, double cst) {
        name = nm;
        number = num;
        cost = cst;
    }

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name;
    }
}
