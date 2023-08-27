/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.*;
import test.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class TestDbSet {

    public static void main(String[] args) {
        System.out.println("hi");
        try {
            System.out.println("hi");
            DbSet<Programme> programme = new DbSet<>(new Programme());

            //String programmeID, String programmeName, int programmeCapacity, String programmeDescription, ArrayList<TutGrp> ttlGrp
            ArrayList<TutorialGroup> list = new ArrayList<TutorialGroup>();
            programme.Add(new Programme("RSW", "Bachelor of Software", 100, "Under IT"));
            programme.Add(new Programme("RST", "Bachelor of Software", 100, "Under IT"));

            ArrayList<Programme> p = programme.getAll();

            System.out.println("getData : " + p);
        } catch (IOException ex) {
            Logger.getLogger(TestDbSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestDbSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
