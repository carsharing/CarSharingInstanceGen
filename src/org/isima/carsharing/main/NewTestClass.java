/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.isima.carsharing.elements.Node;
import org.isima.carsharing.elements.XML.XMLDataCollection;
import org.isima.carsharing.elements.XML.XMLNodeUtilities;
import org.isima.carsharing.elements.utilities.GraphBuilder;
import org.isima.carsharing.elements.utilities.GraphFactory;
import org.isima.carsharing.elements.utilities.MatrixFactory;
import org.isima.carsharing.elements.utilities.NodeUtilities;
import org.isima.carsharing.elements.utilities.Simulation;
import org.isima.otpclient.data.NodeMatrix;

/**
 *
 * @author Hicham
 */
public class NewTestClass {

    public static void main(String[] args) throws MalformedURLException, IOException, JAXBException {
        XMLDataCollection dataCollected = XMLNodeUtilities.unmarshal(new File("C:\\Users\\Hicham\\Desktop\\PFA 3 bis\\data\\bicycle-rental-dijon.xml"));
        List<Node> nodeCollection = new LinkedList<>();
        XMLNodeUtilities.XMLtoElements(dataCollected, nodeCollection);
        NodeUtilities utility = new NodeUtilities();
        Collection<Node> incompleteNodes = utility.getIncompleteNodes(nodeCollection, true);
        Collection<Node> incompleteNodes1 = utility.getIncompleteNodes(nodeCollection, false);
        
        utility.setDefaultValues(incompleteNodes, true);

        MatrixFactory matrixFactory = new MatrixFactory();
        NodeMatrix nodeMatrix = matrixFactory.createNodeMatrix("http://localhost:8080/otp-rest-servlet/plan", nodeCollection);
        System.out.println(nodeMatrix.toString());
        GraphFactory graphFactory = new GraphFactory();
        Simulation sim = graphFactory.createCompleteGraph(nodeMatrix);
        
        JAXBContext jc = JAXBContext.newInstance(GraphBuilder.class);
        
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(sim,new File("simulation.xml"));
        System.out.println("END;");
    }

}
