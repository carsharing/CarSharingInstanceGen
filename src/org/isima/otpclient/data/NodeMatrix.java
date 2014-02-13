/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.isima.otpclient.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.isima.carsharing.elements.Node;

/**
 *
 * @author Hicham
 */
public class NodeMatrix {
     private transient Map<Node,Map<Node,Response>> data;

    public NodeMatrix() {
        data = new HashMap<>();
    }
     
    public NodeMatrix(Collection<Node> nodes) {
        data = new HashMap<>();
        HashMap<Node, Response> tmp;
        for(Node lineNode:nodes){
            for(Node columnNode:nodes){
                tmp = new HashMap<>();
                tmp.put(columnNode, null);
                data.put(lineNode,tmp);
            }
        }
    } 
    
    public NodeMatrix setNodes(Collection<Node> nodes){
        data = new HashMap<>();
        HashMap<Node, Response> tmp;
        for(Node lineNode:nodes){
            for(Node columnNode:nodes){
                tmp = new HashMap<>();
                tmp.put(columnNode, null);
                data.put(lineNode,tmp);
            }
        }
        return this;
    } 
    
    public NodeMatrix addValue(Node fromNode,Node toNode,Response value){
         Map<Node, Response> line = this.data.get(fromNode);
         if(line == null){
             line = new HashMap<>();
             line.put(toNode, value);
             this.data.put(fromNode, line);
             return this;
         }else{
             line.put(toNode, value);
             return this;
         }
    }
    
    public Response getValue(Node fromNode,Node toNode){
        Map<Node, Response> line = this.data.get(fromNode);
         if(line != null){
             Response val = line.get(toNode);
             if(val == null){
                 return null;
             }else{
                 return val;
             }
         }else{
             return null;
         }
    }
    
    public void setValue(Node fromNode,Node toNode,Response value){
        this.data.get(fromNode).put(toNode, value);
    }
    
    public Collection<Response> getLine(Node node){
        Map<Node, Response> line = this.data.get(node);
        return line.values();
    }
    
    public Collection<Node> getNodes(){
         return this.data.keySet();
    }
    
    public Collection<Response> getColumn(Node node){
        List<Response> result = new LinkedList<>();
        for(Node lineNode:this.data.keySet()){
            Response response = this.data.get(lineNode).get(node);
            result.add(response);
        }
        return result;
    }
    
    public Set<Response> getAllValues(){
        Set<Response> result = new HashSet<>();
        Collection<Response> line;
        for(Node node : this.getNodes()){
            line = this.getLine(node);
            result.addAll(line);
        }
        return result;
    }
    
    public Set<Response> getAllKilledValues(){
        Set<Response> result = new HashSet<>();
        Collection<Response> values = this.getAllValues();
        for(Response response : values){
            if(response.isAlive() == false){
                result.add(response);
            }
        }
        
        return result;
    }
    
    public String toString(){
        String result = "\t\t\t\t";
        double previous = -2.0d;
        Response response;
        
        for(Node ligne : data.keySet()){
            result = result + ligne.getPosition() +"\t\t\t";
        }
        result = result + "\n";
        for(Node ligne : data.keySet()){
            result = result + ligne.getPosition();
            for(Node column : data.get(ligne).keySet()){
                response = data.get(ligne).get(column);
                
                if (previous == -2.0d) {
                    //System.out.print("\t\t" + response.getDistance() + "," + response.getDuration());
                    result = result + "\t\t" + response.getDistance() + "," + response.getDuration();
                    previous = response.getDistance();
                } else if (previous == 0.0d) {
                    //System.out.print("\t\t\t\t\t" + response.getDistance() + "," + response.getDuration());
                    result = result + "\t\t\t\t\t" + response.getDistance() + "," + response.getDuration();
                    previous = response.getDistance();
                } else if (previous == -1.0d) {
                    if (response.getDistance() == -1.0d) {
                        //System.out.print("\t\t\t\t" + response.getDistance() + "," + response.getDuration());
                        result = result + "\t\t\t\t" + response.getDistance() + "," + response.getDuration();
                        previous = response.getDistance();
                    } else {
                        //System.out.print("\t\t\t\t" + response.getDistance() + "," + response.getDuration());
                        result = result + "\t\t\t\t" + response.getDistance() + "," + response.getDuration();
                        previous = response.getDistance();
                    }
                } else {
                    //System.out.print("\t\t" + response.getDistance() + "," + response.getDuration());
                    result = result + "\t\t" + response.getDistance() + "," + response.getDuration();
                    previous = response.getDistance();
                }
            }
            previous = -2.0d;
            result = result + "\n";
        }
        
        return result;
    }
}
