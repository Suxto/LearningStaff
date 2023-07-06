import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.LinkedList;
import java.util.Queue;

public class Process {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty(0);
    private final SimpleStringProperty name = new SimpleStringProperty("1");
    private final SimpleIntegerProperty totSize = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty segmentTableLoc = new SimpleIntegerProperty(0);

    Queue<ResidentSetItem> residentSet = new LinkedList<>();

    public int getSegmentTableLoc() {
        return segmentTableLoc.get();
    }

    public SimpleIntegerProperty segmentTableLocProperty() {
        return segmentTableLoc;
    }

    public void setSegmentTableLoc(int segmentTableLoc) {
        this.segmentTableLoc.set(segmentTableLoc);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getTotSize() {
        return totSize.get();
    }

    public SimpleIntegerProperty totSizeProperty() {
        return totSize;
    }

    public void setTotSize(int totSize) {
        this.totSize.set(totSize);
    }
}
