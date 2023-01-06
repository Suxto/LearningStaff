package BigHomeWork.TurtleGraphics;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

class Pen extends Pane {
    Circle circle = new Circle(100, 100, 25);
    boolean draw = false;

    public void swap() {
        draw = !draw;
    }

    public void go(double x, double y) {
        double cx = circle.getCenterX();
        double cy = circle.getCenterY();
        Line line = new Line(cx, cy, x, y);
        circle.setCenterX(x);
        circle.setCenterY(y);
        if (draw) this.getChildren().add(line);
    }

    public void moveX(double dx) {
        double cx = circle.getCenterX();
        double cy = circle.getCenterY();
        Line line = new Line(cx, cy, cx + dx, cy);
        circle.setCenterX(cx + dx);
        circle.setCenterY(cy);
        if (draw) this.getChildren().add(line);
    }

    public void moveY(double dy) {
        double cx = circle.getCenterX();
        double cy = circle.getCenterY();
        Line line = new Line(cx, cy, cx, cy + dy);
        circle.setCenterX(cx);
        circle.setCenterY(cy + dy);
        if (draw) this.getChildren().add(line);
    }

    public Pen() {
        circle.setFill(Color.GREEN);
        this.getChildren().add(circle);
    }
}
