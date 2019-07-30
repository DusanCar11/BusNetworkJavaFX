/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafickiDeo;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Comparator;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import poop_projekat2.*;
/**
 *
 * @author dusan
 */
public class Graph {
    public static String fileName;
    public static SortedSet<Node> nodes = new TreeSet<Node>(Comparator.comparing(Node::getLabel));
    public static SortedSet<Branch> branches = new TreeSet<Branch>(Comparator.comparing(Branch::getID));
    private static double height=0,width=0;
    private static double xcoord=20, ycoord=20;
    public static Color nodeColor=Color.RED, branchColor=Color.BLACK, labelColor=Color.BLUE, highlight=Color.GREEN;
    private static Node toReturn=null;
    private static long nodeSize=10;
    private static Branch branchReturn=null;
    public static Vector<Skup> stanja = new Vector<Skup>();
    public static int index=0;
    public static int maxindex=0;
    public static boolean floyd=false;
    
    public Graph() {
        fileName="";
    }
    
    public static void setFileName(String file) {
        fileName=file;
    }
    
    public static void loadFiles(Canvas canvas) {
        String fileregex=".*.csv";
        if (fileName.matches(fileregex)) {
            new CSVFormat().read();
        }
        else if (fileName.equals(".*.gml")) {
            new GMLFormat().read();
        }
        else {
            new DNGFormat().read();
        }
        height = canvas.getHeight()-10;
        width = canvas.getWidth()-20;
        GraphicsContext g=canvas.getGraphicsContext2D();
        g.setStroke(branchColor);
        for (Node n: nodes) {
            int x = (int)(Math.random()*(width-20)+20);
            int y = (int)(Math.random()*(height-20)+20);
            for (int i=0; i<3; i++) {
                if ((!check_intersection(x,y)) || (i==2)) {
                    n.setX(x);
                    n.setY(y);
                }  
            }
            n.draw(canvas);
        }
        for (Branch b: branches)
            b.draw(canvas);
        for (Node n: nodes)
            n.draw(canvas);
        stanja.add(new Skup(copyNodes(), copyBranches()));
        index++;
        maxindex++;
        if (index==maxindex)
            POOP_Projekat2.mi2.setDisable(true);
    }
    
     public static void makeDisco(Canvas c) {
        try {
            double r = (double)(Math.random()*1);
            double g = (double)(Math.random()*1);
            double b = (double)(Math.random()*1);
            Color cc = Color.color(r, g, b);
            GraphicsContext gg = c.getGraphicsContext2D();
            for (int i=0; i<100; i++) {
                synchronized (c) {
                    for (Node n: nodes) {
                        n.setColor(cc);
                        n.draw(c);
                    }
                    
                }
                Thread.sleep(40);
            }
        } catch (InterruptedException ie) {}
    }
    
    public static void hide(Canvas c, int k) {
        GraphicsContext g = c.getGraphicsContext2D();
        g.clearRect(0,0,c.getWidth(), c.getHeight());
        for (Node n: nodes) {
            if (n.getBranchesIn()<k)
                continue;
            n.draw(c);
        }
        for (Branch b: branches) {
            if (b.getNodeFrom().getBranchesIn()<k || b.getNodeTo().getBranchesIn()<k)
                continue;
            b.draw(c);
        }
    }
    
    public static SortedSet<Node> copyNodes() {
        SortedSet<Node> copy = new TreeSet<Node>(Comparator.comparing(Node::getLabel));
        for (Node n: nodes) {
            Node m = new Node(n.getLabel(), n.getColor());
            m.setX(n.getX());
            m.setY(n.getY());
            m.setR(n.getR());
            copy.add(m);
        }
        return copy;
    }
    
    public static SortedSet<Branch> copyBranches() {
        SortedSet<Branch> copy = new TreeSet<Branch>(Comparator.comparing(Branch::getID));
        for (Branch n: branches) {
            Branch m = new Branch(n.getNodeFrom(), n.getNodeTo());
            m.setWidth(n.getWidth());
            m.setColor(n.getColor());
            copy.add(m);
        }
        return copy;
    }
    
    private static boolean check_intersection(int xx, int yy) {
        for (Node n: nodes) {
            long dist = (long)(Math.sqrt(Math.pow(xx-n.getX(), 2)+ Math.pow(yy-n.getY(),2)));
            if (dist < 2*n.getR())
                return true;
            if ((xx-n.getR()<=0) || (yy-n.getR()<=0) || (xx+n.getR()>=width) || (yy+n.getR()>=height))
                return true;
        }
        return false;
    }
    
