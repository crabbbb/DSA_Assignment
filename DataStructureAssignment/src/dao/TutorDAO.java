

package dao;

import adt.*;
import entity.Tutor;
import java.io.*;

/**
 *
 * @author KELLY TAN
 */


public class TutorDAO {
    
   private String fileName = "tutor.dat"; // For security and maintainability, should not have filename hardcoded here.
  
    
    public void saveToFile(ListInterface<Tutor> tutorList) {
        File file = new File(fileName);
    
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(tutorList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found!");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file!");
        }

    }

  public ListInterface<Tutor> retrieveFromFile() {
      
    File file = new File(fileName);
    ListInterface<Tutor> tutorList = new ArrayList<>();
    
      try {
          ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
          tutorList = (ArrayList<Tutor>) (oiStream.readObject());
          oiStream.close();
      } catch (FileNotFoundException ex) {
          System.out.println("\nNo such file.");
      } catch (IOException ex) {
          System.out.println("\nCannot read from file.");
      } catch (ClassNotFoundException ex) {
          System.out.println("\nClass not found.");
      } finally {
          return tutorList;
      }
    
  }
 
}
