package com.epam.com.aleksandr_vaniukov.curriculum_viewer;

import com.epam.com.aleksandr_vaniukov.curriculum_viewer.Controller.Controller;
import com.epam.com.aleksandr_vaniukov.curriculum_viewer.View.MainController;
import com.epam.com.aleksandr_vaniukov.curriculum_viewer.model.DataStudents;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main extends Application {

    private Stage primaryStage;
    private VBox rootLayout;
    private Controller control;
    private MainController mainControl;

    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Curriculum Viewer");

        initRootLayout();
    }

    private void initRootLayout(){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/window.fxml"));
            rootLayout = loader.load();
            mainControl=loader.getController();
            control=new Controller();
            mainControl.setMain(this);

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
            mainControl.printError(e);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public DataStudents getData(){
        return control.getData();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
