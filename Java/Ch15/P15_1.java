package Ch15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Random;


public class P15_1 extends Application {
    static boolean[] vis = new boolean[52];
    static HBox hBox;

    static int getRand() {
        int rand = (int) (Math.random() * 52.0);
        while (vis[rand]) rand = (int) (Math.random() * 52.0);
        vis[rand] = true;
        return rand;
    }

    static void go() {
        hBox.getChildren().clear();
        for (int i = 0; i < 4; i++) {
            ImageView imageView = new ImageView("http://liveexample.pearsoncmg.com/book/image/card/" + getRand() + ".png");
            hBox.getChildren().add(imageView);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        hBox = new HBox();
        go();
        pane.setCenter(hBox);
        Button refresh = new Button("Refresh");
        pane.setBottom(refresh);
        refresh.setOnAction(e -> go());
        BorderPane.setAlignment(refresh, Pos.BOTTOM_CENTER);
        hBox.setSpacing(5);
        hBox.setPadding(new Insets(5));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
