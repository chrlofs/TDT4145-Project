import java.sql.*;

/**
 * Created by Vegard on 07/03/16.
 */
public class DBController {
    Connection conn ;
    public DBController(){
        String dbURL = "FillIn";
        String username = "root";
        String password = "secret";

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
        String sql = "INSERT INTO Excercise (name, description) VALUES (?, ?)";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name);
        statement.setString(2, description);


        int rowsInserted = statement.executeUpdate();
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
            System.out.println("A new excercise was inserted successfully!");
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
            System.out.println("A new excercise was inserted successfully!");
        }
    }
    public void addExcercise(int sets, int reps, int load, double distance, int duration, String name, Date time) throws SQLException{
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

    public void createGroup(String name) throws SQLException{
        String sql = "INSERT INTO Group (Name) VALUES(?)";
        PreparedStatement ps = conn.prepareStateMent(sql);
        ps.setString(1, name);
        ps.executeUpdate();
    }

    public static void main(String[] args){
        DBController c = new DBController();
    }
}
