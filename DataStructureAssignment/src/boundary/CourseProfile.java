/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.*;
//import control.*;
import entity.*;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author LOH XIN YI
 */
public class CourseProfile{
    
    
    public String getCourseID(){
        System.out.print("Enter Course ID(e.g BACS2063)                       :");
        return new Scanner(System.in).nextLine();
    }
    
    public String getCourseName(){
        System.out.print("Enter Course Name(e.g Data Structure and Algorithm) : ");
        return new Scanner(System.in).nextLine();
    }
    
    public String getCourseDescription(){
        System.out.print("Enter Course Description                            : ");
        return new Scanner(System.in).nextLine();
    }
    
    // 2.0 3.0 2.4
    public String getCourseCreditHours(){
        System.out.print("Enter Credit Hours(e.g 2/3/4)                       : ");
        return new Scanner(System.in).nextLine();
        
    }
    
    /**
     * validate programmeID
     * @return ID
    */
    public String getProgrammeID(){
        System.out.print("Enter Programme ID(e.g RIS)                          : ");
        return new Scanner(System.in).nextLine();
    }
    
    // display
    public void listAllCourse(DoublyLinkedList<Course> courseList) {
        //design
        System.out.println("\nList of Course: \n");
        //print out the data
        Iterator<Course> iterator = courseList.iterator();

        int i = 1;
        while (iterator.hasNext()) {
            Course c = iterator.next();
            // -------------------- make change ( show all data )---------------------------------
            System.out.println(i + "" + c);
            i++;
        }
    }
    
    public void listAllProgramme(DoublyLinkedList<Programme> prgList){
        System.out.println("\nList of Programme: \n");
        if(prgList != null && !prgList.isEmpty()){
            Iterator<Programme> iterator = prgList.iterator();

            while (iterator.hasNext()) {
                Programme p = iterator.next();

                System.out.println(p.getProgrammeID() + "\n");
            }
        }
        
    }
    
    // Menu 
    public int getMenuChoice() {
        // change ---------------------------------------------------------------------------
        System.out.println("\n\n\n");
        System.out.println("******************************************************************************");
        System.out.println("*                                                                            *");
        System.out.println("*                             MAIN MENU                                      *");
        System.out.println("*                           (1) Add course                                   *");
        System.out.println("*                           (2) remove course                                *");
        System.out.println("*                           (3) Find                                         *");
        System.out.println("*                           (4) Amend                                        *");
        System.out.println("*                           (5) list all                                     *");
        System.out.println("*                           (6) add programme to course                      *");
        System.out.println("*                           (7) remove programme from course                 *");
        System.out.println("*                           (8) report                                       *");
        System.out.println("*                           (9) Exit                                         *");
        System.out.println("*                                                                            *");
        System.out.println("*                                                                            *");
        System.out.println("******************************************************************************");

        System.out.print("Enter Your Choice ( 1 - 9 ) > ");
        //int choice = new Scanner(System.in).nextInt();
        //return choice;
        return new Scanner(System.in).nextInt();
    }
    
    /**
     * enter id
     * check id and remove
     * return result
     * design for exit or continue
     * 
     */ 
    public void getRemoveCourse() {
        
        System.out.println("\n\n\n");
        
        System.out.print("Pls enter the Course that you want to remove > \n");
    }

    // change --------------------------------------------------------------------------
    public void getSearchCourse(){
        
       
        System.out.print("Pls enter the Course that you want to search > \n");
        
      
    }
    
    


    public void modifyCourse(){
        System.out.print("Enter Course ID to modify : ");
        
    }
    public int modifyChoice(){
        // Ask the user what to modify
        System.out.println("Choose what to modify:");
        System.out.println("1. Course Name");
        System.out.println("2. Course Description");
        System.out.println("3. Credit Hours");
        System.out.println("0. Exit");
        System.out.print("Enter Your Choice ( 1 - 3 ) ( 0 = exit ) > \n");

        int selection = new Scanner(System.in).nextInt();
        return selection;
    }
        
    /**
     * enter details check and valid course details return result design for
     * exit or continue
     *
     */
    public void addCourse() {

        System.out.println("\n\n\n");
        
        
        System.out.println("Pls enter the Course's Details > \n");
        
 
        //exit the page
    }
    
        /**
     * no. of course is inside list the course 
     * no. of pg in course
     * no. of course inside a pg
     * no. of list in the data field
     *
     *
     */
    public void reportOfCourse(DoublyLinkedList<Course> courseList) {
        //print out the data
        int totalCourses = 0;
        int totalCreditHours = 0;

        // Iterate through the list of courses
        Iterator<Course> iterator = courseList.iterator();
        while (iterator.hasNext()) {
            Course course = iterator.next();
            totalCourses++;
            totalCreditHours += course.getCreditHours();
        }

        // Display the report
        System.out.println("Course Report");
        System.out.println("Total Courses: " + totalCourses);
        System.out.println("Total Credit Hours: " + totalCreditHours);
        

    }
    
    public void addProgramme() {
        
        System.out.println("\n\n\n");
        System.out.print("Pls enter which course ID you are perform to add a new programme > \n");
        

        
    }

    //check-------------------------------------------------------
    
    // Message 
    public void displayInvalidMenuChoice() {
        System.out.println("Invalid choice please enter correct value. ");
    }
    
    
}
