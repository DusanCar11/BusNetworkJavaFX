/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poop_projekat2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import GrafickiDeo.Graph;

/**
 * FXML Controller class
 *
 * @author dusan
 */
public class HidingNodesDialogController implements Initializable {

    @FXML
    private TextField field;
    
    @FXML
    private void process() {
        int k = Integer.parseInt(field.getText());
        Graph.hide(POOP_Projekat2.c,k);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
