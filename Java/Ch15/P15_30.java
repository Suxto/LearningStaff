package Ch15;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class P15_30 extends Application {
    static int cnt = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        javafx.scene.image.ImageView imageView = new ImageView("http://liveexample.pearsoncmg.com/book/image/card/1.png");
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            imageView.setImage(new Image("http://liveexample.pearsoncmg.com/book/image/card/" + ((++cnt % 25) + 1) + ".png"));
        }));
        pane.getChildren().add(imageView);
        pane.setOnMouseClicked(e -> {
            if (timeline.getStatus() == Animation.Status.PAUSED) timeline.play();
            else timeline.pause();
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
