/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.elements.XML;

import java.io.File;
import java.util.Collection;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.isima.carsharing.elements.Node;

/**
 *
 * @author Hicham
 */
public class XMLNodeUtilities {

    public static void marshal(XMLDataCollection nodes, File destination) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(XMLDataCollection.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(nodes, destination);
    }

    public static XMLDataCollection unmarshal(File source) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(XMLDataCollection.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        XMLDataCollection unmarshal = (XMLDataCollection) unmarshaller.unmarshal(source);
        return unmarshal;
    }
    
    public static void XMLtoElements(XMLDataCollection nodesSource,Collection<Node> nodesDestination){
        Collection<XMLNode> XMLNodes = nodesSource.getNodes();
        Node node;
        for(XMLNode XMLnode:XMLNodes){
            node = new Node();
            node.getMetadata().setId(XMLnode.getId());
            node.getMetadata().setTimeStamp(XMLnode.getTimeStamp());
            node.getMetadata().setVersion(XMLnode.getVersion());
            node.setLatitude(Double.parseDouble(XMLnode.getLat()));
            node.setLongitude(Double.parseDouble(XMLnode.getLon()));
            for(XMLNodeTags tag:XMLnode.getTags()){
                if(tag.getKey().equals("capacity")){
                    node.setCapacity(Integer.parseInt(tag.getValue()));
                }else if(tag.getKey().equals("name")){
                    node.getMetadata().setName(tag.getValue());                    
                }else if(tag.getKey().equals("network")){
                    node.getMetadata().setNetwork(tag.getValue());                      
                }                  
            }
            nodesDestination.add(node);
        }
    }
}
