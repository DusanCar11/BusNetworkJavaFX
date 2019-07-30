/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafickiDeo;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;

/**
 *
 * @author dusan
 */

public class Node extends Element implements Serializable {
    public static int ID=0;
    public int id=++ID;
    private transient Color color;
    public transient boolean highlighted=false;
    public transient Color highlightedcolor=Color.GREEN;
    private LinkedList<Node> neighbours = new LinkedList<Node>();
    protected String label;
    protected int branchesIn, branchesOut;
    protected long x,y;
    protected double r;
    
    public Node(String name, Color c) {
        label=name; color=c; r=11;
    }
    
    public void addNeighbour(Node n) {
        neighbours.add(n);
    }
    
    public LinkedList<Node> getNeighbours() {
        return neighbours;
    }
    
    public void refactorNodesExpand(Canvas c,double factor) {
        
        x+=(1-factor)*(c.getWidth()/2-x);
        y+=(1-factor)*(c.getHeight()/2-y);
    }
    
    public void draw(Canvas c) {
        GraphicsContext g = c.getGraphicsContext2D();
        if (highlighted)
            g.setFill(highlightedcolor);
        else
            g.setFill(color);
        g.fillOval(x-r, y-r, 2*r, 2*r);
        g.setFill(Color.BLUE);
        g.fillText(label, x, y);
    }
    
    public void incBranchesIn() {
        branchesIn++;
    }
    
    public void incBranchesOut() {
        branchesOut++;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setX(long xx) {
        x=xx;
    }
    
    public void setY(long yy) {
        y=yy;
    }
    
    public long getX() {
        return x;
    }
    
    public void setR(double rr) {
        if (rr<1)
            r=1;
        else
            r=rr;
    }
    
    public long getY() {
        return y;
    }
    
    public double getR() {
        return r;
    }
    
    public void setLabel(String s) {
        label=s;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color c) {
        color=c;
    }
    
    public int getBranchesIn() {
        return branchesIn;
    }
    
    public int getBranchesOut() {
        return branchesIn;
    }
}
