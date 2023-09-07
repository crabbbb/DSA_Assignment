
package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author KELLY TAN
 */

 public class Tutor implements Serializable {
        private String ID;
        private String name;
        private String email;
        private String course;
        private String schedule;

        
    public Tutor(String ID, String name, String email, String course, String schedule) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.course = course;
        this.schedule = schedule;
    }

    // Get & Set Methods
    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCourse() {
        return course;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

  
    
     @Override
     public int hashCode() {
         int hash = 3;
         return hash;
     }
        
        
     @Override
     public boolean equals(Object o) {
         if (this == o) {
             return true;
         }
         if (o == null ) {
             return false;
         }
         if (getClass() != o.getClass()) {
             return false;
         }
         final Tutor other = (Tutor) o;
         if (!Objects.equals(this.ID, other.ID)) {
             return false;
         }
         return true;
     }

     
     
    @Override
    public String toString() {
        
        return "\nTutor ID : " + ID
                + "\nName       : " + name
                + "\nEmail      : " + email
                + "\nCourse     : " + course
                + "\nSchedule   : " + schedule ;
     }
 
    }
