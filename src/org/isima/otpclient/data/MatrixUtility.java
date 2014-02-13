/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.isima.otpclient.data;

import java.util.Collection;
import org.isima.carsharing.elements.Node;

/**
 *
 * @author Hicham
 */
public class MatrixUtility {
    public static void correctOneWayPaths(NodeMatrix nodeMatrix){
        boolean isNullColumn;
        boolean isNullLine;
        for(Node node:nodeMatrix.getNodes()){
            Collection<Response> column = nodeMatrix.getColumn(node);
            Collection<Response> line = nodeMatrix.getLine(node);
            
            isNullColumn = verifyNullColumn(node, column);
            isNullLine = verifyNullLine(node, line);
            
            if(isNullColumn){
                if(!isNullLine){
                    System.out.println("Line ok, column not ok: "+node.getPosition());
                    copyLineToColumn(line, nodeMatrix);
                }
            }else if(isNullLine){
                if(!isNullColumn){
                    System.out.println("Line not ok, column ok: "+node.getPosition());
                    copyColumnToLine(line, nodeMatrix);
                }
            }
            
        }
    }
    
    private static boolean verifyNullColumn(Node destination,Collection<Response> column){
        for(Response response : column){
            if(!response.getFromNode().equals(destination)){
                if(response.getDistance() != -1d){
                    return false;
                }
            }
        }
        return true;
    }
    
    private static boolean verifyNullLine(Node source,Collection<Response> line){
        for(Response response : line){
            if(!response.getToNode().equals(source)){
                if(response.getDistance() != -1d){
                    return false;
                }
            }
        }
        return true;
    }
    
    private static void copyLineToColumn(Collection<Response> line,NodeMatrix nodeMatrix){
        for(Response response : line){
              nodeMatrix.setValue(response.getToNode(), response.getFromNode(), new Response(response.getToNode(), response.getFromNode(), response.getDuration(), response.getDistance()));
        }
    }
    
    private static void copyColumnToLine(Collection<Response> column,NodeMatrix nodeMatrix){
        for(Response response : column){
            nodeMatrix.setValue(response.getToNode(), response.getFromNode(), new Response(response.getFromNode(), response.getToNode(), response.getDuration(), response.getDistance()));
        }
    }
}
