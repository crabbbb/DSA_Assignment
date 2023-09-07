package control;

import adt.*;
import entity.Tutor;
import boundary.TutorSystemUI;
import dao.TutorDAO;
import utility.PromptMessage;
import java.util.Scanner;

/**
 *
 * @author KELLY TAN
 */
public class TestTutor {
    
    Scanner scan = new Scanner(System.in);
    
    private TutorDAO tutorDAO = new TutorDAO() ;  
    private TutorSystemUI tutorUI = new TutorSystemUI();
    private ListInterface <Tutor> tutorList = new ArrayList<>() ;
      
    
    public TestTutor() {
      tutorList = tutorDAO.retrieveFromFile();
    }
    
    
    /*
     public int getMenuChoice() {
        System.out.println("-----------------------------");
        System.out.println("\n      MAIN MENU       ");
        System.out.println("-----------------------------");
        System.out.println("1. ADD Tutor");
        System.out.println("2. DISPLAY Tutor");
        System.out.println("3. DELETE Tutor");
        System.out.println("4. UPDATE Tutor");
        System.out.println("5. SEARCH Tutor");
        
        System.out.println("6. REPORT ");
        System.out.println("0. EXIT");
        System.out.println("-----------------------------");

        System.out.print("Enter your choice ==>  ");
        int choice = scan.nextInt();

        scan.nextLine();
        System.out.println();
        return choice;
    }
     */
     
  public void displayMainPage() {
        int choice = 0;
        do {
            choice = tutorUI.getMenuChoice();

            switch (choice) {
                case 0:
                    PromptMessage.displayExitMessage();
                    break;

                case 1: // ADD
                    addNewTutor();
                    break;

                case 2: // DISPLAY
                  tutorUI.listAllTutors(getTutorRecord());
                    break;

                  case 3://DELETE
                     deleteTutor();
                    break;

                case 4://UPDATE
                updateTutor() ;
                    break;
              
                 case 5://SEARCH
                    searchTutor();
                    break;

               
                default:
                    PromptMessage.displayErrorMessage();
                }
          } while (choice !=0);
      }

 
  
    public void addNewTutor() {
        System.out.print("Enter ID  :  ");
        String ID = scan.nextLine();
        
        System.out.print("\nEnter Name  :  ");
        String name = scan.nextLine();
                
        System.out.print("\nEnter Email :  ");
        String email = scan.nextLine();
                
        System.out.print("\nEnter Course    :  ");
        String course = scan.nextLine();
                
        System.out.print("\nEnter Schedule  :  ");
        String schedule = scan.nextLine();
        
        Tutor newTutor = new Tutor(ID,name, email, course,schedule);
        tutorList.add(newTutor);
        
        tutorDAO.saveToFile(tutorList);
        System.out.println("New Record Added ! \n") ;
     
    } 
    
    
    

  
    
    
    public String getTutorRecord() {
        String outputStr = "";
        for (int i = 1; i <= tutorList.getNumberOfEntries(); i++) {
           outputStr += tutorList.getEntry(i) + "\n";
        }
        return outputStr;
    }

    
 
    
    public void deleteTutor() {
      System.out.print("Remove Tutor from position 6 : ");
     
        tutorList.remove(6); // Remove Tutor W06
        tutorDAO.saveToFile(tutorList);
        System.out.println("\nRecord Deleted ! \n");
    }
    
    
    
    
    public void updateTutor() {
   System.out.println(tutorList.replace(4, new Tutor("W04","Karen Walker",
           "karen@gmail.com", "BACS1056 Accounting", "Wednesay")) );
   
        tutorDAO.saveToFile(tutorList);
        System.out.println("\nRecord Updated ! \n");
        
    }
    
    
    
    public void searchTutor() {
        
      System.out.println("Search for Tutor in position 1");
      System.out.println(tutorList.getEntry(1) );   
        
      System.out.println("\nSearch for Tutor in position 5");
      System.out.println(tutorList.getEntry(5)); //Tutor W05
   
    }
        

   
    
    
    public static void main(String[] args) {
        TestTutor test = new TestTutor();
        test.displayMainPage();
      }

    

    
}
