/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.isima.carsharing.elements.Node;
import org.isima.carsharing.elements.XML.XMLDataCollection;
import org.isima.carsharing.elements.XML.XMLNodeUtilities;
import org.isima.carsharing.elements.utilities.NodeUtilities;
import org.isima.otpclient.data.Response;
import org.isima.otpclient.data.utilities.DataConverterUtility;
import org.isima.otpclient.request.Request;
import org.isima.otpclient.request.RequestFactory;
import org.isima.otpclient.routing.Planner;

/**
 *
 * @author Hicham
 */
public class testClass {

    public static void main(String[] args) throws MalformedURLException, IOException, JAXBException {
        XMLDataCollection dataCollected = XMLNodeUtilities.unmarshal(new File("C:\\Users\\Hicham\\Desktop\\PFA 3 bis\\data\\bicycle-rental-dijon.xml"));
        List<Node> nodeCollection = new LinkedList<>();
        XMLNodeUtilities.XMLtoElements(dataCollected, nodeCollection);
        NodeUtilities utility = new NodeUtilities();
        utility.getIncompleteNodes(nodeCollection, true);
        utility.getIncompleteNodes(nodeCollection, false);
        Request request;
        RequestFactory rqFactory = new RequestFactory();
        Planner pl;
        Response response;
        List<Response> responseCollection = new LinkedList<>();
        List<Response> badResponse = new LinkedList<>();

        for (Node fromNode : nodeCollection) {
            request = rqFactory.createRequest(fromNode, fromNode);

            request.setParameter(Request.DATE, DataConverterUtility.getDateFromGregorianCalendar(new GregorianCalendar()));
            request.setParameter(Request.MODE, "CAR");
            request.setParameter(Request.LOCALE, "fr_FR");
            request.setParameter(Request.INTERMEDIATE_STOPS, "false");

            pl = new Planner("http://localhost:8080/otp-rest-servlet/plan", request);
            response = pl.sendRequest();
            //System.out.println(response.toString());
            if (response.getDistance() == -1 && response.getDuration() == -1) {
                badResponse.add(response);
            }
        }
        //Print of Dijon
        System.out.print("\t\t\t\t");
        for (Node nde : nodeCollection) {
            System.out.print(nde.getPosition() + "\t\t\t");
        }
        System.out.print("\n");
        double previous = -2.0d;
        for (Node fromNode : nodeCollection) {
            System.out.print(fromNode.getPosition());
            for (Node toNode : nodeCollection) {
                //if(fromNode != toNode){
                request = rqFactory.createRequest(fromNode, toNode);

                request.setParameter(Request.DATE, DataConverterUtility.getDateFromGregorianCalendar(new GregorianCalendar()));
                request.setParameter(Request.MODE, "CAR");
                request.setParameter(Request.LOCALE, "fr_FR");
                request.setParameter(Request.INTERMEDIATE_STOPS, "false");

                pl = new Planner("http://localhost:8080/otp-rest-servlet/plan", request);
                response = pl.sendRequest();
                if (previous == -2.0d) {
                    System.out.print("\t\t" + response.getDistance() + "," + response.getDuration());
                    previous = response.getDistance();
                } else if (previous == 0.0d) {
                    System.out.print("\t\t\t\t\t" + response.getDistance() + "," + response.getDuration());
                    previous = response.getDistance();
                } else if (previous == -1.0d) {
                    if (response.getDistance() == -1.0d) {
                        System.out.print("\t\t\t\t" + response.getDistance() + "," + response.getDuration());
                        previous = response.getDistance();
                    } else {
                        System.out.print("\t\t\t\t" + response.getDistance() + "," + response.getDuration());
                        previous = response.getDistance();
                    }
                } else {
                    System.out.print("\t\t" + response.getDistance() + "," + response.getDuration());
                    previous = response.getDistance();
                }

                //System.out.println(response.toString());
                responseCollection.add(response);
                //}
            }
            previous = -2.0d;
            System.out.print("\n");
        }

        System.out.println("End;");

    }
}
