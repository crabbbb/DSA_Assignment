package entity;

import adt.ArrayList;

public class TutGrp {

    private static int currentGrpId = 1000;
    private int groupId;
    private String groupName;
    private int groupSize;
    private ArrayList<Student> students;

    public TutGrp() {
    }

    public TutGrp(String groupName, int groupSize, ArrayList<Student> students) {
        this.groupName = groupName;
        this.groupSize = groupSize;
        this.students = students;
        this.groupId = currentGrpId;
        currentGrpId++;
    }

    public static int getCurrentGrpId() {
        return (currentGrpId - 1);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public static void sort(Object[] person, int size) {

        int length = size;

        if (length < 2) { //if only one data, then stop processing
            return;
        }

        int midIndex = length / 2; //get Mid Point to split into 2 subArray

        TutGrp[] leftHalf = new TutGrp[midIndex];
        TutGrp[] rightHalf = new TutGrp[length - midIndex];

        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = (TutGrp) person[i];
        }
        for (int i = midIndex; i < length; i++) {
            rightHalf[i - midIndex] = (TutGrp) person[i];
        }

        sort(leftHalf, leftHalf.length);
        sort(rightHalf, rightHalf.length);

        //Merge
        merge(person, leftHalf, rightHalf);

    }

    private static void merge(Object[] person, TutGrp[] leftHalf, TutGrp[] rightHalf) {
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

            if (leftHalf[i].getGroupName().compareTo(rightHalf[j].getGroupName()) < 0) {
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
        return (this.getGroupName().equals(((TutGrp) obj).getGroupName()) && this.getGroupSize() == ((TutGrp) obj).getGroupSize());
    }

    public String toString() {
        String str = "GroupName : " + this.groupName + "\nGroup Size : " + this.groupSize;
        for (int i = 0; i < students.size(); i++) {
            str += students.get(i).toString();
        }
        return str;
    }

}
