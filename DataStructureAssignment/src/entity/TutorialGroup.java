package entity;

import dao.DBModel;
import java.io.Serializable;

public class TutorialGroup extends DBModel<TutorialGroup> implements Serializable {

    private String tutorialGrpId;
    private int groupSize;
    private String programmeID;

    private static final String FILENAME = "TutorialGroup";

    public TutorialGroup() {
        super(FILENAME);
    }

    public TutorialGroup(String tutorialGrpId) {
        super(FILENAME);
        this.tutorialGrpId = tutorialGrpId;
    }

    public TutorialGroup(String tutorialGrpId, int groupSize, String programmeID) {
        super(FILENAME);
        this.tutorialGrpId = tutorialGrpId;
        this.groupSize = groupSize;
        this.programmeID = programmeID;
    }

    public String getTutorialGrpId() {
        return tutorialGrpId;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getProgrammeID() {
        return programmeID;
    }

    public void setTutorialGrpId(String tutorialGrpId) {
        this.tutorialGrpId = tutorialGrpId;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public void setProgrammeID(String programmeID) {
        this.programmeID = programmeID;
    }

    public static void sort(Object[] person, int size) {

        int length = size;

        if (length < 2) { //if only one data, then stop processing
            return;
        }

        int midIndex = length / 2; //get Mid Point to split into 2 subArray

        TutorialGroup[] leftHalf = new TutorialGroup[midIndex];
        TutorialGroup[] rightHalf = new TutorialGroup[length - midIndex];

        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = (TutorialGroup) person[i];
        }
        for (int i = midIndex; i < length; i++) {
            rightHalf[i - midIndex] = (TutorialGroup) person[i];
        }

        sort(leftHalf, leftHalf.length);
        sort(rightHalf, rightHalf.length);

        //Merge
        merge(person, leftHalf, rightHalf);

    }

    private static void merge(Object[] person, TutorialGroup[] leftHalf, TutorialGroup[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        int i = 0, j = 0, k = 0;

        //Age
//        while (i < leftSize && j < rightSize) {
//            if (leftHalf[i].getGroupSize() <= rightHalf[j].getGroupSize()) {
//                person[k] = leftHalf[i];
//                i++;
//            } else {
//                person[k] = rightHalf[j];
//                j++;
//            }
//            k++;
//        }
        ///
        //String (Name)
        while (i < leftSize && j < rightSize) {

            if (leftHalf[i].getTutorialGrpId().compareTo(rightHalf[j].getTutorialGrpId()) < 0) {
                person[k] = leftHalf[i];
                i++;
            } else {
                person[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        ////
        while (i < leftSize) {
            person[k] = leftHalf[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            person[k] = rightHalf[j];
            j++;
            k++;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return (this.getTutorialGrpId().equals(((TutorialGroup) obj).getTutorialGrpId()) && this.getGroupSize() == ((TutorialGroup) obj).getGroupSize());
    }

    public String toString() {
        String str = "GroupName : " + this.tutorialGrpId + "\nGroup Size : " + this.groupSize;
        return str;
    }

    @Override
    public Object getPrimary() {
        return tutorialGrpId;
    }

    @Override
    public int compareTo(TutorialGroup o) {
        return this.tutorialGrpId.compareTo(o.tutorialGrpId);
    }

}
