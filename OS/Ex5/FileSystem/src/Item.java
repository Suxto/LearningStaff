import javafx.geometry.Pos;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Item extends VBox {
    private static Item selected = null;
    private final Label name = new Label();
    private byte attribute, startBlock, length;//5,6,7
    private final byte[] code;

    public byte getLength() {
        return length;
    }

    public String getName() {
        return name.getText();
    }

    public void setName(String s) {
        name.setText(s);
        encode();
    }

    public byte getStartBlock() {
        return startBlock;
    }

    public byte[] getCode() {
        return code;
    }

    private void init() {
        ImageView icon;
//        if (isFolder()) icon = new ImageView(new Image("file:src/res/folder.png"));
//        else icon = new ImageView("file:src/res/file.png");
        if (isFolder())
            icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/res/folder.png"))));
        else icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/res/file.png"))));
        this.getChildren().addAll(icon, name);
        icon.setFitHeight(130);
        icon.setFitWidth(130);
        this.setAlignment(Pos.CENTER);
        this.setOnMouseClicked(event -> {
            if (selected != null) selected.setNotSelected();
            setSelected();
            if (event.getButton() == MouseButton.SECONDARY) {
                ContextMenu contextMenu = new ContextMenu();
                MenuItem del = new MenuItem("删除");
                del.setOnAction(e -> {
                    if (isFolder()) {
                        FileManagementSystemGUI.delFolder(startBlock);
                        FileManagementSystemGUI.loadDirectory();
                    } else FileManagementSystemGUI.delFile(startBlock);
                });
                MenuItem rename = new MenuItem("重命名");
                rename.setOnAction(e -> FileManagementSystemGUI.rename(this));
                contextMenu.getItems().addAll(del, rename);
                // 在鼠标位置显示右键菜单
                contextMenu.show(this, event.getScreenX(), event.getScreenY());
                event.consume();
            } else if (event.getClickCount() == 2) {
                if (isFolder()) FileManagementSystemGUI.enterDirectory(this);
                else FileManagementSystemGUI.openFile(this);
            }
        });
    }

    public void setLength(byte length) {
        this.length = length;
        this.encode();
    }

    public static void clearSelection() {
        if (selected != null) selected.setNotSelected();
    }

    public void setNotSelected() {
        selected = null;
        this.setStyle("-fx-background-color: #00000000");
    }

    public void setSelected() {
        selected = this;
        this.setStyle("-fx-background-color: #00AAAA80;-fx-background-radius: 10");
    }

    public Item(byte[] code) {
        this.code = code;
        decode();
        init();
    }

    public Item(String name, byte attribute, byte startBlock, byte length) {
        this.name.setText(name);
        this.startBlock = startBlock;
        this.attribute = attribute;
        this.length = length;
        this.code = new byte[8];
        encode();
        init();
    }


    void decode() {
        attribute = code[5];
        startBlock = code[6];
        length = code[7];
        StringBuilder sb = new StringBuilder();
        if (isFolder()) {
            for (int i = 0; i < 5; i++) {
                if (code[i] == 0) break;
                sb.append((char) code[i]);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                if (code[i] == 0) break;
                sb.append((char) code[i]);
            }
            sb.append('.');
            for (int i = 3; i < 5; i++) {
                if (code[i] == 0) break;
                sb.append((char) code[i]);
            }
        }
        name.setText(sb.toString());
    }


    void encode() {
        code[5] = attribute;
        code[6] = startBlock;
        code[7] = length;
        byte[] bytes = name.getText().getBytes(StandardCharsets.US_ASCII);
        if (isFolder()) {
            for (int i = 0; i < 5; i++) {
                if (i < bytes.length) code[i] = bytes[i];
                else code[i] = 0;
            }
        } else {
            int idx = -1;
            for (int i = 0; i < 3; i++) {
                if (idx == -1 && bytes[i] == '.') idx = i;
                if (idx == -1) code[i] = bytes[i];
                else code[i] = 0;
            }
            if (idx == -1) idx = 3;
            for (int i = 1; i <= 2; i++) {
                if (idx + i < bytes.length) code[2 + i] = bytes[idx + i];
                else code[2 + i] = 0;
            }
        }
    }

    boolean isFolder() {
        return (attribute & (1 << 3)) != 0;
    }
}
