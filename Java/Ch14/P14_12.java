package Ch14;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;


public class P14_12 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox();
        hBox.setStyle("-fx-spacing: 10;-fx-padding: 10;");
        String[] strings = {"Project -- 20%", "Quiz -- 10%", "Midterm -- 30%", "Final -- 40%"};
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE};
        double[] doubles = {0.2, 0.1, 0.3, 0.4};

        for (int i = 0; i < 4; i++) {
            VBox project = new VBox();
            project.setAlignment(Pos.BOTTOM_CENTER);
            Label label = new Label(strings[i]);
            project.getChildren().add(label);
            Rectangle projectBar = new Rectangle();
            projectBar.heightProperty().bind(hBox.heightProperty().multiply(doubles[i] / 0.4).subtract(label.heightProperty().multiply(3)));
            projectBar.widthProperty().bind(hBox.widthProperty().divide(4).subtract(13));
            projectBar.setFill(colors[i]);
            project.getChildren().add(projectBar);
            hBox.getChildren().add(project);
        }

        stage.setScene(new Scene(hBox, 600, 300));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
