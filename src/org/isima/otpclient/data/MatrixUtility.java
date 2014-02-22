/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.isima.otpclient.data;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.isima.carsharing.elements.Node;

/**
 *
 * @author Hicham
 */
public class MatrixUtility {
    private static final Logger logger = Logger.getLogger("CarSharingInstanceGen");
    
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
                    logger.log(Level.WARNING,"[{0}"+"]"+" Correcting one way path ending with [{1}]", new Object[]{MatrixUtility.class.getName(),node.getPosition()});
                    copyLineToColumn(line, nodeMatrix);
                }
            }else if(isNullLine){
                if(!isNullColumn){
                    logger.log(Level.WARNING,"[{0}"+"]"+" Correcting one way path starting with [{1}]", new Object[]{MatrixUtility.class.getName(),node.getPosition()});
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
