import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RunStage extends Stage {
    boolean ok = false;
    int segNumChoice;
    int addrInSeg;

    RunStage(Stage parent, int SegNum) {
        setTitle("运行进程");
        // 创建名字输入框和标签
        Label lbSeg = new Label("段: ");
        ChoiceBox<Integer> choiceBox = new ChoiceBox<>();
        for (int i = 0; i < SegNum; i++) choiceBox.getItems().add(i);
//        HBox hbSeg = new HBox(lbSeg, choiceBox);
        Label lbAddr = new Label("段内地址: ");
        TextField tfAddr = new TextField();
        HBox hbAddr = new HBox(lbSeg, choiceBox, lbAddr, tfAddr);
        Button btCancel = new Button("Cancel");
        btCancel.setOnAction(e -> this.close());
        Button btOk = new Button("OK");
        btOk.setOnAction(e -> {
            try {
                segNumChoice = choiceBox.getValue();
            } catch (Exception ignore) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("请选择一个段！");
                alert.showAndWait();
                return;
            }
            try {
                addrInSeg = Integer.parseInt(tfAddr.getText());
            } catch (Exception ignore) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("请输入正确的整数！");
                alert.showAndWait();
                return;
            }
            ok = true;
            this.close();
        });
        HBox buttons = new HBox(btCancel, btOk);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        VBox all = new VBox(hbAddr, buttons);
        all.setSpacing(5);
        all.setPadding(new Insets(10));
        this.setScene(new Scene(all));
        //模态控制
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(parent);
//        this.show();
    }


}
