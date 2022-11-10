package Ch14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class P14_1 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button button = new Button("ok");
        stage.setScene(new Scene(button, 200, 250));
        stage.setTitle("2333");
        stage.show();
    }

//    public static void main(String[] args) {
//        Application.launch(args);
//    }
}
