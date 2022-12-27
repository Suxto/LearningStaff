package Ch15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class P15_29 extends Application {

    static double speed = 10;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Group car = new Group();
        Circle w1 = new Circle(15, 245, 5);
        Circle w2 = new Circle(35, 245, 5);
        Rectangle body = new Rectangle(0, 230, 50, 10);
        Polygon up = new Polygon(10, 230, 20, 220, 30, 220, 40, 230);
        car.getChildren().addAll(w1, w2, body, up);


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> {
            if (car.getLayoutX() + 10 <= pane.getWidth())
                car.setLayoutX(car.getLayoutX() + speed);
            else car.setLayoutX(-50);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        pane.setOnMousePressed(e -> timeline.stop());
        pane.setOnMouseReleased(e -> timeline.play());
        pane.getChildren().add(car);
        Scene scene = new Scene(pane, 400, 250);
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.UP) speed += 1;
            else if (e.getCode() == KeyCode.DOWN && speed > 0) speed -= 1;
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
