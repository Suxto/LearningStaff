package BigHomeWork.AlarmClock;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
