/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafickiDeo;
import java.io.Serializable;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
/**
 *
 * @author dusan
 */
public class Branch extends Element implements Serializable {
    private transient Color color;
    private static int id=0;
    private int ID=id++;
    private Node nodeFrom, nodeTo;
    private int branchWidth=2;
    
    public Branch(Node nF, Node nT) {
        nodeFrom=nF; nodeTo=nT; color= Color.BLACK;
    }
    
    public void setWidth(int bw) {
        if (bw<1)
            branchWidth=1;
        else
            branchWidth=bw;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color c) {
        color=c;
    }
    
    public int getWidth() {
        return branchWidth;
    }
    
    public Node getNodeFrom() {
        return nodeFrom;
    }
    
    public Node getNodeTo() {
        return nodeTo;
    }
    
    public void draw(Canvas c) {
        GraphicsContext g = c.getGraphicsContext2D();
        g.strokeLine((double)nodeFrom.getX(), (double)nodeFrom.getY(), (double)nodeTo.getX(), (double)nodeTo.getY());
    }
    
    public int getID() {
        return ID;
    }
    
    public String toString() {
        return nodeFrom.getLabel() + " " + nodeTo.getLabel();
    }
}
