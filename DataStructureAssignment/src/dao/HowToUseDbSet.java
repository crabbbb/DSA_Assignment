/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.DoublyLinkedList;
import entity.*;
import java.io.IOException;

/**
 * this is only for testing will be delete de ~ Note : DbSet is save and
 * retrieve by DoublyLinkedList, if need to use this DbSet please use
 * DoublyLinkedList
 *
 * @author LOH XIN JIE
 */
public class HowToUseDbSet {

    public static void main(String[] args) {
        Programme p1 = new Programme("RSW", "Bachelor of Software", 100, "Under IT");
        Programme p2 = new Programme("RST", "Bachelor of Software", 100, "Under IT");
        Programme p3 = new Programme("RIS", "Bachelor of Information System", 100, "Under IT");

        // first call the DBTable
        DBTable db = new DBTable();

        try {
            // if you use Programme just choose db.Programme if TutorialGroup then will be db.TutorialGroup
            // inside dbSet have basic CRUD for you to easy interact with text file
            // 1. add = add a new value into file
            System.out.println("Add p1 > " + db.Programme.Add(p1));
            System.out.println("Add p2 > " + db.Programme.Add(p2));
            System.out.println("Add p1 again should be show false > " + db.Programme.Add(p1));
            // because Have IOException and ClassNotFoundException will be throws so remember need to catch and give reply
            DoublyLinkedList list = db.Programme.getAll();
            System.out.println(list);

            // run this code you will see the Programme file have already been create and the data have been store
            //Programme pp = db.Programme.contains("RSW", list);
            //System.out.println("check contains > " + pp);
            Programme p2Update = new Programme("RST", "Testing testing", 100, "Under IT");
            System.out.println("Test Update p2 > " + db.Programme.Update(p2Update));
            System.out.println("After Update ... \n" + db.Programme.getAll());

            System.out.println("Test Delete p2 by using id only > " + db.Programme.Delete(new Programme("RST")));
            System.out.println("After Delete ... \n" + db.Programme.getAll());

            System.out.println("Function getWithId() find RSW > " + db.Programme.getWithId("RSW"));
            System.out.println("Function getWithId() find no exist value > " + db.Programme.getWithId("RBB"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
