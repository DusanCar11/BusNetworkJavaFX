/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poop_projekat2;

import GrafickiDeo.Branch;
import GrafickiDeo.Element;
import GrafickiDeo.Graph;
import GrafickiDeo.GraphCollector;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ColorPicker;
import GrafickiDeo.Node;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
/**
 *
 * @author dusan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private MenuItem importMenuItem;
    @FXML
    private ColorPicker chosenNodeColor, chosenBranchColor;
    
    @FXML
    private ScrollPane scroll;
    
    public static Node clicked=null;
    private static boolean released=true;
    
    @FXML
    private void handleResizeItem(ActionEvent a) {
        Graph.resizeCanvas(POOP_Projekat2.c, POOP_Projekat2.sta.getHeight(), POOP_Projekat2.sta.getWidth());
    }
    
    @FXML
    private void force() {
        Platform.runLater(new Runnable(){
            public void run(){  
               Graph.mayTheForceBeWithYou(POOP_Projekat2.c);
            }
        });
    }
    
    @FXML
    private void discoNit() {
        Platform.runLater(new Runnable() {
            public void run() {
                Graph.makeDisco(POOP_Projekat2.c);
            }
        });
    }
    
    @FXML
    private void shortestDialog(ActionEvent a) {
         try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ShortestDialog.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Change node's name:");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {}
    }
    
    @FXML
    private void handleChangeNameItem(ActionEvent a) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ChangeNameDialog.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Change node's name:");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {}
    }
    
    @FXML
    private void handleHidingNodes(ActionEvent a) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("HidingNodesDialog.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Hide nodes:");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {}
    }
    
    @FXML
    private void handleSelectedNodeEditItem(ActionEvent a) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("SelectedNodeEdit.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit selected node:");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {}
    }
    
    @FXML
    private void handleUndoItem(ActionEvent a) {
        Graph.undo(POOP_Projekat2.c);
    }
    
    @FXML
    private void handleRedoItem(ActionEvent a) {
        Graph.redo(POOP_Projekat2.c);
    }
    
    @FXML
    private void handleAddItem(ActionEvent a) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddNodeDialog.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Add node:");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {}
    }
    
    @FXML
    private void handleEditBranchNum(ActionEvent a) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("EditAllNodes.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit branch");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {}
    }
    
    @FXML
    private void handleRefactorItem(ActionEvent a) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("RefactorDialog.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Refactor:");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {}
    }
    
    @FXML
    private void handleAddBranchItem(ActionEvent a) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddBranchDialog.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Add branch:");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {}
    }
    
    @FXML
    private void handleImportItem(ActionEvent a) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ImportDialog.fxml"));
            Parent root1 = (Parent)fxmlloader.load();
            Stage stage = new Stage();
            stage.setTitle("Import window");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e) {}
    }
    
    @FXML
    private void canvasClicked(MouseEvent m) {
        Graph.defaultColors(POOP_Projekat2.c);
        clicked=Graph.getClickedNode(POOP_Projekat2.c, m.getX(), m.getY());
        for (Node n: Graph.nodes) {
            if (n.getColor()==Color.CYAN)
                n.setColor(Graph.nodeColor);
        }
        Graph.redraw(POOP_Projekat2.c);
    }
    
    @FXML
    private void ExportImage() {
	Scene scene = POOP_Projekat2.sta.getScene();
        String path = new File("").getAbsolutePath()+"Slika.png";
        WritableImage image = scene.lookup("#canvas").snapshot(new SnapshotParameters(), null);
	 File file = new File(path);
     try { 
         ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
         e.printStackTrace();
        }
    }
    
    @FXML
    private void ExportAsDNG() {
        try {
            File fajl=new File("Dusan_node");
            File fajl2=new File("Dusan_branch");
            FileOutputStream fout = new FileOutputStream(fajl.getAbsolutePath());
            FileOutputStream fout2 = new FileOutputStream(fajl2.getAbsolutePath());
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            ObjectOutputStream oos2 = new ObjectOutputStream(fout2);
            oos.writeInt(Graph.nodes.size());
            oos2.writeInt(Graph.branches.size());
            for (Node n: Graph.nodes)
                oos.writeObject(n);
            for (Branch b:Graph.branches)
                oos2.writeObject(b);
            oos.flush();
            oos.close();
            oos2.flush();
            oos2.close();
        }
        catch (FileNotFoundException io) {}
        catch (IOException io) {}
    }
    
    @FXML
    private void zoom(ScrollEvent m) {
        double returned = m.getDeltaY();
        if (returned > 0) {
            POOP_Projekat2.c.setHeight(POOP_Projekat2.c.getHeight()*1.3);
            POOP_Projekat2.c.setWidth(POOP_Projekat2.c.getWidth()*1.3);
            Graph.readjustAll(1.3);
        }
        else {
            POOP_Projekat2.c.setHeight(POOP_Projekat2.c.getHeight()*0.7);
            POOP_Projekat2.c.setWidth(POOP_Projekat2.c.getWidth()*0.7);
            Graph.readjustAll(0.7);
        }
        GraphicsContext g = POOP_Projekat2.c.getGraphicsContext2D();
        g.clearRect(0, 0, POOP_Projekat2.c.getWidth(), POOP_Projekat2.c.getHeight());
        Graph.redraw(POOP_Projekat2.c);
    }
    
    @FXML
    private void canvasDragged(MouseEvent m) {
        double x = m.getX();
        double y = m.getY();
        released=false;
        if ((clicked==null) || (released==true))
            clicked=Graph.getClickedNode(POOP_Projekat2.c, m.getX(), m.getY());
        if (clicked !=null) {
            clicked.setX((long)x);
            clicked.setY((long)y);
            Graph.redraw(POOP_Projekat2.c);
        }
    }
    
    @FXML
    private void canvasReleased(MouseEvent m) {
        released=true;
        if (clicked!=null)
            clicked.highlighted=false;
        //clicked=null;
    }
    
    @FXML
    private void nodeColorChanged() {
        Color cc = chosenNodeColor.getValue();
        Graph.changeColorNode(POOP_Projekat2.c,cc);
    }
    
    @FXML
    private void branchColorChanged() {
        Color cc= chosenBranchColor.getValue();
        Graph.changeColorBranch(POOP_Projekat2.c, cc);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
