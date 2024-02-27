import java.util.ArrayList;
import java.util.StringJoiner;

public class Assembly extends Component {
    public final String name;
    private final ArrayList<Component> components = new ArrayList<>();

    public Assembly(String name) {
        this.name = name;
    }


    public void add(Component c) {
        components.add(c);
    }

    @Override
    public double getCost() {
        double sum = 0;
        for (var x : components) sum += x.getCost();
        return sum;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "Assembly: " + name + "[", "]");
        for (var x : components) stringJoiner.add(x.toString());
        return stringJoiner.toString();
    }
}
