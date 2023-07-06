import java.util.ArrayList;

//页表类
public class PageTable extends ArrayList<PageTable.Item> {
    static class Item {
        int base;
        //因为页会被换出，所以用标记
        boolean present;

        Item(int base, boolean present) {
            this.base = base;
            this.present = present;
        }
    }

    void add(int base, boolean present) {
        this.add(new Item(base, present));
    }
}
