package BigHomeWork.AlarmClock;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;

import java.time.LocalTime;

public class AlarmClock extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        TimePane timePane = new TimePane();
        pane.setCenter(timePane);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxHeight(120);
        scrollPane.setPannable(true);
        pane.setBottom(scrollPane);
        VBox notes = new VBox();
        notes.setPadding(new Insets(10, 30, 10, 30));
        notes.heightProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> scrollPane.setVvalue(1));
        scrollPane.setContent(notes);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timePane.upDate();
            ObservableList<Node> list = notes.getChildren();
            for (int i = 0; i < list.size(); i++) {
                Message m = (Message) list.get(i);
                if (m.isTheTime(timePane)) {
                    list.remove(i);
                    new WarnStage("Time " + timePane.text.getText() + " is Reached!\n" + "The Note You Left: " + m.note);
                    break;
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        Button button = new Button("Add an event");
        button.layoutXProperty().bind(timePane.widthProperty().multiply(0.65));
        button.layoutYProperty().bind(timePane.heightProperty().multiply(0.55));
        button.setFont(Font.font(14));
        timePane.getChildren().add(button);
        timePane.setMinHeight(270);
        VBox setTime = new VBox();
        setTime.setAlignment(Pos.CENTER);
        button.setOnAction(e -> {
            if (timePane.getChildren().contains(setTime)) {
                timePane.getChildren().remove(setTime);
                button.setText("Add an event");
            } else {
                timePane.getChildren().add(setTime);
                button.setText("    Cancel    ");
            }
        });
        HBox getTime = new HBox();
        setTime.getChildren().add(getTime);
        setTime.layoutYProperty().bind(button.layoutYProperty().add(50));
        setTime.layoutXProperty().bind(timePane.widthProperty().divide(2).subtract(130));
        getTime.setSpacing(10);
        getTime.setAlignment(Pos.BOTTOM_CENTER);
        Text tH = new Text("H:");
        TextField tfH = new TextField();
        tfH.setPrefSize(50, 0);
        Text tM = new Text("M:");
        TextField tfM = new TextField();
        tfM.setPrefSize(50, 0);
        Text tS = new Text("S:");
        TextField tfS = new TextField();
        tfS.setPrefSize(50, 0);
        Button add = new Button("Add");

        getTime.getChildren().addAll(tH, tfH, tM, tfM, tS, tfS, add);
        HBox getNote = new HBox();
        setTime.getChildren().add(getNote);
        Text tNote = new Text("Note:");
        TextArea taNote = new TextArea();
        taNote.setPrefColumnCount(20);
        taNote.setPrefRowCount(1);
        add.setOnAction(e -> {
            int h, m, s;
            try {
                h = Integer.parseInt(tfH.getText());
                m = Integer.parseInt(tfM.getText());
                s = Integer.parseInt(tfS.getText());
            } catch (RuntimeException runtimeException) {
                new WarnStage("Please Check Your Input");
                return;
            }
            if (h < 0 || h > 23 || m < 0 || m > 59 || s < 0 || s > 59) {
                new WarnStage("Please Input a Valid Time");
                return;
            }
            ObservableList<Node> list = notes.getChildren();
            for (Node n : list) {
                if (((Message) n).isTheTime(h, m, s)) {
                    new WarnStage("You Have Created a Note at The Same Time");
                    return;
                }
            }
            list.add(new Message(h, m, s, taNote.getText()));
        });
        getNote.getChildren().addAll(tNote, taNote);
        Scene scene = new Scene(pane, 420, 400);
        stage.setTitle("Alarm Clock");
        stage.setScene(scene);
        stage.show();
    }

}

class WarnStage extends Stage {
    public WarnStage(String warning) {
        this(warning, 0, 0);
    }

    public WarnStage(String warning, int x, int y) {
        VBox pane = new VBox();
        pane.setAlignment(Pos.CENTER);
        Text text = new Text(warning);
        text.setFont(Font.font(15));
        pane.getChildren().add(text);
        Button button = new Button("   OK   ");
        button.setOnAction(e -> this.close());
        pane.getChildren().add(button);
        pane.setSpacing(10);
        pane.setPadding(new Insets(10, 30, 10, 30));

        Scene scene;
        if (x == 0 || y == 0) scene = new Scene(pane);
        else scene = new Scene(pane, x, y);
        this.setTitle("Warn");
        this.setScene(scene);
        this.show();
    }
}

class Message extends VBox {
    int h, m, s;
    String note;

    public Message(int h, int m, int s, String note) {
        this.h = h;
        this.m = m;
        this.s = s;
        this.note = note;
        show();
    }

    boolean isTheTime(TimePane timePane) {
        return timePane.h == h && timePane.m == m && timePane.s == s;
    }

    boolean isTheTime(int h, int m, int s) {
        return this.h == h && this.m == m && this.s == s;
    }

    void show() {
        Text time = new Text(String.format("%02d:%02d:%02d", h, m, s));
        time.setFont(Font.font(26));
        Text msg = new Text(note);
        this.getChildren().addAll(time, msg);
    }
}

class TimePane extends Pane {
    int h, m, s;
    Text text;

    public void setTime(int h, int m, int s) {
        this.h = h;
        this.m = m;
        this.s = s;
        upDate();
    }

    public TimePane(int h, int m, int s) {
        text = new Text();
        getChildren().add(text);
        setTime(h, m, s);
        show();
    }

    public TimePane() {
        text = new Text();
        getChildren().add(text);
        upDate();
        show();
    }

    public void upDate() {
        this.h = LocalTime.now().getHour();
        this.m = LocalTime.now().getMinute();
        this.s = LocalTime.now().getSecond();
        text.setText(String.format("%02d:%02d:%02d", h, m, s));
    }

    public void show() {
//        text.setText(String.format("%02d:%02d:%02d", h, m, s));
        text.xProperty().bind(this.widthProperty().divide(2).subtract(122));
        text.yProperty().bind(this.heightProperty().divide(2).subtract(10));
        text.setFont(Font.font(65));
    }
}
