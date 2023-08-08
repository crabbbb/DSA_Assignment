package entity;

/**
 * @author LOH XIN JIE
 */
import adt.*;
import dao.*;
import java.io.Serializable;
import java.util.Objects;

public class Programme extends DBModel implements Serializable {

    //RSW
    private String programmeID;

    //full name of RSW
    private String programmeName;

    //maximum number need of student
    private int programmeCapacity;

    private String programmeDescription;

    //private ArrayList<TutGrp> ttlGrp;
    public Programme() {
        super("Programme");
    }

    public Programme(String programmeID, String programmeName, int programmeCapacity, String programmeDescription) {
        super("Programme");
        this.programmeID = programmeID;
        this.programmeName = programmeName;
        this.programmeCapacity = programmeCapacity;
        this.programmeDescription = programmeDescription;
//        this.ttlGrp = ttlGrp;
    }

    public String getProgrammeID() {
        return programmeID;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public int getProgrammeCapacity() {
        return programmeCapacity;
    }

    public String getProgrammeDescription() {
        return programmeDescription;
    }

    public void setProgrammeID(String programmeID) {
        this.programmeID = programmeID;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public void setProgrammeCapacity(int programmeCapacity) {
        this.programmeCapacity = programmeCapacity;
    }

    public void setProgrammeDescription(String programmeDescription) {
        this.programmeDescription = programmeDescription;
    }

//    //getter and setter and add for TutorialGrp
//    public ArrayList<TutGrp> getTtlGrp() {
//        return ttlGrp;
//    }
//
//    public void setTtlGrp(ArrayList<TutGrp> ttlGrp) {
//        this.ttlGrp = ttlGrp;
//    }
//
//    public boolean addTtlGrp(TutGrp ttlGrp) {
//        return this.ttlGrp.add(ttlGrp);
//    }
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final Programme programme = (Programme) obj;
        if (!Objects.equals(this.programmeID, programme.programmeID)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Programme{" + "programmeID=" + programmeID + ", programmeName=" + programmeName + ", programmeCapacity=" + programmeCapacity + ", programmeDescription=" + programmeDescription + ", ttlGrp=" + '}';
    }

}
