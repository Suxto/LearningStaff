import java.util.ArrayList;

//段表类
public class SegmentTable extends ArrayList<SegmentTable.Segment> {
    static class Segment {
        int base;
        //因为页表不会被换出，所以不用标记
//        boolean present;

        Segment(int base) {//, boolean present) {
            this.base = base;
//            this.present = present;
        }
    }

    void add(int base) {
        this.add(new Segment(base));
    }
}
