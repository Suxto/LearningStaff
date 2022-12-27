package Ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class P15_9 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        final double[] pos = {100, 100};
        Scene scene = new Scene(pane, 400, 250);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                pane.getChildren().add(new Line(pos[0], pos[1], pos[0], pos[1] - 10));
                pos[1] -= 10;
            } else if (e.getCode() == KeyCode.DOWN) {
                pane.getChildren().add(new Line(pos[0], pos[1], pos[0], pos[1] + 10));
                pos[1] += 10;
            } else if (e.getCode() == KeyCode.LEFT) {
                pane.getChildren().add(new Line(pos[0], pos[1], pos[0] - 10, pos[1]));
                pos[0] -= 10;
            } else if (e.getCode() == KeyCode.RIGHT) {
                pane.getChildren().add(new Line(pos[0], pos[1], pos[0] + 10, pos[1]));
                pos[0] += 10;
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
