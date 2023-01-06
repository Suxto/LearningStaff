package BigHomeWork.ShoppingChart;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

class ProductPane extends BorderPane {
    Label label;
    int cnt = 0;
    final double price;
    final double tex;

    public ProductPane(TextField tot, double price, double tex, String name, String pic) {
        this.price = price;
        this.tex = tex;
        label = new Label(name + " $" + price, new ImageView(pic));
        label.setContentDisplay(ContentDisplay.TOP);
        this.setCenter(label);
        //control area
        TextField tfNum = new TextField("0");
        tfNum.setEditable(false);
        tfNum.setPrefWidth(30);
        tfNum.setAlignment(Pos.CENTER);

        Button btAdd = new Button("+");
        btAdd.setOnAction(e -> {
            double pre = getSum();
            tfNum.setText(++cnt + "");
            tot.setText(Double.parseDouble(tot.getText()) - pre + getSum() + "");
        });

        Button btSubtract = new Button("-");
        btSubtract.setOnAction(e -> {
            if (cnt > 0) {
                double pre = getSum();
                tfNum.setText(--cnt + "");
                tot.setText(Double.parseDouble(tot.getText()) - pre + getSum() + "");
            }
        });
        HBox hBox = new HBox(btSubtract, tfNum, btAdd);
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        this.setBottom(hBox);
    }

    public String getName() {
        return label.getText();
    }

    public double getSum() {
        return cnt * price;
    }

    public double getTex() {
        return cnt * price * tex;
    }
}
