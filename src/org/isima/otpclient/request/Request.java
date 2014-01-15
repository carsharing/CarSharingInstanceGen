/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.otpclient.request;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Hicham
 */
public class Request {

    //Parameters
    public static final String FROM = "fromPlace"; //lat,lon
    public static final String TO = "toPlace"; //lat,lon
    public static final String DATE = "date"; //MM-dd-YYYY
    public static final String MODE = "mode";
    public static final String INTERMEDIATE_STOPS = "showIntermediateStops";
    public static final String LOCALE = "locale";

    private transient Map<String, String> parameters;

    public Request() {
        super();
        this.parameters = new HashMap<>();
    }

    public Request(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void setParameter(String parameter, String value) {
        this.parameters.put(parameter, value);
    }

    public String getAllParameters() {
        String result = "";
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
        xmlResult = xmlResult + "<lat>" + this.parameters.get("from").substring(0, this.parameters.get("from").indexOf(",")) + "</lat>";
        xmlResult = xmlResult + "<lng>" + this.parameters.get("from").substring(this.parameters.get("from").indexOf(",") + 1, this.parameters.get("from").length()) + "</lng>";
        xmlResult += "</latLng></location>";

        xmlResult += "<location><latLng>";
        xmlResult = xmlResult + "<lat>" + this.parameters.get("to").substring(0, this.parameters.get("to").indexOf(",")) + "</lat>";
        xmlResult = xmlResult + "<lng>" + this.parameters.get("to").substring(this.parameters.get("to").indexOf(",") + 1, this.parameters.get("to").length()) + "</lng>";
        xmlResult += "</latLng></location>";

        xmlResult += "</locations></route>";
        return xmlResult;
    }
}
