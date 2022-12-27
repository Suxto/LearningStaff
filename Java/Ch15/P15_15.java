package Ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class P15_15 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 250);
        pane.setOnMousePressed(e -> {

            if (e.isPrimaryButtonDown()) {
                Circle circle = new Circle(e.getX(), e.getY(), 10);
                circle.setStyle("-fx-fill: white;-fx-stroke-width: 1;-fx-stroke: black;");
                pane.getChildren().add(circle);
                circle.setOnMousePressed(mouseEvent -> {
                    if (mouseEvent.isSecondaryButtonDown()) {
                        pane.getChildren().remove(circle);
                    }
                });
            }
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
