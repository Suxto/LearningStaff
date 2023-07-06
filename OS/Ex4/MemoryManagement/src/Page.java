import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundFill;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Page extends Label {
    private PageTable pageTable = null;
    private SegmentTable segmentTable = null;
    private boolean inUse = false;

    public boolean isInUse() {
        return inUse;
    }

    public PageTable getPageTable() {
        return pageTable;
    }

    public void setAsResidentBlock(int id) {
        this.setText("Resident Block\nPID:" + id);
        setInUse();
    }

    public void setPageTable(PageTable pageTable, int id) {
        this.pageTable = pageTable;
        this.setText("Page Table\nPID:" + id);
        setInUse();
    }

    public SegmentTable getSegmentTable() {
        return segmentTable;
    }

    public void setSegmentTable(SegmentTable segmentTable, int id) {
        this.segmentTable = segmentTable;
        this.setText("Seg Table\nPID:" + id);
        setInUse();
    }

    public Page() {
        this.setPrefSize(70, 70);
        this.setStyle("-fx-border-color: black;-fx-background-color: green;");
        this.setAlignment(Pos.CENTER);
        this.setText("Free");
        this.setOnMouseClicked(e -> {
            if (isInUse()) return;
            setInUse();
            this.setText("System use");
            ManagerGUI.free -= 1;
        });
    }

    public void setInUse() {
        this.setStyle("-fx-border-color: black;-fx-background-color: red");
        inUse = true;
    }

    void setNotUse() {
        this.setStyle("-fx-border-color: black;-fx-background-color: green");
        inUse = false;
        segmentTable = null;
        pageTable = null;
        this.setText("Free");
    }


}
