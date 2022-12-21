package Ch14;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalTime;


public class P14_27 extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new ClockPane(), 400, 400));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

class ClockPane extends Pane {
    int h, m, s;
    Circle circle = new Circle();

    public ClockPane(int h, int m, int s, DoubleBinding x, DoubleBinding divide) {
        this.h = h % 12;
        this.m = m;
        this.s = s;
        circle.centerXProperty().bind(x.divide(2));
        circle.centerYProperty().bind(divide);
        circle.radiusProperty().bind(divide.subtract(20));
        show();
    }

    public ClockPane() {
        this.h = LocalTime.now().getHour() % 12;
        this.m = LocalTime.now().getMinute();
        this.s = LocalTime.now().getSecond();
        circle.centerXProperty().bind(this.widthProperty().divide(2));
        circle.centerYProperty().bind(this.heightProperty().divide(2));
        circle.radiusProperty().bind(this.heightProperty().divide(2).subtract(40));
        show();
    }

    void addAPin(DoubleProperty x, DoubleProperty y, DoubleProperty r, double degree, double lenPec, double width) {
        Line line = new Line();
        line.startXProperty().bind(x);
        line.startYProperty().bind(y);
        line.endXProperty().bind(x.add(r.multiply(lenPec * Math.sin(degree))));
        line.endYProperty().bind(y.add(r.multiply(lenPec * Math.cos(Math.PI - degree))));
        line.setStrokeWidth(width);
        this.getChildren().add(line);
    }

    void show() {
//        System.out.println(h + ":" + m + ":" + s);
        Text time = new Text(h + ":" + m + ":" + s);
        time.setFont(new Font(24));
        time.xProperty().bind(circle.centerXProperty().subtract(35));
        time.yProperty().bind(circle.centerYProperty().add(circle.radiusProperty().add(30)));
        this.getChildren().add(time);
        circle.setStyle("-fx-fill: none;-fx-stroke-width: 2;-fx-stroke: black");
        for (int i = 1; i <= 12; i++) {
            double sin = Math.sin(Math.PI / 6 * i);
            double cos = Math.cos(Math.PI - Math.PI / 6 * i);
            Text text = new Text(i + "");
            text.xProperty().bind(circle.centerXProperty().add(circle.radiusProperty().subtract(30).multiply(sin)).subtract(9));
            text.yProperty().bind(circle.centerYProperty().add(circle.radiusProperty().subtract(30).multiply(cos)).add(10));
            text.setFont(new Font(24));
            this.getChildren().add(text);
            Line line = new Line();
            line.endXProperty().bind(circle.centerXProperty().add(circle.radiusProperty().multiply(sin)));
            line.endYProperty().bind(circle.centerYProperty().add(circle.radiusProperty().multiply(cos)));
            line.startXProperty().bind(circle.centerXProperty().add(circle.radiusProperty().subtract(13).multiply(sin)));
            line.startYProperty().bind(circle.centerYProperty().add(circle.radiusProperty().subtract(13).multiply(cos)));
            line.setStyle("-fx-stroke-width: 2");
            this.getChildren().add(line);
            for (int j = 1; j <= 4; j++) {
                double deg = Math.PI / 6 * i + j * Math.PI / 30;
                double sin1 = Math.sin(deg);
                double cos1 = Math.cos(Math.PI - deg);
                Line line1 = new Line();
                line1.endXProperty().bind(circle.centerXProperty().add(circle.radiusProperty().multiply(sin1)));
                line1.endYProperty().bind(circle.centerYProperty().add(circle.radiusProperty().multiply(cos1)));
                line1.startXProperty().bind(circle.centerXProperty().add(circle.radiusProperty().subtract(10).multiply(sin1)));
                line1.startYProperty().bind(circle.centerYProperty().add(circle.radiusProperty().subtract(10).multiply(cos1)));
                this.getChildren().add(line1);
            }
//            System.out.println(sin + " " + cos);
        }
        addAPin(circle.centerXProperty(), circle.centerYProperty(), circle.radiusProperty(), Math.PI * s / 30.0, 0.8, 1);
        addAPin(circle.centerXProperty(), circle.centerYProperty(), circle.radiusProperty(), Math.PI * m / 30.0, 0.6, 2);
        addAPin(circle.centerXProperty(), circle.centerYProperty(), circle.radiusProperty(), Math.PI * h / 6.0, 0.4, 4);
        this.getChildren().add(circle);
    }
}