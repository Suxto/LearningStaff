package Ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class P15_11 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane1 = new Pane();
        Circle circle = new Circle(100, 100, 50);
        pane1.getChildren().add(circle);
        Scene scene = new Scene(pane1, 400, 250);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) {
                double val = circle.getCenterY() - 10;
                if (val - circle.getRadius() >= 0) circle.setCenterY(val);
            } else if (e.getCode() == KeyCode.DOWN) {
                double val = circle.getCenterY() + 10;
                if (val + circle.getRadius() <= pane1.getHeight()) circle.setCenterY(val);
            } else if (e.getCode() == KeyCode.LEFT) {
                double val = circle.getCenterX() - 10;
                if (val - circle.getRadius() >= 0) circle.setCenterX(val);
            } else if (e.getCode() == KeyCode.RIGHT) {
                double val = circle.getCenterX() + 10;
                if (val + circle.getRadius() <= pane1.getWidth()) circle.setCenterX(val);
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
