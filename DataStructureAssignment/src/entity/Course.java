/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import dao.*;
import java.io.Serializable;
/**
 *
 * @author LOH XIN YI
 */
public class Course extends DBModel<Course> implements Serializable{
    //bacs 1107
    private String courseID;
    private String courseName;
    private String courseDescription;
    private double creditHours;
    
    private final static String FILENAME = "Course";

    public Course() {
        super(FILENAME);
    }

    public Course(String courseID) {
        this.courseID = courseID;
    }

    

    public Course(String courseID, String courseName, String courseDescription, double creditHours) {
        super(FILENAME);
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.creditHours = creditHours;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseId) {
        this.courseID = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public double getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(double creditHours) {
        this.creditHours = creditHours;
    }

    @Override
    public String toString() {
        return "Course{" + "courseID=" + courseID + ", courseName=" + courseName + ", courseDescription=" + courseDescription + ", creditHours=" + creditHours + '}';
    }
    
    public static void main(String[] args) {
        Course c = new Course("yyy", "", "", 0);
        System.out.println(c);
    }

    @Override
    public Object getPrimary() {
        return this.courseID;
    }

    @Override
    public int compareTo(Course o) {
        return this.courseID.toUpperCase().compareTo(o.courseID.toUpperCase());
    }
}
