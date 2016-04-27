import java.io.Serializable;
import java.util.ArrayList;

/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 4/13/2016.
 */
public class Assessment implements Serializable {

    private String name;
    private ArrayList<Submission> submissionsList = new ArrayList<>();
    private String weight;

    /**
     *
     * @param name used to store name of the assessment
     * @param weight used to store the weight of the assessment
     */
    public Assessment(String name, String weight){
        this.name = name;
        this.weight = weight;
    }

    /**
     *
     * @param name used to set name of the assessment
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param weight used to set weight of the assessment
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /**
     *
     * @return name of the assessment
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return weight of the assessment
     */
    public String getWeight() {
        return weight;
    }

    /**
     * print assessment details
     */
    public void printAssessmentDetails(){
        System.out.println("Name: " + getName() + "\n" + "Weight: " + getWeight() + "\n");
        System.out.println("List of submissions :");
        for(int i =0; i < submissionsList.size(); i++){
            System.out.println("\t" + i + "." + submissionsList.get(i).getName());
        }
        System.out.println();
    }

    /**
     *
     * @return toString of assessment details
     */
    @Override
    public String toString() {
        return ("Assessment Name:" + this.getName() + "\n" + "Weight :" + this.getWeight());
    }

    /**
     *
     * @param submission used to set the submission of the student in an arraylist
     */
    public void setSubmission(Submission submission){
        this.submissionsList.add(submission);
    }

    /**
     *
     * @return the submission list of student
     */
    public ArrayList<Submission> getSubmissionsList() {
        return submissionsList;
    }

    /**
     *
     * @param inputStudName used to remove the submission from the arraylist
     */
    public void removeSubmission(String inputStudName){
        for(int i =0; i < submissionsList.size(); i++){
            if(submissionsList.get(i).getName().equals(inputStudName)){
                this.submissionsList.remove(submissionsList.get(i));
            }
        }
    }
}
