package Ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class P14_1 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        ImageView caFlag = new ImageView("https://liveexample.pearsoncmg.com/book/image/ca.gif");
        ImageView usFlag = new ImageView("https://liveexample.pearsoncmg.com/book/image/us.gif");
        ImageView bgFlag = new ImageView("https://liveexample.pearsoncmg.com/book/image/ca.gif");
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(caFlag, bgFlag);
        gridPane.add(vBox, 0, 0);
        gridPane.add(usFlag, 1, 0);
        stage.setScene(new Scene(gridPane));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
