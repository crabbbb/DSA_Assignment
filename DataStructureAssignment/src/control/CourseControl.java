/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.*;
import boundary.CourseProfile;
import dao.DBTable;
import entity.*;
import java.io.IOException;
import utility.Message;

/**
 *
 * @author LOH XIN YI
 */
public class CourseControl {
    //private ListInterface<Course> courseList = new DoublyLinkedList<>();
    private DBTable db;
    private CourseProgrammeControl cpControl;
    private CourseProfile courseUI;

    public CourseControl() {
        db = new DBTable();
        cpControl = new CourseProgrammeControl();
        courseUI = new CourseProfile();
    }
    
    public void runCourseManagement(){
        int choice;
        do{
            // get choice
            choice = courseUI.getMenuChoice();
            
            switch (choice) {
                case 1://add course
                    //courseUI.addCourse();
                    addNewCourse();
                    break;
                case 2://remove course
                    //courseUI.getRemoveCourse();
                    deleteCourse() ;
                    break;

                case 3://find
                    //courseUI.getSearchCourse();
                    searchByCourseID();
                    break;
                case 4://amend
                    modifyCourse();
                    break;
                case 5://list all
                    courseUI.listAllCourse(getAllData());
                    break;
                case 6://add programme
                    addProgramme();
                    break;
                case 7://remove programme
                    removeProgramme();
                    break;
                case 8://report
                    courseUI.reportOfCourse(getAllData());
                    break;
                case 9:
                    System.out.println("Exiting the Course Management System.");
                    System.exit(0);
                    break;
                default:
                    courseUI.displayInvalidMenuChoice();
            }

        }while(choice != 9);
    }
    
    /**
     * add course if valid
     * db add course id, name, description, creditHours
     * return message + id
     * read n add
     */ 
    public void addNewCourse() {
        boolean stopCase = false;
        do{
            courseUI.addCourse();
            //validate the input
            String courseID = doValidateCourseID();
            String courseName, courseDescription, creditHours;

            try {
                Course c = db.Course.getWithId(courseID);
                if (c == null) {
                    // no exist
                    Course newCourse = new Course();
                    newCourse.setCourseID(courseID);
                    courseName = doValidateCourseName();

                    newCourse.setCourseName(courseName);
                    courseDescription = CourseDescriptionValidator();
                    newCourse.setCourseDescription(courseDescription);
                    creditHours = CourseCHValidator();
                    double ch = Double.parseDouble(creditHours);
                    newCourse.setCreditHours(ch);

                    if(db.Course.Add(newCourse)){
                        stopCase = true;
                        System.out.println("Course ID " + courseID + " had added successfully.");
                    }

                } else {
                    //error message
                    System.out.println("Course ID already been created.");
                }

            } catch (IOException | ClassNotFoundException ex) {
                // print error message
                System.out.println("Unexcepted situation occurs, data retreive or store unssuccessful\nMore Details > " + ex.getMessage());
                stopCase = true;

            }
        }while(!stopCase);

    }
    
    /**
     * under what situation will be repeating loop
     * only can have 8 character length
     * check repeat
     * cannot be empty or null value 
     * length BACS2063 = 8
     * 
     * @return string in valid and correct format
     */
    public String doValidateCourseID(){
        boolean valid = false;
        String courseID = null;
        do{
            courseID = courseUI.getCourseID();
            
            // do validation

            if (courseID != null && !courseID.isEmpty()) {

                if (courseID.length() == 8) {
                    // valid length
                    valid = true;
                } else {
                    System.out.println("Course ID is only in length of 8, Please renter again");
                }
            } else {
                System.out.println("Course ID is required.");
            }
        }while(!valid);
        
        return courseID;
    }
    
    
    /**
     * only in character format
     * check repeat
     * cannot be empty or null value 
     */
    public String doValidateCourseName(){
        boolean valid = true; // valid == true when the format is correct , false when invalid format
        String courseName = null;
        do {
            valid = true; // renew
            
            courseName = courseUI.getCourseName();

            // do validation
            if (courseName != null && !courseName.isEmpty()) {

                for (int i = 0; i < courseName.length() && valid; i++) {
                    if (!Character.isLetter(courseName.charAt(i))  && ! Character.isWhitespace(courseName.charAt(i))) {
                    
                        valid = false;
                        System.out.println("Course Name only in Character.");
                    }else{
                        valid = true;
                    }
                }
                                
            } else {
                System.out.println("Course Name is required.");
                valid = false;
            }

        } while (!valid);

        return courseName;
        
    }
    
    
    /**
     *
     * cannot be empty or null value
     * @return
     */
    public String CourseDescriptionValidator(){
        boolean valid = false;
        String courseDescription = null;
        do {
            courseDescription = courseUI.getCourseDescription();

            // do validation
            if (courseDescription != null && !courseDescription.isEmpty()) {
                // valid length
                valid = true;
            } else {
                System.out.println("Credit Hours is required.");
            }
        } while (!valid);
        
        return courseDescription;      
    }
    
