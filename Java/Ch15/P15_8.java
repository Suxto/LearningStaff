package Ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class P15_8 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Text text = new Text();
        pane.getChildren().add(text);
        text.setFont(new Font(20));
        pane.setOnMouseDragged(e -> {
            text.setX(e.getX());
            text.setY(e.getY());
            text.setText("(" + e.getX() + ", " + e.getY() + ")");
        });
        pane.setOnMouseReleased(e -> {
            text.setText("");
        });
        Scene scene = new Scene(pane, 400, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
