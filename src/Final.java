/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 4/14/2016.
 */
public class Final extends Assessment {
    /**
     *
     * @param name store name of Final
     * @param weight store the weight of Final
     */
    public Final(String name, String weight){
        super(name, weight);
    }

    /**
     *
     * @return toString of Final datails
     */
    @Override
    public String toString() {
        return ("Final Name:" + this.getName() + "\n" + "Weight :" + this.getWeight());
    }
}
