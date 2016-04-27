import java.io.Serializable;

/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 4/19/2016.
 */
public class Submission implements Serializable {
    /*
    submission variable used initialize here
     */
    private String name;
    private String id;
    private double mark;

    /**
     *
     * @param name store the name of the student into submission
     * @param id store the id of the student into submission
     */
    public Submission(String name, String id){
        this.name = name;
        this.id = id;
        this.mark = 0;
    }

    /**
     *
     * @param name set the name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param mark set mark of the student
     */
    public void setMark(double mark) {
        this.mark = mark;
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
     * @return get the name of the student in the submission
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the mark of the submission
     */
    public double getMark() {
        return mark;
    }

    /**
     *
     * @return id of the student in the submission list
     */
    public String getId() {
        return id;
    }


}
