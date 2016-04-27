import java.io.*;
import java.util.ArrayList;

/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 3/29/2016.
 */
public class Class implements Serializable{
    /*
    initialize variables
     */
    private String name;
    private String code;
    private Unit unit;
    private String sem;
    private String year;
    private ArrayList<Student> studentList = new ArrayList<>();
    private ArrayList<Assessment> assessmentList = new ArrayList<>();

    /**
     * Empty Contructor
     */
    public Class(){}

    /**
     *
     * @param name used to store the name of the class
     * @param code used to store the code of the class
     * @param sem used to store the semester of the class
     * @param year used to store the year of the class
     */
    public Class(String name, String code, String sem, String year){
        this.name = name;
        this.code = code;
        this.sem = sem;
        this.year = year;
    }

    /**
     *
     * @return semester of the class
     */
    public String getSem() {
        return sem;
    }

    /**
     *
     * @return year of the class
     */
    public String getYear() {
        return year;
    }

    /**
     *
     * @param sem set the semester of the class
     */
    public void setSem(String sem) {
        this.sem = sem;
    }

    /**
     *
     * @param year set the year of the class
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     *
     * @return get the name of the class
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return get the code of the class
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param name set name for the class
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param code set code for the class
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @param student add student into the class in a list of array
     */
    public void enrollStudent(Student student){
        this.studentList.add(student);
    }

    /**
     *
     * @param studName remove student from the class in the list of array
     */
    public void removeStudent(String studName){
        for(int i =0; i < studentList.size(); i++){
            if(studentList.get(i).getName().equals(studName)){
                this.studentList.remove(studentList.get(i));
            }
        }
    }

    /**
     *
     * @return unit detail
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     *
     * @return a list of student added to the class
     */
    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    /**
     *
     * @param unit set unit into the class
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    /**
     *
     * @param assessment set assessment into the class in a list of array
     */
    public void setAssessment(Assessment assessment){
        this.assessmentList.add(assessment);
    }

    /**
     *
     * @return get list of assessment added into the class
     */
    public ArrayList<Assessment> getAssessmentList() {
        return assessmentList;
    }

    /**
     * print the class details
     */
    public void printClassDetails(){
        System.out.println("Class Name :" + this.getName() + "\n" + "Class Code :" + this.getCode()
                + "\n" + "Semester :" + this.getSem() + "\n" + "Year :" + this.getYear());
        System.out.println(this.getUnit());

        System.out.println("List of assessments :");
        for(int i =0; i < assessmentList.size(); i++){
            System.out.println( i + "." + assessmentList.get(i).getName());
        }
        System.out.println();
        System.out.println("List of students :");
        for(int i =0; i < studentList.size(); i++){
            System.out.println("\t" + i + "." + studentList.get(i).getName());
        }
        System.out.println();
    }

    /**
     * print unit summary report of a class
     */
    public void printSummaryReport(){
        System.out.println(this.getUnit());
        System.out.println("Semester: " + this.getSem() + ", " + this.getYear() );
    }
}
