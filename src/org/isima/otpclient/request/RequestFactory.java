/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.isima.otpclient.request;

import java.util.Map;

/**
 *
 * @author Hicham
 */
public class RequestFactory {
    public Request createRequest(){
        return new Request();
    }
    
    public Request createRequest(Map<String,String> parameters){
        return new Request(parameters);
    }
}
