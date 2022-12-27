package Ch15;

import javafx.application.Application;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class P15_21 extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Circle circle = new Circle(200, 120, 90);
        circle.setStyle("-fx-fill: none;-fx-stroke: black;-fx-stroke-width: 2");
        Circle[] points = new Circle[3];
        Polygon polygon = new Polygon();
        Group texts = new Group();
        pane.getChildren().addAll(polygon, circle, texts);
        polygon.setStyle("-fx-fill: none;-fx-stroke: black;-fx-stroke-width: 2");
        for (int i = 0; i < 3; i++) {
            points[i] = new Circle(circle.getCenterX() + circle.getRadius() * Math.sin(Math.PI * 2 * i / 3), circle.getCenterY() + circle.getRadius() * Math.cos(Math.PI - Math.PI * 2 * i / 3), 10);
            pane.getChildren().add(points[i]);
            polygon.getPoints().addAll(points[i].getCenterX(), points[i].getCenterY());
            int finalI = i;
            Text text = new Text("60");
            text.setX(points[i].getCenterX() + 10);
            text.setY(points[i].getCenterY() - 10);
            texts.getChildren().add(text);
            points[i].setOnMouseDragged(e -> {
                double dx = e.getX() - circle.getCenterX();
                double dy = e.getY() - circle.getCenterY();
                double len = Math.sqrt(dx * dx + dy * dy);
                points[finalI].setCenterX(circle.getCenterX() + dx / len * circle.getRadius());
                points[finalI].setCenterY(circle.getCenterY() + dy / len * circle.getRadius());
                texts.getChildren().clear();
                polygon.getPoints().clear();
                for (Circle c : points) {
                    polygon.getPoints().addAll(c.getCenterX(), c.getCenterY());
                    double bb = -1, cc = 0, x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                    for (Circle c1 : points) {
                        if (c1 == c) continue;
                        double dis = Math.sqrt(Math.pow(c.getCenterX() - c1.getCenterX(), 2) + Math.pow(c.getCenterY() - c1.getCenterY(), 2));
                        if (bb < 0) {
                            bb = dis;
                            x1 = c1.getCenterX();
                            y1 = c1.getCenterY();
                        } else {
                            cc = dis;
                            x2 = c1.getCenterX();
                            y2 = c1.getCenterY();
                        }
                    }
                    double dis = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
                    Text text1 = new Text(Math.toDegrees(Math.acos((bb * bb + cc * cc - dis * dis) / (2 * bb * cc))) + "");
                    text1.setX(c.getCenterX() + 10);
                    text1.setY(c.getCenterY() - 10);
                    texts.getChildren().add(text1);
                }
            });
        }

        Scene scene = new Scene(pane, 400, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
