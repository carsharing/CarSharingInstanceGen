/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.launcher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import org.isima.otpclient.data.MatrixUtility;
import org.isima.otpclient.data.NodeMatrix;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Hicham
 */
public class Launcher {
    private static final Logger logger = Logger.getLogger("CarSharingInstanceGen");

    public static void main(String[] args) throws Exception {
        //dC:7 dD:1 dP:7 dT:n/a dNm:test dNt:nt dV:n/a Llvl:a serv:localhost oConf:y defValues:y completeGraph:y incompleteGraph:y in:"C:\Users\Hicham\Desktop\PFA 3 bis\data\bicycle-rental-dijon.xml" Ldout:log out:out conf:"C:\Users\Hicham\Documents\NetBeansProjects\CarSharing Instance Generator\test\conf.properties" serv:"http://localhost:8080/otp-rest-servlet/plan" 
        SettingsDelegateFactory settingsDelegateFactory = new SettingsDelegateFactory();
        System.out.println("Called config");
        for (String s : args) {
            System.out.println(s);
        }
        
        //init the logger
        logger.setLevel(Level.ALL);
        
        logger.log(Level.INFO,"[{0}"+"]"+" Reading config ...", Launcher.class.getName());
        SettingsDelegate settingsDelegate = settingsDelegateFactory.findSettingsdDelegate(args);
        //Update the logger
        LoggingConfiguratorFactory loggingConfiguratorFactory = new LoggingConfiguratorFactory(logger, settingsDelegate.getLogLevel(), settingsDelegate.getLogDirectory());
        loggingConfiguratorFactory.applySimpleLoggingConfig();
        loggingConfiguratorFactory.applyXMLLoggingConfig();
        
        logger.log(Level.INFO,"[{0}"+"]"+" Unmarshalling input file", Launcher.class.getName());
        logger.log(Level.WARNING,"[{0}"+"]"+" Input file: {1}", new Object[]{Launcher.class.getName(), settingsDelegate.getInputFile().getAbsolutePath()});
        XMLDataCollection dataCollected = XMLNodeUtilities.unmarshal(settingsDelegate.getInputFile());
        List<Node> nodeCollection = new LinkedList<>();
        logger.log(Level.INFO,"[{0}"+"]"+" Converting collected data to Nodes", Launcher.class.getName());
        XMLNodeUtilities.XMLtoElements(dataCollected, nodeCollection);
        logger.log(Level.WARNING,"[{0}"+"]"+" Found {1} Nodes", new Object[]{Launcher.class.getName(), nodeCollection.size()});

        NodeUtilities utility = new NodeUtilities();
        //Test node's info and its metadata
        Collection<Node> incompleteNodes = utility.getIncompleteNodes(nodeCollection, true);
        logger.log(Level.WARNING,"[{0}"+"]"+" Found {1} incomplete Nodes (MetaData tested)", new Object[]{Launcher.class.getName(), incompleteNodes.size()});
        //Test only node's info
        Collection<Node> incompleteNodes1 = utility.getIncompleteNodes(nodeCollection, false);
        logger.log(Level.WARNING,"[{0}"+"]"+" Found {1} incomplete Nodes (MetaData untested)", new Object[]{Launcher.class.getName(), incompleteNodes1.size()});
        //Set default values for incomplete node's info and metadata
        if(settingsDelegate.isActivateDefaultValues()){
            logger.log(Level.WARNING,"[{0}"+"]"+" Setting default values in incomplete Nodes (MetaData included)", Launcher.class.getName());
            utility.setDefaultValues(incompleteNodes, settingsDelegate, true);
        }

        //Creating the nodes matrix (and calculating routes)
        MatrixFactory matrixFactory = new MatrixFactory();
        logger.log(Level.INFO,"[{0}"+"]"+" Creating Nodes' matrix", Launcher.class.getName());
        NodeMatrix nodeMatrix = matrixFactory.createNodeMatrix(settingsDelegate.getOtpServerURL(), nodeCollection);
        logger.log(Level.INFO,"[{0}"+"]"+" Correcting one way paths in Nodes' matrix", Launcher.class.getName());
        MatrixUtility.correctOneWayPaths(nodeMatrix);
        
        System.out.println(nodeMatrix.toString());

        //Getting GraphFactory
        GraphFactory graphFactory = new GraphFactory();

        //Marshaling graph output
        logger.log(Level.FINE,"[{0}"+"]"+" Preparing graph output", Launcher.class.getName());
        JAXBContext jc = JAXBContext.newInstance(GraphBuilder.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        if (settingsDelegate.isGenerateCompleteGraph()) {
            File out = new File(settingsDelegate.getOutputDirectory().getAbsolutePath() + File.separator + "completeGraph.xml");
            logger.log(Level.WARNING,"[{0}"+"]"+" Creating complete graph to output {1}", new Object[]{Launcher.class.getName(), out.getAbsolutePath()});
            Simulation sim = graphFactory.createCompleteGraph(nodeMatrix);
            marshaller.marshal(sim, out);
            logger.log(Level.INFO,"[{0}"+"]"+" Finalizing complete graph output", Launcher.class.getName());
            addConfigComment(settingsDelegate,out);
        }
        if (settingsDelegate.isGenerateIncompleteGraph()) {
            File out =  new File(settingsDelegate.getOutputDirectory().getAbsolutePath() + File.separator + "incompleteGraph.xml");
            logger.log(Level.WARNING,"[{0}"+"]"+" Creating incomplete graph to output {1}", new Object[]{Launcher.class.getName(), out.getAbsolutePath()});
            Simulation sim = graphFactory.createIncompleteGraph(nodeMatrix, settingsDelegate.getDistanceMargin());
            marshaller.marshal(sim,out);
            logger.log(Level.INFO,"[{0}"+"]"+" Finalizing incomplete graph output", Launcher.class.getName());
            addConfigComment(settingsDelegate,out);
        }

        logger.log(Level.INFO,"[{0}"+"]"+" Finalizing complete graph output {1}", Launcher.class.getName());
    }

    public static void addConfigComment(SettingsDelegate settingsDelegate,File out) {
        try {
            SAXBuilder builder = new SAXBuilder();
            
            Document doc = (Document) builder.build(out);
            Element rootNode = doc.getRootElement();
            Element rootNodeCopy = rootNode.clone();
            
            doc.removeContent(rootNode);
            rootNodeCopy.detach();
            
            Comment comment = new Comment(settingsDelegate.usedConfigsToXMLComment());
            doc.addContent(comment);
            doc.addContent(rootNodeCopy);
            
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc,new FileWriter(out));
    } catch (JDOMException | IOException ex) {
            Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
