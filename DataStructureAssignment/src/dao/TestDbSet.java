/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import entity.*;
import test.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * this is only for testing will be delete de ~
 *
 * @author LOH XIN JIE
 */
public class TestDbSet {

    public static void main(String[] args) {
        System.out.println("hi");
        try {
            System.out.println("hi");
            DbSet<Programme> programme = new DbSet<>(new Programme());

            //String programmeID, String programmeName, int programmeCapacity, String programmeDescription, ArrayList<TutGrp> ttlGrp
            DoublyLinkedList<TutorialGroup> list = new DoublyLinkedList<TutorialGroup>();
            programme.Add(new Programme("RSW", "Bachelor of Software", 100, "Under IT"));
            programme.Add(new Programme("RST", "Bachelor of Software", 100, "Under IT"));

            DoublyLinkedList<Programme> p = programme.getAll();

            System.out.println("getData : " + p);
        } catch (IOException ex) {
            Logger.getLogger(TestDbSet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestDbSet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
