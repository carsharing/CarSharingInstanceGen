/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.isima.carsharing.launcher;

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
        SettingsdDelegate settingsdDelegate = settingsDelegateFactory.findSettingsdDelegate(args);
        System.out.println("END;");
        //TODO business code
    }
    
}
