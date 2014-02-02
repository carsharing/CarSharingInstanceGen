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
    public static void main(String[] args){
        SettingsDelegateFactory settingsDelegateFactory = new SettingsDelegateFactory();
        SettingsdDelegate findSettingsdDelegate = settingsDelegateFactory.findSettingsdDelegate(args);
        //TODO business code
    }
    
}
