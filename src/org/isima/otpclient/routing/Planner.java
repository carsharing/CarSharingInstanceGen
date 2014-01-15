/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.otpclient.routing;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.isima.otpclient.data.Response;
import org.isima.otpclient.request.Request;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Hicham
 */
public class Planner {

    private static final boolean debug = false;
    private transient String serviceURL;
    private transient Request request;

    public Planner() {
    }

    public Planner(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public Planner(String serviceURL, Request request) {
        this.serviceURL = serviceURL;
        this.request = request;
    }

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

//    public void setServiceURL(String protocol, String host, String service) throws MalformedURLException {
//        this.serviceURL = new URL(protocol,host,service);
//    }
    public Response sendRequest() throws IOException {
        URL url = new URL(this.serviceURL + "?" + this.request.getAllParameters());
        if (debug) {
            System.out.println(this.serviceURL + "?" + this.request.getAllParameters());
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Host", url.getHost());
        connection.setRequestProperty("Accept", "application/xml");
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferRecepetion = new BufferedReader(new InputStreamReader(inputStream));

        File streamOutput = new File("tmp.xml");
        FileWriter fw = new FileWriter(streamOutput, false);
        String line;
        while ((line = bufferRecepetion.readLine()) != null) {
            fw.write(line);
        }
        fw.close();
        connection.disconnect();
        Response result = new Response(request.getFromNode(), request.getToNode());
        parseXML(streamOutput, result);
        return result;
    }

    private void parseXML(File f, Response response) {
        SAXBuilder sxb = new SAXBuilder();
        Document document;
        try {
            document = sxb.build(f);
            Element root = document.getRootElement();
            Element plan = root.getChild("plan");
            if (plan != null) {
                Element from = plan.getChild("from");
                Element fromLat = from.getChild("lat");
                Element fromLon = from.getChild("lon");
                if (debug) {
                    System.out.println("from: " + fromLat.getText() + "," + fromLon.getText());
                }

                Element to = plan.getChild("to");
                Element toLat = to.getChild("lat");
                Element toLon = to.getChild("lon");
                if (debug) {
                    System.out.println("to: " + toLat.getText() + "," + toLon.getText());
                }

                Element itineraries = plan.getChild("itineraries");
                //System.out.println("itineraries: " + itineraries.getText());
                Element itinerary = itineraries.getChild("itinerary");
                //System.out.println("itinerary: " + itinerary.getText());

                Element duration = itinerary.getChild("duration");
                if (debug) {
                    System.out.println("duration: " + duration.getText());
                }
                response.setDuration(Double.parseDouble(duration.getText()));

                Element distance = itinerary.getChild("legs").getChild("leg").getChild("distance");
                if (debug) {
                    System.out.println("distance: " + distance.getText());
                }
                response.setDistance(Double.parseDouble(distance.getText()));
            } else {
                response.setDistance(-1);
                response.setDuration(-1);
                if (debug) {
                    System.out.println("Response Problem ------------");
                    InputStream input = new BufferedInputStream(new FileInputStream(f));
                    byte[] buffer = new byte[8192];

                    try {
                        for (int length = 0; (length = input.read(buffer)) != -1;) {
                            System.out.write(buffer, 0, length);
                        }
                    } finally {
                        input.close();
                    }
                }
            }

        } catch (JDOMException | IOException ex) {
            System.out.println("Erreur traitement XMl-" + ex.getMessage());
        }
    }

}
