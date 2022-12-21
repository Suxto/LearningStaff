package Ch14;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalTime;


public class P14_26 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
//        SimpleClockPane clockPane = new SimpleClockPane();
        HBox hBox = new HBox(10);
        SimpleClockPane simpleClockPane = new SimpleClockPane(4, 20, 45, hBox.widthProperty().divide(2), hBox.heightProperty().divide(2));
        SimpleClockPane simpleClockPane1 = new SimpleClockPane(22, 46, 15, hBox.widthProperty().divide(2), hBox.heightProperty().divide(2));
        hBox.getChildren().add(simpleClockPane);
        hBox.getChildren().add(simpleClockPane1);
        stage.setScene(new Scene(hBox, 800, 400));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

class SimpleClockPane extends Pane {
    int h, m, s;
    Circle circle = new Circle();

    public SimpleClockPane(int h, int m, int s, DoubleBinding x, DoubleBinding divide) {
        this.h = h % 12;
        this.m = m;
        this.s = s;
        circle.centerXProperty().bind(x.divide(2));
        circle.centerYProperty().bind(divide);
        circle.radiusProperty().bind(divide.subtract(20));
        show();
    }

    public SimpleClockPane(int h, int m, int s, ReadOnlyDoubleProperty x, DoubleProperty y) {
        this.h = h % 12;
        this.m = m;
        this.s = s;
        circle.centerXProperty().bind(x.divide(2));
        circle.centerYProperty().bind(y.divide(2));
        circle.radiusProperty().bind(y.divide(2).subtract(20));
        show();
    }

    public SimpleClockPane() {
        this.h = LocalTime.now().getHour() % 12;
        this.m = LocalTime.now().getMinute();
        this.s = LocalTime.now().getSecond();
        circle.centerXProperty().bind(this.widthProperty().divide(2));
        circle.centerYProperty().bind(this.heightProperty().divide(2));
        circle.radiusProperty().bind(this.widthProperty().divide(2).subtract(20));
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
        System.out.println(h + ":" + m + ":" + s);
//        circle.centerXProperty().bind(this.widthProperty().divide(2));
//        circle.centerYProperty().bind(this.heightProperty().divide(2));
//        circle.radiusProperty().bind(this.widthProperty().divide(2).subtract(20));
        circle.setStyle("-fx-fill: none;-fx-stroke-width: 2;-fx-stroke: black");
        for (int i = 1; i <= 4; i++) {
            double sin = Math.sin(Math.PI / 2 * i);
            double cos = Math.cos(Math.PI - Math.PI / 2 * i);
            double sign1 = sin >= 0.0001 ? -1 : 1;
            double sign2 = cos >= 0.0001 ? 1 : -1;
            Text text = new Text(i * 3 + "");
            text.xProperty().bind(circle.centerXProperty().add(circle.radiusProperty().subtract(15).multiply(sin)).subtract(8));
            text.yProperty().bind(circle.centerYProperty().add(circle.radiusProperty().subtract(15).multiply(cos)).add(10));
            text.setFont(new Font(24));
            this.getChildren().add(text);
//            System.out.println(sin + " " + cos);
        }
        addAPin(circle.centerXProperty(), circle.centerYProperty(), circle.radiusProperty(), Math.PI * s / 30.0, 0.8, 1);
        addAPin(circle.centerXProperty(), circle.centerYProperty(), circle.radiusProperty(), Math.PI * m / 30.0, 0.6, 2);
        addAPin(circle.centerXProperty(), circle.centerYProperty(), circle.radiusProperty(), Math.PI * h / 6.0, 0.4, 4);
        this.getChildren().add(circle);
    }
}
