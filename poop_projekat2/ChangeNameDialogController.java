/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poop_projekat2;

import GrafickiDeo.Graph;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import GrafickiDeo.Graph;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author dusan
 */
public class ChangeNameDialogController implements Initializable {

    @FXML
    private TextField nameOne, nameTwo;
    
    @FXML
    private void handleChangeNameItem(ActionEvent a) {
        Graph.changeLabel(POOP_Projekat2.c, nameOne.getText(), nameTwo.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
