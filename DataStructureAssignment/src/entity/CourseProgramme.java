/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.DBModel;
import java.io.Serializable;



/**
 *
 * @author LOH XIN YI
 */
public class CourseProgramme extends DBModel<CourseProgramme>  implements Serializable {

    private Programme programme;
    private Course course;
    private String courseID;
    private String programmeID;
    private final static String FILENAME = "CourseProgramme";

    
    
    public CourseProgramme(){
        super(FILENAME);
    }
    
    public CourseProgramme(String programmeID) {
        this.programmeID = programmeID;
    }
    
    

    
    public CourseProgramme(Programme programme, Course course, String courseID, String programmeID, String filename) {
        super(FILENAME);
        this.programme = programme;
        this.course = course;
        this.courseID = courseID;
        this.programmeID = programmeID;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(String programmeID) {
        this.programmeID = programmeID;
    }
    
    
    
    /*public CourseProgramme(String courseID, Programme programme) {
        super(FILENAME);
        this.courseID = courseID;
        this.programme = programme;
    }*/

    /*public Programme getProgramme() {
        return programme;
    }

    public String getCourse() {
        return courseID;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public void setCourse(String courseID) {
        this.courseID = courseID;
    }*/

    @Override
    public Object getPrimary() {
        return courseID;
    }

    @Override
    public int compareTo(CourseProgramme o) {
        //return this.course.getCourseID().toUpperCase().compareTo(o.course.getCourseID().toUpperCase());
        return this.courseID.toUpperCase().compareTo(o.courseID.toUpperCase());
    }

    
    
}
