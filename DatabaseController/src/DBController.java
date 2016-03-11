import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vegard on 07/03/16.
 */
public class DBController {
    Connection conn ;
    public DBController(){
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

    public void addExercise(String name, String description) throws SQLException{
        String sql = "INSERT INTO treningsdagbok.Exercise (Name, Description) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        System.out.println("statement created");
        statement.setString(1, name);
        System.out.println("added name");
        statement.setString(2, description);
        System.out.println("Added desc");


        int rowsInserted = statement.executeUpdate();
        System.out.println("updated");
        if (rowsInserted > 0) {
            System.out.println("A new excercise was inserted successfully!");
        }
    }

    public void addSport(String name) throws SQLException{
        String sql = "INSERT INTO Excercise (name) VALUES (?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new sport was inserted successfully!");
        }
    }

    public void addTrainingSession(Date time, int duration, int shape, int rating, String note,
                                   String sportname, String type, double temperature, double humidity, int spectators) throws SQLException {
        String sql = "INSERT INTO Training_Session (Time, Duration, Shape, Rating, Note, sport_name, Type, Weather_type, Temperature, Humidity, Spectators) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setDate(1, time);
        statement.setInt(2, duration);
        statement.setInt(3, shape);
        statement.setInt(4, rating);
        statement.setString(5, note);
        statement.setString(6, sportname);
        statement.setString(7, type);
        statement.setDouble(8, temperature);
        statement.setDouble(9, humidity);
        statement.setInt(10, spectators);


        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new training session was inserted successfully!");
        }
    }
    public void addExcercisePerformed(int sets, int reps, int load, double distance, int duration, String name, Date time) throws SQLException{
        String sql = "INSERT INTO ExercisePerformed (Sets, Reps, Load, Distance, Duration, Excercise_Name, TrainingSessionDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, sets);
        statement.setInt(2, reps);
        statement.setInt(3, load);
        statement.setDouble(4, distance);
        statement.setInt(5, duration);
        statement.setString(6, name);
        statement.setDate(7, time);
        statement.executeUpdate();
    }

    public boolean ExcerciseExists(String name) throws SQLException{
        final String queryCheck = "SELECT count(*) from Excercise WHERE name = ?";
        final PreparedStatement ps = conn.prepareStatement(queryCheck);
        ps.setString(1, name);
        final ResultSet resultSet = ps.executeQuery();
        int count = 0;
        if(resultSet.next()) {
            count = resultSet.getInt(1);
        }
        return count>0;
    }

    public List<String> getNotes() throws SQLException{
        final String queryCheck = "SELECT note from TrainingSession";
        final PreparedStatement ps = conn.prepareStatement(queryCheck);
        ResultSet results = ps.executeQuery();
        List<String> notes = new ArrayList<String>();
        while(results.next()){
            notes.add(results.getString(1));
        }
        return notes;
    }
//NOT FINISHED
    public List<Excercise> getExercises(String name) throws SQLException{
        final String queryCheck = "SELECT * from ExercisePerformed where ExercisePerformed.Exercise_Name=name";
        final PreparedStatement ps = conn.prepareStatement(queryCheck);
        ResultSet results = ps.executeQuery();
        List<Excercise> notes = new ArrayList<Excercise>();
        Excercise e ;
        while(results.next()){
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

    public void addGoal(Date date, double speed, int weightLifted, String exerciseName) throws SQLException{
        String sql = "INSERT INTO Goal (Date, Speed, TotalWeightLifted, Achieved, Exercise_Name) VALUES (?, ?, ?, ?, ?)";

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


    public void createGroup(String name) throws SQLException{
        String sql = "INSERT INTO Group (Name) VALUES(?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, name);
        ps.executeUpdate();
    }

    public void addGroupToExercise(String exercise, String group) throws SQLException{
        String sql = "INSERT INTO Exercise_has_Group (Group_Name, Exercise_Name) VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, group);
        ps.setString(2, exercise);
        ps.executeUpdate();
    }

    public static void main(String[] args){
        DBController c = new DBController();
        try{
            c.addExercise("Deadlift", "Supercool");
        }catch(SQLException e){
            System.out.print("SQL error");
        }
    }
}
