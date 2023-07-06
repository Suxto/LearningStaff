import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InputDialog {
    private ArrayList<Integer> numbers;

    public InputDialog(Component parent, String title, String... args) {
        numbers = new ArrayList<>();
        // 将输入框和标签添加到面板中
        JPanel panel = new JPanel();
        ArrayList<JTextField> textFields = new ArrayList<>();
        //将需要的内容加入对话框
        for (String now : args) {
            JLabel label = new JLabel(now);
            JTextField textField = new JTextField(5);
            textFields.add(textField);
            panel.add(label);
            panel.add(textField);
        }

        // 弹出窗口
        int result = JOptionPane.showConfirmDialog(parent, panel, title, JOptionPane.OK_CANCEL_OPTION);

        // 处理用户输入
        if (result == JOptionPane.OK_OPTION) {
            for (JTextField textField : textFields) {
                try {
                    numbers.add(Integer.parseInt(textField.getText()));
                } catch (Exception ignore) {
                    JOptionPane.showMessageDialog(parent, "请输入整数", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            numbers = null;
        }
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }
}