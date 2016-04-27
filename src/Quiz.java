/**
 * Project Name : Java Assignment 1
 * Created by Mulder on 4/14/2016.
 */
public class Quiz extends Assessment {
    /**
     *
     * @param name store the name of the quiz
     * @param weight store the weight of the quiz
     */
    public Quiz(String name, String weight){
        super(name, weight);
    }

    /**
     *
     * @return toString of the quiz details
     */
    @Override
    public String toString() {
        return ("Quiz Name:" + this.getName() + "\n" + "Weight :" + this.getWeight());
    }

}
