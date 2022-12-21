package Ch14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class P14_18 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Polyline polyline = new Polyline();
        ObservableList<Double> points = polyline.getPoints();
        Line x = new Line(20, 200, 380, 200);
        Line x1 = new Line(360, 190, 380, 200);
        Line x2 = new Line(360, 210, 380, 200);
        Text textX = new Text(375, 190, "X");
        Group arcX = new Group(x, x1, x2, textX);

        Line y = new Line(200, 20, 200, 300);
        Line y1 = new Line(200, 20, 190, 40);
        Line y2 = new Line(200, 20, 210, 40);
        Text textY = new Text(210, 25, "Y");
        Group arcY = new Group(y, y1, y2, textY);
        double scale = 0.0125;
        for (int i = -100; i <= 100; i++) {
            points.add(i + 200.0);
            points.add(200 - i * i * scale);
        }
        pane.getChildren().add(polyline);
        pane.getChildren().add(arcX);
        pane.getChildren().add(arcY);
        stage.setScene(new Scene(pane, 400, 300));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
