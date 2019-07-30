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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import GrafickiDeo.Graph;

/**
 * FXML Controller class
 *
 * @author dusan
 */
public class EditAllNodesController implements Initializable {
    
    @FXML
    private TextField fieldOne, fieldTwo;
    
    @FXML
    private ColorPicker colorPick;
    
    @FXML
    private RadioButton rbMore;

    @FXML
    public void EditAll() {
        int num = Integer.parseInt(fieldOne.getText());
        boolean more = !rbMore.isSelected();
        long size = Long.parseLong(fieldTwo.getText());
        Color c = colorPick.getValue();
        Graph.readjustBranchNum(POOP_Projekat2.c, num, more, size,c);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
