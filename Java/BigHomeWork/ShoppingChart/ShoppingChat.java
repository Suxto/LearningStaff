package BigHomeWork.ShoppingChart;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ShoppingChat extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10));
        FlowPane products = new FlowPane();
        products.setHgap(20);
        products.setVgap(20);
        products.setAlignment(Pos.CENTER);
        pane.setCenter(products);
        TextField tfSubtotal = new TextField("0");
        tfSubtotal.setEditable(false);
        go(tfSubtotal, products.getChildren());
        Text tSubtotal = new Text("Subtotal: ");
        tSubtotal.setFont(Font.font(15));
        Button btCheckOut = new Button("Check Out");
        HBox bottom = new HBox(tSubtotal, tfSubtotal, btCheckOut);
        bottom.setSpacing(10);
        pane.setBottom(bottom);
        Scene scene1 = new Scene(pane, 400, 400);
        stage.setScene(scene1);
        stage.show();
        stage.setTitle("Shopping Cart");
        VBox checkOutPane = new VBox();

        checkOutPane.setAlignment(Pos.TOP_LEFT);
        checkOutPane.setSpacing(15);
        checkOutPane.setPadding(new Insets(15));
        Scene scene2 = new Scene(checkOutPane, 400, 200);

        Button btBack = new Button("Back");
        btBack.setOnAction(e -> stage.setScene(scene1));
        checkOutPane.getChildren().add(btBack);
        GridPane numbers = new GridPane();
        numbers.setVgap(15);
        numbers.setHgap(10);
        numbers.setPadding(new Insets(0, 20, 0, 20));
        checkOutPane.getChildren().add(numbers);

        ColumnConstraints labels = new ColumnConstraints();
        labels.setHalignment(HPos.RIGHT);
        ColumnConstraints textFields = new ColumnConstraints();
        textFields.setHalignment(HPos.LEFT);
        numbers.getColumnConstraints().addAll(labels, textFields);
        Label lbSubtotal = new Label("Subtotal: ");
        lbSubtotal.setFont(Font.font(15));
        numbers.add(lbSubtotal, 0, 0);
        Label lbTax = new Label("Tax: ");
        lbTax.setFont(Font.font(15));
        numbers.add(lbTax, 0, 1);
        Label lbTotal = new Label("Total: ");
        lbTotal.setFont(Font.font(15));
        numbers.add(lbTotal, 0, 2);

        TextField tfSubtotal1 = new TextField();
        tfSubtotal1.setEditable(false);
        numbers.add(tfSubtotal1, 1, 0);
        TextField tfTax = new TextField();
        tfTax.setEditable(false);
        numbers.add(tfTax, 1, 1);
        TextField tfTotal = new TextField();
        tfTotal.setEditable(false);
        numbers.add(tfTotal, 1, 2);
        btCheckOut.setOnAction(e -> {
            tfSubtotal1.setText(tfSubtotal.getText());
            double tex = 0;
            for (Node node : products.getChildren()) {
                tex += ((ProductPane) node).getTex();
            }
            tfTax.setText(tex + "");
            tfTotal.setText((Double.parseDouble(tfSubtotal.getText()) + tex) + "");
            stage.setScene(scene2);
        });
    }

    static private void go(TextField t, ObservableList<Node> list) {
        list.add(new ProductPane(t, 10, 0.1, "US Flag", "http://liveexample.pearsoncmg.com/book/image/us.gif"));
        list.add(new ProductPane(t, 11, 0.2, "Canada Flag", "http://liveexample.pearsoncmg.com/book/image/ca.gif"));
        list.add(new ProductPane(t, 12, 0.25, "UK Flag", "http://liveexample.pearsoncmg.com/book/image/uk.gif"));
        list.add(new ProductPane(t, 13, 0.3, "China Flag", "http://liveexample.pearsoncmg.com/book/image/china.gif"));
    }
}

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