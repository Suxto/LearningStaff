package Etc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Test extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage s) {
        TextField one = new TextField();
        TextField two = new TextField();
        TextField res = new TextField();
        HBox hBox = new HBox(one, two, res);

        s.setScene(new Scene(hBox, 300, 400));
        s.show();
    }
}
