package Ch14;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.Properties;


public class P14_11 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final String style = "-fx-fill: none;-fx-stroke: black;-fx-stroke-width: 2;";
        Pane pane = new Pane();
        Circle circle = new Circle(50);
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        circle.radiusProperty().bind(pane.heightProperty().divide(2).subtract(10));
        circle.setStyle(style);
        pane.getChildren().add(circle);

        Ellipse leftEyeOutside = new Ellipse();
        leftEyeOutside.centerXProperty().bind(circle.centerXProperty().subtract(circle.radiusProperty().divide(2 * Math.sqrt(2))));
        leftEyeOutside.centerYProperty().bind(circle.centerYProperty().subtract(circle.radiusProperty().divide(2 * Math.sqrt(2))));
        leftEyeOutside.radiusXProperty().bind(circle.radiusProperty().divide(4));
        leftEyeOutside.radiusYProperty().bind(circle.radiusProperty().divide(6));
        leftEyeOutside.setStyle(style);
        pane.getChildren().add(leftEyeOutside);

        Circle leftEye = new Circle();
        leftEye.radiusProperty().bind(leftEyeOutside.radiusYProperty().subtract(5));
        leftEye.centerXProperty().bind(leftEyeOutside.centerXProperty());
        leftEye.centerYProperty().bind(leftEyeOutside.centerYProperty());
        pane.getChildren().add(leftEye);


        Ellipse rightEyeOutside = new Ellipse();
        rightEyeOutside.centerXProperty().bind(circle.centerXProperty().add(circle.radiusProperty().divide(2 * Math.sqrt(2))));
        rightEyeOutside.centerYProperty().bind(circle.centerYProperty().subtract(circle.radiusProperty().divide(2 * Math.sqrt(2))));
        rightEyeOutside.radiusXProperty().bind(circle.radiusProperty().divide(4));
        rightEyeOutside.radiusYProperty().bind(circle.radiusProperty().divide(6));
        rightEyeOutside.setStyle(style);
        pane.getChildren().add(rightEyeOutside);

        Circle rightEye = new Circle();
        rightEye.radiusProperty().bind(rightEyeOutside.radiusYProperty().subtract(5));
        rightEye.centerXProperty().bind(rightEyeOutside.centerXProperty());
        rightEye.centerYProperty().bind(rightEyeOutside.centerYProperty());
        pane.getChildren().add(rightEye);


        Polygon nose = new Polygon();
        nose.getPoints().addAll(120.0, 180.0);
        nose.getPoints().addAll(180.0, 180.0);
        nose.getPoints().addAll(150.0, 120.0);
        nose.setStyle(style);
        pane.getChildren().add(nose);

//        StackPane stackPane = new StackPane();
//        stackPane.getChildren().add(circle);
//        stackPane.getChildren().add(nose);
//        pane.getChildren().add(stackPane);


        Arc mouth = new Arc();
        mouth.setStartAngle(180);
        mouth.setLength(180);
        mouth.centerXProperty().bind(circle.centerXProperty());
        mouth.centerYProperty().bind(circle.centerYProperty().add(circle.radiusProperty().divide(4)));
        mouth.radiusXProperty().bind(circle.radiusProperty().multiply(4).divide(6));
        mouth.radiusYProperty().bind(circle.radiusProperty().divide(3));
        mouth.setStyle(style);
        pane.getChildren().add(mouth);

        stage.setScene(new Scene(pane, 300, 300));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
