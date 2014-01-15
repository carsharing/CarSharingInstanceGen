/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.elements.utilities;

import java.util.Collection;
import java.util.HashSet;
import org.isima.carsharing.elements.Node;

/**
 *
 * @author Hicham
 */
public class NodeUtilities {

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
}
