import java.io.Serializable;
import java.util.ArrayList;

/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 3/28/2016.
 */
public class Student implements Serializable {
    /*
    initialize variables
     */
    private String name;
    private String id;
    private String program;

    /**
     * Empty contructor
     */
    public Student() {}

    /**
     *
     * @param name store the name of the student
     * @param id store the id of the student
     * @param program store the program of the student
     */
    public Student(String name, String id, String program){
        this.name = name;
        this.id = id;
        this.program = program;
    }

    /**
     *
     * @param name set name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return get the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param id set id for the student
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return get id of the student
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param program set program for the student
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     *
     * @return get the program of the student
     */
    public String getProgram() {
        return program;
    }

    /**
     * print the student details
     */
    public void printStudentDetails(){
        System.out.println("Name :" + this.getName() + "\n"
                + "ID :" + this.getId() + "\n"
                + "Program Enrolled :" + this.getProgram() + "\n");
    }
}
