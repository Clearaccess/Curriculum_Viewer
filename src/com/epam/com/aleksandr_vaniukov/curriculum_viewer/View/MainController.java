package com.epam.com.aleksandr_vaniukov.curriculum_viewer.View;


import com.epam.com.aleksandr_vaniukov.curriculum_viewer.Main;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class MainController {

    private Main mainWin;
    private FileChooser fileChooser;
    @FXML
    private Label label;
    @FXML
    private TreeView<String> treeView;
    @FXML
    private void initialize() {

        //Init FileChooser
        fileChooser = new FileChooser();
        fileChooser.setTitle("Import from XML");
        fileChooser.setInitialDirectory(new File("D:\\Projects\\Curriculum_Viewer\\resources"));
        FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("XML","*.xml");
        fileChooser.getExtensionFilters().add(filter);
    }

    public void setMain(Main mainWin){
        this.mainWin=mainWin;
    }

    @FXML
    public void handleImportXML(){
        File file=fileChooser.showOpenDialog(mainWin.getPrimaryStage());
        if(file!=null){
            mainWin.setFile(file);
        }

        TreeItem<String> rootItem=new TreeItem<>("Digits");
        rootItem.setExpanded(true);

        makeBranch("1",rootItem);
        makeBranch("2",rootItem);
        makeBranch("3",rootItem);

        treeView.setRoot(rootItem);

    }

    @FXML
    public void handleExit(){
        System.exit(0);
    }

    private TreeItem<String> makeBranch(String title, TreeItem<String>rootItem){
        TreeItem<String>item=new TreeItem<>(title);
        item.setExpanded(true);
        rootItem.getChildren().add(item);
        return item;
    }


}
