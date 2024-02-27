import java.util.StringJoiner;

public class Part extends Component {
    private final CatalogueEntry catalogueEntry;

    public Part(CatalogueEntry e) {
        catalogueEntry = e;
    }

    @Override
    public double getCost() {
        return catalogueEntry.getCost();
    }

    @Override
    public String getName() {
        return catalogueEntry.getName();
    }

    @Override
    public String toString() {
        return "Part: " + catalogueEntry;
    }
}
