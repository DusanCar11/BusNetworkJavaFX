/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafickiDeo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dusan
 */
public class CSVFormat extends Format {
    
    public CSVFormat() {super();}
    
    public void read() {
            String noderegex = "([^;]*);([^\\n]*)\\n";
            Pattern nodeRegex = Pattern.compile(noderegex);
            File file = new File(Graph.fileName);
            StringBuilder sb = new StringBuilder();
            try {
                Scanner sc = new Scanner(file);      
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
                sb.append("\n");
            }
            Matcher m = nodeRegex.matcher(sb.toString());
            while (m.find()) {
                int exists1=0, exists2=0;
                Node nodeMaker1=null, nodeMaker2=null;
                for (Node n: Graph.nodes) {
                    if (n.getLabel().equals(m.group(1))) {
                        exists1=1;
                        nodeMaker1=n;
                    }
                    if (n.getLabel().equals(m.group(2))) {
                        exists2=1;
                        nodeMaker2=n;
                    }
                }
                if (exists1==0) {
                    nodeMaker1=new Node(m.group(1), Graph.nodeColor);
                    Graph.nodes.add(nodeMaker1);
                }
                if ((exists2==0) && (!m.group(1).equals(m.group(2)))) {
                    nodeMaker2=new Node(m.group(2), Graph.nodeColor);
                    Graph.nodes.add(nodeMaker2);
                }
                if (nodeMaker1!=null && nodeMaker2!=null) {
                    nodeMaker1.incBranchesOut();
                    nodeMaker2.incBranchesIn();
                }
                if (!m.group(1).equals(m.group(2))) {
                    Graph.branches.add(new Branch(nodeMaker1, nodeMaker2));
                    nodeMaker1.addNeighbour(nodeMaker2);
                }
            }
        }
        catch (FileNotFoundException ie) {}
    } 
}
