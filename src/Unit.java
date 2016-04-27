import java.io.Serializable;
import java.util.ArrayList;

/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 3/29/2016.
 */
public class Unit implements Serializable {
    /*
    initialize unit variables
     */
    private String name;
    private String code;

    /**
     * empty contructor
     */
    public Unit(){}

    /**
     *
     * @param name store the name of the unit
     * @param code store unit code
     */
    public Unit(String name, String code){
        this.name = name;
        this.code = code;
    }

    /**
     *
     * @param name set the unit name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param code set unit code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return get the name of the unit
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return get the code of the unit
     */
    public String getCode() {
        return code;
    }

    /**
     * print the unit details
     */
    public void printUnitDetails(){
        System.out.println("Unit :" + this.getName() + "\n" + "Unit Code :" + this.getCode() + "\n");
    }

    /**
     *
     * @return toString the unit details
     */
    @Override
    public String toString() {
        return ("Unit Name:" + this.getName() + "\n" + "Unit Code :" + this.getCode());
    }
}
