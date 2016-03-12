import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vegard on 07/03/16.
 */
public class DBController {
    Connection conn;

    public DBController() {
        String dbURL = "jdbc:mysql://localhost:3306/training_diary_DB";
        String username = "root";
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
        String sql = "INSERT INTO treningsdagbok.Exercise (Name, Description) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, description);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new excercise was inserted successfully!");
        }
    }

    public void addSport(String name) throws SQLException {
        String sql = "INSERT INTO treningsdagbok.sport (name) VALUES (?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new sport was inserted successfully!");
        }
    }

    public void addTrainingSession(Date time, Integer duration, Integer shape, Integer rating, String note,
                                   String sportname, String type, String weather_type, double temperature, double humidity, Integer spectators) throws SQLException {
        String sql = "INSERT INTO treningsdagbok.TrainingSession (Date, Duration, Shape, Rating, Note, sport_name, Type, Weather_type, Temperature, Humidity, Spectators) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        System.out.println(sql);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new training session was inserted successfully!");
        }
    }
    public void addExcercisePerformed(Integer sets, Integer reps, Integer weight, double distance, Integer duration, String name, Date time) throws SQLException{
        String sql = "INSERT INTO treningsdagbok.ExercisePerformed (Sets, Reps, Weight, Distance, Duration, Exercise_Name, TrainingSession_Date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        System.out.println(sql);

        PreparedStatement statement = conn.prepareStatement(sql);
        System.out.println();
        System.out.println("Statment was made, ey o k");
        statement.setInt(1, sets);
        System.out.println("First thing was put in");
        statement.setInt(2, reps);
        statement.setInt(3, weight);
        statement.setDouble(4, distance);
        statement.setInt(5, duration);
        statement.setString(6, name);
        statement.setDate(7, time);
        System.out.println(sql);
        int rowsInserted = statement.executeUpdate();
        System.out.println("THe line after");
        if (rowsInserted > 0) {
            System.out.println("A new exercise was performed");
        }
    }

    public boolean ExcerciseExists(String name) throws SQLException {
        final String queryCheck = "SELECT count(*) FROM Excercise WHERE name = ?";
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
        final String queryCheck = "SELECT note FROM treningsdagbok.TrainingSession";
        final PreparedStatement ps = conn.prepareStatement(queryCheck);
        ResultSet results = ps.executeQuery();
        List<String> notes = new ArrayList<String>();
        while (results.next()) {
            notes.add(results.getString(1));
        }
        return notes;
    }

    //NOT FINISHED
    public List<Excercise> getExercises(String name) throws SQLException {
        final String queryCheck = "SELECT * FROM treningsdagbok.ExercisePerformed WHERE ExercisePerformed.Exercise_Name=?";
        final PreparedStatement ps = conn.prepareStatement(queryCheck);
        ps.setString(1, name);
        ResultSet results = ps.executeQuery();
        List<Excercise> notes = new ArrayList<Excercise>();
        Excercise e;
        while (results.next()) {
            int sets = results.getInt(1);
            int reps = results.getInt(2);
            int load = results.getInt(3);
            double distance = results.getDouble(4);
            int duration = results.getInt(5) ;
            String excercise_name = results.getString(6);
            Date time = results.getDate(7);
            e = new Excercise(sets, reps, load, distance, duration, excercise_name, time);
            notes.add(e);
        }
        return notes;
    }

    public void addGoal(Date date, double speed, Integer weightLifted, String exerciseName) throws SQLException{
        String sql = "INSERT INTO treningsdagbok.Goal (Date, Speed, TotalWeightLifted, Achieved, Exercise_Name) VALUES (?, ?, ?, ?, ?)";

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
        String sql = "INSERT INTO treningsdagbok.Group (Name) VALUES(?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
    }

    public void addGroupToExercise(String exercise, String group) throws SQLException {
        String sql = "INSERT INTO treningsdagbok.Exercise_has_Group (Group_Name, Exercise_Name) VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, group);
        ps.setString(2, exercise);
        ps.executeUpdate();
    }


    public static void main(String[] args) {
        DBController c = new DBController();
        try{
            String s = "Bench Press";
            /*
            Date d = new Date(1,1,1);
            c.addSport("Olympic lifts");
            c.addExercise(s, "Press a bench");
            c.addTrainingSession(d,50,10,7,"Fucking killed it today. I'm a beast",
                    "Olympic lifts", "Indoor", "0", 0, 0, 1000000);
            c.addExcercisePerformed(5,5,100,0,0,s,d);
            System.out.println(c.getNotes());
            */
            System.out.println(c.getExercises(s));



        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public void deleteExercise(String name) throws SQLException {
        String sql = "DELETE FROM treningsdagbok.Exercise WHERE Name=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A exercise was deleted successfully!");
        }
    }

    public void changeDescription(String name, String description) throws SQLException{
        String sql = "UPDATE treningsdagbok.Exercise SET description=?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, description);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing exercise was updated successfully!");
        }
    }
}