    public static void resizeCanvas(Canvas c,double h, double w) {
        double oldheight = c.getHeight();
        double oldwidth=c.getWidth();
        c.setHeight(h-104);
        c.setWidth(w-40);
        width=w-40;
        height=h-104;
        GraphicsContext g = c.getGraphicsContext2D();
        g.clearRect(0, 0, c.getWidth(), c.getHeight());
        for (Node n: nodes) {
            n.setX((long)(c.getWidth()/oldwidth*n.getX()));
            n.setY((long)(c.getHeight()/oldheight*n.getY()));
            n.draw(c);
        }
        for (Branch b: branches)
            b.draw(c);
        for (Node n: nodes) {
            n.draw(c);
        }
    }
    
    public static void addNode(Canvas c, String l) {
        stanja.add(new Skup(copyNodes(), copyBranches()));
        if (index==maxindex)
            maxindex++;
        index++;
         if (POOP_Projekat2.mi.isDisable())
            POOP_Projekat2.mi.setDisable(false);

        for (Node n:nodes)
            if (n.getLabel().equals(l))
                return;
        Node nodeMaker = new Node(l, nodeColor);
        nodeMaker.setX((long)xcoord);
        nodeMaker.setY((long)ycoord);
        xcoord+=50;
        if (xcoord >= width) {
            ycoord+=50;
            xcoord=20;
        }
        nodes.add(nodeMaker);
        nodeMaker.draw(c);
    }
    
    public static void addBranch(Canvas c, String from, String to) {
        stanja.add(new Skup(copyNodes(), copyBranches()));
        if (index==maxindex)
            maxindex++;
        index++;
         if (POOP_Projekat2.mi.isDisable())
            POOP_Projekat2.mi.setDisable(false);

        Node nodeFrom=null, nodeTo=null;
        for (Node n: nodes) {
            if (n.getLabel().equals(from))
                nodeFrom=n;
            if (n.getLabel().equals(to))
                nodeTo=n;
            if (nodeFrom !=null && nodeTo!=null)
                break;
        }
        if (nodeFrom==null || nodeTo==null)
            return;
        nodeFrom.incBranchesOut();
        nodeTo.incBranchesIn();
        Branch branchMaker = new Branch(nodeFrom, nodeTo);
        branches.add(branchMaker);
        branchMaker.draw(c);
    }
    
    public static void changeLabel(Canvas c, String s1,String s2) {
        stanja.add(new Skup(copyNodes(), copyBranches()));
        if (index==maxindex)
            maxindex++;
        index++;
         if (POOP_Projekat2.mi.isDisable())
            POOP_Projekat2.mi.setDisable(false);

        Node toChange=null;
        for (Node n: nodes) {
            if (n.getLabel().equals(s1)) {
                toChange=n;
                break;
            }
        }
        if (toChange != null) {
            toChange.setLabel(s2);
            GraphicsContext g = c.getGraphicsContext2D();
            g.clearRect(toChange.getX()-toChange.getR(), toChange.getY()-toChange.getR(), 2*toChange.getR(), 2*toChange.getR());
            toChange.draw(c);
        }
    }
    
    public static Node getClickedNode(Canvas c,double x, double y) {
        toReturn=null;
        for (Node n: nodes) {
            long dist = (long)(Math.sqrt(Math.pow(x-n.getX(), 2)+ Math.pow(y-n.getY(),2)));
            if (dist <= 2*n.getR()) {
                //n.setColor(highlight);
                n.highlighted=true;
                toReturn=n;
                n.draw(c);
                return n;
            }
        }
        return null;
    }
    
    public static Branch getClickedBranch(Canvas c, int x, int y) {
        branchReturn=null;
        GraphicsContext g = c.getGraphicsContext2D();
        for (Branch b: branches) {
            if (collinear((int)b.getNodeFrom().getX(), (int)b.getNodeFrom().getY(), (int)b.getNodeTo().getX(), (int)b.getNodeTo().getY(), x,y)) {
                branchReturn=b;
                g.setLineWidth(3);
                g.setStroke(Color.WHITE);
                g.strokeLine((double)b.getNodeFrom().getX(), (double)b.getNodeFrom().getY(), (double)b.getNodeTo().getX(), (double)b.getNodeTo().getY());
                g.setStroke(highlight);
                g.strokeLine((double)b.getNodeFrom().getX(), (double)b.getNodeFrom().getY(), (double)b.getNodeTo().getX(), (double)b.getNodeTo().getY());
                break;
            }
        }
        return branchReturn;
    }
    
