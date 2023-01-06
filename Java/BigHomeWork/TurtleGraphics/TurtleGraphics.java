package BigHomeWork.TurtleGraphics;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TurtleGraphics extends Application {
    static final double dis = 10;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Pen pen = new Pen();
        pane.getChildren().add(pen);

        //control pane
        Text txCtrlPane = new Text("Ctrl Pane");
        txCtrlPane.setFont(Font.font(20));
        Button btUp = new Button("  Up  ");
        btUp.setOnAction(e -> pen.moveY(-dis));
        Button btLeft = new Button(" Left ");
        btLeft.setOnAction(e -> pen.moveX(-dis));
        Button btPenState = new Button(" Drop ");
        btPenState.setFont(Font.font(15));
        btPenState.setOnAction(e -> {
            if (pen.draw) btPenState.setText(" Drop ");
            else btPenState.setText(" Lift ");
            pen.swap();
        });
        Button btRight = new Button("Right");
        btRight.setOnAction(e -> pen.moveX(dis));
        HBox center = new HBox(btLeft, btPenState, btRight);
        center.setSpacing(15);
        center.setAlignment(Pos.CENTER);
        Button btDown = new Button("Down");
        btDown.setOnAction(e -> pen.moveY(dis));
        Text txX = new Text("X:");
        txX.setFont(Font.font(15));
        TextField tfX = new TextField("100.0");
        tfX.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.matches("\\d*(\\.\\d*)?")) tfX.setText(s);
        });
        pen.circle.centerXProperty().addListener(e -> tfX.setText(pen.circle.getCenterX() + ""));
        tfX.setPrefWidth(45);
        Text txY = new Text("Y:");
        txY.setFont(Font.font(15));
        TextField tfY = new TextField("100.0");
        tfY.textProperty().addListener((observableValue, s, t1) -> {
            if (!t1.matches("\\d*(\\.\\d*)?")) tfY.setText(s);
        });
        pen.circle.centerYProperty().addListener(e -> tfY.setText(pen.circle.getCenterY() + ""));
        tfX.setOnAction(e -> tfY.requestFocus());
        tfY.setPrefWidth(45);
        Button btGo = new Button(" Go ");
        tfY.setOnAction(e -> {
            if (tfX.getText().equals("")) tfX.setText("0.0");
            if (tfY.getText().equals("")) tfY.setText("0.0");
            pen.go(Double.parseDouble(tfX.getText()), Double.parseDouble(tfY.getText()));
            btGo.requestFocus();
        });
        btGo.setOnAction(e -> {
            if (tfX.getText().equals("")) tfX.setText("0.0");
            if (tfY.getText().equals("")) tfY.setText("0.0");
            pen.go(Double.parseDouble(tfX.getText()), Double.parseDouble(tfY.getText()));
        });
        HBox bottom = new HBox(txX, tfX, txY, tfY, btGo);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(10);
        VBox vBox = new VBox(txCtrlPane, btUp, center, btDown, bottom);
        vBox.layoutXProperty().bind(stage.widthProperty().multiply(0.65));
        vBox.layoutYProperty().bind(stage.heightProperty().multiply(0.65));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        pane.getChildren().add(vBox);

        stage.setScene(new Scene(pane, 600, 600));
        stage.show();
    }
}


