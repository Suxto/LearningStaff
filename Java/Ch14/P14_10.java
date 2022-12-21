package Ch14;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.*;
import javafx.stage.Stage;


public class P14_10 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        final String style = "-fx-fill: none;-fx-stroke: black;-fx-stroke-width: 2";
        Pane pane = new Pane();
        Ellipse ellipseTop = new Ellipse();
        ellipseTop.centerXProperty().bind(pane.widthProperty().divide(2));
        ellipseTop.radiusXProperty().bind(pane.widthProperty().divide(4));
        ellipseTop.radiusYProperty().bind(pane.heightProperty().divide(8));
        ellipseTop.centerYProperty().bind(pane.heightProperty().divide(4));
        ellipseTop.setStyle(style);

        Arc bottomFront = new Arc();
        bottomFront.centerXProperty().bind(ellipseTop.centerXProperty());
        bottomFront.centerYProperty().bind(pane.heightProperty().multiply(3).divide(4));
        bottomFront.radiusXProperty().bind(ellipseTop.radiusXProperty());
        bottomFront.radiusYProperty().bind(ellipseTop.radiusYProperty());
        bottomFront.setStartAngle(180);
        bottomFront.setLength(180);
        bottomFront.setStyle(style);

        Line lineLeft = new Line();
        lineLeft.startXProperty().bind(ellipseTop.centerXProperty().subtract(ellipseTop.radiusXProperty()));
        lineLeft.startYProperty().bind(ellipseTop.centerYProperty());
        lineLeft.endXProperty().bind(bottomFront.centerXProperty().subtract(ellipseTop.radiusXProperty()));
        lineLeft.endYProperty().bind(bottomFront.centerYProperty());
        lineLeft.setStyle("-fx-stroke-width: 2");

        Line lineRight = new Line();
        lineRight.startXProperty().bind(ellipseTop.centerXProperty().add(ellipseTop.radiusXProperty()));
        lineRight.startYProperty().bind(ellipseTop.centerYProperty());
        lineRight.endXProperty().bind(bottomFront.centerXProperty().add(ellipseTop.radiusXProperty()));
        lineRight.endYProperty().bind(bottomFront.centerYProperty());
        lineRight.setStyle("-fx-stroke-width: 2");

        Arc bottomBack = new Arc();
        bottomBack.centerXProperty().bind(ellipseTop.centerXProperty());
        bottomBack.centerYProperty().bind(pane.heightProperty().multiply(3).divide(4));
        bottomBack.radiusXProperty().bind(ellipseTop.radiusXProperty());
        bottomBack.radiusYProperty().bind(ellipseTop.radiusYProperty());
        bottomBack.setStartAngle(0);
        bottomBack.setLength(180);
        bottomBack.setStyle(style);
        bottomBack.getStrokeDashArray().addAll(6.0, 21.0);

        stage.setScene(new Scene(pane, 300, 400));
        pane.getChildren().add(ellipseTop);
        pane.getChildren().add(lineRight);
        pane.getChildren().add(lineLeft);
        pane.getChildren().add(bottomFront);
        pane.getChildren().add(bottomBack);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
