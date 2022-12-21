package Ch14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;


public class P14_3 extends Application {
    static boolean[] vis = new boolean[52];

    int getRand() {
        int rand = (int) (Math.random() * 52.0);
        while (vis[rand]) rand = (int) (Math.random() * 52.0);
        vis[rand] = true;
        return rand;
    }

    @Override
    public void start(Stage stage) throws Exception {
        HBox hBox = new HBox();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView("http://liveexample.pearsoncmg.com/book/image/card/" + getRand() + ".png");
            hBox.getChildren().add(imageView);
        }
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5));
        Scene scene = new Scene(hBox);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
