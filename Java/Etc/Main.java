package Etc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Rectangle rectangle = new Rectangle(10, 10, 100, 200);
        pane.getChildren().add(rectangle);
        rectangle.setRotate(-45);
        stage.setScene(new Scene(pane, 400, 500));
        Color color = Color.color(0.5, 1, 1, 0.8);
        Font font = new Font(20);
        ImageView imageView = new ImageView(new Image("http://liveexample.pearsoncmg.com/book/image/us.gif"));
        pane.getChildren().add(imageView);
        rectangle.setFill(color);
        Line line = new Line();
        GridPane gridPane = new GridPane();
        stage.show();
    }
}