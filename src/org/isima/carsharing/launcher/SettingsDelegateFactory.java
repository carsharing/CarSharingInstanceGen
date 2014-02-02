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
public class SettingsDelegateFactory {
    public SettingsdDelegate findSettingsdDelegate(String[] args){
        /*
        work with:
            for (String s: args) {
                System.out.println(s);
            }
            and StringTokenizer
        */
        return new SettingsdDelegate();
    }
}
