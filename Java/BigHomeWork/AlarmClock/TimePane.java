package BigHomeWork.AlarmClock;

import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.time.LocalTime;

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