    /**
     * only in numeric format
     * only 1-4 accept
     * cannot be empty or null value]
     * @return 
     */ 
    public String CourseCHValidator(){
        boolean valid = false;
        String creditHours = null;
        do {
            creditHours = courseUI.getCourseCreditHours();

            // do validation
            if (creditHours != null && !creditHours.isEmpty()) {
                try {
                    double ch = Double.parseDouble(creditHours);

                    if (ch > 4 || ch < 1) {
                        // invalid range
                        System.out.println("The credit hours only in range 1 to 4.");
                    }else{
                        // valid length
                        valid = true;
                    }
                } catch (NumberFormatException ex) {
                    // when creditHours is not in numeric 
                    System.out.println("Pls enter a numeric input, e.g 1 or 2.0.");
                }
                
            } else {
                System.out.println("Credit Hours is required.");
            }
        } while (!valid);

        return creditHours;
       
    }
    
    /**
     * check id related
     * cannot be empty or null value
     * return result
     * 
     */ 
    public void deleteCourse() {
        //pgID 
        //String courseID, courseName,courseDescription,creditHours ;
        courseUI.getRemoveCourse();
        String courseID = doValidateCourseID();
        String courseName,courseDescription,creditHours ;
        
        try {
            Course c = db.Course.getWithId(courseID);
            if (c != null) {
                // no exist
                /*Course newCourse = new Course();
                newCourse.getCourseID(courseID);
                newCourse.getCourseName(courseName);
                newCourse.getCourseDescription(courseDescription);
                double ch = Double.parseDouble(creditHours);
                newCourse.getCreditHours(creditHours);*/

                db.Course.Delete(new Course(courseID));
                System.out.println("Course "+ courseID +" remove successfully.");
                
                

            } else {
                //error message
                System.out.println("Course ID had not created yet.");
                //courseUI.getRemoveCourse();
            }
         
           
        }catch (IOException | ClassNotFoundException ex){
            // print error message
            System.out.println("Unexcepted situation occurs, data retreive or store unssuccessful\nMore Details > " + ex.getMessage());
            //courseUI.getRemoveCourse();
        }
      
     
    }
    
    
    /**
     * check id related
     * cannot be empty or null value
     * return the search result 
     * 
     */ 
    public void searchByCourseID(){
        courseUI.getSearchCourse();
        
        String courseID = doValidateCourseID();
        

        try {
            
            Course c = db.Course.getWithId(courseID);
            
            
            if (c != null) {
                System.out.println("Course Found:");
                System.out.println("Course ID: " + c.getCourseID());
                System.out.println("Course Name: " + c.getCourseName());
                System.out.println("Course Description: " + c.getCourseDescription());
                System.out.println("Credit Hours: " + c.getCreditHours());
            }else{
                //display data
                System.out.println("There is no any result in the database based on your search.");
                
            }
            
            
            
        } catch (IOException | ClassNotFoundException ex) {
            // print error message
            System.out.println("Course NOT Found, pls type again.");
            //back the page to key in
            //courseUI.getSearchCourse();
            
        }
    }
    
   
   
    
    /**
     * list all the data of Course 
     * 
     * @return the result
     * 
     */ 
    public DoublyLinkedList<Course> getAllData() {
        
        try {
            return db.Course.getAll();
        } catch (IOException | ClassNotFoundException ex) {
            Message.ErrorMessage(ex.getMessage());
        }
        return null;
    }
    
    
   
    
    public int doModifyChoice(){
   
        int selection;
        do {

            selection = courseUI.modifyChoice();
            switch (selection) {
                case 1:
                    doValidateCourseName();

                    break;
                case 2:
                    CourseDescriptionValidator();

                    break;
                case 3:
                    CourseCHValidator();

                    break;
                case 0:
                    System.out.println("Exiting the Modify Page.");
                    courseUI.getMenuChoice();
                    break;
                    
                default:
                    courseUI.displayInvalidMenuChoice();
            }
           
            
        }while(selection != 3);
        return 0;

        
    }
    
