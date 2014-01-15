/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.isima.otpclient.data.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hicham
 */
public class DataConverterUtility {
    public static Calendar getDateFromString(String date){
        Calendar gc = new GregorianCalendar();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-YYYY");
        try {
            Date parsedDate = format.parse(date);
            gc.setTime(parsedDate);
        } catch (ParseException ex) {
            Logger.getLogger(DataConverterUtility.class.getName()).log(Level.SEVERE, "String containing incorrect date, now() date taken", ex);
            gc.setTime(new Date());
        }finally{
            return gc;
        }
    }
    
    public static String getDateFromGregorianCalendar(Calendar date){
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-YYYY");
        return format.format(date.getTime());
        /* Problem with months :
                return date.get(Calendar.MONTH)+1+"-"+date.get(Calendar.DAY_OF_MONTH)+"-"+date.get(Calendar.YEAR);
        */
    }
}
