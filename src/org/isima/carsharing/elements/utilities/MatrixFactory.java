/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.elements.utilities;

import java.io.IOException;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.isima.carsharing.elements.Node;
import org.isima.otpclient.data.NodeMatrix;
import org.isima.otpclient.data.Response;
import org.isima.otpclient.data.utilities.DataConverterUtility;
import org.isima.otpclient.request.Request;
import org.isima.otpclient.request.RequestFactory;
import org.isima.otpclient.routing.Planner;

/**
 *
 * @author Hicham
 */
public class MatrixFactory {

    private static final Logger logger = Logger.getLogger("CarSharingInstanceGen");
    private static final String Local = "us_US";

    public NodeMatrix createNodeMatrix(String serviceURL, Collection<Node> nodes) {
        //TODO : node utilites for incomplete nodes
        NodeMatrix nodeMatrix = new NodeMatrix();

        //Create a requests factory and a planner to call the routing server
        RequestFactory requestFactory = new RequestFactory();
        Request request;
        Planner planner;
        Response response;

        logger.log(Level.INFO, "[{0}" + "]" + " Requesting routes from OTP server for all nodes", MatrixFactory.class.getName());
        for (Node fromNode : nodes) {
            for (Node toNode : nodes) {
                try {
                    logger.log(Level.FINE, "[{0}" + "]" + " trip:[from: {1}] [to: {2}]", new Object[]{MatrixFactory.class.getName(), fromNode.getPosition(), toNode.getPosition()});
                    //Creating the requet from the two nodes
                    request = requestFactory.createRequest(fromNode, toNode);
                    //Setting request parameters
                    request.setParameter(Request.DATE, DataConverterUtility.getDateFromGregorianCalendar(new GregorianCalendar()));
                    request.setParameter(Request.MODE, "CAR");
                    request.setParameter(Request.LOCALE, Local);
                    request.setParameter(Request.INTERMEDIATE_STOPS, "false");
                    //Calculate route
                    planner = new Planner(serviceURL, request);
                    response = planner.sendRequest();
                    //Add in node matrix
                    nodeMatrix.addValue(fromNode, toNode, response);
                } catch (IOException ex) {
                    Logger.getLogger(MatrixFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return nodeMatrix;
    }

}
