/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 4/14/2016.
 */
public class Test extends Assessment {
    /**
     *
     * @param name store the name of the Test
     * @param weight store the weight of the weight
     */
    public Test(String name, String weight){
        super(name, weight);
    }

    /**
     *
     * @return toString of Test details
     */
    @Override
    public String toString() {
        return ("Test Name:" + this.getName() + "\n" + "Weight :" + this.getWeight());
    }
}
