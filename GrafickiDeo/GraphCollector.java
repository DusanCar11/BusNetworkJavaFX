/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafickiDeo;

import java.io.Serializable;
import java.util.SortedSet;

/**
 *
 * @author dusan
 */
public class GraphCollector implements Serializable {
    public SortedSet<Node> set;
    public SortedSet<Branch> set2;
    
    public GraphCollector(SortedSet<Node> s, SortedSet<Branch> s2) {
        set=s; set2=s2;
    }
}
