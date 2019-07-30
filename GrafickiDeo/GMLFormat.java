/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafickiDeo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dusan
 */
public class GMLFormat extends Format {
    
    public GMLFormat() {}
    
    public void read() {
            String noderegex = "(node|edge)\\n\t\\[\\n\t( id| source) ([^\\n]*)\\n\t(\\]| target [^\\n]*\\n\t\\])";
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
                Node nodeMaker1=null, nodeMaker2=null;
                if (m.group(1).equals("node")) {
                    nodeMaker1=new Node(m.group(3), Graph.nodeColor);
                    Graph.nodes.add(nodeMaker1);
                }
                else {
                    char[] buff = new char[m.group(4).length()-2];
                    Node nodeFinder1=null, nodeFinder2=null;
                    m.group(4).getChars(8, m.group(4).length()-2, buff, 0);
                    StringBuilder ss=new StringBuilder();
                    for (int i=0; i<buff.length-9; i++)
                        ss.append(buff[i]);
                    for (Node n: Graph.nodes) {
                        if (n.getLabel().equals(m.group(3))) {
                            nodeFinder1=n;
                            continue;
                        }
                        if (n.getLabel().equals("11"))
                            System.out.println("Dusan");
                        if (n.getLabel().equals(ss.toString())) {
                            nodeFinder2=n;
                            continue;
                        }
                    }
                    if (nodeFinder2==null)
                        continue;
                    nodeFinder1.addNeighbour(nodeFinder2);
                    Graph.branches.add(new Branch(nodeFinder1, nodeFinder2));
                }
            }
        }
        catch (FileNotFoundException ie) {}
    }
}
