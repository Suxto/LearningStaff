import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class editStage extends Stage {
    Item item;
    TextArea textArea = new TextArea();

    private void init() {
        textArea.setWrapText(true);
        textArea.setFont(new Font(18));
        Button btSave = new Button("保存");
        btSave.setOnAction(e -> {
            if (FileManagementSystemGUI.saveFile(item, textArea.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("状态");
                alert.setHeaderText("保存成功");
                alert.showAndWait();
                close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("状态");
                alert.setHeaderText("保存失败");
                alert.setContentText("磁盘空间不足，请减少文件长度");
                alert.showAndWait();
            }
        });
        Button btClose = new Button("退出");
        btClose.setOnAction(event -> close());
        HBox hBox = new HBox(btSave, btClose);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);
        VBox root = new VBox(textArea, hBox);
        root.setSpacing(15);
//        root.setPadding(new Insets(10));
        textArea.prefHeightProperty().bind(this.heightProperty().subtract(hBox.heightProperty().multiply(2)));
        this.initModality(Modality.APPLICATION_MODAL);
        this.setScene(new Scene(root, 768, 768));
        this.setTitle("文件编辑器");
        this.show();
    }

    public editStage(byte[] buf, Item item) {
        this.item = item;
        StringBuilder stringBuilder = new StringBuilder(buf.length);
        for (byte b : buf) {
            if (b == 0) break;
            stringBuilder.append((char) b);
        }
        textArea.setText(stringBuilder.toString());
        init();
    }
}
