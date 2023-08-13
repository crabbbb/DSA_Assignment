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

    private String programmeDescription;

    public Programme() {
        super("Programme");
    }

    public Programme(String programmeID, String programmeName, int programmeCapacity, String programmeDescription) {
        super("Programme");
        this.programmeID = programmeID;
        this.programmeName = programmeName;
        this.programmeDescription = programmeDescription;
    }

    public String getProgrammeID() {
        return programmeID;
    }

    public String getProgrammeName() {
        return programmeName;
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

    public void setProgrammeDescription(String programmeDescription) {
        this.programmeDescription = programmeDescription;
    }

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
        return "Programme{" + "programmeID=" + programmeID + ", programmeName=" + programmeName + ", programmeDescription=" + programmeDescription + '}';
    }

}
