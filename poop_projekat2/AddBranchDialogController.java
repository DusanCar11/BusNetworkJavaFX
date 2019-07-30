/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poop_projekat2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import GrafickiDeo.Graph;

/**
 * FXML Controller class
 *
 * @author dusan
 */
public class AddBranchDialogController implements Initializable {

    @FXML
    private TextField nodeFrom, nodeTo;
    
    @FXML
    public void handleButtonAction(ActionEvent a) {
        Graph.addBranch(POOP_Projekat2.c, nodeFrom.getText(), nodeTo.getText());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
