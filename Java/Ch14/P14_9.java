package Ch14;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Stack;


public class P14_9 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-padding: 10;-fx-hgap: 10;-fx-vgap: 10;");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                StackPane stackPane = new StackPane();
                Circle circle = new Circle(100);
                circle.centerXProperty().bind(stackPane.widthProperty().divide(2));
                circle.centerYProperty().bind(stackPane.heightProperty().divide(2));
                circle.setStyle("-fx-fill: none;-fx-stroke: black;-fx-stroke-width: 1.5");
                stackPane.getChildren().add(circle);
                Group group = new Group();
                for (int k = 0; k < 4; k++) {
                    Arc arc = new Arc();
                    arc.radiusXProperty().bind(circle.radiusProperty().subtract(15));
                    arc.radiusYProperty().bind(circle.radiusProperty().subtract(15));
//                    arc.centerXProperty().bind(stackPane.heightProperty().divide(2));
//                    arc.centerYProperty().bind(stackPane.heightProperty().divide(2));
                    arc.setStartAngle(30 + 90 * k);
                    arc.setLength(45);
                    arc.setType(ArcType.ROUND);
                    arc.setStyle("-fx-fill: black");
                    group.getChildren().add(arc);
                }
                stackPane.getChildren().add(group);
                gridPane.add(stackPane, j, i);
            }
        }
        stage.setScene(new Scene(gridPane));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
