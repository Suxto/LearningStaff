package Ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class P14_2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int rand = (int) (Math.random() * 3.0);
                if (rand == 0) continue;
                ImageView imageView;
                if (rand == 1) imageView = new ImageView("http://liveexample.pearsoncmg.com/book/image/x.gif");
                else imageView = new ImageView("http://liveexample.pearsoncmg.com/book/image/o.gif");
                gridPane.add(imageView, i, j);
            }
        }
        stage.setScene(new Scene(gridPane));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
