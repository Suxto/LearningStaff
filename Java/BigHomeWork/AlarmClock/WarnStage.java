package BigHomeWork.AlarmClock;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

class WarnStage extends Stage {
    public WarnStage(String warning) {
        this(warning, 0, 0);
    }

    public WarnStage(String warning, int x, int y) {
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        Text text = new Text(warning);
        text.setFont(Font.font(15));
        pane.getChildren().add(text);
        Button button = new Button("   OK   ");
        button.setOnAction(e -> this.close());
        pane.getChildren().add(button);
        pane.setSpacing(10);
        pane.setPadding(new Insets(10, 30, 10, 30));

        Scene scene;
        if (x == 0 || y == 0) scene = new Scene(pane);
        else scene = new Scene(pane, x, y);
        this.setTitle("Warn");
        this.setScene(scene);
        this.show();
    }
}
