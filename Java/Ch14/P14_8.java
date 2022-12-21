package Ch14;

import com.sun.javafx.sg.prism.NGRectangle;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class P14_8 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-padding: 10");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                VBox vBox = new VBox();
                vBox.setSpacing(5);
                vBox.getChildren().add(new ImageView("http://liveexample.pearsoncmg.com/book/image/card/" + (i * 9 + j + 1) + ".png"));
//                vBox.getChildren().add(new Label((i * 9 + j + 1) + ""));
                gridPane.add(vBox, j, i);
            }
        }
        stage.setScene(new Scene(gridPane));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
