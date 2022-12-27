package Ch15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class P15_18 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Rectangle rectangle = new Rectangle(200, 100, 40, 25);
        pane.getChildren().add(rectangle);
        rectangle.setOnMouseDragged(e -> {
            rectangle.setX(e.getX() - rectangle.getWidth() / 2);
            rectangle.setY(e.getY() - rectangle.getHeight() / 2);
        });
        Scene scene = new Scene(pane, 400, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
