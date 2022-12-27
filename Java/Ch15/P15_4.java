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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class P15_4 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        HBox hBox1 = new HBox();
        pane.setCenter(hBox1);
        TextField num1 = new TextField();
        num1.setPrefSize(80, 0);
        hBox1.getChildren().addAll(new Label("Number 1: "), num1);
        TextField num2 = new TextField();
        num2.setPrefSize(80, 0);
        hBox1.getChildren().addAll(new Label("Number 2: "), num2);
        TextField res = new TextField();
        res.setPrefSize(80, 0);
        hBox1.getChildren().addAll(new Label("Result: "), res);
        res.setEditable(false);
        HBox hBox = new HBox();
        Button btAdd = new Button("Add");
        btAdd.setOnAction(e -> {
            res.setText(Double.parseDouble(num2.getText()) + Double.parseDouble(num1.getText()) + "");
        });
        Button btSubtract = new Button("Subtract");
        btSubtract.setOnAction(e -> {
            res.setText(Double.parseDouble(num1.getText()) - Double.parseDouble(num2.getText()) + "");
        });
        Button btMultiply = new Button("Multiply");
        btMultiply.setOnAction(e -> {
            res.setText(Double.parseDouble(num1.getText()) * Double.parseDouble(num2.getText()) + "");
        });
        Button btDivide = new Button("Divide");
        btDivide.setOnAction(e -> {
            res.setText(Double.parseDouble(num1.getText()) / Double.parseDouble(num2.getText()) + "");
        });
        hBox.getChildren().addAll(btAdd, btSubtract, btMultiply, btDivide);
        pane.setBottom(hBox);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setSpacing(10);
        BorderPane.setAlignment(hBox, Pos.BOTTOM_CENTER);
        Scene scene = new Scene(pane, 400, 50);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
