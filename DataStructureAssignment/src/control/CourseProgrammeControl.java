/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.CourseProfile;
import adt.*;
import dao.DBTable;
import entity.*;
import java.io.IOException;

/**
 *
 * @author LOH XIN YI
 */
public class CourseProgrammeControl {

    private DBTable db;
    //private CourseControl cControl;
    //private CourseProfile courseUI;

    public CourseProgrammeControl() {
        //cControl = new CourseControl();
        //courseUI = new CourseProfile();
        this.db = new DBTable();
    }

    /**
     * under what situation will be repeating loop only can have 3 character
     * length check repeat cannot be empty or null value length RIS = 3
     *
     * @return string in valid and correct format
     */
    public boolean doValidateProgrammeID(String programmeID) {
        //boolean valid = false;
        //String programmeID = null;
        //do {
            //programmeID = courseUI.getProgrammeID();


            // do validation
            if (programmeID != null && !programmeID.isEmpty()) {
                char[] array = programmeID.toCharArray();

                if (array.length == 3) {
                    
                    for (int i = 0; i < array.length; i++) {
                        if (!Character.isLetter(array[i])) {
                            // programme ID have one is not letter then can stop the loop and return null, because not fulfill requirement
                            System.out.println("Programme ID cannot have special character");
                            return false;
                        }
                    }
                    
                    // last check > have exist in file
                    if(getTargetProgramme(programmeID) != null){
                        return true;
                    }else{
                        System.out.println("Programme ID not exist please reenter again");
                    }
                } else {
                    System.out.println("Programme ID must in 3 character");
                }
            } else {
                System.out.println("Programme ID is required.");
            }
        //} while (!valid);

        return true;
    }
    
    public DoublyLinkedList<Programme> getAllProgramme(){
        try{
            return db.Programme.getAll();
        }catch(IOException | ClassNotFoundException ex){
            // print error message
            System.out.println("Unexpected situation occurs, Unable to get data from file \nDetail Message > " + ex.getMessage());
        }
        
        return null;
    }
    
    public Programme getTargetProgramme(String programmeID){
        try{
           return db.Programme.getWithId(programmeID);
        }catch(IOException | ClassNotFoundException ex){
            // print error message
            System.out.println("Unexpected situation occurs, Unable to get data from file \nDetail Message > " + ex.getMessage());
        }
        
        return null; // no this data inside
    }

    /**
     * add course if valid db add course return message + id read n add
     * @return false when update unsuccessful and true when success 
     */
    public boolean addProgrammeInCourse(String courseID, Programme programme) {
        //boolean stopCase = false;
        
        try{
            // courseID have data
            if (courseID != null && !courseID.isEmpty()) {
                // check programme
                if (programme != null && programme.getProgrammeID() != null && !programme.getProgrammeID().isEmpty()) {
                    // programme valid , then check exist
                    Programme targetProgramme = getTargetProgramme(programme.getProgrammeID());
                    if (targetProgramme != null) {
                        // have data inside programme file, then save into courseProgramme file
                        if(db.CourseProgramme.Add(new CourseProgramme(programme, courseID))){
                            // success , give a message on courseControl
                            return true;
                        }
                    }
                }
            }
        }catch(IOException | ClassNotFoundException ex){
            // print error message
            System.out.println("Unexpected situation occurs, Unable to get or save data to file \nDetail Message > " + ex.getMessage());
        }
        
        return false;

    }

    /**
     * check id related cannot be empty or null value return result
     *
     *
     */
    public boolean removeProgrammeInCourse(String courseID,Programme programme) {
        //boolean stopCase = false;
        try{
            // courseID have data
            if (courseID != null && !courseID.isEmpty()) {
                // check programme
                if (programme != null && programme.getProgrammeID() != null && !programme.getProgrammeID().isEmpty()) {
                    // programme valid , then check exist
                    Programme targetProgramme = getTargetProgramme(programme.getProgrammeID());
                    if (targetProgramme != null) {
                        // have data inside programme file, then delete from courseProgramme file
                        if(db.CourseProgramme.Delete(new CourseProgramme(programme, courseID))){
                            // success , give a message on courseControl
                            return true;
                        }
                    }
                }
            }
        }catch(IOException | ClassNotFoundException ex){
            // print error message
            System.out.println("Unexpected situation occurs, Unable to get or save data to file \nDetail Message > " + ex.getMessage());
        }
        
        return false;
        
        
        
       
    }

    
    
}
