package Ch14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Scanner;


public class P14_20 extends Application {

    static void drawArrowLine(double startX, double startY, double endX, double endY, Pane pane) {
        Line line = new Line(startX, startY, endX, endY);
        double sign1 = startX >= endX ? 1 : -1;
        double sign2 = startY > endY ? 1 : -1;
        double length = Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2));
        length *= 0.1;
        double angele = Math.atan(Math.abs(startX - endX) / Math.abs(startY - endY));
//        System.out.print(angele);
        Line l1 = new Line(endX, endY, endX + sign1 * length * Math.sin(Math.abs(angele + Math.PI / 6)), endY + sign2 * length * Math.cos(angele + Math.PI / 6));
        if (startX == endX) sign1 *= -1;
//        l1.setStroke(Color.RED);
        Line l2 = new Line(endX, endY, endX + sign1 * length * Math.sin(Math.abs(angele - Math.PI / 6)), endY + sign2 * length * Math.cos(angele - Math.PI / 6));

        pane.getChildren().addAll(line, l1, l2);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        double r = 100;
        double x = 200, y = 200;
        for (int i = 0; i < 12; i++) {
            drawArrowLine(x, y, x + r * Math.sin(Math.PI / 6 * i), y + r * Math.cos(Math.PI / 6 * i), pane);
        }
        stage.setScene(new Scene(pane, 400, 400));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
