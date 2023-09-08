/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.CourseProfile;
import dao.DBTable;
import entity.*;
import java.io.IOException;

/**
 *
 * @author LOH XIN YI
 */
public class CourseProgrammeControl {

    private DBTable db;
    private CourseControl cControl;
    private CourseProfile courseUI;

    public CourseProgrammeControl() {
        //this.cControl = new CourseControl();
        courseUI = new CourseProfile();
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

            programmeID = cControl.getProgrammeID();
            // do validation
            if (programmeID != null && !programmeID.isEmpty()) {
                char[] array = programmeID.toCharArray();

                if (array.length == 3) {
                    
                    for (int i = 0; i < array.length; i++) {
                        if (!Character.isLetter(array[i])) {
                            // programme ID have one is not letter then can stop the loop and return null, because not fulfill requirement
                            System.out.println("Programme ID cannot have special character");
                            return false;
                        } else if (i == 2 && Character.isLetter(array[i])) {
                            // i == 2 means already last && array[i] is letter == all requirement match return value
                            // valid length
                            //valid = true;
                            return true;
                            
                        }
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

    /**
     * add course if valid db add course return message + id read n add
     */
    public void addProgrammeInCourse() {
        boolean stopCase = false;
        do {
            String courseID, programmeID;
            courseID = cControl.doValidateCourseID();

            courseUI.getCourseID();
            try {
                CourseProgramme cp = db.CourseProgramme.getWithId(courseID);

                if (cp != null) {
                    CourseProgramme newCourseProgramme = new CourseProgramme();
                    newCourseProgramme.setCourseID(courseID);
                    //programmeID = courseUI.getProgrammeID();
                    programmeID = cControl.getProgrammeID();
                    newCourseProgramme.setProgrammeID(programmeID);

                    db.CourseProgramme.Update(newCourseProgramme);
                    System.out.println("Programme" + programmeID + " added to course " + courseID + "successfully.");
                    stopCase = true;
                } else {
                    System.out.println("Course ID had not created yet.");
                    //courseUI.addCourse();
                }

            } catch (IOException | ClassNotFoundException ex) {
                // print error message
                System.out.println("Unexcepted situation occurs, data retreive or store unssuccessful\nMore Details > " + ex.getMessage());

                stopCase = true;
            }
        } while (!stopCase);
        
       
    }

    /**
     * check id related cannot be empty or null value return result
     *
     *
     */
    public void removeProgrammeInCourse() {

        String courseID, programmeID;
        courseID = cControl.doValidateCourseID();
        
        //programmeID = doValidateProgrammeID();
        

        try {
            CourseProgramme cp = db.CourseProgramme.getWithId(courseID);
            
            if (cp != null) {
                CourseProgramme newCourseProgramme = new CourseProgramme();
                newCourseProgramme.setCourseID(courseID);
                //programmeID = courseUI.getProgrammeID();
                programmeID = cControl.getProgrammeID();
                newCourseProgramme.setProgrammeID(programmeID);
                
                db.CourseProgramme.Delete(new CourseProgramme(programmeID));
                System.out.println("Course " + courseID + "remove successfully.");
                
            } else {
                System.out.println("Course ID had not created yet.");
                //courseUI.courseIDOfRemoveProgramme();
            }
            
            
        } catch (IOException | ClassNotFoundException ex) {
            // print error message
            System.out.println("Course NOT Found, pls type again.");
            
            
        }
       
    }

    /**
     * check id related
     * cannot be empty or null value
     * return the search result result
     * 
     */ 
    /*public void searchByProgrammeID(){
        String programmeID = doValidateProgrammeID();
        

        try {
            db.Course.getWithId(programmeID);
            //next interface for modify and exit data
            
            
        } catch (IOException | ClassNotFoundException ex) {
            // print error message
            System.out.println("Course NOT Found, pls type again.");
            //back the page to key in
            courseUI.getSearchCourse();
            
        }
    }*/
}
