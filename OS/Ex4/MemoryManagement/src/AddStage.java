import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

public class AddStage extends Stage {
    ArrayList<TextField> segments = new ArrayList<>();
    ArrayList<Integer> integers = new ArrayList<>();
    TextField nameField;
    boolean ok = false;

    AddStage(Stage parent) {
        setTitle("添加进程");
        // 创建名字输入框和标签
        Label nameLabel = new Label("名字:");
        nameField = new TextField();
        nameField.setPromptText("请输入进程名字");
        HBox hbName = new HBox(nameLabel, nameField);
        Label lbStatement = new Label("在下面填入分段大小，点击加号添加一个段");
        VBox vSeg = new VBox();

        Label lbFstSeg = new Label("段1 ");
        TextField tfFstSeg = new TextField();
        segments.add(tfFstSeg);
        tfFstSeg.setPromptText("输入段大小");
        HBox hBox = new HBox(lbFstSeg, tfFstSeg);
        vSeg.getChildren().add(hBox);
        vSeg.setSpacing(5);
        Button btPlus = new Button("+");
        Stage that = this;
        btPlus.setOnAction(e -> {
            if (segments.size() == 3) btPlus.setDisable(true);

            TextField tf = new TextField();
            segments.add(tf);
            tf.setPromptText("输入段大小");
            Label label = new Label("段" + segments.size() + " ");
            HBox hBox1 = new HBox(label, tf);
            vSeg.getChildren().add(hBox1);
            that.setHeight(that.getHeight() + 30);
        });
        Button btCancel = new Button("Cancel");
        Button btOk = new Button("OK");
        HBox buttons = new HBox(btPlus, btCancel, btOk);
        btCancel.setOnAction(e -> this.close());
        btOk.setOnAction(e -> {
            try {
                for (TextField textField : segments) {
                    integers.add(Integer.parseInt(textField.getText()));
                }
            } catch (Exception ignore) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误");
                alert.setHeaderText("请输入正确的整数！");
                alert.showAndWait();
                return;
            }
            ok = true;
            that.close();
        });
        buttons.setSpacing(10);

        VBox all = new VBox(hbName, lbStatement, vSeg, buttons);
        all.setSpacing(5);
        all.setPadding(new Insets(10));
        all.setMinHeight(150); // set minimum height for VBox
        this.setScene(new Scene(all));
        //模态控制
        this.initModality(Modality.APPLICATION_MODAL);
        this.initOwner(parent);
//        this.show();
    }


}
