/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.elements.utilities;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import org.isima.carsharing.elements.Node;
import org.isima.carsharing.launcher.SettingsdDelegate;

/**
 *
 * @author Hicham
 */
public class NodeUtilities {

    private static final Integer defaultCapacity = 2;
    private static final Integer defaultDriverAvailable = 0;
    private static final Integer defaultParkedCar = 2;
    private static final String defaultTimeStamp = new Long(new Date().getTime()).toString();
    private static final String defaultName = "unknown";
    private static final String defaultNetwork = "unknown";
    private static final String defaultVersion = "0.0(n/a)";

    /**
     *
     * @param nodes
     * @param metaDataTest true to test complete metadata info, false if else
     * @return
     */
    public Collection<Node> getIncompleteNodes(Collection<Node> nodes, boolean metaDataTest) {
        HashSet<Node> incompleteNodes = new HashSet<>();
        if (metaDataTest) {
            for (Node node : nodes) {
                if ((node.getMetadata().isInfoComplete() && node.isInfoComplete()) == false) {
                    incompleteNodes.add(node);
                }
            }
        } else {
            for (Node node : nodes) {
                if (node.isInfoComplete() == false) {
                    incompleteNodes.add(node);
                }
            }
        }
        return (incompleteNodes.isEmpty()) ? null : incompleteNodes;
    }

    public Node setDefaultValues(Node node, SettingsdDelegate settingsdDelegate,boolean metaDataTest) {
        if (node.getCapacity() == null) {
            node.setCapacity(settingsdDelegate.getDefaultCapacity());
        }
        if (node.getDriverAvailable() == null) {
            node.setDriverAvailable(settingsdDelegate.getDefaultDriverAvailable());
        }
        if (node.getParkedCar() == null) {
            node.setParkedCar(settingsdDelegate.getDefaultParkedCar());
        }
        if (metaDataTest) {
            if(node.getMetadata().getName()==null){
                node.getMetadata().setName(settingsdDelegate.getDefaultName());
            }
            if(node.getMetadata().getNetwork()==null){
                node.getMetadata().setNetwork(settingsdDelegate.getDefaultNetwork());
            }
            if(node.getMetadata().getTimeStamp()==null){
                node.getMetadata().setTimeStamp(settingsdDelegate.getDefaultTimeStamp());
            }
            if(node.getMetadata().getVersion()==null){
                node.getMetadata().setVersion(settingsdDelegate.getDefaultVersion());
            }
        }
        return node;
    }
    
    public Collection<Node> setDefaultValues(Collection<Node> nodes, SettingsdDelegate settingsdDelegate,boolean metaDataTest) {
        for(Node node : nodes){
            setDefaultValues(node, settingsdDelegate,metaDataTest);
        }
        return nodes;
    }
}
