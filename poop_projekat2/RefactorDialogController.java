/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poop_projekat2;

import GrafickiDeo.Graph;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author dusan
 */
public class RefactorDialogController implements Initializable {

    @FXML
    private TextField fieldOne;
    
    @FXML
    private void process() {
        Graph.refactorAllNodes(POOP_Projekat2.c,Double.parseDouble(fieldOne.getText()));
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
