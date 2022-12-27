package Ch15;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;


public class P15_27 extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Text text = new Text("Programing is fun");
        text.setX(10);
        text.setY(100);
        text.setFont(Font.font(25));
        pane.getChildren().add(text);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            if (text.getX() > pane.getWidth() + 70) text.setX(-180);
            else text.setX(text.getX() + 1);
        }));
        pane.setOnMousePressed(e -> {
            timeline.stop();
        });
        pane.setOnMouseReleased(e -> {
            timeline.play();
        });
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        Scene scene = new Scene(pane, 400, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
