package entity;

/**
 * @author LOH XIN JIE
 */
import dao.*;
import java.io.Serializable;
import java.util.Objects;

public class Programme extends DBModel<Programme> implements Serializable {

    //RSW
    private String programmeID;

    //full name of RSW
    private String programmeName;

    private String programmeDescription;

    private int targetCapacity;

    private static final String FILENAME = "Programme";

    public Programme() {
        super(FILENAME);
    }

    public Programme(String programmeID) {
        super(FILENAME);
        this.programmeID = programmeID;
    }

    public Programme(String programmeID, String programmeName, int targetCapacity, String programmeDescription) {
        super(FILENAME);
        this.programmeID = programmeID;
        this.programmeName = programmeName;
        this.programmeDescription = programmeDescription;
        this.targetCapacity = targetCapacity;
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

    public int getTargetCapacity() {
        return targetCapacity;
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

    public void setTargetCapacity(int targetCapacity) {
        this.targetCapacity = targetCapacity;
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
        return String.format("%4s %-30s\t %10s %6d\n", programmeID, programmeName, programmeDescription, targetCapacity);
    }

    @Override
    public Object getPrimary() {
        return programmeID;
    }

    @Override
    public int compareTo(Programme o) {
        return this.programmeID.toUpperCase().compareTo(o.programmeID.toUpperCase());
    }

}
