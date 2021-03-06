import java.util.Date;

/**
 * Created by Vegard on 07/03/16.
 */
public class Excercise {

    private int sets, reps, load, duration;
    double distance ;
    String name ;
    Date training_session;

    public Excercise(int sets, int reps, int load, double distance, int duration, String name, Date training_session) {
        this.sets = sets;
        this.reps = reps;
        this.load = load;
        this.duration = duration;
        this.distance = distance;
        this.name = name;
        this.training_session = training_session;
    }



    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public int getLoad() {
        return load;
    }

    public double getDistance() {
        return distance;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public Date getTraining_session() {
        return training_session;
    }
}
