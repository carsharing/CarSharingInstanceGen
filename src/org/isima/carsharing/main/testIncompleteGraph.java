/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.isima.carsharing.main;

import java.util.LinkedList;
import org.isima.carsharing.elements.Node;
import org.isima.carsharing.elements.utilities.GraphFactory;
import org.isima.otpclient.data.NodeMatrix;
import org.isima.otpclient.data.Response;

/**
 *
 * @author Hicham
 */

/**              ______(2)_______
 *              /                \
 *            (0,0)   (0,1)    (0,2)
 *              x--(1)--y--(1)--z      
 *               \      |      /
 *               (1)   (1)   (1)
 *                 \    |   /
 *                  \   |  /      
 *                   \  | / 
 *                    t(1,0)
 * @author Hicham
 */
public class testIncompleteGraph {
    public static void main(String[] args){
        Node nodex = new Node(0d, 0d, 1, null);
        Node nodey = new Node(0d, 1d, 1, null);
        Node nodez = new Node(0d, 2d, 1, null);
        Node nodet = new Node(1d, 0d, 1, null);
        
        Response responsexx = new Response(nodex, nodex, 0d, 0d);
        Response responseyy = new Response(nodey, nodey, 0d, 0d);
        Response responsezz = new Response(nodez, nodez, 0d, 0d);
        Response responsett = new Response(nodet, nodet, 0d, 0d);
        
        Response responsexy = new Response(nodex, nodey, 1d, 1d);
        Response responsexz = new Response(nodex, nodez, 2d, 2d);
        Response responsext = new Response(nodex, nodet, 1d, 1d);
        
        Response responseyx = new Response(nodey, nodex, 1d, 1d);
        Response responseyz = new Response(nodey, nodez, 1d, 1d);
        Response responseyt = new Response(nodey, nodet, 1d, 1d);
        
        Response responsezx = new Response(nodez, nodex, 2d, 2d);
        Response responsezy = new Response(nodez, nodey, 1d, 1d);
        Response responsezt = new Response(nodez, nodet, 1d, 1d);
        
        Response responsetx = new Response(nodet, nodex, 1d, 1d);
        Response responsety = new Response(nodet, nodey, 1d, 1d);
        Response responsetz = new Response(nodet, nodez, 1d, 1d);
        
        NodeMatrix nodeMatrix = new NodeMatrix();
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(nodex);
        nodes.add(nodey);
        nodes.add(nodez);
        nodes.add(nodet);
        nodeMatrix.setNodes(nodes);
        
        nodeMatrix.addValue(nodex, nodex, responsexx);
        nodeMatrix.addValue(nodey, nodey, responseyy);
        nodeMatrix.addValue(nodez, nodez, responsezz);
        nodeMatrix.addValue(nodet, nodet, responsett);
        
        nodeMatrix.addValue(nodex, nodey, responsexy);
        nodeMatrix.addValue(nodex, nodez, responsexz);
        nodeMatrix.addValue(nodex, nodet, responsext);
        
        nodeMatrix.addValue(nodey, nodex, responseyx);
        nodeMatrix.addValue(nodey, nodez, responseyz);
        nodeMatrix.addValue(nodey, nodet, responseyt);
        
        nodeMatrix.addValue(nodez, nodex, responsezx);
        nodeMatrix.addValue(nodez, nodey, responsezy);
        nodeMatrix.addValue(nodez, nodet, responsezt);        
        
        nodeMatrix.addValue(nodet, nodex, responsetx);
        nodeMatrix.addValue(nodet, nodey, responsety);
        nodeMatrix.addValue(nodet, nodez, responsetz);
        
        GraphFactory gf = new GraphFactory();
        
        gf.createIncompleteGraph(nodeMatrix);
        
        for(Response response : nodeMatrix.getAllValues()){
            System.out.println("Response: ");
            System.out.println("\tFrom: "+response.getFromNode().toString());
            System.out.println("\tTo: "+response.getToNode().toString());
            System.out.println("\tAlive: "+response.isAlive());
            System.out.println("\tMarked: "+response.isMarqued());
        }
        
        System.out.println("End;");
    }
}
