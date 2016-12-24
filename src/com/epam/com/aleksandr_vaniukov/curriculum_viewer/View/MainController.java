package com.epam.com.aleksandr_vaniukov.curriculum_viewer.View;


import com.epam.com.aleksandr_vaniukov.curriculum_viewer.Main;
import com.epam.com.aleksandr_vaniukov.curriculum_viewer.model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.xml.sax.SAXException;

import java.io.File;

public class MainController {

    private Main mainWin;
    private FileChooser fileChooser;
    @FXML
    private Label label;
    @FXML
    private TreeView<ListOfStudents> treeView;
    @FXML
    private void initialize() {

        //Init FileChooser
        fileChooser = new FileChooser();
        fileChooser.setTitle("Import from XML");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/resources"));
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("XML","*.xml");
        fileChooser.getExtensionFilters().add(filter);
        new Student();
    }

    public void setMain(Main mainWin){
        this.mainWin=mainWin;
    }

    @FXML
    public void handleImportXML(){
        File file=fileChooser.showOpenDialog(mainWin.getPrimaryStage());
        if(file!=null){
            mainWin.setFile(file);
            fillTreeView(mainWin.getData());
        }
    }

    @FXML
    public void handleExit(){
        System.exit(0);
    }

    public void printError(SAXException e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("XML file isn't correct");
        alert.setContentText(e.getMessage());

        alert.showAndWait();
    }

    private void fillTreeView(DataStudents dataStudents){
        TreeItem<ListOfStudents> rootItem=new TreeItem<>(new ListOfStudents() {
            @Override
            public String getText() {
                return "";
            }
        });

        rootItem.setExpanded(true);

        for(int i=0;i<dataStudents.getStudents().size();i++){
            addStudent(dataStudents.getStudents().get(i),rootItem);
        }
        treeView.setRoot(rootItem);
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(newValue.getValue().getText());
        });
    }

    private void addStudent(Student student, TreeItem<ListOfStudents> rootItem){
        TreeItem<ListOfStudents>item= new TreeItem<>(student);
        addProgram(student.getProgram(),item);
        rootItem.getChildren().add(item);
    }

    private void addProgram(Program program, TreeItem<ListOfStudents> rootItem){
        TreeItem<ListOfStudents>item= new TreeItem<>(program);

        for(int i=0;i<program.getCourses().size();i++){
            addCourse(program.getCourses().get(i),item);
        }

        rootItem.getChildren().add(item);
    }

    private void addCourse(Course course, TreeItem<ListOfStudents> rootItem){
        TreeItem<ListOfStudents>item= new TreeItem<>(course);

        for(int i=0;i<course.getTasks().size();i++){
            addTask(course.getTasks().get(i),item);
        }

        rootItem.getChildren().add(item);
    }

    private void addTask(Task task, TreeItem<ListOfStudents> rootItem){
        TreeItem<ListOfStudents>item= new TreeItem<>(task);
        rootItem.getChildren().add(item);
    }

    private TreeItem<String> makeBranch(String title, TreeItem<String>rootItem){
        TreeItem<String>item= new TreeItem<>(title);
        rootItem.getChildren().add(item);
        return item;
    }
}
