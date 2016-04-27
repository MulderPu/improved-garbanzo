/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 4/14/2016.
 */
public class Assignment extends Assessment {
    /**
     *
     * @param name used to store name of the assignment
     * @param weight used to store the weight of the assignment
     */
    public Assignment(String name, String weight){
        super(name, weight);
    }

    /**
     *
     * @return toString of assignment details
     */
    @Override
    public String toString() {
        return ("Assignment Name:" + this.getName() + "\n" + "Weight :" + this.getWeight());
    }

}
