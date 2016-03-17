import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vegard on 07/03/16.
 */
public class DBController {
    Connection conn;

    public DBController() {
        String dbURL = "jdbc:mysql://mysql.stud.ntnu.no:3306/chrisslo_treningsdagbok";
        String username = "chrisslo_trening";
        String password = "";

        try {

            conn = DriverManager.getConnection(dbURL, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addExercise(String name, String description) throws SQLException {
        if (this.exerciseExists(name) ){
            System.out.println("This exercise already exists");
            return;
        }
        String sql = "INSERT INTO chrisslo_treningsdagbok.Exercise (Name, Description) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, description);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new exercise was inserted successfully!");
        }
    }

    public void addSport(String name) throws SQLException {
        String sql = "INSERT INTO chrisslo_treningsdagbok.sport (name) VALUES (?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new sport was inserted successfully!");
        }
    }

    public void addTrainingSession(Date time, Integer duration, Integer shape, Integer rating, String note,
                                   String sportname, String type, String weather_type, double temperature, double humidity, Integer spectators) throws SQLException {
        String sql = "INSERT INTO chrisslo_treningsdagbok.TrainingSession (Date, Duration, Shape, Rating, Note, sport_name, Type, Weather_type, Temperature, Humidity, Spectators) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(1, time);
        statement.setInt(2, duration);
        statement.setInt(3, shape);
        statement.setInt(4, rating);
        statement.setString(5, note);
        statement.setString(6, sportname);
        statement.setString(7, type);
        statement.setString(8, weather_type);
        statement.setDouble(9, temperature);
        statement.setDouble(10, humidity);
        statement.setInt(11, spectators);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new training session was inserted successfully!");
        }
    }
    public void addExercisePerformed(Integer sets, Integer reps, Integer weight, double distance,
                                      Integer duration, String name, Date time) throws SQLException{
        String sql = "INSERT INTO chrisslo_treningsdagbok.ExercisePerformed (Sets, Reps, Weight, Distance, Duration, Exercise_Name, TrainingSession_Date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        System.out.println();
        statement.setInt(1, sets);
        statement.setInt(2, reps);
        statement.setInt(3, weight);
        statement.setDouble(4, distance);
        statement.setInt(5, duration);
        statement.setString(6, name);
        statement.setDate(7, time);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new exercise was performed");
        }
    }

    public boolean exerciseExists(String name) throws SQLException {
        final String queryCheck = "SELECT count(*) FROM Exercise WHERE name = ?";
        final PreparedStatement ps = conn.prepareStatement(queryCheck);
        ps.setString(1, name);
        final ResultSet resultSet = ps.executeQuery();
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count > 0;
    }

    public List<String> getNotes() throws SQLException {
        final String queryCheck = "SELECT note FROM chrisslo_treningsdagbok.TrainingSession";
        final PreparedStatement ps = conn.prepareStatement(queryCheck);
        ResultSet results = ps.executeQuery();
        List<String> notes = new ArrayList<String>();
        while (results.next()) {
            notes.add(results.getString(1));
        }
        return notes;
    }

    public List<Exercise> getExercises(String name) throws SQLException {
        final String queryCheck = "SELECT * FROM chrisslo_treningsdagbok.ExercisePerformed WHERE ExercisePerformed.Exercise_Name=?";
        final PreparedStatement ps = conn.prepareStatement(queryCheck);
        ps.setString(1, name);
        ResultSet results = ps.executeQuery();
        List<Exercise> exercises = new ArrayList<Exercise>();
        Exercise e;
        while (results.next()) {
            int sets = results.getInt(1);
            int reps = results.getInt(2);
            int load = results.getInt(3);
            double distance = results.getDouble(4);
            int duration = results.getInt(5) ;
            String exercise_name = results.getString(6);
            Date time = results.getDate(7);
            e = new Exercise(sets, reps, load, distance, duration, exercise_name, time);
            exercises.add(e);
        }
        return exercises;
    }

    public void addGoal(Date date, double speed, Integer weightLifted, String exerciseName) throws SQLException{
        String sql = "INSERT INTO chrisslo_treningsdagbok.Goal (Date, Speed, TotalWeightLifted, Achieved, Exercise_Name) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(1, date);
        statement.setDouble(2, speed);
        statement.setInt(3, weightLifted);
        statement.setBoolean(4, false);
        statement.setString(5, exerciseName);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new goal was inserted successfully!");
        }
    }


    public void createGroup(String name) throws SQLException {
        String sql = "INSERT INTO chrisslo_treningsdagbok.Group (Name) VALUES(?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        int rowsInserted = ps.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new group was been constructed");
        }
    }

    public void addExerciseToGroup(String exercise, String group) throws SQLException {
        String sql = "INSERT INTO chrisslo_treningsdagbok.Exercise_has_Group (Group_Name, Exercise_Name) VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, group);
        ps.setString(2, exercise);
        int rowsInserted = ps.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Exercise added to group");
        }
    }

    public void deleteExercise(String name) throws SQLException {
        String sql = "DELETE FROM chrisslo_treningsdagbok.Exercise WHERE Name=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A exercise was deleted successfully!");
        }
    }

    public void changeDescription(String name, String description) throws SQLException{
        String sql = "UPDATE chrisslo_treningsdagbok.Exercise SET description=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, description);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing exercise was updated successfully!");
        }
    }
    /*
   public static void main(String[] args){

       Main_class m = new Main_class(new DBController());
       String s = "s";
       Date d = new Date(1,1,1);
       DBController c = new DBController();
       try{
           c.addSport("b");
           c.addExercise(s, "Press a bench");
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }

       while(true) {
           String welcome = "Velkommen til treningsdagboken\n";
           welcome += "Vennligst velg din handling ved å taste riktig tallkode under, etterfulgt av enter: \n";
           welcome += "1 - Legg inn ny treningsøkt\n";
           welcome += "2 - Legg til ny utført øvelse\n";
           welcome += "3 - Hent ut tidligere treningsøkter\n";
           welcome += "4 - Oppdater øvelse med ny beskrivelse\n";

           System.out.print(welcome);
           System.out.print("> ");

           int scase = Integer.parseInt(m.scanner.nextLine());
           switch(scase) {
               case 1:
                   m.addTrainingSession();
                   break;
               case 2:
                   m.addExercisePerformed();
                   break;
               case 3:
                   m.collectSessions();
                   break;
               case 4:
                   m.updateExerciseDescription();
                   break;
               default:
                   break;
           }
       }
   }
    */
    public static void main(String[] args) {
        DBController c = new DBController();
        try{
            String s = "Bench Press";
            Date d = new Date(1,1,1);
            c.addSport("Olympic lifts");
            c.addExercise(s, "Press a bench");
            c.exerciseExists(s);
            c.deleteExercise(s);
            c.addExercise(s, "Press a bench");
            c.changeDescription(s, "Bench a press");
            c.addTrainingSession(d,50,10,7,"Fucking killed it today. I'm a beast",
                    "Olympic lifts", "Indoor", "0", 0, 0, 1000000);
            c.addExercisePerformed(5,5,100,0,0,s,d);
            c.createGroup(s);
            c.addExerciseToGroup(s, s);
            c.addGoal(d, 0, 100, s);

            System.out.println(c.getNotes());
            System.out.println(c.getExercises(s));

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}


