package Ch15;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Builder;
import javafx.util.Duration;
import javafx.scene.control.Button;

import java.time.LocalTime;


public class P15_32 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        ClockPane clockPane = new ClockPane(pane);
        pane.getChildren().add(clockPane);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            clockPane.plusS(1);
        }));
        HBox hBox = new HBox();
        Button btStop = new Button("Stop");
        btStop.setOnAction(e -> {
            timeline.pause();
        });
        Button btStart = new Button("Start");
        btStart.setOnAction(e -> {
            timeline.play();
        });
        hBox.getChildren().addAll(btStop, btStart);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setSpacing(10);
        borderPane.setBottom(hBox);
        timeline.setCycleCount(-1);
        timeline.play();
        Scene scene = new Scene(borderPane, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

class ClockPane extends Pane {
    int h, m, s;
    Circle circle = new Circle();
    Group pins = new Group();
    Text time = new Text();

    public ClockPane(int h, int m, int s, Pane pane) {
        this.h = h % 12;
        this.m = m;
        this.s = s;
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        circle.radiusProperty().bind(pane.heightProperty().divide(2).subtract(20));
        showAll();
    }

    public ClockPane(Pane pane) {
        this.h = LocalTime.now().getHour() % 12;
        this.m = LocalTime.now().getMinute();
        this.s = LocalTime.now().getSecond();
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        circle.radiusProperty().bind(pane.heightProperty().divide(2).subtract(35));
        showAll();
    }

    public ClockPane() {
        this.h = LocalTime.now().getHour() % 12;
        this.m = LocalTime.now().getMinute();
        this.s = LocalTime.now().getSecond();
        circle.centerXProperty().bind(this.widthProperty().divide(2));
        circle.centerYProperty().bind(this.heightProperty().divide(2));
        circle.radiusProperty().bind(this.heightProperty().divide(2).subtract(40));
        showAll();
    }

    void plusS(int x) {
        s += x;
        if (s >= 60) {
            s -= 60;
            m++;
        }
        if (m >= 60) {
            m -= 60;
            h++;
        }
        h %= 12;
        showPins();
        showText();
    }

    void setTime(int h, int m, int s) {
        this.h = h % 12;
        this.m = m;
        this.s = s;
        showPins();
        showText();
    }

    void showPins() {
        pins.getChildren().clear();
        addAPin(circle.centerXProperty(), circle.centerYProperty(), circle.radiusProperty(), Math.PI * s / 30.0, 0.8, 1);
        addAPin(circle.centerXProperty(), circle.centerYProperty(), circle.radiusProperty(), Math.PI * m / 30.0, 0.6, 2);
        addAPin(circle.centerXProperty(), circle.centerYProperty(), circle.radiusProperty(), Math.PI * h / 6.0, 0.4, 4);
    }

    void addAPin(DoubleProperty x, DoubleProperty y, DoubleProperty r, double degree, double lenPec, double width) {
        Line line = new Line();
        line.startXProperty().bind(x);
        line.startYProperty().bind(y);
        line.endXProperty().bind(x.add(r.multiply(lenPec * Math.sin(degree))));
        line.endYProperty().bind(y.add(r.multiply(lenPec * Math.cos(Math.PI - degree))));
        line.setStrokeWidth(width);
        pins.getChildren().add(line);
    }

    void showText() {
//        time.setText(h + ":" + m + ":" + s);
        time.setText(String.format("%02d:%02d:%02d", h, m, s));
    }

    void showAll() {
//        System.out.println(h + ":" + m + ":" + s);
        time.setFont(new Font(24));
        showText();
        time.xProperty().bind(circle.centerXProperty().subtract(45));
        time.yProperty().bind(circle.centerYProperty().add(circle.radiusProperty().add(25)));
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
        showPins();
        this.getChildren().add(circle);
        this.getChildren().add(pins);
    }
}