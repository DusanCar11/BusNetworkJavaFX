/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafickiDeo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.SortedSet;

/**
 *
 * @author dusan
 */
public class DNGFormat extends Format {
    
    public DNGFormat() {}
    
    public void read() {
         try {
            FileInputStream fout = new FileInputStream(Graph.fileName+"_node");
            FileInputStream fout2 = new FileInputStream(Graph.fileName+"_branch");
            ObjectInputStream oos = new ObjectInputStream(fout);
            ObjectInputStream oos2 = new ObjectInputStream(fout2);
            int size1=oos.readInt();
            int size2=oos2.readInt();
            for (int i=0; i<size1; i++) {
                Node n = (Node)oos.readObject();
                if (n!=null)
                Graph.nodes.add(n);
            }
            for (int i=0; i<size2; i++)
                Graph.branches.add((Branch)oos2.readObject());
            oos.close();
            oos2.close();
        }
        catch (ClassNotFoundException io) {}
        catch (FileNotFoundException io) {}
        catch (IOException io) {}
    }
}
