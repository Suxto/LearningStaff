package Ch15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class P15_3 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        Pane pane1 = new Pane();
        pane.setCenter(pane1);
        Circle circle = new Circle(200, 150, 50);
        circle.setStyle("-fx-fill: none;-fx-stroke: black;-fx-stroke-width: 2");
        pane1.getChildren().add(circle);
        HBox hBox = new HBox();
        Button btUp = new Button("Up");
        btUp.setOnAction(e -> {
            double val = circle.getCenterY() - 10;
            if (val - circle.getRadius() >= 0) circle.setCenterY(val);
        });
        Button btDown = new Button("Down");
        btDown.setOnAction(e -> {
            double val = circle.getCenterY() + 10;
            if (val + circle.getRadius() <= pane1.getHeight()) circle.setCenterY(val);
        });

        Button btLeft = new Button("Left");
        btLeft.setOnAction(e -> {
            double val = circle.getCenterX() - 10;
            if (val - circle.getRadius() >= 0) circle.setCenterX(val);
        });
        Button btRight = new Button("Right");
        btRight.setOnAction(e -> {
            double val = circle.getCenterX() + 10;
            if (val + circle.getRadius() <= pane1.getWidth()) circle.setCenterX(val);
        });
        hBox.getChildren().addAll(btLeft, btRight, btUp, btDown);
        pane.setBottom(hBox);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setSpacing(10);
        BorderPane.setAlignment(hBox, Pos.BOTTOM_CENTER);
        Scene scene = new Scene(pane, 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
