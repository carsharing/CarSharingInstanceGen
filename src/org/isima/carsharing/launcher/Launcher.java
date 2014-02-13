/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.isima.carsharing.launcher;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.isima.carsharing.elements.Node;
import org.isima.carsharing.elements.XML.XMLDataCollection;
import org.isima.carsharing.elements.XML.XMLNodeUtilities;
import org.isima.carsharing.elements.utilities.GraphBuilder;
import org.isima.carsharing.elements.utilities.GraphFactory;
import org.isima.carsharing.elements.utilities.MatrixFactory;
import org.isima.carsharing.elements.utilities.NodeUtilities;
import org.isima.carsharing.elements.utilities.Simulation;
import org.isima.carsharing.launcher.logging.LoggingConfiguratorFactory;
import org.isima.otpclient.data.NodeMatrix;

/**
 *
 * @author Hicham
 */
public class Launcher {
    public static void main(String[] args) throws Exception{
        //dC:7 dD:1 dP:7 dT:n/a dNm:test dNt:nt dV:n/a Llvl:a serv:localhost oConf:y margin:30 defValues:y completeGraph:y incompleteGraph:y in:"C:\Users\Hicham\Desktop\PFA 3 bis\data\bicycle-rental-dijon.xml" Ldout:log out:out conf:conf.properties serv:"http://localhost:8080/otp-rest-servlet/plan"  oConf:y
        SettingsDelegateFactory settingsDelegateFactory = new SettingsDelegateFactory();
        for(String s : args){
            System.out.println(s);
        }
        
        SettingsDelegate settingsDelegate = settingsDelegateFactory.findSettingsdDelegate(args);
        LoggingConfiguratorFactory loggingConfiguratorFactory = new LoggingConfiguratorFactory(Logger.getLogger("CarSharing"), settingsDelegate.getLogLevel(), settingsDelegate.getLogDirectory());
        loggingConfiguratorFactory.applySimpleLoggingConfig();
        loggingConfiguratorFactory.applyXMLLoggingConfig();
        
        XMLDataCollection dataCollected = XMLNodeUtilities.unmarshal(settingsDelegate.getInputFile());
        List<Node> nodeCollection = new LinkedList<>();
        XMLNodeUtilities.XMLtoElements(dataCollected, nodeCollection);
        
        NodeUtilities utility = new NodeUtilities();
        //Test node's info and its metadata
        Collection<Node> incompleteNodes = utility.getIncompleteNodes(nodeCollection, true);
        //Test only node's info
        Collection<Node> incompleteNodes1 = utility.getIncompleteNodes(nodeCollection, false);
        //Set default values for incomplete node's info and metadata
        utility.setDefaultValues(incompleteNodes, settingsDelegate,true);
        
        //Creating the nodes matrix (and calculating routes)
        MatrixFactory matrixFactory = new MatrixFactory();
        NodeMatrix nodeMatrix = matrixFactory.createNodeMatrix(settingsDelegate.getOtpServerURL(), nodeCollection);
        System.out.println(nodeMatrix.toString());
        
        //Getting GraphFactory
        GraphFactory graphFactory = new GraphFactory();
        
        //Marshaling graph output
        JAXBContext jc = JAXBContext.newInstance(GraphBuilder.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        if(settingsDelegate.isGenerateCompleteGraph()){
            Simulation sim = graphFactory.createCompleteGraph(nodeMatrix);
            marshaller.marshal(sim,new File(settingsDelegate.getOutputDirectory().getAbsolutePath()+File.separator+"completeGraph.xml"));
        }
        if(settingsDelegate.isGenerateIncompleteGraph()){
            Simulation sim = graphFactory.createIncompleteGraph(nodeMatrix);
            marshaller.marshal(sim,new File(settingsDelegate.getOutputDirectory().getAbsolutePath()+File.separator+"incompleteGraph.xml"));
        }
        
        //TODO business code
        System.out.println("END;");
    }
    
}
