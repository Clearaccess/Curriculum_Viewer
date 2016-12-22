package com.epam.com.aleksandr_vaniukov.curriculum_viewer;

import com.epam.com.aleksandr_vaniukov.curriculum_viewer.Controller.Controller;
import com.epam.com.aleksandr_vaniukov.curriculum_viewer.View.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private VBox rootLayout;
    private Controller control;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Curriculum Viewer");

        initRootLayout();
    }

    public void initRootLayout(){
        try {
            // Загружаем корневой макет из fxml файла.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/window.fxml"));
            rootLayout = (VBox) loader.load();
            MainController mainControl=loader.getController();
            control=new Controller();
            mainControl.setMain(this);

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public void setFile(File file){
        control.setFile(file);
        try {
            control.parseXML();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
