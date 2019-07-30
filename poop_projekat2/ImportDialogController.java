/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poop_projekat2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import GrafickiDeo.Graph;
import javafx.stage.*;
/**
 * FXML Controller class
 *
 * @author dusan
 */
public class ImportDialogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField fieldName;
    
    @FXML
    public void handleButtonAction(ActionEvent e) {
        String fileName = fieldName.getText();
        Graph.setFileName(fileName);
        fieldName.setText("");
        Stage stage=(Stage)fieldName.getScene().getWindow();
        stage.close();
        Graph.loadFiles(POOP_Projekat2.c);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
