package BigHomeWork.TextEditor;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

class FileTab extends Tab {
    File file = null;
    TextArea textArea = null;

    void save() {
        FileWriter fileWriter;
        if (file == null) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("C++", "*.cpp"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java", "*.java"));
            file = fileChooser.showSaveDialog(new Stage());
            this.setText(file.getName());
        }
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(textArea.getText());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileTab openTab() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("C++", "*.cpp"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Java", "*.java"));
        FileTab fileTab = new FileTab();
        fileTab.file = fileChooser.showOpenDialog(new Stage());
        FileReader fileReader;
        try {
            fileReader = new FileReader(fileTab.file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Scanner scanner = new Scanner(fileReader);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()) stringBuilder.append(scanner.nextLine()).append('\n');
        fileTab.textArea = new TextArea(stringBuilder.toString());
        fileTab.textArea.setPrefHeight(1000);
        fileTab.textArea.setFont(Font.font(20));
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileTab.setText(fileTab.file.getName());
        fileTab.setContent(fileTab.textArea);
        return fileTab;
    }


    public static FileTab newTab() {
        FileTab fileTab = new FileTab();
        fileTab.textArea = new TextArea();
        fileTab.textArea.setPrefHeight(1000);
        fileTab.textArea.setFont(Font.font(20));
        fileTab.setText("No Name");
        fileTab.setContent(fileTab.textArea);
        return fileTab;
    }
}
