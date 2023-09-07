
package boundary;

import java.util.Scanner;
import adt.*;
import entity.Tutor;

/**
 * Reference from ECBDemo
 * 
 * @author KELLY TAN 
 */


public class TutorSystemUI {
    
    Scanner scan = new Scanner(System.in);
        private ListInterface <Tutor> tutorList = new ArrayList<>() ;

        
        
    public int getMenuChoice() {
        System.out.println("-----------------------------");
        System.out.println("\n      MAIN MENU       ");
        System.out.println("-----------------------------");
        System.out.println("1. ADD Tutor");
        System.out.println("2. DISPLAY Tutor");
        System.out.println("3. DELETE Tutor");
        System.out.println("4. UPDATE Tutor");
        System.out.println("5. SEARCH Tutor");
        
        System.out.println("0. EXIT");
        System.out.println("-----------------------------");

        System.out.print("Enter your choice ==>  ");
        int choice = scan.nextInt();

        scan.nextLine();
        System.out.println();
        return choice;
    }
        
    
     public void listAllTutors(String outputStr) {
        System.out.println("\nList of Tutors:\n" + outputStr);
    }

    public void printTutorDetails(Tutor tutors) {
        System.out.println("             TUTOR RECORD                    ");
        System.out.println("----------------------------------------------");
        System.out.println("ID : " + tutors.getID());
        System.out.println("ID : " + tutors.getName());
        System.out.println("ID : " + tutors.getEmail());
        System.out.println("ID : " + tutors.getCourse());
        System.out.println("ID : " + tutors.getSchedule() );
        System.out.println("----------------------------------------------");
    }
    
    
    public String enterTutorID() {
        System.out.print("Enter Tutor ID :  ");
        String code = scan.nextLine();
        return code;
    }
   
    public String enterTutorName() {
        System.out.print("Enter Name :  ");
        String code = scan.nextLine();
        return code;
    }
    
    
     public String enterTutorEmail() {
        System.out.print("Enter Email :  ");
        String code = scan.nextLine();
        return code;
    }
   
    public String enterTutorCourse() {
        System.out.print("Enter course name :  ");
        String code = scan.nextLine();
        return code;
    }
    
     public String enterTutorSchedule() {
        System.out.print("Enter Schedule :  ");
        String code = scan.nextLine();
        return code;
    }
    
     
    public Tutor addTutorDetails() {
        String ID = enterTutorID();
        String name = enterTutorName();
        String email = enterTutorEmail();
        String course = enterTutorCourse();
        String schedule = enterTutorSchedule();
        System.out.println();
        return new Tutor(ID, name, email, course, schedule);
    }

 
   
    }
   

   
       
      
    
    
    
    
    
