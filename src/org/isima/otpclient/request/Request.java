/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.otpclient.request;

import java.util.HashMap;
import java.util.Map;
import org.isima.carsharing.elements.Node;

/**
 *
 * @author Hicham
 */
public class Request {

    //Parameters
    private static final String FROM = "fromPlace"; //lat,lon
    private static final String TO = "toPlace"; //lat,lon
    public static final String DATE = "date"; //MM-dd-YYYY
    public static final String MODE = "mode";
    public static final String INTERMEDIATE_STOPS = "showIntermediateStops";
    public static final String LOCALE = "locale";

    private transient Map<String, String> parameters;
    private transient Node fromNode;
    private transient Node toNode;

    public Request() {
        super();
        this.parameters = new HashMap<>();
    }

    public Request(Node fromNode, Node toNode) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.parameters = new HashMap<>();
    }

    public Request(Node fromNode, Node toNode, Map<String, String> parameters) {
        this.parameters = parameters;
        this.fromNode = fromNode;
        this.toNode = toNode;
    }

    public void setFromNode(Node fromNode) {
        this.fromNode = fromNode;
    }

    public void setToNode(Node toNode) {
        this.toNode = toNode;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public void setParameter(String parameter, String value) {
        this.parameters.put(parameter, value);
    }

    public String getAllParameters() {
        String result = FROM + "=" + fromNode.getPosition() + "&" + TO + "=" + toNode.getPosition() + "&";
        String value;
        for (String param : parameters.keySet()) {
            value = parameters.get(param);
            result += param + "=" + value + "&";
        }
        return result;
    }

    public String getXMLLocations() {
        String xmlResult = "<route><locations>";

        xmlResult += "<location><latLng>";
        xmlResult = xmlResult + "<lat>" + this.fromNode.getLatitude() + "</lat>";
        xmlResult = xmlResult + "<lng>" + this.fromNode.getLongitude() + "</lng>";
        xmlResult += "</latLng></location>";

        xmlResult += "<location><latLng>";
        xmlResult = xmlResult + "<lat>" + this.toNode.getLatitude() + "</lat>";
        xmlResult = xmlResult + "<lng>" + this.toNode.getLongitude() + "</lng>";
        xmlResult += "</latLng></location>";

        xmlResult += "</locations></route>";
        return xmlResult;
    }
}