    private static boolean collinear(int x1, int y1, int x2, int y2, int x3, int y3)
    {
         
        int a = x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2);
        if (a==0)
            return true;
        return false;
    }
    
    public static void readjustAll(double k) {
       stanja.add(new Skup(copyNodes(), copyBranches()));
       if (index==maxindex)
            maxindex++;
       index++;
       
        if (POOP_Projekat2.mi.isDisable())
            POOP_Projekat2.mi.setDisable(false);

        for (Node n: nodes) {
            n.setX((long)(n.getX()*k));
            n.setY((long)(n.getY()*k));
            n.setR((n.getR()*k));
        }
        for (Branch b: branches)
            b.setWidth((int)(b.getWidth()*k));
    }
    
    public static void defaultColors(Canvas c) {
        if (toReturn!=null) {
            toReturn.setColor(toReturn.getColor());
            toReturn.draw(c);
        }
    }
    
    public static void redraw(Canvas c) {
        GraphicsContext g = c.getGraphicsContext2D();
        g.clearRect(0, 0, c.getWidth(), c.getHeight());
        g.setStroke(branches.first().getColor());
        for (Branch b: branches)
            b.draw(c);
        for (Node n: nodes) {
            g.setFill(n.getColor());
            n.draw(c);
        }
    }
    
    public static void changeColorNode(Canvas c, Color cc) {
        stanja.add(new Skup(copyNodes(), copyBranches()));
        if (index==maxindex)
            maxindex++;
        index++;
        
         if (POOP_Projekat2.mi.isDisable())
            POOP_Projekat2.mi.setDisable(false);
        for (Node n: nodes) {
            n.setColor(cc);
            n.draw(c);
        }
        nodeColor=cc;
    }
    
    public static void changeColorBranch(Canvas c, Color cc) {
        stanja.add(new Skup(copyNodes(), copyBranches()));
        if (index==maxindex)
            maxindex++;
        index++;
        if (POOP_Projekat2.mi.isDisable())
            POOP_Projekat2.mi.setDisable(false);
        GraphicsContext g = c.getGraphicsContext2D();
        branchColor=cc;
        for (Branch b: branches)
            b.setColor(cc);
        redraw(c);
    }
    
    public static void readjustBranchNum(Canvas c, int num, boolean more, long size, Color col) {
        stanja.add(new Skup(copyNodes(), copyBranches()));
        if (index==maxindex)
            maxindex++;
        index++;
        if (more) {
            for (Node n: nodes) {
                if (n.getBranchesIn()+n.getBranchesOut() >= num) {
                    n.setR(size);
                    n.setColor(col);
                }
            }
        }
        else {
            for (Node n: nodes) {
                if (n.getBranchesIn()+n.getBranchesOut() < num) {
                    n.setR(size);
                    n.setColor(col);
                }
            }
        }
        redraw(c);
        
    }
    
    public static void Flojd(Canvas c, String label1, String label2) {
        int matcvor[][] = new int[Node.ID+1][Node.ID+1];
        int matsled[][] = new int[Node.ID+1][Node.ID+1];
        for (int i=0; i<Node.ID+1; i++) {
            for (int j=0; j<Node.ID+1;j++) {
                matcvor[i][j]=10000;
            }
        }
        for (Branch b: branches) {
            int prvi = b.getNodeFrom().id;
            int drugi = b.getNodeTo().id;
            matcvor[prvi][drugi]=1;
            matsled[prvi][drugi]=drugi;
        }
        for (Node n: nodes) 
            matcvor[n.id][n.id]=0;
        for (Node k: nodes) {
            for (Node i: nodes) {
                for (Node j: nodes) {
                    if (matcvor[i.id][j.id] > matcvor[i.id][k.id]+matcvor[k.id][j.id]) {
                        matcvor[i.id][j.id]=matcvor[i.id][k.id]+matcvor[k.id][j.id];
                        matsled[i.id][j.id]=matsled[i.id][k.id];
                    }
                }
            }
        }
        int id1=0, id2=0;
        for (Node n: nodes) {
            if (n.getLabel().equals(label1))
                id1=n.id;
            if (n.getLabel().equals(label2))
                id2=n.id;
        }
        while (id1!=id2 && id1!=0) {
            for (Node n: nodes) {
                if (id1==n.id) {
                    n.setColor(Color.CYAN);
                    n.draw(c);
                    id1=matsled[id1][id2];
                    break;
                }
            }
        }
        if (id1!=0) {
        for (Node n: nodes) {
                if (id2==n.id) {
                    n.setColor(Color.CYAN);
                    n.draw(c);
                    break;
                }
            }
        }
        floyd=true;
    }
    
    public static void mayTheForceBeWithYou(Canvas c) {
        int i=0;
        SortedSet<Node> toPass = new TreeSet<Node>(Comparator.comparing(Node::getLabel));
        SortedSet<Node> visited = new TreeSet<Node>(Comparator.comparing(Node::getLabel));
        Vector<SortedSet<Node>> groups = new Vector<SortedSet<Node>>();
        for (Node n: nodes) {
            if (visited.contains(n))
                continue;
            visited.add(n);
            groups.add(new TreeSet<Node>(Comparator.comparing(Node::getLabel)));
            groups.get(i).add(n);
            for (Node neighbour: n.getNeighbours()) {
                if (!visited.contains(neighbour))
                    toPass.add(neighbour);
            }
            while (!toPass.isEmpty()) {
                Node newNeighbour= toPass.first();
                toPass.remove(newNeighbour);
                visited.add(newNeighbour);
                groups.get(i).add(newNeighbour);
                for (Node neighbour: n.getNeighbours()) {
                    if (!visited.contains(neighbour))
                        toPass.add(neighbour);
                }
            }
            i++;
        }
        int canvasParts = (int)Math.ceil(Math.sqrt(i));
        int lastCol = i%canvasParts;
        
        int minX=50;
        int minY=50;
        int maxX=(int)c.getWidth()/canvasParts;
        int maxY=(int)c.getHeight()/(i/canvasParts+1);
        
        for (int j=0; j<groups.size();j++) {
            int centX = minX+(maxX-minX)/2;
            int centY = minY+(maxY-minY)/2;
            int size = (maxX-minX)/4;
            Node l = groups.get(j).first();
            groups.get(j).first().setX(centX);
            groups.get(j).first().setY(centY);
            groups.get(j).remove(l);
            int groupSize = groups.get(j).size();
            double angle = Math.random()*Math.PI*2;
            for (int k=1; k<groups.get(j).size();k++) {
                Node gotNode = groups.get(j).first();
                groups.get(j).remove(gotNode);
                gotNode.setX((long)(centX+Math.cos(angle)*size));
                gotNode.setY((long)(centY+Math.cos(angle)*size));
                angle+=2*Math.PI/groupSize;
            }
            if (j+1==i-lastCol) {
                minX=50; maxX=(int)c.getWidth()/lastCol;
                minY+=(int)c.getHeight()/(i/canvasParts+1)-100;
                maxY+=(int)c.getHeight()/(i/canvasParts+1)-120;
            }
            else {
                minX+=(int)c.getWidth()/canvasParts;
                maxX+=(int)c.getWidth()/canvasParts;
                if (maxX>=c.getWidth()-30) {
                    minX=50;
                    maxX=(int)c.getWidth()/canvasParts;
                    minY+=(int)c.getHeight()/(i/canvasParts+1);
                    maxY+=(int)c.getHeight()/(i/canvasParts+1);
                }
                if (j+1+canvasParts==i-lastCol) {
                    minY-=100;
                    maxY-=100;
                }
            }
        }
        redraw(c);
    }
    
    public static void undo(Canvas c) {
        if (index==1) {
            POOP_Projekat2.mi.setDisable(true);
        }
        POOP_Projekat2.mi2.setDisable(false);
        stanja.add(new Skup(copyNodes(), copyBranches()));
        //index++;
        Skup s;
        index--;
        s = stanja.get(index);
        nodes = s.n;
        branches=s.b;
        redraw(c);
    }
    
    public static void redo(Canvas c) {
        Skup s;
        index++;
        if (index==maxindex)
            POOP_Projekat2.mi2.setDisable(true);
        s=stanja.get(index);

        nodes=s.n;
        branches=s.b;
        redraw(c);
    }
    
    public static void refactorAllNodes(Canvas c, double factor) {
        for (Node n: nodes) {
            n.refactorNodesExpand(c,factor);
        }
        GraphicsContext g = c.getGraphicsContext2D();
        g.clearRect(0,0,c.getWidth(), c.getHeight());
        for (Branch b:branches) {
            b.draw(c);
        }
        for (Node n:nodes) {
            n.draw(c);
        }
    }
}
