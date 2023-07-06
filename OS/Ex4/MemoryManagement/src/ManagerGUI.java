import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class ManagerGUI extends Application {
    final int PAGE_NUM = 64;
    final int PAGE_SIZE = 1000;
    final int RESIDENT_SET_SIZE = 4;
    static int free = 64;
    int IDCount = 0;
    TextArea logText = new TextArea();
    ObservableList<Process> processes = FXCollections.observableArrayList();
    ObservableList<Node> pages;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Text lbPages = new Text("页使用情况");
        lbPages.setFont(Font.font(30));
        VBox centerPane = new VBox();
        Button btAdd = new Button("添加");
        Button btRm = new Button("移除");
        Button btRun = new Button("运行");
        FlowPane buttons = new FlowPane(btAdd, btRm, btRun);
        buttons.setHgap(10);
        FlowPane pageBoxes = new FlowPane();
        pages = pageBoxes.getChildren();
        for (int i = 0; i < PAGE_NUM; i++) {
            pages.add(new Page());
        }
        buttons.setAlignment(Pos.CENTER);
        centerPane.getChildren().addAll(lbPages, pageBoxes, buttons);

        TableView<Process> processTable = new TableView<>();
        TableColumn<Process, String> clName = new TableColumn<>("进程名");
        clName.setMinWidth(100);
        clName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Process, Integer> clID = new TableColumn<>("PID");
        clID.setMinWidth(50);
        clID.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Process, Integer> clTotSize = new TableColumn<>("申请空间");
        clTotSize.setMinWidth(100);
        clTotSize.setCellValueFactory(new PropertyValueFactory<>("totSize"));

        processTable.setItems(processes);
        processTable.getColumns().addAll(clID, clName, clTotSize);

        btAdd.setOnAction(e -> {
            AddStage addStage = new AddStage(primaryStage);
            addStage.showAndWait();
            if (!addStage.ok) return;
            addProcess(addStage.integers, addStage.nameField.getText());
        });
        btRm.setOnAction(e -> {
            int idx = processTable.getSelectionModel().getFocusedIndex();
            if (idx == -1) return;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("确认");
            Process process = processes.get(idx);
            alert.setHeaderText("您确定要删除进程 " + process.getName() + " 吗？");
            alert.setContentText("PID: " + process.getId());
            ButtonType buttonTypeYes = new ButtonType("是");
            ButtonType buttonTypeNo = new ButtonType("否");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeNo) {
                return;
            }
            removeProcess(processes.get(idx));
            processes.remove(idx);
        });
        btRun.setOnAction(e -> {
            int idx = processTable.getSelectionModel().getFocusedIndex();
            if (idx == -1) return;
            Process process = processes.get(idx);
            RunStage runStage = new RunStage(primaryStage, ((Page) (pages.get(process.getSegmentTableLoc()))).getSegmentTable().size());
            runStage.showAndWait();
            if (!runStage.ok) return;
            runProcess(process, runStage.segNumChoice, runStage.addrInSeg);
        });
        BorderPane mainPane = new BorderPane();
        mainPane.setCenter(centerPane);
        mainPane.setRight(processTable);
        mainPane.setBottom(logText);
        logText.setEditable(false);


        primaryStage.setScene(new Scene(mainPane, 1024, 768));
        primaryStage.setTitle("内存管理器");
        primaryStage.show();
    }

    Integer getAFreePage() {
        for (int i = 0; i < PAGE_NUM; i++) {
            if (!((Page) pages.get(i)).isInUse()) {
                return i;
            }
        }
        return null;
    }

    void addProcess(ArrayList<Integer> segments, String pName) {
        if (segments.size() + 1 + RESIDENT_SET_SIZE > free) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("无法添加更多进程");
            alert.setContentText("内存不足");
            log("进程添加失败，内存不足");
            alert.showAndWait();
            return;
        }
        free -= segments.size() + 1 + RESIDENT_SET_SIZE;
        //建立段表
        SegmentTable segmentTable = new SegmentTable();
        int SegLoc = getAFreePage();
        Page page = (Page) pages.get(SegLoc);
        page.setSegmentTable(segmentTable, IDCount);
        log("为进程 " + IDCount + " 创建段表，储存在 " + SegLoc * PAGE_SIZE + " 单元");
        int totSize = 0;
        for (int i : segments) {
            totSize += i;
            //建立页表
            PageTable pageTable = new PageTable();
            while (i > 0) {
                pageTable.add(-1, false);
                i -= PAGE_SIZE;
            }
            int idx = getAFreePage();
            page = (Page) pages.get(idx);
            page.setPageTable(pageTable, IDCount);
            log("为进程 " + IDCount + " 创建页表，储存在 " + idx * PAGE_SIZE + " 单元");
            segmentTable.add(idx);//加入段表
        }

        Process process = new Process();
        process.setId(IDCount++);
        process.setName(pName);
        process.setTotSize(totSize);
        process.setSegmentTableLoc(SegLoc);
        processes.add(process);
    }

    void removeProcess(Process process) {
        int SegLoc = process.getSegmentTableLoc();
        //释放页表空间
        SegmentTable segmentTable = ((Page) pages.get(SegLoc)).getSegmentTable();
        free += 1 + segmentTable.size() + RESIDENT_SET_SIZE;
        for (SegmentTable.Segment segment : segmentTable) {
            Page page = (Page) pages.get(segment.base);
            page.setNotUse();
            log("释放进程 " + process.getId() + " 于单元 " + segment.base * PAGE_SIZE + " 的页表");
        }
        ((Page) pages.get(SegLoc)).setNotUse();
        log("释放进程 " + process.getId() + " 于单元 " + SegLoc * PAGE_SIZE + " 的段表");
        //释放驻留集内容
        while (!process.residentSet.isEmpty()) {
            ResidentSetItem residentSetItem = process.residentSet.poll();
            ((Page) pages.get(residentSetItem.block)).setNotUse();
            log("释放进程 " + process.getId() + " 于单元 " + residentSetItem.block * PAGE_SIZE + " 的驻留集页");
        }
        log("进程 " + process.getId() + " 空间释放完成");
    }

    void runProcess(Process process, int seg, int addrInSeg) {
        log("开始运行进程 " + process.getId() + " 的第 " + seg + " 段中的地址 " + addrInSeg);
        SegmentTable segmentTable = ((Page) pages.get(process.getSegmentTableLoc())).getSegmentTable();
        log("得到段表地址 " + process.getSegmentTableLoc() * PAGE_SIZE);
        PageTable pageTable = ((Page) pages.get(segmentTable.get(seg).base)).getPageTable();
        log("得到页表地址 " + segmentTable.get(seg).base);
        int pageNo = addrInSeg / PAGE_SIZE;
        int addrInPage = addrInSeg % PAGE_SIZE;
        if (pageNo < pageTable.size()) {
            log("得到段中页号为 " + pageNo + " 页中地址为 " + addrInPage);
        } else {
            log("段错误：该地址超出了段 " + seg + " 的最大地址");
            return;
        }
        if (pageTable.get(pageNo).present) {
            log("访问的页处于驻留集中，物理地址为: " + (pageTable.get(pageNo).base * PAGE_SIZE + addrInPage));
        } else {
            log("访问的页不在驻留集中，开始缺页中断");
            if (process.residentSet.size() < RESIDENT_SET_SIZE) {
                int idx = getAFreePage();
                process.residentSet.add(new ResidentSetItem(seg, pageNo, addrInPage, idx));
                pageTable.get(pageNo).present = true;
                pageTable.get(pageNo).base = idx;
                ((Page) (pages.get(idx))).setAsResidentBlock(process.getId());
                log("加入驻留集成功，物理地址为 " + (idx * PAGE_SIZE + addrInPage));
            } else {
                ResidentSetItem residentSetItem = process.residentSet.poll();
                log("驻留集已满，换出段 " + residentSetItem.seg + " 中，页为" + residentSetItem.page + "，页中地址为 " + residentSetItem.addrInPage + "的页框");
                Page page = (Page) pages.get(segmentTable.get(residentSetItem.seg).base);
                page.getPageTable().get(residentSetItem.page).present = false;
                pageTable.get(addrInPage).present = true;
                pageTable.get(addrInPage).base = residentSetItem.block;
                log("访问的物理地址为 " + (residentSetItem.block + addrInPage));
            }
        }
    }

    void log(String msg) {
        logText.appendText(msg + "\n");
    }
}
