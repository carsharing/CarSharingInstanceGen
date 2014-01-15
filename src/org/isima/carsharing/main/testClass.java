/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.isima.carsharing.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.GregorianCalendar;
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

    public static void main(String[] args) throws MalformedURLException, IOException {
        RequestFactory rqFactory = new RequestFactory();
        Request request = rqFactory.createRequest();

        request.setParameter(Request.FROM, "47.32902191707277,5.071263836559878");
        request.setParameter(Request.TO, "47.315350839509186,5.039632153489996");
        request.setParameter(Request.DATE, DataConverterUtility.getDateFromGregorianCalendar(new GregorianCalendar()));
        request.setParameter(Request.MODE, "CAR");
        request.setParameter(Request.LOCALE, "fr_FR");
        request.setParameter(Request.INTERMEDIATE_STOPS, "false");

        Planner pl = new Planner("http://localhost:8080/otp-rest-servlet/plan", request);
        Response response = pl.sendRequest();
        System.out.println(response.toString());
    }
}
