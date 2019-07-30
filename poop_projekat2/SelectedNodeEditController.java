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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import GrafickiDeo.Node;
import GrafickiDeo.Skup;
import GrafickiDeo.Graph;
import static GrafickiDeo.Graph.index;
import static GrafickiDeo.Graph.maxindex;
/**
 * FXML Controller class
 *
 * @author dusan
 */
public class SelectedNodeEditController implements Initializable {

    @FXML
    private TextField fieldOne, fieldTwo;
    
    @FXML
    private ColorPicker colorPick;
    
    @FXML
    public void setStart() {
        fieldOne.setText(FXMLDocumentController.clicked.getLabel());
        fieldTwo.setText(Integer.toString((int)FXMLDocumentController.clicked.getR()));
        colorPick.setValue(FXMLDocumentController.clicked.getColor());
    }
    
    @FXML
    public void process() {
        Graph.stanja.add(new Skup(Graph.copyNodes(), Graph.copyBranches()));
        if (Graph.index==Graph.maxindex)
            Graph.maxindex++;
        Graph.index++;
        if (POOP_Projekat2.mi.isDisable())
            POOP_Projekat2.mi.setDisable(false);
        String s = fieldOne.getText();
        long k = Long.parseLong(fieldTwo.getText());
        Color c = colorPick.getValue();
        FXMLDocumentController.clicked.setLabel(s);
        FXMLDocumentController.clicked.setR(k);
        FXMLDocumentController.clicked.setColor(c);
        FXMLDocumentController.clicked.draw(POOP_Projekat2.c);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
