

/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 4/14/2016.
 */
public class Lab extends Assessment{;

    /**
     *
     * @param name store the name of Lab
     * @param weight store the weight of the Lab
     */
    public Lab(String name, String weight){
        super(name, weight);
    }

    /**
     *
     * @return toString of Lab details
     */
    @Override
    public String toString() {
        return ("Lab Name:" + this.getName() + "\n" + "Weight :" + this.getWeight());
    }

}
