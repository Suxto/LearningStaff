package Ch15;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class P15_6 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Label label = new Label("Java is fun");
        label.setFont(new Font(30));
        pane.getChildren().add(label);
        pane.setOnMouseClicked(e -> {
            if (label.getText().length() <= 12) label.setText("Java is powerful");
            else label.setText("Java is fun");
        });
        Scene scene = new Scene(pane, 400, 250);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
