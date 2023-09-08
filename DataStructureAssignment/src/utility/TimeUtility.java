/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author LAPTOP
 */
public class TimeUtility {

    public static String time() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy,HH:mm:ss,");
        return formatter.format(date);
    }
}