    public void modifyCourse() {
        
        courseUI.modifyCourse();
        String courseID = doValidateCourseID();

        String courseName, courseDescription, creditHours;
        

        try {
            Course c = db.Course.getWithId(courseID);
            if (c != null) {
                System.out.println("Course Found:");
                System.out.println("Course ID: " + c.getCourseID());
                System.out.println("Course Name: " + c.getCourseName());
                System.out.println("Course Description: " + c.getCourseDescription());
                System.out.println("Credit Hours: " + c.getCreditHours());

                doModifyChoice();

                c.setCourseID(courseID);

                //update these methods to return the modified values.
                courseName = doValidateCourseName();
                c.setCourseName(courseName);

                courseDescription = CourseDescriptionValidator();
                c.setCourseDescription(courseDescription);

                creditHours = CourseDescriptionValidator();
                double ch = Double.parseDouble(creditHours);
                c.setCreditHours(ch);

                db.Course.Update(c);
                System.out.println("Course " + courseID + " modified successfully.");

            } else {
                System.out.println("Course ID does not exist.");
            }
        } catch (IOException | ClassNotFoundException | NumberFormatException ex) {
            System.out.println("Unexcepted situation occurs, data retreive or store unssuccessful\nMore Details > " + ex.getMessage());
        }
    }
    
        
      
     

    
    
        
  
    public String getProgrammeID(){
        String programmeID;
        do{
            programmeID = courseUI.getProgrammeID();
        }while(!cpControl.doValidateProgrammeID(programmeID));
        
        return programmeID;
    }

    
    
    public void addProgramme(){
        // get input
        String courseID = null;
        boolean stopcase = true;
        do{
            stopcase = true; // reset
            courseID = doValidateCourseID();
            if(getTargetCourse(courseID) == null){
                // data no exist in file reenter again 
                System.out.println("Course not found inside file, please reenter again");
                stopcase = false;
            }
        }while(!stopcase);
        
        DoublyLinkedList<Programme> plist = cpControl.getAllProgramme();
        
        courseUI.listAllProgramme(plist);
        
        // exist in file, format correct
        String programmeID = getProgrammeID();
        
        if(cpControl.addProgrammeInCourse(courseID, new Programme(programmeID))){
            // added success 
            System.out.println("Programme" + programmeID + " added to course " + courseID + "successfully.");
        }else{
            // cannot added 
            System.out.println("It is failed to add the programme to course.");
        }
        
        
    }
    
    public void removeProgramme(){
        // get input
        String courseID = null;
        boolean stopcase = true;
        do{
            stopcase = true; // reset
            courseID = doValidateCourseID();
            if(getTargetCourse(courseID) == null){
                // data no exist in file reenter again 
                System.out.println("Course not found inside file, please reenter again");
                stopcase = false;
            }
        }while(!stopcase);
        
        DoublyLinkedList<Programme> plist = cpControl.getAllProgramme();
        
        courseUI.listAllProgramme(plist);
        
        // exist in file, format correct
        String programmeID = getProgrammeID();
        
        if(cpControl.removeProgrammeInCourse(courseID, new Programme(programmeID))){
            // added success 
            System.out.println("Programme" + programmeID + " remove from course " + courseID + "successfully.");
        }else{
            // cannot added 
            System.out.println("It is failed to remove the programme from course.");
        }
        
        
    }
    
    public Course getTargetCourse(String courseID){
        try{
            return db.Course.getWithId(courseID);
        }catch(IOException | ClassNotFoundException ex){
            // print error message
            System.out.println("Unexpected situation occurs, Unable to get data from file \nDetail Message > " + ex.getMessage());
        }
        
        return null;
    }
    

}
