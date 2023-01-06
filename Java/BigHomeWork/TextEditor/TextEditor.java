package BigHomeWork.TextEditor;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class TextEditor extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();
        MenuBar menuBar = new MenuBar();
        TabPane tabPane = new TabPane();
        vBox.getChildren().addAll(menuBar, tabPane);
        Menu muFile = new Menu("_File");
        menuBar.getMenus().add(muFile);
        MenuItem miNew = new MenuItem("_New   ");
        miNew.setAccelerator(KeyCombination.keyCombination("Ctrl + N"));
        miNew.addEventHandler(EventType.ROOT, e -> {
            tabPane.getTabs().add(FileTab.newTab());
            tabPane.getSelectionModel().getSelectedItem().getContent().requestFocus();
            tabPane.getSelectionModel().getSelectedItem().getContent().setStyle("-fx-underline: true");
        });
        muFile.getItems().add(miNew);
        MenuItem miOpen = new MenuItem("_Open  ");
        muFile.getItems().add(miOpen);
        miOpen.setAccelerator(KeyCombination.keyCombination("Ctrl + O"));
        miOpen.addEventHandler(EventType.ROOT, event -> {
            tabPane.getTabs().add(FileTab.openTab());
            tabPane.getSelectionModel().selectLast();
            tabPane.getSelectionModel().getSelectedItem().getContent().requestFocus();
        });
        MenuItem miSave = new MenuItem("_Save   ");
        miSave.setDisable(true);
        miSave.setAccelerator(KeyCombination.keyCombination("Ctrl + S"));
        muFile.getItems().add(miSave);
        miSave.addEventHandler(EventType.ROOT, e -> {
            ((FileTab) tabPane.getSelectionModel().getSelectedItem()).save();
        });
        MenuItem miClose = new MenuItem("_Close    ");
        miClose.setDisable(true);
        miClose.setAccelerator(KeyCombination.keyCombination("Ctrl + W"));
        miClose.addEventHandler(EventType.ROOT, e -> {
            if (!tabPane.getTabs().isEmpty()) tabPane.getTabs().remove(tabPane.getSelectionModel().getSelectedIndex());
        });
        muFile.getItems().add(miClose);
        MenuItem miCloseAll = new MenuItem("Close All");
        miCloseAll.setDisable(true);
        miCloseAll.addEventHandler(EventType.ROOT, e -> tabPane.getTabs().clear());
        muFile.getItems().add(miCloseAll);
        MenuItem miExit = new MenuItem("_Exit  ");
        miExit.addEventHandler(EventType.ROOT, e -> stage.close());
        muFile.getItems().add(miExit);

        Menu muEdit = new Menu("_Edit");
        menuBar.getMenus().add(muEdit);

        MenuItem miCopy = new MenuItem("Copy");
        muEdit.getItems().add(miCopy);
        miCopy.setDisable(true);
        miCopy.setAccelerator(KeyCombination.keyCombination("Ctrl + C"));
        miCopy.addEventHandler(EventType.ROOT, e -> {
            TextArea textArea = ((FileTab) tabPane.getSelectionModel().getSelectedItem()).textArea;
            textArea.copy();
        });
        MenuItem miPaste = new MenuItem("Paste");
        muEdit.getItems().add(miPaste);
        miPaste.setDisable(true);
        miPaste.setAccelerator(KeyCombination.keyCombination("Ctrl + V"));
        miPaste.addEventHandler(EventType.ROOT, e -> {
            TextArea textArea = ((FileTab) tabPane.getSelectionModel().getSelectedItem()).textArea;
            textArea.paste();
        });
        MenuItem miUndo = new MenuItem("Undo");
        muEdit.getItems().add(miUndo);
        miUndo.setDisable(true);
        miUndo.setAccelerator(KeyCombination.keyCombination("Ctrl + Z"));
        miUndo.addEventHandler(EventType.ROOT, e -> {
            TextArea textArea = ((FileTab) tabPane.getSelectionModel().getSelectedItem()).textArea;
            textArea.undo();
        });
        MenuItem miRedo = new MenuItem("Redo");
        muEdit.getItems().add(miRedo);
        miRedo.setDisable(true);
        miRedo.setAccelerator(KeyCombination.keyCombination("Ctrl + Y"));
        miRedo.addEventHandler(EventType.ROOT, e -> {
            TextArea textArea = ((FileTab) tabPane.getSelectionModel().getSelectedItem()).textArea;
            textArea.redo();
        });

        Menu muFormat = new Menu("_Format");
        menuBar.getMenus().add(muFormat);
        CheckMenuItem cmiBold = new CheckMenuItem("Bold");
        cmiBold.setDisable(true);
        cmiBold.setAccelerator(KeyCombination.keyCombination("Ctrl+B"));
        cmiBold.addEventHandler(EventType.ROOT, e -> {
            FileTab fileTab = (FileTab) tabPane.getSelectionModel().getSelectedItem();
            Font font = fileTab.textArea.getFont();
            FontPosture fontPosture = font.getStyle().contains("Italic") ? FontPosture.ITALIC : FontPosture.REGULAR;
            if (cmiBold.isSelected())
                fileTab.textArea.setFont(Font.font(font.getName(), FontWeight.BOLD, fontPosture, font.getSize()));
            else fileTab.textArea.setFont(Font.font(font.getName(), FontWeight.NORMAL, fontPosture, font.getSize()));
        });
        CheckMenuItem cmiItalics = new CheckMenuItem("Italics");
        cmiItalics.setAccelerator(KeyCombination.keyCombination("Ctrl+I"));
        cmiItalics.addEventHandler(EventType.ROOT, e -> {
            FileTab fileTab = (FileTab) tabPane.getSelectionModel().getSelectedItem();
            Font font = fileTab.textArea.getFont();
            FontWeight fontWeight = font.getStyle().contains("Bold") ? FontWeight.BOLD : FontWeight.NORMAL;
            if (cmiItalics.isSelected())
                fileTab.textArea.setFont(Font.font(font.getName(), fontWeight, FontPosture.ITALIC, font.getSize()));
            else fileTab.textArea.setFont(Font.font(font.getName(), fontWeight, FontPosture.REGULAR, font.getSize()));
        });
        cmiItalics.setDisable(true);
        CheckMenuItem cmiWrap = new CheckMenuItem("Auto Wrap");
        cmiWrap.setDisable(true);
        cmiWrap.addEventHandler(EventType.ROOT, e -> {
            ((FileTab) tabPane.getSelectionModel().getSelectedItem()).textArea.setWrapText(cmiWrap.isSelected());
        });
        muFormat.getItems().addAll(cmiBold, cmiItalics, cmiWrap);
        Scene scene = new Scene(vBox, 800, 800);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Text Editor");
        tabPane.getTabs().addListener((ListChangeListener<? super Tab>) e -> {
            if (tabPane.getTabs().isEmpty()) {
                miCloseAll.setDisable(true);
                miClose.setDisable(true);
                miSave.setDisable(true);
                miCopy.setDisable(true);
                miPaste.setDisable(true);
                miRedo.setDisable(true);
                miUndo.setDisable(true);
                cmiBold.setDisable(true);
                cmiItalics.setDisable(true);
                cmiWrap.setDisable(true);
            } else {
                miCloseAll.setDisable(false);
                miClose.setDisable(false);
                miSave.setDisable(false);
                miCopy.setDisable(false);
                miPaste.setDisable(false);
                miRedo.setDisable(false);
                miUndo.setDisable(false);
                cmiBold.setDisable(false);
                cmiItalics.setDisable(false);
                cmiWrap.setDisable(false);
            }
        });
        new FileTab();
    }
}


