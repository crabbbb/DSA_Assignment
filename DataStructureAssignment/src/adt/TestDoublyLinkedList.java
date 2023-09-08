/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import entity.Programme;

/**
 * this is only for test run will be delete de ~
 *
 * @author LOH XIN JIE
 */
public class TestDoublyLinkedList {

    public static void main(String[] args) {
        DoublyLinkedList<Programme> p = new DoublyLinkedList<>();
        Programme p1 = new Programme("RSW", "Bachelor of Software", 100, "Under IT");
        Programme p2 = new Programme("RST", "Bachelor of Software", 100, "Under IT");

        p.add(p1);
        p.add(p2);

        System.out.println("add new : " + p.contains(p1));
        System.out.println("add new : " + p.contains(p2));
        System.out.println("is empty : " + p.isEmpty());
        System.out.println("size : " + p.size());

        System.out.println("get data p2 > " + p.get(2));
        System.out.println("check get out of range result > " + p.get(0));

        System.out.println("\n\ntest add position start ");
        Programme p3 = new Programme("ttt", "Bachelor of Software", 100, "Under IT");
        Programme p4 = new Programme("qqq", "Bachelor of Software", 100, "Under IT");
        Programme p5 = new Programme("xxx", "Bachelor of Software", 100, "Under IT");
        System.out.println("add from front > " + p.add(1, p3) + "\nresult > " + p.get(1));
        System.out.println("");
        System.out.println("add from middle > " + p.add(2, p4) + "\nresult > " + p.get(2) + "\nindex 3 result > " + p.get(3));
        System.out.println("");
        System.out.println("add from middle > " + p.add(p.size() + 1, p5) + "\nresult > " + p.get(p.size()));

        System.out.println("\n\nsize > " + p.size());

        System.out.println("remove by index (false) > " + p.remove(0));
        System.out.println("remove by index (true) > " + p.remove(1));

        System.out.println("\n\nremove by entry > " + p.remove(p5));

        System.out.println(p);
        System.out.println("\n\nstart replace p4 index 0 -> p5 > " + p.replace(1, p5));
        System.out.println(p);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        p.sort();
        System.out.println(p);

        p.clear();
        System.out.println("\n\nafter clear > is empty : " + p.isEmpty());

    }
}
