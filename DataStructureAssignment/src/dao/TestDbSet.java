/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.*;
import adt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class TestDbSet {

    public static void main(String[] args) {
        try {
            DbSet<Programme> programme = new DbSet<>(new Programme());

            //String programmeID, String programmeName, int programmeCapacity, String programmeDescription, ArrayList<TutGrp> ttlGrp
            ArrayList<TutGrp> list = new ArrayList<TutGrp>();
            programme.Add(new Programme("RSW", "Bachelor of Software", 100, "Under IT"));
            programme.Add(new Programme("RST", "Bachelor of Software", 100, "Under IT"));

            //programme.getData(); --------- got problem
        } catch (IOException ex) {
            Logger.getLogger(TestDbSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestDbSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
