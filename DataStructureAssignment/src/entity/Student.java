package entity;

import dao.DBModel;
import java.io.Serializable;

public class Student extends DBModel<Student> implements Serializable {

    private String id;
    private String name;
    private int age;
    private String tutGrpId;

    private static final String FILENAME = "Student";

    public Student() {
        super(FILENAME);
    }

    public Student(String id, String name, int age, String tutGrpId) {
        super(FILENAME);
        this.id = id;
        this.name = name;
        this.age = age;
        this.tutGrpId = tutGrpId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTutGrpId() {
        return tutGrpId;
    }

    public void setTutGrpId(String tutGrpId) {
        this.tutGrpId = tutGrpId;
    }

    public String toString() {
        return "\nID : " + this.id + "\nName : " + this.name + "\nAge : " + this.age;
    }

    @Override
    public Object getPrimary() {
        return id;
    }

    @Override
    public int compareTo(Student o) {
        return this.id.toUpperCase().compareTo(o.id.toUpperCase());
    }

}
