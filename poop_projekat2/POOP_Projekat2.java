/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poop_projekat2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.*;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
/**
 *
 * @author dusan
 */
public class POOP_Projekat2 extends Application {
    static Canvas c;
    public static MenuItem mi, mi2;
    static Stage sta;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        sta=stage;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Ekstra graf");
        stage.show();
        
        mi=((MenuBar)scene.lookup("#menuBar")).getMenus().get(1).getItems().get(8);
         mi2=((MenuBar)scene.lookup("#menuBar")).getMenus().get(1).getItems().get(9);
        c=(Canvas)scene.lookup("#canvas");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
