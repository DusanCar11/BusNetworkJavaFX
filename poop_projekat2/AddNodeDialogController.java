/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poop_projekat2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import GrafickiDeo.Graph;
/**
 * FXML Controller class
 *
 * @author dusan
 */
import javafx.event.ActionEvent;
public class AddNodeDialogController implements Initializable {

    @FXML
    private TextField nodeName;
    
    @FXML
    public void handleButtonAction(ActionEvent a) {
        String name = nodeName.getText();
        Graph.addNode(POOP_Projekat2.c, name);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
