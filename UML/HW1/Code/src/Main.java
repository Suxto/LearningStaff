import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<CatalogueEntry> catalogueEntries =
                new ArrayList<>(List.of(new CatalogueEntry[]{
                        new CatalogueEntry("a", 10, 10),
                        new CatalogueEntry("b", 10, 10),
                        new CatalogueEntry("c", 10, 10),
                        new CatalogueEntry("d", 10, 10),
                }));
        Assembly assembly = new Assembly("All");
        assembly.add(new Part(catalogueEntries.get(0)));
        assembly.add(new Part(catalogueEntries.get(1)));
        Assembly assembly1 = new Assembly("NotAll");
        assembly.add(assembly1);
        assembly1.add(new Part(catalogueEntries.get(2)));
        Assembly assembly2 = new Assembly("NotAtAll");
        assembly1.add(assembly2);
        assembly2.add(new Part(catalogueEntries.get(3)));
        System.out.println(assembly);
    }
}
