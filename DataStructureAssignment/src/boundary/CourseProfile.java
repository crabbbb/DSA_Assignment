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
 * @author idoll
 */
public class CourseProfile{
    //private CourseControl ccontrol;
    //private CourseProgrammeControl cpControl;
    
    /*public CourseProfile(){
        this.ccontrol = new CourseControl();
        this.cpControl = new CourseProgrammeControl();
    }*/
    
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
        System.out.println("*                           (6) add programme to ...                         *");
        System.out.println("*                           (7) remove programme from ....                   *");
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
     * @return i
     */ 
    public String getRemoveCourse() {
        
        System.out.println("\n\n\n");
        
        System.out.print("Pls enter the Course that you want to remove ( 0 = exit ) > ");
        return getCourseID();
    }

    // change --------------------------------------------------------------------------
    public void getSearchCourse(){
        
       
        System.out.print("Pls enter the Course that you want to search ( 0 = exit ) > \n");
        
        //return getCourseID();
        
            
        
    }
    
    // change --------------------------------------------------------------------------
    
    public String courseIDOfModifyCourse(){
        System.out.println("\n\n\n");
        System.out.println("Please enter which course ID you are perform to modify ( 0 = exit ) > ");
        modifyCourse();
        return getCourseID();

    }
    public int modifyCourse(){
        System.out.println("Which part you will like to change: ");
        System.out.println("1. Course Name");
        System.out.println("2. Course Description");
        System.out.println("3. Credit Hours of the course");
        System.out.print("Enter Your Choice ( 1 - 3 ) ( 0 = exit ) > \n");
        int choice = new Scanner(System.in).nextInt();
        return choice;
        
        //return new Scanner(System.in).nextInt();
    }
        
    /**
     * enter details check and valid course details return result design for
     * exit or continue
     *@return what user have enter
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
    public void reportOfCourse() {
        //print out the data

    }
    
    public void addProgramme() {
        
        System.out.println("\n\n\n");
        System.out.print("Pls enter which course ID you are perform to add a new programme ( 0 = exit ) > \n");
        
        //return getCourseID();

        
    }

    //check-------------------------------------------------------
    public String courseIDOfRemoveProgramme(){
        System.out.println("\n\n\n");
        System.out.println("Please enter which course ID you are perform to remove an existing programme ( 0 = exit ) > \n");
        
        removeProgramme();
        return getCourseID();

    }
    public String removeProgramme() {
        System.out.println("\n\n\n");
        System.out.println("Please enter which programme ID you are perform to remove in the course ( 0 = exit ) > \n");
        return getProgrammeID();
  
        
        
    }
    
    // Message 
    public void displayInvalidMenuChoice() {
        System.out.println("Invalid choice please enter correct value. ");
    }
    
    
}
